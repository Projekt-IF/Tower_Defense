package graphical_Interface;

import network.Protocol;

public class TD_Client extends Client {

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
			this.setPlayerUsername(tags[1], tags[2]);
			this.myGui.switchPanelLobby();
			break;
			
		case Protocol.SC_LOGIN_PASSWORD_DENIED:
			break;

		case Protocol.SC_LOGOUT_CONFIRMED:
			break;

		case Protocol.SC_LOBBY_USERS:
			this.setPlayerUsername(tags[1], tags[2]);
			this.myGui.ausgeben("The Player: " + tags[1] + " is named " + tags[2] + " !");

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

	}
}
