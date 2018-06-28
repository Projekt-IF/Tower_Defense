package graphical_Interface;

import java.util.ArrayList;

import network.Protocol;
import objects.Enemy;
import objects.Tower;

/**
 * The TD_Client class extends the Client class and manages the incoming and
 * outgoing messages. It is the interface between the Server and the GUI.
 * 
 * @author Jonas Schröder, Simon Lukas
 * @version 1.0
 */
public class TD_Client extends Client {

	private Integer positionInLobby;

	private ArrayList<Tower> baughtTowers;
	private ArrayList<Enemy> baughtEnemies;

	private TextGui myGui;

	/**
	 * Constructs a TD_Client and connecting it to the server with the given IP on
	 * the given Port and initializing the TextGui.
	 * 
	 * @param pServerIP
	 *            The dedicated server's IP.
	 * @param pServerPort
	 *            The dedicated server's Port.
	 * @param tg
	 *            The textGUI.
	 */
	public TD_Client(String pServerIP, int pServerPort, TextGui tg) {
		super(pServerIP, pServerPort);
		this.myGui = tg;

	}

	/**
	 * Processes the message send by the server.
	 */
	public void processMessage(String pMessage) {
		// Splits the message into different parts where the first indicates what kind
		// of message it is. That is the prefix.
		String[] tags = pMessage.split(Protocol.SEPARATOR);
		String prefix = tags[0];

		switch (prefix) {

		// Login

		/* SC_LOGIN_USERNAME_CONFIRMED:<Username> */
		case Protocol.SC_LOGIN_USERNAME_CONFIRMED:
			this.myGui.switchPanelLoginPassword(tags[1]);
			break;

		/* SC_LOGIN_USERNAME_DENIED:<Username> */
		case Protocol.SC_LOGIN_USERNAME_DENIED:
			this.myGui.setUsernameResponseLabelText("The Username : " + tags[1] + " is not existent!");
			break;

		/* SC_LOGIN_PASSWORD_CONFIRMED:<Username> */
		case Protocol.SC_LOGIN_PASSWORD_CONFIRMED:
			this.myGui.switchPanelLoggedIn(tags[1]);
			break;

		/* SC_LOGIN_PASSWORD_DENIED */
		case Protocol.SC_LOGIN_PASSWORD_DENIED:
			this.myGui.setPasswordResponseLabelText("The Password is incorrect!");
			break;

		// Lobby

		/* SC_LOBBY_FOUND:<Position> */
		case Protocol.SC_LOBBY_FOUND:
			this.positionInLobby = setLobbyPosition(tags[1]);
			this.myGui.setMapName(tags[2]);
			this.myGui.setMapPicture(tags[2]);
			setSpinnerMaximum(tags[2]);
			this.myGui.switchPanelLobby();
			break;

		/* SC_LOBBY_USERS:<Username_1>:<Ready_1>:<Username_2>:<Ready_2> */
		case Protocol.SC_LOBBY_USERS:
			if (!tags[1].equals("null")) {
				this.setPlayerUsername("1", tags[1]);
				this.setPlayerReady("1", tags[2]);
			} else {
				this.setPlayerUsername("1", "EMPTY");
				this.setPlayerReady("1", "false");
			}
			if (!tags[3].equals("null")) {
				this.setPlayerUsername("2", tags[3]);
				this.setPlayerReady("2", tags[4]);
			} else {
				this.setPlayerUsername("2", "EMPTY");
				this.setPlayerReady("2", "false");
			}
			break;

		/* SC_PLAYER_READY:<Integer>:<boolean> */
		case Protocol.SC_PLAYER_READY:
			this.setPlayerReady(tags[1], tags[2]);
			break;

		/* SC_ALL_PLAYER_READY:<String> */
		case Protocol.SC_ALL_PLAYER_READY:
			this.myGui.switchPanelGame();
			this.myGui.switchPanelGameBuyTowers();
			this.send(Protocol.CS_GO);
			break;

		/* SC_LOBBY_DISCONNECT:<Position> */
		case Protocol.SC_LOBBY_DISCONNECT:
			String position = tags[1];
			reinitializeLobby(position);
			this.myGui.clearGameTowerBuyChosenList();
			break;

		// In-Game

		/* SC_GAME_STARTING */
		case Protocol.SC_GAME_STARTING:
			this.myGui.switchPanelGameBuyTowers();
			break;

		/* SC_UPDATE_PLAYER_MONEY:<Money> */
		case Protocol.SC_UPDATE_PLAYER_MONEY:
			String money = tags[1];
			updatePlayerMoney(money);
			break;

		/* SC_UPDATE_POSITION_TOWER:<PosX>:<PosY>:<Type> */
		case Protocol.SC_UPDATE_POSITION_TOWER:
			int tPosX = Integer.parseInt(tags[1]);
			int tPosY = Integer.parseInt(tags[2]);
			String tType = tags[3];
			String towerListEntry = "Y: " + (tPosY + 1) + "  X: " + (tPosX + 1) + "  Type: " + tType;
			addTowerChosen(towerListEntry);
			break;

		/* SC_TOWER_NOT_AFFORDABLE:<Cost>:<Money> */
		case Protocol.SC_TOWER_NOT_AFFORDABLE:
			String cost = tags[1];
			String pMoney = tags[2];
			String towerBuyErrorAffordable = "Not Affordable! Cost: " + cost + "  Money: " + pMoney;
			setErrorTowerBuy(towerBuyErrorAffordable);
			break;

		/* SC_TOWER_NOT_PLACABLE:<TowerPosX>:<TowerPosY>:<TileType> */
		case Protocol.SC_TOWER_NOT_PLACEABLE:
			String towerX = tags[1];
			String towerY = tags[2];
			String tileType = tags[3];
			String towerBuyErrorPlaceable = generateErrorResponsePlaceable(towerX, towerY, tileType);
			setErrorTowerBuy(towerBuyErrorPlaceable);
			break;

		/* SC_ENEMY_BUY_SWAP */
		case Protocol.SC_ENEMY_BUY_SWAP:
			this.myGui.switchPanelGameBuyEnemies();
			break;

		/* SC_ENEMY_BUY_ADD */
		case Protocol.SC_ENEMY_BUY_ADD:
			String eType = tags[1];
			String enemyListEntry = "	Type: " + eType;
			addEnemyChosen(enemyListEntry);
			break;

		/* SC_ENEMY_NOT_AFFORDABLE:<Cost>:<Money> */
		case Protocol.SC_ENEMY_NOT_AFFORDABLE:
			String eCost = tags[1];
			String ePlayerMoney = tags[2];
			setErrorEnemyBuy("Enemy not affordable! Cost: " + eCost + " Money: " + ePlayerMoney + "!");
			break;

		/* SC_BUY_DONE */
		case Protocol.SC_BUY_DONE:
			this.myGui.switchPanelGameBuyWait();
			break;

		/* SC_BUY_ALL_READY */
		case Protocol.SC_BUY_ALL_READY:
			this.myGui.switchPanelGamePlay();
			break;

		/* SC_LOAD_MAP_DIMENSIONS:<Hight>:<Length> */
		case Protocol.SC_LOAD_MAP_DIMENSIONS:
			int height = Integer.parseInt(tags[1]);
			int length = Integer.parseInt(tags[2]);
			this.setGameMapBoundries(height, length);
			break;

		/* SC_LOAD_MAP_TYPE:<PosY>:<PosX>:<Type> */
		case Protocol.SC_LOAD_MAP_TYPE:
			int tilePosY = Integer.parseInt(tags[1]);
			int tilePosX = Integer.parseInt(tags[2]);
			int type = Integer.parseInt(tags[3]);
			this.setGameMapType(tilePosY, tilePosX, type);
			break;

		/*
		 * SC_UPDATE_POSITION_ENEMY:<PreviousPosX>:<PreviousPosY>:<CurrentPosX>:<
		 * CurrentPosY>
		 */
		case Protocol.SC_UPDATE_POSITION_ENEMY:
			updatePositionEnemy(tags[1], tags[2], tags[3], tags[4]);
			break;

		/* SC_UPDATE_PLAYER_HEALTH:<Health> */
		case Protocol.SC_UPDATE_PLAYER_HEALTH:
			Integer ownHealth = Integer.parseInt(tags[1]);
			Integer otherHealth = Integer.parseInt(tags[2]);
			this.myGui.updateOwnHealth(ownHealth);
			this.myGui.updateOtherHealth(otherHealth);
			break;

		/* SC_ROUND_OVER */
		case Protocol.SC_ROUND_OVER:
			this.send(Protocol.CS_ARE_ALL_ROUND_OVER);
			break;

		/* SC_ALL_ROUND_OVER_FALSE */
		case Protocol.SC_ALL_ROUND_OVER_FALSE:
			this.myGui.switchPanelGameBuyWait();
			break;

		/* SC_ALL_ROUND_OVER_TRUE */
		case Protocol.SC_ALL_ROUND_OVER_TRUE:
			this.myGui.resetGameMap();
			this.myGui.switchPanelGame();
			this.myGui.switchPanelGameBuyTowers();
			break;

		// End of Game

		/* SC_CHANGE_ENDSCREEN */
		case Protocol.SC_CHANGE_ENDSCREEN:
			this.myGui.switchPanelGameEndScreen();
			break;

		/* SC_UPDATE_ENDSCREEN_LEVEL:<Level> */
		case Protocol.SC_UPDATE_ENDSCREEN_LEVEL:
			this.myGui.updateEndScreenLevel(tags[1]);
			break;

		/* SC_UPDATE_ENDSCREEN_OWN:<State>:<Name>:<Health>:<Money>:<Enemies>:<Towers> */
		case Protocol.SC_UPDATE_ENDSCREEN_OWN:
			String ownState = tags[1];
			String ownName = tags[2];
			String own_Health = tags[3];
			String ownMoney = tags[4];
			String ownEnemies = tags[5];
			String ownTowers = tags[6];
			this.myGui.updateEndScreenOwn(ownState, ownName, own_Health, ownMoney, ownEnemies, ownTowers);
			break;

		/*
		 * SC_UPDATE_ENDSCREEN_OTHER:<State>:<Name>:<Health>:<Money>:<Enemies>:<Towers>
		 */
		case Protocol.SC_UPDATE_ENDSCREEN_OTHER:
			String otherState = tags[1];
			String otherName = tags[2];
			String other_Health = tags[3];
			String otherMoney = tags[4];
			String otherEnemies = tags[5];
			String otherTowers = tags[6];
			this.myGui.updateEndScreenOther(otherState, otherName, other_Health, otherMoney, otherEnemies, otherTowers);
			break;

		/* SC_EXIT_ENDSCREEN */
		case Protocol.SC_EXIT_ENDSCREEN:
			this.myGui.clearEndScreen();
			this.myGui.clearGameEnemyBuyChosenList();
			this.myGui.clearGameTowerBuyChosenList();
			this.myGui.resetGameMap();
			this.myGui.switchPanelLoggedIn(tags[1]);
			break;

		default:
			this.myGui.ausgeben(pMessage);
			break;

		}
	}

