package network;

import java.util.ArrayList;

import envoirement.Grid;
import envoirement.Tile;
import network.Protocol;
import objects.Enemy;
import objects.EnemyTypes;
import objects.Player;
import objects.Tower;
import objects.TowerTypes;
import utility.Lobby;

///Server
public class Server_TD extends Server {

	public static final String globalPassword = "p";
	public static final String globalUsername = "u";

	private ArrayList<Player> playerList;
	private ArrayList<Lobby> lobbyList;

	public Server_TD(int pPort) {
		super(pPort);
		playerList = new ArrayList<Player>();
		lobbyList = new ArrayList<Lobby>();
		lobbyList.add(new Lobby(this));
		System.out.println("Server auf Port: " + pPort + " geöffnet!");
		showLobbys();
		showPlayer();
	}

	@Override
	public void processNewConnection(String pClientIP, int pClientPort) {
		Player newPlayer = new Player(pClientIP, pClientPort);
		newPlayer.setConnected(true);
		newPlayer.setBuyDone(false);
		playerList.add(newPlayer);
		System.out.println("Connected new Player! \n IP: " + pClientIP + " Port: " + pClientPort + " !");
	}

	@Override
	public void processMessage(String pClientIP, int pClientPort, String pMessage) {

		Player player = getPlayer(pClientIP, pClientPort);

		System.out.println("Server: Empfangen <" + pMessage + "> von " + pClientIP + "/" + pClientPort);
		String[] token = pMessage.split(Protocol.SEPARATOR);
		String prefix = token[0];

		String backMessage = "";

		switch (prefix) {
		/* CS_HELLO_WORLD */
		case Protocol.CS_HELLO_WORLD:
			backMessage = Protocol.SC_HELLO_WORLD + Protocol.SEPARATOR + "Hello!";
			break;
		/* CS_LOGIN_USERNAME:<Username> */
		case Protocol.CS_LOGIN_USERNAME:
			if (token[1].equals(globalUsername)) {
				player.setUsername(token[1]);
				backMessage = Protocol.SC_LOGIN_USERNAME_CONFIRMED + Protocol.SEPARATOR + token[1];
			} else {
				backMessage = Protocol.SC_LOGIN_USERNAME_DENIED + Protocol.SEPARATOR + token[1];
			}
			this.send(pClientIP, pClientPort, backMessage);
			break;
		/* CS_LOGIN_PASSWORD:<Password> */
		case Protocol.CS_LOGIN_PASSWORD:
			if (token[1].equals(globalPassword)) {
				backMessage = Protocol.SC_LOGIN_PASSWORD_CONFIRMED + Protocol.SEPARATOR + player.getUsername();
			} else {
				backMessage = Protocol.SC_LOGIN_PASSWORD_DENIED;
			}
			this.send(pClientIP, pClientPort, backMessage);
			break;
		/* CS_SET_NAME:<Username> */
		case Protocol.CS_PLAYER_SET_NAME:
			player.setUsername(token[1]);
			backMessage = Protocol.SC_LOBBY_USERS + Protocol.SEPARATOR + player.getPositionInLobby()
					+ Protocol.SEPARATOR + player.getUsername();
			this.sendToLobby(player.getLobbyIndex(), backMessage);
			break;
		/* CS_SEARCH_LOBBY */
		case Protocol.CS_SEARCH_LOBBY:
			sortInLobby(player);
			String mapName = lobbyList.get(player.getLobbyIndex()).getMapName();
			backMessage = Protocol.SC_LOBBY_FOUND + Protocol.SEPARATOR + player.getPositionInLobby()
					+ Protocol.SEPARATOR + mapName;
			this.send(pClientIP, pClientPort, backMessage);
			backMessage = Protocol.SC_LOBBY_USERS + Protocol.SEPARATOR + createLobbyUsersResponse(player);
			this.sendToLobby(player.getLobbyIndex(), backMessage);
			break;
		/* CS_READY_LOBBY */
		case Protocol.CS_READY_LOBBY:
			if (lobbyReady(player)) {
				backMessage = Protocol.SC_ALL_PLAYER_READY;
				this.sendToLobby(player.getLobbyIndex(), backMessage);
			} else {
				player.setReady(!player.isReady());
				backMessage = Protocol.SC_PLAYER_READY + Protocol.SEPARATOR + player.getPositionInLobby()
						+ Protocol.SEPARATOR + player.isReady();
				this.sendToLobby(player.getLobbyIndex(), backMessage);
			}
			showLobbys();
			showPlayer();
			break;
		/* CS_GO */
		case Protocol.CS_GO:
			lobbyList.get(player.getLobbyIndex()).setInGame(true);
			lobbyList.get(player.getLobbyIndex()).initializeGame();
			backMessage = Protocol.SC_GAME_STARTING;
			this.sendToLobby(player.getLobbyIndex(), backMessage);
			updateMoney(player);
			updateHealth(player);
			break;
		/* CS_PURCHASE_TOWER:<TowerPosX>:<TowerPosY>:<TowerType> */
		case Protocol.CS_PURCHASE_TOWER:
			TowerTypes tT = new TowerTypes();
			int tPosX = Integer.parseInt(token[1]) - 1;
			int tPosY = Integer.parseInt(token[2]) - 1;
			int tType = Integer.parseInt(token[3]);
			if (checkTowerAffordable(player, tType)) {
				if (lobbyList.get(player.getLobbyIndex()).getGameFrameWork()
						.getGameController(player.getPositionInLobby()).getGlobalGrid().getGridLayer()[tPosY][tPosX]
								.getType() == 0) {
					Tower t = new Tower(tPosX, tPosY, tType);
					lobbyList.get(player.getLobbyIndex()).getGameFrameWork()
							.pushToBoughtTowers(player.getPositionInLobby(), t);
					player.setPlayerMoney(player.getPlayerMoney() - tT.calcCost(tType));
					backMessage = Protocol.SC_UPDATE_POSITION_TOWER + Protocol.SEPARATOR + tPosX + Protocol.SEPARATOR
							+ tPosY + Protocol.SEPARATOR + tType;
					this.send(pClientIP, pClientPort, backMessage);
					backMessage = Protocol.SC_UPDATE_PLAYER_MONEY + Protocol.SEPARATOR + player.getPlayerMoney();
					this.send(pClientIP, pClientPort, backMessage);
					// TODO: editing so you can undo the buy
				} else {
					int tileType = lobbyList.get(player.getLobbyIndex()).getGameFrameWork()
							.getGameController(player.getPositionInLobby()).getGlobalGrid().getGridLayer()[tPosY][tPosX]
									.getType();
					backMessage = Protocol.SC_TOWER_NOT_PLACEABLE + Protocol.SEPARATOR + tPosX + Protocol.SEPARATOR
							+ tPosY + Protocol.SEPARATOR + tileType;
					this.send(pClientIP, pClientPort, backMessage);
				}
			} else {
				backMessage = Protocol.SC_TOWER_NOT_AFFORDABLE + Protocol.SEPARATOR + tT.calcCost(tType)
						+ Protocol.SEPARATOR + player.getPlayerMoney();
				this.send(pClientIP, pClientPort, backMessage);
			}
			break;
		/* CS_READY_TOWERPLACING */
		case Protocol.CS_READY_TOWERPLACING:
			lobbyList.get(player.getLobbyIndex()).getGameFrameWork().placeNewTowers(player.getPositionInLobby());
			backMessage = Protocol.SC_ENEMY_BUY_SWAP;
			this.send(pClientIP, pClientPort, backMessage);
			break;
		/* CS_PURCHASE_ENEMY:<EnemyPosX>:<EnemyPosY>:<EnemyType> */
		case Protocol.CS_PURCHASE_ENEMY:
			EnemyTypes eT = new EnemyTypes();
			int eType = Integer.parseInt(token[1]);
			if (checkEnemyAffordable(player, eType)) {
				for (int i = 0; i < 3; i++) {
					Enemy e = new Enemy(null, null, eType);
					lobbyList.get(player.getLobbyIndex()).getGameFrameWork()
							.pushToBoughtEnemies(player.getPositionInLobby(), e);
				}
				player.setPlayerMoney(player.getPlayerMoney() - eT.calcCost(eType));
				backMessage = Protocol.SC_ENEMY_BUY_ADD + Protocol.SEPARATOR + eType;
				this.send(pClientIP, pClientPort, backMessage);
				backMessage = Protocol.SC_UPDATE_PLAYER_MONEY + Protocol.SEPARATOR + player.getPlayerMoney();
				this.send(pClientIP, pClientPort, backMessage);
				// TODO: editing so you can undo the buy
			} else {
				backMessage = Protocol.SC_ENEMY_NOT_AFFORDABLE + Protocol.SEPARATOR + eT.calcCost(eType)
						+ Protocol.SEPARATOR + player.getPlayerMoney();
				this.send(pClientIP, pClientPort, backMessage);
			}
			break;
		/* CS_READY_ENEMIESPURCHASED */
		case Protocol.CS_READY_ENEMIESPURCHASED:
			lobbyList.get(player.getLobbyIndex()).getGameFrameWork().assambleWaves(player.getPositionInLobby());
			player.setBuyDone(true);
			generateBuyPhaseDoneResponse(player);
			break;
		/* SC_ARE_ALL_ROUND_OVER */
		case Protocol.CS_ARE_ALL_ROUND_OVER:
			generateRoundOverResponse(player);
			break;
		/* CS_EXIT_ENDSCREEN */
		case Protocol.CS_EXIT_ENDSCREEN:
			this.send(pClientIP, pClientPort, Protocol.SC_EXIT_ENDSCREEN + Protocol.SEPARATOR + player.getUsername());
			break;
		/* CS_LOGOUT:<Username> */
		case Protocol.CS_LOGOUT:
			// TODO:
			backMessage = Protocol.SC_LOGOUT_CONFIRMED + Protocol.SEPARATOR + "Logout successful!";
			break;
		default:
			backMessage = Protocol.SC_SENDERRORMESSAGE + Protocol.SEPARATOR + "Error!";
			break;
		}
		// this.send(pClientIP, pClientPort, backMessage);
		// if (prefix.equals(Protocol.CS_LOGOUT)) {
		// this.closeConnection(pClientIP, pClientPort);
		// }

	}

