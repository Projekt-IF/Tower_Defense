package graphical_Interface;


import java.util.ArrayList;

import javax.swing.SpinnerNumberModel;

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
			if (tags[2].equals("true")) {
				this.myGui.ausgeben("The Player: " + tags[1] + " is ready!");
			} else {
				this.myGui.ausgeben("The Player: " + tags[1] + " is not ready!");
			}
			break;

		case Protocol.SC_ALL_PLAYER_READY:
			this.myGui.switchPanelGame();
			break;

		// In-Game communication

		case Protocol.SC_GAME_STARTING:
			break;

		case Protocol.SC_LOAD_ENEMIES:
			break;

		case Protocol.SC_LOAD_MAP:
			break;

		case Protocol.SC_LOAD_TOWER:
			break;

		// Graphical Changes to be made by the Client

		case Protocol.SC_UPDATE_PLAYER_MONEY:
			break;

		case Protocol.SC_UPDATE_POSITION_ENEMY:
			break;

		case Protocol.SC_UPDATE_POSITION_TOWER:
			break;

		case Protocol.SC_CHANGE_MAP:
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

	private int setLobbyPosition(String string) {
		if (string.equals("1")) {
			return 1;
		}
		return 2;
	}
	
	private void setSpinnerMaximum(String mapName) {
		switch(mapName) {
		
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
		if(playerPosition.equals(this.positionInLobby.toString())) {
			this.myGui.setReadyButton(playerStatus);
		}

	}

	/**
	 * @return the baughtTowers
	 */
	public ArrayList<Tower> getBaughtTowers() {
		return baughtTowers;
	}

	/**
	 * @param baughtTowers the baughtTowers to set
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
	 * @param baughtEnemies the baughtEnemies to set
	 */
	public void setBaughtEnemies(ArrayList<Enemy> baughtEnemies) {
		this.baughtEnemies = baughtEnemies;
	}
}