	/**
	 * If the enemy was on a position before the button's enemyCount is decreased by
	 * 1. If the enemy is on a position now the button's enemyCount is increased by
	 * 1.
	 * 
	 * @param PreviousPosX
	 *            The Enemy's previous x-position.
	 * @param PreviousPosY
	 *            The Enemy's previous y-position.
	 * @param CurrentPosX
	 *            The Enemy's current x-position.
	 * @param CurrentPosY
	 *            The Enemy's current x-position.
	 */
	private void updatePositionEnemy(String PreviousPosX, String PreviousPosY, String CurrentPosX, String CurrentPosY) {
		// Was on a Tile before
		if (!PreviousPosX.equals("null") && !PreviousPosY.equals("null")) {
			// Is on another Tile
			if (!CurrentPosX.equals("null") && !CurrentPosY.equals("null")) {
				this.myGui.decreaseEnemyCount(Integer.parseInt(PreviousPosX), Integer.parseInt(PreviousPosY));
				this.myGui.increaseEnemyCount(Integer.parseInt(CurrentPosX), Integer.parseInt(CurrentPosY));
			} else {
				this.myGui.decreaseEnemyCount(Integer.parseInt(PreviousPosX), Integer.parseInt(PreviousPosY));
			}
		} else {
			this.myGui.increaseEnemyCount(Integer.parseInt(CurrentPosX), Integer.parseInt(CurrentPosY));
		}
	}