	private boolean checkTowerAffordable(Player player, int type) {
		TowerTypes tt = new TowerTypes();
		if (player.getPlayerMoney() >= tt.calcCost(type)) {
			return true;
		}
		return false;
	}

	private boolean checkEnemyAffordable(Player player, int type) {
		EnemyTypes et = new EnemyTypes();
		if (player.getPlayerMoney() >= et.calcCost(type)) {
			return true;
		}
		return false;
	}

	@Override
	public void processClosingConnection(String pClientIP, int pClientPort) {
		System.out.println("Der Client mit der IP: " + pClientIP + " wird abgemeldet.");
		Player removePlayer = getPlayer(pClientIP, pClientPort);
		removePlayer.setUsername("EMPTY");
		removePlayer.setReady(false);
		lobbyList.get(removePlayer.getLobbyIndex()).getGameFrameWork().setLoopStopped(true);
		String removeMessage = Protocol.SC_LOBBY_USERS + Protocol.SEPARATOR + createLobbyUsersResponse(removePlayer);
		this.sendToLobby(removePlayer.getLobbyIndex(), removeMessage);
		this.sendToLobby(removePlayer.getLobbyIndex(),
				Protocol.SC_LOBBY_USERS + Protocol.SEPARATOR + createLobbyUsersResponse(removePlayer));
		this.sendToLobby(removePlayer.getLobbyIndex(),
				Protocol.SC_LOBBY_DISCONNECT + Protocol.SEPARATOR + removePlayer.getPositionInLobby());
		removeFromLobby(removePlayer);
		removeFromPlayers(removePlayer);
		removePlayer.setConnected(false);
		lobbyList.get(removePlayer.getLobbyIndex()).getGameFrameWork().clear();
		this.closeConnection(pClientIP, pClientPort);

	}

