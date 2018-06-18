package graphical_Interface;

import java.util.ArrayList;

import network.Protocol;
import objects.Enemy;
import objects.Tower;

public class TD_Client extends Client {

	private Integer positionInLobby;

	private ArrayList<Tower> baughtTowers;
	private ArrayList<Enemy> baughtEnemies;

	private TextGui myGui;

	public TD_Client(String pServerIP, int pServerPort, TextGui tg) {
		super(pServerIP, pServerPort);
		this.myGui = tg;

	}

	public void processMessage(String pMessage) {
		String[] tags = pMessage.split(Protocol.SEPARATOR);
		String prefix = tags[0];

		switch (prefix) {

		case Protocol.SC_HELLO_WORLD:
			this.myGui.ausgeben(tags[1]);
			break;

		// Not game important communication

		case Protocol.SC_LOGIN_USERNAME_CONFIRMED:
			this.myGui.switchPanelLoginPassword(tags[1]);
			break;

		case Protocol.SC_LOGIN_USERNAME_DENIED:
			this.myGui.setUsernameResponseLabelText("The Username : " + tags[1] + " is not existent!");
			break;

		case Protocol.SC_LOGIN_PASSWORD_CONFIRMED:
			this.myGui.switchPanelLoggedIn(tags[1]);
			;
			break;

		case Protocol.SC_LOGIN_PASSWORD_DENIED:
			break;

		case Protocol.SC_LOBBY_FOUND:
			this.positionInLobby = setLobbyPosition(tags[1]);
			this.myGui.setMapName(tags[2]);
			this.myGui.setMapPicture(tags[2]);
			setSpinnerMaximum(tags[2]);
			this.myGui.switchPanelLobby();
			break;

		case Protocol.SC_LOGOUT_CONFIRMED:
			break;

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

		case Protocol.SC_PLAYER_JOINED:
			break;

		case Protocol.SC_LOBBY_FULL:
			break;

		case Protocol.SC_PLAYER_READY:
			this.setPlayerReady(tags[1], tags[2]);
			break;

		case Protocol.SC_ALL_PLAYER_READY:
			System.out.println("Test 1");
			this.myGui.switchPanelGame();
			this.myGui.switchPanelGameBuyTowers();
			System.out.println("Test 2");
			this.send(Protocol.CS_GO);
			break;

		case Protocol.SC_LOBBY_DISCONNECT:
			String position = tags[1];
			reinitializeLobby(position);
			break;

		// In-Game communication

		case Protocol.SC_GAME_STARTING:
			this.myGui.switchPanelGameBuyTowers();
			break;

		case Protocol.SC_LOAD_ENEMIES:
			break;

		case Protocol.SC_LOAD_TOWER:
			break;

		// Graphical Changes to be made by the Client

		case Protocol.SC_UPDATE_PLAYER_MONEY:
			String money = tags[1];
			updatePlayerMoney(money);
			break;

		case Protocol.SC_UPDATE_POSITION_TOWER:
			int tPosX = Integer.parseInt(tags[1]);
			int tPosY = Integer.parseInt(tags[2]);
			String tType = tags[3];
			String towerListEntry = "Y: " + (tPosY + 1) + "  X: " + (tPosX + 1) + "  Type: " + tType;
			addTowerChosen(towerListEntry);
			break;

		case Protocol.SC_TOWER_NOT_AFFORDABLE:
			String cost = tags[1];
			String pMoney = tags[2];
			String towerBuyErrorAffordable = "Not Affordable! Cost: " + cost + "  Money: " + pMoney;
			setErrorTowerBuy(towerBuyErrorAffordable);
			break;

		case Protocol.SC_TOWER_NOT_PLACEABLE:
			String towerX = tags[1];
			String towerY = tags[2];
			String tileType = tags[3];
			String towerBuyErrorPlaceable = generateErrorResponsePlaceable(towerX, towerY, tileType);
			setErrorTowerBuy(towerBuyErrorPlaceable);
			break;

		case Protocol.SC_ENEMY_BUY_SWAP:
			this.myGui.switchPanelGameBuyEnemies();
			break;

		case Protocol.SC_ENEMY_BUY_ADD:
			String eType = tags[1];
			String enemyListEntry = "	Type: " + eType;
			addEnemyChosen(enemyListEntry);
			break;

		case Protocol.SC_BUY_DONE:
			this.myGui.switchPanelGameBuyWait();
			break;

		case Protocol.SC_BUY_ALL_READY:
			this.myGui.switchPanelGamePlay();
			break;

		case Protocol.SC_LOAD_MAP_DIMENSIONS:
			int height = Integer.parseInt(tags[1]);
			int length = Integer.parseInt(tags[2]);
			this.setGameMapBoundries(height, length);
			break;

		case Protocol.SC_LOAD_MAP_TYPE:
			int type = Integer.parseInt(tags[1]);
			this.setGameMapType(type);
			break;

		case Protocol.SC_UPDATE_POSITION_ENEMY:
			updatePositionEnemy(tags[1], tags[2], tags[3], tags[4]);
			System.out.println("PREVIOUS X: " + tags[1] + " Y: " + tags[2] + "  CURRENT X: " + tags[3] + " Y: " + tags[4]);
			break;

		case Protocol.SC_UPDATE_PLAYER_HEALTH:
			Integer ownHealth = Integer.parseInt(tags[1]);
			Integer otherHealth = Integer.parseInt(tags[2]);
			this.myGui.updateOwnHealth(ownHealth);
			this.myGui.updateOtherHealth(otherHealth);
			break;

		case Protocol.SC_REMOVE_ENEMY:
			break;

		case Protocol.SC_REMOVE_TOWER:
			break;

		// End of game messages

		case Protocol.SC_VICTORY:
			break;

		case Protocol.SC_LOSS:
			break;

		case Protocol.SC_ENDOFGAMESTATS:
			break;

		// Universal

		case Protocol.SC_SENDERRORMESSAGE:
			break;

		case Protocol.SC_SURRENDER_SUCCESSFUL:
			break;

		case Protocol.SC_SURRENDER_UNSUCCESSFUL:
			break;

		case Protocol.SEPARATOR:
			break;

		default:
			this.myGui.ausgeben(pMessage);
			break;

		}
	}

