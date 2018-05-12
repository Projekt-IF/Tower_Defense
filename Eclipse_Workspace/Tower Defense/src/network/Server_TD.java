package network;

import java.util.ArrayList;

import network.Protocol;
import objects.Player;
import utility.Lobby;

///Server
public class Server_TD extends Server {

	private ArrayList<Player> playerList;
	private ArrayList<Lobby> lobbyList;

	public Server_TD(int pPort) {
		super(pPort);
		playerList = new ArrayList<Player>();
		lobbyList = new ArrayList<Lobby>();
		lobbyList.add(new Lobby());
		System.out.println("Server auf Port: " + pPort + " geöffnet!");
		showLobbys();
		showPlayer();

	}

	@Override
	public void processNewConnection(String pClientIP, int pClientPort) {
		Player newPlayer = new Player(pClientIP, pClientPort);
		newPlayer.setConnected(true);
		playerList.add(newPlayer);
		System.out.println("Connected new Player! \n IP: " + pClientIP + " Port: " + pClientPort + " !");
		sortInLobby(newPlayer);
		String backMessage = "Connected!";
		this.send(pClientIP, pClientPort, backMessage);
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
		/* CS_LOGIN:<Username><Password> */
		case Protocol.CS_LOGIN:
			backMessage = Protocol.SC_LOGIN_CONFIRMED + Protocol.SEPARATOR + "Login Successful!";
			break;
		/* CS_SURRENDER */
		case Protocol.CS_SURRENDER:
			backMessage = Protocol.SC_SURRENDER_SUCCESSFUL + Protocol.SEPARATOR + "You surrendered!";
			break;
		/* CS_LOGOUT:<Username> */
		case Protocol.CS_LOGOUT:
			backMessage = Protocol.SC_LOGOUT_CONFIRMED + Protocol.SEPARATOR + "Logout successful!";
			break;
		/* CS_GO */
		case Protocol.CS_GO:
			backMessage = Protocol.SC_GAME_STARTING + Protocol.SEPARATOR + "Game Starting";
			break;
		/* CS_READY_LOBBY */
		case Protocol.CS_READY_LOBBY:
			System.out.println(token[1]);
			// TODO: Fix so it can change the actual state of ready!
			player.setReady(!player.isReady());
			showLobbys();
			showPlayer();
			backMessage = Protocol.SC_PLAYER_READY + Protocol.SEPARATOR + player.getPositionIndex() + Protocol.SEPARATOR
					+ player.isReady();
			break;
		default:
			backMessage = Protocol.SC_SENDERRORMESSAGE + Protocol.SEPARATOR + "Error!";
			break;
		}
		System.out.println("Server: Gesendet :<" + backMessage + ">");

		this.send(pClientIP, pClientPort, backMessage);
		// if (prefix.equals(Protocol.CS_LOGOUT)) {
		// this.closeConnection(pClientIP, pClientPort);
		// }

	}

	@Override
	public void processClosingConnection(String pClientIP, int pClientPort) {
		System.out.println("Der Client mit der IP: " + pClientIP + " wird abgemeldet.");
		Player removePlayer = new Player(pClientIP, pClientPort);
		removeFromLobby(removePlayer);
		removeFromPlayers(removePlayer);
		removePlayer.setConnected(false);
		this.closeConnection(pClientIP, pClientPort);

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

	public void sortInLobby(Player player) {
		for (int i = 0; i < lobbyList.size(); i++) {
			if (!lobbyList.get(i).getIsFull()) {
				if ((lobbyList.get(i).getPlayer_1() != null) && (lobbyList.get(i).getPlayer_2() == null)) {
					lobbyList.get(i).setPlayer_2(player, playerList.indexOf(player));
					player.setLobbyIndex(i);
					player.setPositionIndex(2);
					lobbyList.get(i).setIsFull(true);
					break;
				} else if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() != null)) {
					lobbyList.get(i).setPlayer_1(player, playerList.indexOf(player));
					player.setLobbyIndex(i);
					player.setPositionIndex(1);
					lobbyList.get(i).setIsFull(true);
					break;
				} else if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() == null)) {
					lobbyList.get(i).setPlayer_1(player, playerList.indexOf(player));
					player.setLobbyIndex(i);
					player.setPositionIndex(1);
					lobbyList.get(i).setIsFull(false);
					break;
				}

			} else {
				if (i == (lobbyList.size() - 1)) {
					Lobby newLobby = new Lobby();
					newLobby.setPlayer_1(player, playerList.indexOf(player));
					player.setLobbyIndex(i);
					player.setPositionIndex(1);
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
				if (lobbyList.get(i).haveSameStats(mPlayer, lobbyList.get(i).getPlayer_1())) {
					lobbyList.get(i).resetPlayer_1();
					;
					lobbyList.get(i).setIsFull(false);
				}
			} else if ((lobbyList.get(i).getPlayer_1() == null) && (lobbyList.get(i).getPlayer_2() != null)) {
				if (lobbyList.get(i).haveSameStats(mPlayer, lobbyList.get(i).getPlayer_2())) {
					lobbyList.get(i).resetPlayer_2();
					;
					lobbyList.get(i).setIsFull(false);
				}
			} else if ((lobbyList.get(i).getPlayer_1() != null) && (lobbyList.get(i).getPlayer_2() != null)) {
				if (lobbyList.get(i).haveSameStats(mPlayer, lobbyList.get(i).getPlayer_1())) {
					lobbyList.get(i).resetPlayer_1();
					;
					lobbyList.get(i).setIsFull(false);
				} else if (lobbyList.get(i).haveSameStats(mPlayer, lobbyList.get(i).getPlayer_2())) {
					lobbyList.get(i).resetPlayer_2();
					;
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

	public static void main(String[] args) {
		new Server_TD(51678);
	}

}
