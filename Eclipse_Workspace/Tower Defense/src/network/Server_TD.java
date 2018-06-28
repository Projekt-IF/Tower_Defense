package network;

import java.util.ArrayList;

import envoirement.Grid;
import envoirement.Tile;
import network.Protocol;
import objects.Enemy;
import objects.EnemyTypes;
import objects.Lobby;
import objects.Player;
import objects.Tower;
import objects.TowerTypes;

/**
 * The Server_TD class extends the Server class and manages the messages send by
 * the client. It has a List of Lobbies and Players. The Players are sorted into
 * the Lobbies of the LobbyList.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class Server_TD extends Server {

	// hard coded password and username
	public static final String globalPassword = "p";
	public static final String globalUsername = "u";

	private ArrayList<Player> playerList;
	private ArrayList<Lobby> lobbyList;

	/**
	 * Constructs a Server_TD on the given port and creates the first Lobby.
	 * 
	 * @param pPort
	 *            The Port the Server runs on the machine.
	 */
	public Server_TD(int pPort) {
		super(pPort);
		playerList = new ArrayList<Player>();
		lobbyList = new ArrayList<Lobby>();
		lobbyList.add(new Lobby(this));
		System.out.println("Server auf Port: " + pPort + " geöffnet!");
		showLobbys();
		showPlayerList();
	}

	/**
	 * Processes a new incoming connection from a client creating a new Player and
	 * sorting it into the playerList.
	 */
	@Override
	public void processNewConnection(String pClientIP, int pClientPort) {
		Player newPlayer = new Player(pClientIP, pClientPort);
		newPlayer.setConnected(true);
		newPlayer.setBuyDone(false);
		playerList.add(newPlayer);
		System.out.println("Connected new Player! \n IP: " + pClientIP + " Port: " + pClientPort + " !");
	}

	/**
	 * Processes the message send by the Client.
	 */
	@Override
	public void processMessage(String pClientIP, int pClientPort, String pMessage) {

		// Gets the Player from the playerList by its IP and Port.
		Player player = getPlayer(pClientIP, pClientPort);

		System.out.println("Server: Empfangen <" + pMessage + "> von " + pClientIP + "/" + pClientPort);
		// Splits the message into different parts where the first indicates what kind
		// of message it is. That is the prefix.
		String[] token = pMessage.split(Protocol.SEPARATOR);
		String prefix = token[0];

		String backMessage = "";

		switch (prefix) {

		// Login

		/* CS_LOGIN_USERNAME:<Username> */
		case Protocol.CS_LOGIN_USERNAME:
			// checking if entered username equals the coded username.
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
			// checking if entered password equals the coded password.
			if (token[1].equals(globalPassword)) {
				backMessage = Protocol.SC_LOGIN_PASSWORD_CONFIRMED + Protocol.SEPARATOR + player.getUsername();
			} else {
				backMessage = Protocol.SC_LOGIN_PASSWORD_DENIED;
			}
			this.send(pClientIP, pClientPort, backMessage);
			break;

		// Lobby

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
			showPlayerList();
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

		// In-Game

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

		// End of Game

		/* CS_EXIT_ENDSCREEN */
		case Protocol.CS_EXIT_ENDSCREEN:
			this.send(pClientIP, pClientPort, Protocol.SC_EXIT_ENDSCREEN + Protocol.SEPARATOR + player.getUsername());
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

	/**
	 * Returns weather the Player has enough money to pay for the tower.
	 * 
	 * @param player
	 *            The player requesting to place a tower.
	 * @param type
	 *            The type of the tower to be placed.
	 * @return True: Player has money >= cost. False: Player has money < cost.
	 */
	private boolean checkTowerAffordable(Player player, int type) {
		TowerTypes tt = new TowerTypes();
		if (player.getPlayerMoney() >= tt.calcCost(type)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns weather the Player has enough money to pay for the enemy.
	 * 
	 * @param player
	 *            The player requesting to buy an enemy.
	 * @param type
	 *            The type of the enemy to be bought.
	 * @return True: Player has money >= cost. False: Player has money < cost.
	 */
	private boolean checkEnemyAffordable(Player player, int type) {
		EnemyTypes et = new EnemyTypes();
		if (player.getPlayerMoney() >= et.calcCost(type)) {
			return true;
		}
		return false;
	}

	/**
	 * Processes closing connections to clients.
	 */
	@Override
	public void processClosingConnection(String pClientIP, int pClientPort) {
		System.out.println("Der Client mit der IP: " + pClientIP + " wird abgemeldet.");
		Player removePlayer = getPlayer(pClientIP, pClientPort);
		removePlayer.setUsername("EMPTY");
		removePlayer.setReady(false);
		// when in game stopping the game-loop
		lobbyList.get(removePlayer.getLobbyIndex()).getGameFrameWork().setLoopStopped(true);
		// sending the clients in the lobby the information to reset to lobby view
		String removeMessage = Protocol.SC_LOBBY_USERS + Protocol.SEPARATOR + createLobbyUsersResponse(removePlayer);
		this.sendToLobby(removePlayer.getLobbyIndex(), removeMessage);
		this.sendToLobby(removePlayer.getLobbyIndex(),
				Protocol.SC_LOBBY_USERS + Protocol.SEPARATOR + createLobbyUsersResponse(removePlayer));
		this.sendToLobby(removePlayer.getLobbyIndex(),
				Protocol.SC_LOBBY_DISCONNECT + Protocol.SEPARATOR + removePlayer.getPositionInLobby());
		// removing the Player from the playerList and lobbyList
		removeFromLobby(removePlayer);
		removeFromPlayers(removePlayer);
		removePlayer.setConnected(false);
		lobbyList.get(removePlayer.getLobbyIndex()).getGameFrameWork().clear();
		// closing the connection to the client
		this.closeConnection(pClientIP, pClientPort);

	}

	/**
	 * Resets the Player's game and lobby important variables. The Player gets
	 * removed from the Lobby but not disconnected from the server.
	 * 
	 * @param pClientIP
	 *            The Clients(Players) IP.
	 * @param pClientPort
	 *            The Clients(Players) Port.
	 */
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

	/**
	 * Sends a message to all the players in the given lobby.
	 * 
	 * @param lobbyIndex
	 *            The position of the lobby in the lobbyList.
	 * @param pMessage
	 *            The message to be send to the players in the lobby.
	 */
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

	/**
	 * Returns the Player from the playerList the IP and Port fit to.
	 * 
	 * @param pClientIP
	 *            The Player's IP.
	 * @param pClientPort
	 *            The Player's Port.
	 * @return (Player) The requested Player from the playerList.
	 */
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

	/**
	 * Returns the sequence of Protocol messages fitting to the Player's position in
	 * the lobby.
	 * 
	 * @param player
	 *            The Player to base the response on.
	 * @return (String) The sequence of Protocol messages fitting to the Player's
	 *         position in the lobby.
	 */
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

	/**
	 * Sends messages to start the game play mode to the clients according to their
	 * state of buying.
	 * 
	 * @param player
	 *            The Player to base the response on.
	 */
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
			lobby.getGameFrameWork().startWave();
		} else {
			backMessage = Protocol.SC_BUY_DONE;
			this.send(player.getPlayerIP(), player.getPlayerPort(), backMessage);
		}
	}

	/**
	 * Sends messages to start the game buy mode to the clients according to their
	 * state of playing.
	 * 
	 * @param player
	 *            The Player to base the response on.
	 */
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

	/**
	 * Sends messages to the clients to update the money of the players based on the
	 * given players position.
	 * 
	 * @param player
	 *            The player to base the response on.
	 */
	private void updateMoney(Player player) {
		Lobby lobby = lobbyList.get(player.getLobbyIndex());
		this.send(player.getPlayerIP(), player.getPlayerPort(),
				Protocol.SC_UPDATE_PLAYER_MONEY + Protocol.SEPARATOR + player.getPlayerMoney());
		this.send(lobby.getOtherPlayer(player).getPlayerIP(), lobby.getOtherPlayer(player).getPlayerPort(),
				Protocol.SC_UPDATE_PLAYER_MONEY + Protocol.SEPARATOR + lobby.getOtherPlayer(player).getPlayerMoney());
	}

	/**
	 * Sends messages to the clients to update the health of the players based on
	 * the given players position.
	 * 
	 * @param player
	 *            The player to base the response on.
	 */
	private void updateHealth(Player player) {
		Lobby lobby = lobbyList.get(player.getLobbyIndex());
		this.send(player.getPlayerIP(), player.getPlayerPort(), Protocol.SC_UPDATE_PLAYER_HEALTH + Protocol.SEPARATOR
				+ player.getHealth() + Protocol.SEPARATOR + player.getOtherplayer().getHealth());
		this.send(lobby.getOtherPlayer(player).getPlayerIP(), lobby.getOtherPlayer(player).getPlayerPort(),
				Protocol.SC_UPDATE_PLAYER_HEALTH + Protocol.SEPARATOR + lobby.getOtherPlayer(player).getHealth()
						+ Protocol.SEPARATOR + player.getHealth());
	}

	/**
	 * Sends messages the client uses to create the map that shows the enemy's
	 * movement.
	 * 
	 * @param player
	 *            The player to base the response on.
	 */
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

	/**
	 * Returns weather all players in the lobby of the given player are ready to
	 * play.
	 * 
	 * @param player
	 *            The player to base the response on.
	 * @return (boolean) True: All players ready. False: Not all players ready.
	 */
	private boolean lobbyReady(Player player) {
		Lobby lobby = lobbyList.get(player.getLobbyIndex());
		if ((lobby.getPlayer_1() != null) && lobby.getPlayer_1().isReady()) {
			if ((lobby.getPlayer_2() != null) && lobby.getPlayer_2().isReady()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sorts the given player into a lobby with a free spot. If no place is free a
	 * new lobby is created and the player is placed in it.
	 * 
	 * @param player
	 *            The player to be sorted into a lobby.
	 */
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
		showPlayerList();
	}

	/**
	 * Removes the given player from its lobby. If the lobby is empty after removing
	 * a new map is chosen for the lobby.
	 * 
	 * @param mPlayer
	 *            The player to be removed from its lobby.
	 */
	public void removeFromLobby(Player mPlayer) {
		for (int i = 0; i < lobbyList.size(); i++) {
			if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() == null)) {
			} else if ((lobbyList.get(i).getPlayer_1() != null) && (lobbyList.get(i).getPlayer_2() == null)) {
				if (mPlayer.haveSameStats(mPlayer, lobbyList.get(i).getPlayer_1())) {
					lobbyList.get(i).resetPlayer_1();
					lobbyList.get(i).setIsFull(false);
					lobbyList.get(i).chooseRandomMap();
				}
			} else if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() != null)) {
				if (mPlayer.haveSameStats(mPlayer, lobbyList.get(i).getPlayer_2())) {
					lobbyList.get(i).resetPlayer_2();
					lobbyList.get(i).setIsFull(false);
					lobbyList.get(i).chooseRandomMap();
				}
			} else if ((lobbyList.get(i).getPlayer_1() != null) && (lobbyList.get(i).getPlayer_2() != null)) {
				if (mPlayer.haveSameStats(mPlayer, lobbyList.get(i).getPlayer_1())) {
					lobbyList.get(i).resetPlayer_1();
					lobbyList.get(i).setIsFull(false);
				} else if (mPlayer.haveSameStats(mPlayer, lobbyList.get(i).getPlayer_2())) {
					lobbyList.get(i).resetPlayer_2();
					lobbyList.get(i).setIsFull(false);
				}
			}
		}
		mPlayer.setInLobby(false);
		showLobbys();
	}

	/**
	 * Removes the given player from the playerList if there is a player with the
	 * same IP and Port of the given player.
	 * 
	 * @param mPlayer
	 *            The player to be removed from its lobby.
	 */
	public void removeFromPlayers(Player mPlayer) {
		for (int i = 0; i < playerList.size(); i++) {
			if (mPlayer != null) {
				if (playerList.get(i).haveSameStats(mPlayer, playerList.get(i))) {
					playerList.remove(playerList.get(i));
				}
			}
		}
		mPlayer = null;
		showPlayerList();
	}

	/**
	 * Starts the game for the given lobby if all players are ready.
	 * 
	 * @param lobbyIndex
	 *            The position of the lobby in the lobbyList.
	 */
	public void startGame(int lobbyIndex) {
		if (lobbyList.get(lobbyIndex).getGameFrameWork().playerReadyCheck()) {
			lobbyList.get(lobbyIndex).setInGame(true);
		}
	}

	/**
	 * Prints a list of the lobbies and the players in it with their status into the
	 * Command Prompt.
	 */
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

	/**
	 * Prints a list of the players with their IP and Port into the Command Prompt.
	 */
	public void showPlayerList() {
		for (int i = 0; i < playerList.size(); i++) {
			System.out.println("List Place: " + (i + 1) + " is Player: " + playerList.get(i).getPlayerIP() + " + "
					+ playerList.get(i).getPlayerPort() + " !");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		new Server_TD(51678);
	}

}
