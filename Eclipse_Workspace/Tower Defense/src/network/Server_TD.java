package network;

import java.util.ArrayList;

import network.Protocol;
import objects.EnemyTypes;
import objects.Player;
import objects.TowerTypes;
import utility.Lobby;

///Server
public class Server_TD extends Server {

	public static final String globalPassword = "password";
	public static final String globalUsername = "user";

	private ArrayList<Player> playerList;
	private ArrayList<Lobby> lobbyList;

	public Server_TD(int pPort) {
		super(pPort);
		playerList = new ArrayList<Player>();
		lobbyList = new ArrayList<Lobby>();
		lobbyList.add(new Lobby());
		System.out.println("Server auf Port: " + pPort + " ge�ffnet!");
		showLobbys();
		showPlayer();
	}

	@Override
	public void processNewConnection(String pClientIP, int pClientPort) {
		Player newPlayer = new Player(pClientIP, pClientPort);
		newPlayer.setConnected(true);
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
			backMessage = Protocol.SC_LOBBY_FOUND + Protocol.SEPARATOR + player.getPositionInLobby();
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
			String pLevel = token[1];
			lobbyList.get(player.getPositionInLobby()).setInGame(true);
			lobbyList.get(player.getPositionInLobby()).initializeGame(pLevel);
			backMessage = Protocol.SC_GAME_STARTING + Protocol.SEPARATOR + pLevel;
			this.sendToLobby(player.getLobbyIndex(), backMessage);
			break;
		/* CS_PURCHASE_TOWER:<TowerPosX>:<TowerPosY>:<TowerType> */
		case Protocol.CS_PURCHASE_TOWER:
			int tPosX = Integer.parseInt(token[1]);
			int tPosY = Integer.parseInt(token[2]);
			int tType = Integer.parseInt(token[3]);
			if (checkTowerAffordable(player, tType)) {
				if (lobbyList.get(player.getLobbyIndex()).getGameFrameWork()
						.getGameController(player.getPositionInLobby()).getGlobalGrid().getGridLayer()[tPosY][tPosX]
								.getType() == 0) {
					// TODO: complete the pushing to the towerlist
				}
			}
			break;
		/* CS_READY_TOWERPLACING */
		case Protocol.CS_READY_TOWERPLACING:
			lobbyList.get(player.getLobbyIndex()).getGameFrameWork().getGameController(player.getPositionInLobby())
					.addPurchasedTowers(lobbyList.get(player.getLobbyIndex()).getGameFrameWork()
							.getBoughtTowerList(player.getPositionInLobby()));
			;
			break;
		/* CS_PURCHASE_ENEMY:<EnemyPosX>:<EnemyPosY>:<EnemyType> */
		case Protocol.CS_PURCHASE_ENEMY:
			int ePosX = Integer.parseInt(token[1]);
			int ePosY = Integer.parseInt(token[2]);
			int eType = Integer.parseInt(token[3]);
			if (checkEnemyAffordable(player, eType)) {

				// TODO: complete the pushing to the EnemyList

			}
			break;
		/* CS_READY_ENEMIESPURCHASED */
		case Protocol.CS_READY_ENEMIESPURCHASED:
			lobbyList.get(player.getLobbyIndex()).getGameFrameWork().getGameController(player.getPositionInLobby())
					.addPurchasedEnemies(lobbyList.get(player.getLobbyIndex()).getGameFrameWork()
							.getBoughtEnemyList(player.getPositionInLobby()));
			;
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
		System.out.println("Server: Gesendet :<" + backMessage + ">");

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
		String removeMessage = Protocol.SC_LOBBY_USERS + Protocol.SEPARATOR + createLobbyUsersResponse(removePlayer);
		this.sendToLobby(removePlayer.getLobbyIndex(), removeMessage);
		removeFromLobby(removePlayer);
		removeFromPlayers(removePlayer);
		removePlayer.setConnected(false);
		this.closeConnection(pClientIP, pClientPort);

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
					Lobby newLobby = new Lobby();
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
				}
			} else if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() != null)) {
				if (lobbyList.get(i).haveSamePortIP(mPlayer, lobbyList.get(i).getPlayer_2())) {
					lobbyList.get(i).resetPlayer_2();
					lobbyList.get(i).setIsFull(false);
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