	public void exitGame(String pClientIP, int pClientPort) {
		System.out.println("Der Client mit der IP: " + pClientIP + " beendet das Spiel.");
		Player removePlayer = getPlayer(pClientIP, pClientPort);
		removePlayer.setReady(false);
		removePlayer.setBuyDone(false);
		removePlayer.setInGame(false);
		removePlayer.setInLobby(false);
		removeFromLobby(removePlayer);
		lobbyList.get(removePlayer.getLobbyIndex()).getGameFrameWork().clear();
	}

	public void sendToLobby(int lobbyIndex, String pMessage) {
		if ((lobbyList.get(lobbyIndex).getPlayer_1() != null) && (lobbyList.get(lobbyIndex).getPlayer_2() == null)) {
			this.send(lobbyList.get(lobbyIndex).getPlayer_1().getPlayerIP(),
					lobbyList.get(lobbyIndex).getPlayer_1().getPlayerPort(), pMessage);
		} else if ((lobbyList.get(lobbyIndex).getPlayer_1() == null)
				&& (lobbyList.get(lobbyIndex).getPlayer_2() != null)) {
			this.send(lobbyList.get(lobbyIndex).getPlayer_2().getPlayerIP(),
					lobbyList.get(lobbyIndex).getPlayer_2().getPlayerPort(), pMessage);
		} else if ((lobbyList.get(lobbyIndex).getPlayer_1() != null)
				&& (lobbyList.get(lobbyIndex).getPlayer_2() != null)) {
			this.send(lobbyList.get(lobbyIndex).getPlayer_1().getPlayerIP(),
					lobbyList.get(lobbyIndex).getPlayer_1().getPlayerPort(), pMessage);
			this.send(lobbyList.get(lobbyIndex).getPlayer_2().getPlayerIP(),
					lobbyList.get(lobbyIndex).getPlayer_2().getPlayerPort(), pMessage);
		}

	}

