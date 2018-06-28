package game_Controller;

import java.util.ArrayList;
import java.util.Random;

import envoirement.Level_1_Preset;
import envoirement.Level_2_Preset;
import envoirement.Level_Test_Preset;
import envoirement.Tile;
import network.Protocol;
import network.Server_TD;
import objects.Enemy;
import objects.Player;
import objects.Tower;

/**
 * The GameFrameWork manages the game and functions as an interface between the
 * game and the lobby(server).
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class GameFrameWork {

	public static final int startMoney = 200;

	public static final int startHealth = 100;

	private Server_TD server;

	private Player player_1;
	private Player player_2;

	private Game_Controller player_1_Game_Controller;
	private Game_Controller player_2_Game_Controller;

	private String currentLevel;

	private ArrayList<Enemy> boughtEnemies_Player_1;
	private ArrayList<Enemy> boughtEnemies_Player_2;

	private ArrayList<Tower> boughtTowers_Player_1;
	private ArrayList<Tower> boughtTowers_Player_2;

	private boolean loopStopped;

	/**
	 * Constructs a GameFrameWork initializing the players as null.
	 * 
	 * @param pServer
	 *            The designated server.
	 */
	public GameFrameWork(Server_TD pServer) {
		this.server = pServer;
		this.player_1 = null;
		this.player_2 = null;
		this.player_1_Game_Controller = new Game_Controller(this.server, this);
		this.player_2_Game_Controller = new Game_Controller(this.server, this);
		boughtEnemies_Player_1 = new ArrayList<Enemy>();
		boughtEnemies_Player_2 = new ArrayList<Enemy>();
		boughtTowers_Player_1 = new ArrayList<Tower>();
		boughtTowers_Player_2 = new ArrayList<Tower>();
		this.currentLevel = "";
	}

	/**
	 * Clears the gameControllers and enemy and tower lists.
	 */
	public void clear() {
		this.player_1_Game_Controller = new Game_Controller(this.server, this);
		this.player_2_Game_Controller = new Game_Controller(this.server, this);
		boughtEnemies_Player_1 = new ArrayList<Enemy>();
		boughtEnemies_Player_2 = new ArrayList<Enemy>();
		boughtTowers_Player_1 = new ArrayList<Tower>();
		boughtTowers_Player_2 = new ArrayList<Tower>();
		setLevel();
	}

	/**
	 * Stops both player game loops.
	 */
	public void stopGame() {
		this.player_1_Game_Controller.setLoopStopped(true);
		this.player_2_Game_Controller.setLoopStopped(true);
	}

	/**
	 * Evaluating who won and sending the evaluated statistics. Exiting the players
	 * from the lobby.
	 */
	public void evaluateGameResults() {
		int health_player_1 = player_1.getHealth();
		int health_player_2 = player_2.getHealth();
		if ((health_player_1 <= 0) && (health_player_2 <= 0)) {
			// BOTH LOST
			sendEndOfGameResults("LOST", "LOST");
		} else if ((health_player_1 <= 0) && (health_player_2 > 0)) {
			// PLAYER ONE LOST
			sendEndOfGameResults("LOST", "WON");
		} else if ((health_player_1 > 0) && (health_player_2 <= 0)) {
			// PLAYER TWO LOST
			sendEndOfGameResults("WON", "LOST");
		}
		this.server.sendToLobby(player_1.getLobbyIndex(), Protocol.SC_CHANGE_ENDSCREEN);
		this.server.exitGame(player_1.getPlayerIP(), player_1.getPlayerPort());
		this.server.exitGame(player_2.getPlayerIP(), player_2.getPlayerPort());
	}

	/**
	 * Sending the statistics for the end screen to the server.
	 * 
	 * @param player_1_placing
	 *            Has won/lost.
	 * @param player_2_placing
	 *            Has won/lost.
	 */
	public void sendEndOfGameResults(String player_1_placing, String player_2_placing) {
		this.server.send(player_1.getPlayerIP(), player_1.getPlayerPort(), Protocol.SC_UPDATE_ENDSCREEN_LEVEL
				+ Protocol.SEPARATOR + player_1_Game_Controller.getCurrentWaveIndex());
		this.server.send(player_2.getPlayerIP(), player_2.getPlayerPort(), Protocol.SC_UPDATE_ENDSCREEN_LEVEL
				+ Protocol.SEPARATOR + player_2_Game_Controller.getCurrentWaveIndex());
		this.server.send(player_1.getPlayerIP(), player_1.getPlayerPort(),
				Protocol.SC_UPDATE_ENDSCREEN_OWN + Protocol.SEPARATOR + player_1_placing + Protocol.SEPARATOR
						+ player_1.getUsername() + Protocol.SEPARATOR + player_1.getHealth() + Protocol.SEPARATOR
						+ player_1.getPlayerMoney() + Protocol.SEPARATOR + player_1.getEnemiesKilled()
						+ Protocol.SEPARATOR + player_1.getTowersPlaced());
		this.server.send(player_2.getPlayerIP(), player_2.getPlayerPort(),
				Protocol.SC_UPDATE_ENDSCREEN_OWN + Protocol.SEPARATOR + player_2_placing + Protocol.SEPARATOR
						+ player_2.getUsername() + Protocol.SEPARATOR + player_2.getHealth() + Protocol.SEPARATOR
						+ player_2.getPlayerMoney() + Protocol.SEPARATOR + player_2.getEnemiesKilled()
						+ Protocol.SEPARATOR + player_2.getTowersPlaced());
		this.server.send(player_1.getPlayerIP(), player_1.getPlayerPort(),
				Protocol.SC_UPDATE_ENDSCREEN_OTHER + Protocol.SEPARATOR + player_2_placing + Protocol.SEPARATOR
						+ player_2.getUsername() + Protocol.SEPARATOR + player_2.getHealth() + Protocol.SEPARATOR
						+ player_2.getPlayerMoney() + Protocol.SEPARATOR + player_2.getEnemiesKilled()
						+ Protocol.SEPARATOR + player_2.getTowersPlaced());
		this.server.send(player_2.getPlayerIP(), player_2.getPlayerPort(),
				Protocol.SC_UPDATE_ENDSCREEN_OTHER + Protocol.SEPARATOR + player_1_placing + Protocol.SEPARATOR
						+ player_1.getUsername() + Protocol.SEPARATOR + player_1.getHealth() + Protocol.SEPARATOR
						+ player_1.getPlayerMoney() + Protocol.SEPARATOR + player_1.getEnemiesKilled()
						+ Protocol.SEPARATOR + player_1.getTowersPlaced());
	}

	/**
	 * Chooses a random map from the map-pool.
	 * 
	 * @return (String) The chosen mapName.
	 */
	public String chooseRandomMap() {
		Random rand = new Random();
		String mapName = "";
		int number = rand.nextInt(3);
		switch (number) {
		case 0:
			mapName = Level_Test_Preset.NAME;
			break;
		case 1:
			mapName = Level_1_Preset.NAME;
			break;
		case 2:
			mapName = Level_2_Preset.NAME;
			break;
		default:
			mapName = "";
			break;
		}
		this.currentLevel = mapName;
		return mapName;
	}

	/**
	 * Sets the level for the GameControllers who create the grid using the
	 * Level_Controller.
	 */
	public void setLevel() {
		player_1_Game_Controller.setCurrentLevel(currentLevel);
		player_1_Game_Controller.iniciateLevel();
		player_2_Game_Controller.setCurrentLevel(currentLevel);
		player_2_Game_Controller.iniciateLevel();
	}

	/**
	 * The Game is engaged for both players. The grid gets set. Then the possibility
	 * to buy towers and enemies.
	 */
	public void startGame() {
		prepareGame();
		setLevel();
	}

	/**
	 * Starting two separate threads, one for each player, to go over the in-game
	 * loop.
	 */
	public void startWave() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				player_1.setRoundOver(false);
				player_1_Game_Controller.createWave();
				player_1_Game_Controller.startLoop();
			}
		}).start();
		;
		new Thread(new Runnable() {

			@Override
			public void run() {
				player_2.setRoundOver(false);
				player_2_Game_Controller.createWave();
				player_2_Game_Controller.startLoop();
			}
		}).start();
		;
	}

	/**
	 * Sets the players starting health and money.
	 */
	private void prepareGame() {
		player_1.setPlayerMoney(startMoney);
		player_2.setPlayerMoney(startMoney);
		player_1.setHealth(startHealth);
		player_2.setHealth(startHealth);
	}

	/**
	 * Sets the other player for each player.
	 */
	public void setOtherPlayer() {
		this.player_1.setOtherplayer(player_2);
		this.player_2.setOtherplayer(player_1);
	}

	/**
	 * Adds a new Enemy to the boughtEmemyList.
	 * 
	 * @param positionInLobby
	 *            The player's position in the lobby.
	 * @param pEnemy
	 *            The purchased Enemy.
	 */
	public void pushToBoughtEnemies(int positionInLobby, Enemy pEnemy) {
		if (positionInLobby == 1) {
			this.boughtEnemies_Player_1.add(pEnemy);
		} else if (positionInLobby == 2) {
			this.boughtEnemies_Player_2.add(pEnemy);
		}
	}

	/**
	 * Adds a new Tower to the boughtTowerList and changing the grid's type.
	 * 
	 * @param positionInLobby
	 *            The player's position in the lobby.
	 * @param pTower
	 *            The purchased Tower.
	 */
	public void pushToBoughtTowers(int positionInLobby, Tower pTower) {
		if (positionInLobby == 1) {
			this.boughtTowers_Player_1.add(pTower);
			this.getPlayer_1_Game_Controller().getGlobalGrid().getGridLayer()[pTower.getPosY()][pTower.getPosX()]
					.setType(Tile.TYPE_TOWER);
			this.getPlayer_1_Game_Controller().getGlobalGrid().printGrid();
		} else if (positionInLobby == 2) {
			this.boughtTowers_Player_2.add(pTower);
			this.getPlayer_2_Game_Controller().getGlobalGrid().getGridLayer()[pTower.getPosY()][pTower.getPosX()]
					.setType(Tile.TYPE_TOWER);
			this.getPlayer_2_Game_Controller().getGlobalGrid().printGrid();
		}
	}

	/**
	 * Adds the purchased enemyLists crosswise to the enemie's enemies.
	 * 
	 * @param positionInLobby
	 *            The player'S position in the lobby.
	 */
	public void assambleWaves(int positionInLobby) {
		if (positionInLobby == 1) {
			for (int i = 0; i < boughtEnemies_Player_2.size(); i++) {
				boughtEnemies_Player_2.get(i).setBounty(0);
			}
			player_1_Game_Controller.addEnemies(boughtEnemies_Player_2);
		} else if (positionInLobby == 2) {
			for (int i = 0; i < boughtEnemies_Player_1.size(); i++) {
				boughtEnemies_Player_1.get(i).setBounty(0);
			}
			player_2_Game_Controller.addEnemies(boughtEnemies_Player_1);
		}
	}

	/**
	 * Adds the purchased towerList to the towerList.
	 * 
	 * @param positionInLobby
	 *            The player's position in the lobby.
	 */
	public void placeNewTowers(int positionInLobby) {
		if (positionInLobby == 1) {
			player_1_Game_Controller.addPurchasedTowers(boughtTowers_Player_1);
		} else if (positionInLobby == 2) {
			player_2_Game_Controller.addPurchasedTowers(boughtTowers_Player_2);
		}
	}

	/**
	 * Checks if both players are ready.
	 * 
	 * @return (boolean) True: All players ready for game start. False: Not all
	 *         players ready for game start.
	 */
	public boolean playerReadyCheck() {
		if (player_1.isReady()) {
			if (player_2.isReady()) {
				// Both Players Ready
				return true;
			} else {
				// Player_1 Ready, Player_2 Not
				return false;
			}
		} else {
			if (player_2.isReady()) {
				// Player_1 Not, Player_2 Ready
				return false;
			} else {
				// Player_1 Not, Player_2 Not
				return false;
			}
		}
	}

	/**
	 * @return the player_1
	 */
	public Player getPlayer_1() {
		return player_1;
	}

	/**
	 * @param player_1
	 *            the player_1 to set
	 */
	public void setPlayer_1(Player player_1) {
		this.player_1 = player_1;
		player_1_Game_Controller.setPlayer(player_1);
	}

	/**
	 * @return the player_2
	 */
	public Player getPlayer_2() {
		return player_2;
	}

	/**
	 * @param player_2
	 *            the player_2 to set
	 */
	public void setPlayer_2(Player player_2) {
		this.player_2 = player_2;
		player_2_Game_Controller.setPlayer(player_2);
	}

	/**
	 * @return the player_1_Game_Controller
	 */
	public Game_Controller getPlayer_1_Game_Controller() {
		return player_1_Game_Controller;
	}

	/**
	 * @param player_1_Game_Controller
	 *            the player_1_Game_Controller to set
	 */
	public void setPlayer_1_Game_Controller(Game_Controller player_1_Game_Controller) {
		this.player_1_Game_Controller = player_1_Game_Controller;
	}

	/**
	 * @return the player_2_Game_COntroller
	 */
	public Game_Controller getPlayer_2_Game_Controller() {
		return player_2_Game_Controller;
	}

	/**
	 * @param player_2_Game_COntroller
	 *            the player_2_Game_COntroller to set
	 */
	public void setPlayer_2_Game_COntroller(Game_Controller player_2_Game_COntroller) {
		this.player_2_Game_Controller = player_2_Game_COntroller;
	}

	/**
	 * 
	 * @param positionInLobby
	 *            The player's position in the lobby.
	 * @return (game_COntroller) the palyers gameCOntroller.
	 */
	public Game_Controller getGameController(int positionInLobby) {
		if (positionInLobby == 1) {
			return player_1_Game_Controller;
		} else if (positionInLobby == 2) {
			return player_2_Game_Controller;
		}
		return null;
	}

	/**
	 * 
	 * @param positionInLobby
	 *            The player's position in the lobby.
	 * @return (ArrayList<Tower>) The player's towerList.
	 */
	public ArrayList<Tower> getBoughtTowerList(int positionInLobby) {
		if (positionInLobby == 1) {
			return boughtTowers_Player_1;
		} else if (positionInLobby == 2) {
			return boughtTowers_Player_2;
		}
		return null;
	}

	/**
	 * 
	 * @param positionInLobby
	 *            The player's position in the lobby.
	 * @return (ArrayList<Enemy>) The player's enemyList.
	 */
	public ArrayList<Enemy> getBoughtEnemyList(int positionInLobby) {
		if (positionInLobby == 1) {
			return boughtEnemies_Player_1;
		} else if (positionInLobby == 2) {
			return boughtEnemies_Player_2;
		}
		return null;
	}

	/**
	 * @return the loopStopped
	 */
	public boolean isLoopStopped() {
		return loopStopped;
	}

	/**
	 * @param loopStopped
	 *            the loopStopped to set
	 */
	public void setLoopStopped(boolean loopStopped) {
		this.loopStopped = loopStopped;
		this.player_1_Game_Controller.setLoopStopped(loopStopped);
		this.player_2_Game_Controller.setLoopStopped(loopStopped);
	}
}
