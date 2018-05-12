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

		case Protocol.SC_LOGIN_CONFIRMED:
			break;

		case Protocol.SC_LOGOUT_CONFIRMED:
			break;

		case Protocol.SC_RECONNECT_PLAYER:
			break;

		case Protocol.SC_KICK_PLAYER:
			break;

		case Protocol.SC_LOBBY_USERS:
			break;

		case Protocol.SC_PLAYER_JOINED:
			break;

		case Protocol.SC_LOBBY_FULL:
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
			this.myGui.ausgeben("Error!");
			break;

		}
	}
}