	private Player getPlayer(String pClientIP, int pClientPort) {
		Player testPlayer = new Player(pClientIP, pClientPort);
		Player player = null;
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).haveSameStats(testPlayer, playerList.get(i))) {
				player = playerList.get(i);
				return player;
			}
		}
		return null;
	}

	private String createLobbyUsersResponse(Player player) {
		String pMessage = "";
		Lobby lobby = lobbyList.get(player.getLobbyIndex());
		if ((lobby.getPlayer_1() != null) && (lobby.getPlayer_2() != null)) {
			pMessage = lobby.getPlayer_1().getUsername() + Protocol.SEPARATOR + lobby.getPlayer_1().isReady()
					+ Protocol.SEPARATOR + lobby.getPlayer_2().getUsername() + Protocol.SEPARATOR
					+ lobby.getPlayer_2().isReady();
		} else if ((lobby.getPlayer_1() != null) && (lobby.getPlayer_2() == null)) {
			pMessage = lobby.getPlayer_1().getUsername() + Protocol.SEPARATOR + lobby.getPlayer_1().isReady()
					+ Protocol.SEPARATOR + "null" + Protocol.SEPARATOR + "null";
		} else if ((lobby.getPlayer_1() == null) && (lobby.getPlayer_2() != null)) {
			pMessage = "null" + Protocol.SEPARATOR + "null" + Protocol.SEPARATOR + lobby.getPlayer_2().getUsername()
					+ Protocol.SEPARATOR + lobby.getPlayer_2().isReady();
		}
		return pMessage;
	}

	private void generateBuyPhaseDoneResponse(Player player) {
		Lobby lobby = lobbyList.get(player.getLobbyIndex());
		String backMessage = "";
		if (lobby.allBuyDone()) {
			loadMapClient(player);
			loadMapClient(lobby.getOtherPlayer(player));
			this.send(player.getPlayerIP(), player.getPlayerPort(),
					Protocol.SC_UPDATE_PLAYER_HEALTH + Protocol.SEPARATOR + player.getHealth() + Protocol.SEPARATOR
							+ player.getOtherplayer().getHealth());
			this.send(player.getOtherplayer().getPlayerIP(), player.getOtherplayer().getPlayerPort(),
					Protocol.SC_UPDATE_PLAYER_HEALTH + Protocol.SEPARATOR + player.getOtherplayer().getHealth()
							+ Protocol.SEPARATOR + player.getHealth());
			backMessage = Protocol.SC_BUY_ALL_READY;
			this.sendToLobby(player.getLobbyIndex(), backMessage);
			lobby.getPlayer_1().setBuyDone(false);
			lobby.getPlayer_2().setBuyDone(false);
			// TODO: now the wave has to be played.
			lobby.getGameFrameWork().startWave();
		} else {
			backMessage = Protocol.SC_BUY_DONE;
			this.send(player.getPlayerIP(), player.getPlayerPort(), backMessage);
		}
	}

	private void generateRoundOverResponse(Player player) {
		Lobby lobby = lobbyList.get(player.getLobbyIndex());
		String backMessage = "";
		if (lobby.allRoundOver()) {
			backMessage = Protocol.SC_ALL_ROUND_OVER_TRUE;
			this.sendToLobby(player.getLobbyIndex(), backMessage);
		} else {
			backMessage = Protocol.SC_ALL_ROUND_OVER_FALSE;
			this.send(player.getPlayerIP(), player.getPlayerPort(), backMessage);
		}
	}

	private void updateMoney(Player player) {
		Lobby lobby = lobbyList.get(player.getLobbyIndex());
		this.send(player.getPlayerIP(), player.getPlayerPort(),
				Protocol.SC_UPDATE_PLAYER_MONEY + Protocol.SEPARATOR + player.getPlayerMoney());
		this.send(lobby.getOtherPlayer(player).getPlayerIP(), lobby.getOtherPlayer(player).getPlayerPort(),
				Protocol.SC_UPDATE_PLAYER_MONEY + Protocol.SEPARATOR + lobby.getOtherPlayer(player).getPlayerMoney());
	}
	
	private void updateHealth(Player player) {
		Lobby lobby = lobbyList.get(player.getLobbyIndex());
		this.send(player.getPlayerIP(), player.getPlayerPort(),
				Protocol.SC_UPDATE_PLAYER_HEALTH + Protocol.SEPARATOR + player.getHealth() + Protocol.SEPARATOR + player.getOtherplayer().getHealth());
		this.send(lobby.getOtherPlayer(player).getPlayerIP(), lobby.getOtherPlayer(player).getPlayerPort(),
				Protocol.SC_UPDATE_PLAYER_HEALTH + Protocol.SEPARATOR + lobby.getOtherPlayer(player).getHealth() + Protocol.SEPARATOR + player.getHealth());
	}

	public void loadMapClient(Player player) {
		Grid grid = lobbyList.get(player.getLobbyIndex()).getGameFrameWork()
				.getGameController(player.getPositionInLobby()).getGlobalGrid();
		Tile[][] layer = grid.getGridLayer();
		this.send(player.getPlayerIP(), player.getPlayerPort(), Protocol.SC_LOAD_MAP_DIMENSIONS + Protocol.SEPARATOR
				+ grid.getHeight() + Protocol.SEPARATOR + grid.getLength());
		for (int y = 0; y < grid.getLength(); y++) {
			for (int x = 0; x < grid.getHeight(); x++) {
				this.send(player.getPlayerIP(), player.getPlayerPort(), Protocol.SC_LOAD_MAP_TYPE + Protocol.SEPARATOR
						+ y + Protocol.SEPARATOR + x + Protocol.SEPARATOR + layer[y][x].getType());
			}
		}
	}

	private boolean lobbyReady(Player player) {
		Lobby lobby = lobbyList.get(player.getLobbyIndex());
		if ((lobby.getPlayer_1() != null) && lobby.getPlayer_1().isReady()) {
			if ((lobby.getPlayer_2() != null) && lobby.getPlayer_2().isReady()) {
				return true;
			}
		}
		return false;
	}

	public void sortInLobby(Player player) {
		for (int i = 0; i < lobbyList.size(); i++) {
			if (!lobbyList.get(i).getIsFull()) {
				if ((lobbyList.get(i).getPlayer_1() != null) && (lobbyList.get(i).getPlayer_2() == null)) {
					lobbyList.get(i).setPlayer_2(player, playerList.indexOf(player));
					player.setLobbyIndex(i);
					player.setPositionInLobby(2);
					lobbyList.get(i).setIsFull(true);
					break;
				} else if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() != null)) {
					lobbyList.get(i).setPlayer_1(player, playerList.indexOf(player));
					player.setLobbyIndex(i);
					player.setPositionInLobby(1);
					lobbyList.get(i).setIsFull(true);
					break;
				} else if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() == null)) {
					lobbyList.get(i).setPlayer_1(player, playerList.indexOf(player));
					player.setLobbyIndex(i);
					player.setPositionInLobby(1);
					lobbyList.get(i).setIsFull(false);
					break;
				}

			} else {
				if (i == (lobbyList.size() - 1)) {
					Lobby newLobby = new Lobby(this);
					newLobby.setPlayer_1(player, playerList.indexOf(player));
					player.setLobbyIndex(i);
					player.setPositionInLobby(1);
					lobbyList.add(newLobby);
					break;
				}
			}
		}
		player.setInLobby(true);
		showLobbys();
		showPlayer();
	}

	public void removeFromLobby(Player mPlayer) {
		for (int i = 0; i < lobbyList.size(); i++) {
			if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() == null)) {
			} else if ((lobbyList.get(i).getPlayer_1() != null) && (lobbyList.get(i).getPlayer_2() == null)) {
				if (lobbyList.get(i).haveSamePortIP(mPlayer, lobbyList.get(i).getPlayer_1())) {
					lobbyList.get(i).resetPlayer_1();
					lobbyList.get(i).setIsFull(false);
					lobbyList.get(i).chooseRandomMap();
				}
			} else if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() != null)) {
				if (lobbyList.get(i).haveSamePortIP(mPlayer, lobbyList.get(i).getPlayer_2())) {
					lobbyList.get(i).resetPlayer_2();
					lobbyList.get(i).setIsFull(false);
					lobbyList.get(i).chooseRandomMap();
				}
			} else if ((lobbyList.get(i).getPlayer_1() != null) && (lobbyList.get(i).getPlayer_2() != null)) {
				if (lobbyList.get(i).haveSamePortIP(mPlayer, lobbyList.get(i).getPlayer_1())) {
					lobbyList.get(i).resetPlayer_1();
					lobbyList.get(i).setIsFull(false);
				} else if (lobbyList.get(i).haveSamePortIP(mPlayer, lobbyList.get(i).getPlayer_2())) {
					lobbyList.get(i).resetPlayer_2();
					lobbyList.get(i).setIsFull(false);
				}
			}
		}
		mPlayer.setInLobby(false);
		showLobbys();
	}

	public void removeFromPlayers(Player mPlayer) {
		for (int i = 0; i < playerList.size(); i++) {
			if (mPlayer != null) {
				if (playerList.get(i).haveSameStats(mPlayer, playerList.get(i))) {
					playerList.remove(playerList.get(i));
				}
			}
		}
		mPlayer = null;
		showPlayer();
	}

	public void startGame(int lobbyIndex) {
		if (lobbyList.get(lobbyIndex).getGameFrameWork().playerReadyCheck()) {
			lobbyList.get(lobbyIndex).setInGame(true);
		}
	}

	public void showLobbys() {
		for (int i = 0; i < lobbyList.size(); i++) {
			if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() == null)) {
				System.out.println("Lobby " + (i + 1) + " is Empty!");
			} else if ((lobbyList.get(i).getPlayer_1() != null) && (lobbyList.get(i).getPlayer_2() == null)) {
				System.out.println(
						"In Lobby " + (i + 1) + " are Player_1: " + lobbyList.get(i).getPlayer_1().getPlayerIP()
								+ " ISREADY: " + lobbyList.get(i).getPlayer_1().isReady() + " and Player_2: EMPTY");
				System.out.println();
			} else if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() != null)) {
				System.out.println("In Lobby " + (i + 1) + " are Player_1: EMPTY " + " and Player_2: "
						+ lobbyList.get(i).getPlayer_2().getPlayerIP() + " ISREADY: "
						+ lobbyList.get(i).getPlayer_2().isReady());
				System.out.println();
			} else if ((lobbyList.get(i).getPlayer_1() != null) && (lobbyList.get(i).getPlayer_2() != null)) {
				System.out.println(
						"In Lobby " + (i + 1) + " are Player_1: " + lobbyList.get(i).getPlayer_1().getPlayerIP()
								+ " ISREADY: " + lobbyList.get(i).getPlayer_1().isReady() + " and Player_2: "
								+ lobbyList.get(i).getPlayer_2().getPlayerIP() + " ISREADY: "
								+ lobbyList.get(i).getPlayer_2().isReady());
				System.out.println();
			}
		}
	}

	public void showPlayer() {
		for (int i = 0; i < playerList.size(); i++) {
			System.out.println("List Place: " + (i + 1) + " is Player: " + playerList.get(i).getPlayerIP() + " + "
					+ playerList.get(i).getPlayerPort() + " !");
			System.out.println();
		}
	}

	public void testPrint(String nNachricht) {
		System.out.println(nNachricht);
	}

	public static void main(String[] args) {
		new Server_TD(51678);
	}

}