	private void updatePositionEnemy(String PreviousPosX, String PreviousPosY, String CurrentPosX, String CurrentPosY) {
		//Was on a Tile before
		if(!PreviousPosX.equals("null")&&!PreviousPosY.equals("null")) {
			//Is on another Tile
			if(!CurrentPosX.equals("null")&&!CurrentPosY.equals("null")) {
				this.myGui.decreaseEnemyCount(Integer.parseInt(PreviousPosX), Integer.parseInt(PreviousPosY));
				this.myGui.increaseEnemyCount(Integer.parseInt(CurrentPosX), Integer.parseInt(CurrentPosY));
			} else {
				this.myGui.decreaseEnemyCount(Integer.parseInt(PreviousPosX), Integer.parseInt(PreviousPosY));
			}
		} else {
			this.myGui.increaseEnemyCount(Integer.parseInt(CurrentPosX), Integer.parseInt(CurrentPosY));
		}
	}

	private int setLobbyPosition(String string) {
		if (string.equals("1")) {
			return 1;
		}
		return 2;
	}

	private void setSpinnerMaximum(String mapName) {
		switch (mapName) {

		case "Level_Test_Preset":
			this.myGui.setTowerBuyModelXSpinner(5);
			this.myGui.setTowerBuyModelYSpinner(5);
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

	private void setPlayerUsername(String playerPosition, String playerName) {
		if (playerPosition.equals("1")) {
			this.myGui.setUsernamePlayer1(playerName);
		} else {
			this.myGui.setUsernamePlayer2(playerName);
		}
	}

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

	public void reinitializeLobby(String playerPosition) {
		this.setPlayerReady(playerPosition, "false");
		this.myGui.resetGameMap();
		this.myGui.switchPanelLobby();
	}

	public void addTowerChosen(String string) {
		this.myGui.addTowerChosen(string);
	}

	public void addEnemyChosen(String string) {
		this.myGui.addEnemyChosen(string);
	}

	public void updatePlayerMoney(String string) {
		this.myGui.updateMoney(string);
	}

	public void setErrorTowerBuy(String string) {
		this.myGui.setErrorTowerBuy(string);
	}

	public void setErrorEnemyBuy(String string) {
		this.myGui.setErrorEnemyBuy(string);
	}

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

	private void setGameMapBoundries(int height, int length) {
		this.myGui.setGameMapBoundries(height, length);
	}

	private void setGameMapType(int type) {
		this.myGui.setGameMapType(type);
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