	/**
	 * Returns the position of the player in the lobby as an integer.
	 * 
	 * @param string
	 *            The player's position.
	 * @return (int) The player's position.
	 */
	private int setLobbyPosition(String string) {
		if (string.equals("1")) {
			return 1;
		}
		return 2;
	}

	/**
	 * Sets the GUI's jSpinner's maximum for placing Towers according to the map.
	 * 
	 * @param mapName
	 */
	private void setSpinnerMaximum(String mapName) {
		switch (mapName) {

		case "Level_Test_Preset":
			this.myGui.setTowerBuyModelXSpinner(10);
			this.myGui.setTowerBuyModelYSpinner(10);
			break;
		case "Level_1_Preset":
			this.myGui.setTowerBuyModelXSpinner(5);
			this.myGui.setTowerBuyModelYSpinner(5);
			break;
		case "Level_2_Preset":
			this.myGui.setTowerBuyModelXSpinner(9);
			this.myGui.setTowerBuyModelYSpinner(9);
			break;
		default:
			this.myGui.setTowerBuyModelXSpinner(5);
			this.myGui.setTowerBuyModelYSpinner(5);
			break;
		}
	}

	/**
	 * Requests the GUI to change the usernames according to the players usernames
	 * and their position.
	 * 
	 * @param playerPosition
	 *            The player's position in the lobby.
	 * @param playerName
	 *            The player's name.
	 */
	private void setPlayerUsername(String playerPosition, String playerName) {
		if (playerPosition.equals("1")) {
			this.myGui.setUsernamePlayer1(playerName);
		} else {
			this.myGui.setUsernamePlayer2(playerName);
		}
	}

	/**
	 * Requests the GUI to change the player's status according to the players ready
	 * status.
	 * 
	 * @param playerPosition
	 *            The player's position in the lobby.
	 * @param playerStatus
	 *            The player's ready status.
	 */
	private void setPlayerReady(String playerPosition, String playerStatus) {
		if (playerPosition.equals("1")) {
			if (playerStatus.equals("true")) {
				this.myGui.setReadyPlayer1("READY");
			} else {
				this.myGui.setReadyPlayer1("NOT READY");
			}

		} else {
			if (playerStatus.equals("true")) {
				this.myGui.setReadyPlayer2("READY");
			} else {
				this.myGui.setReadyPlayer2("NOT READY");
			}

		}
		if (playerPosition.equals(this.positionInLobby.toString())) {
			this.myGui.setReadyButton(playerStatus);
		}
	}

	/**
	 * The lobby in reinitialized with the given players.
	 * 
	 * @param playerPosition
	 *            The player's position in the lobby.
	 */
	public void reinitializeLobby(String playerPosition) {
		this.setPlayerReady(playerPosition, "false");
		this.myGui.resetGameMap();
		this.myGui.switchPanelLobby();
	}

	/**
	 * Appends a new list entry to the Tower list of the GUI.
	 * 
	 * @param string
	 *            The String to be appended to the Tower list.
	 */
	public void addTowerChosen(String string) {
		this.myGui.addTowerChosen(string);
	}

	/**
	 * Appends a new list entry to the Enemy list of the GUI.
	 * 
	 * @param string
	 *            The String to be appended to the Enemy list.
	 */
	public void addEnemyChosen(String string) {
		this.myGui.addEnemyChosen(string);
	}

	/**
	 * Updates the money for the players.
	 * 
	 * @param string
	 *            The message the GUI processes.
	 */
	public void updatePlayerMoney(String string) {
		this.myGui.updateMoney(string);
	}

	/**
	 * Updates the error text when buying a tower for the player.
	 * 
	 * @param string
	 *            The message the GUI processes.
	 */
	public void setErrorTowerBuy(String string) {
		this.myGui.setErrorTowerBuy(string);
	}

	/**
	 * Updates the error when buying an enemy for the player.
	 * 
	 * @param string
	 *            The message the GUI processes.
	 */
	public void setErrorEnemyBuy(String string) {
		this.myGui.setErrorEnemyBuy(string);
	}

	/**
	 * Creates the error response for placing a tower based on the position and
	 * Tile.
	 * 
	 * @param pPosX
	 *            The Tower's x-position.
	 * @param pPosY
	 *            The Tower's y-position.
	 * @param tileType
	 *            The Tile's type.
	 * @return (String) errorMessage
	 */
	public String generateErrorResponsePlaceable(String pPosX, String pPosY, String tileType) {
		String message = "";
		switch (tileType) {

		case "1":
			message = "Not Placeable! On PosX: " + pPosX + " PosY: " + pPosY + " is the Base!";
			break;
		case "2":
			message = "Not Placeable! On PosX: " + pPosX + " PosY: " + pPosY + " is the Path!";
			break;
		case "3":
			message = "Not Placeable! On PosX: " + pPosX + " PosY: " + pPosY + " is the Spawn!";
			break;
		case "4":
			message = "Not Placeable! On PosX: " + pPosX + " PosY: " + pPosY + " is another Tower!";
			break;
		default:
			message = "Not Placeable!";
			break;
		}
		return message;
	}

	/**
	 * Sets the height and width of the GUI's button array according to the maps
	 * height and width.
	 * 
	 * @param height
	 *            The maps height.
	 * @param width
	 *            The maps width.
	 */
	private void setGameMapBoundries(int height, int width) {
		this.myGui.setGameMapBoundries(height, width);
	}

	/**
	 * Sets the color of the button at the position to the color of the tileType.
	 * 
	 * @param posY
	 *            The button's y-position.
	 * @param posX
	 *            The button's x-position.
	 * @param type
	 *            The tile's type.
	 */
	private void setGameMapType(int posY, int posX, int type) {
		this.myGui.setGameMapType(posY, posX, type);
	}

	/**
	 * @return the baughtTowers
	 */
	public ArrayList<Tower> getBaughtTowers() {
		return baughtTowers;
	}

	/**
	 * @param baughtTowers
	 *            the baughtTowers to set
	 */
	public void setBaughtTowers(ArrayList<Tower> baughtTowers) {
		this.baughtTowers = baughtTowers;
	}

	/**
	 * @return the baughtEnemies
	 */
	public ArrayList<Enemy> getBaughtEnemies() {
		return baughtEnemies;
	}

	/**
	 * @param baughtEnemies
	 *            the baughtEnemies to set
	 */
	public void setBaughtEnemies(ArrayList<Enemy> baughtEnemies) {
		this.baughtEnemies = baughtEnemies;
	}
}
