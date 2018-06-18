package utility;

import java.util.ArrayList;
import java.util.Random;

import envoirement.Level_1_Preset;
import envoirement.Level_2_Preset;
import envoirement.Level_Test_Preset;
import envoirement.Tile;
import game_Controller.Game_Controller;
import network.Server_TD;
import objects.Enemy;
import objects.Player;
import objects.Tower;

public class GameFrameWork {

	public static final int startMoney = 500;

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

	public GameFrameWork(Server_TD pServer) {
		this.server = pServer;
		this.player_1 = null;
		this.player_2 = null;
		this.player_1_Game_Controller = new Game_Controller(this.server);
		this.player_2_Game_Controller = new Game_Controller(this.server);
		boughtEnemies_Player_1 = new ArrayList<Enemy>();
		boughtEnemies_Player_2 = new ArrayList<Enemy>();
		boughtTowers_Player_1 = new ArrayList<Tower>();
		boughtTowers_Player_2 = new ArrayList<Tower>();
		this.currentLevel = "";
	}

	public void clear() {
		this.player_1_Game_Controller = new Game_Controller(this.server);
		this.player_2_Game_Controller = new Game_Controller(this.server);
		boughtEnemies_Player_1 = new ArrayList<Enemy>();
		boughtEnemies_Player_2 = new ArrayList<Enemy>();
		boughtTowers_Player_1 = new ArrayList<Tower>();
		boughtTowers_Player_2 = new ArrayList<Tower>();
		setLevel();
	}

	public String chooseRandomMap() {
		Random rand = new Random();
		String mapName = "";
		int number = rand.nextInt(3);
		System.out.println("NUMBER: " + number);
		switch (number) {
		case 0:
			System.out.println("NUMBER: " + number);
			mapName = Level_Test_Preset.NAME;
			break;
		case 1:
			System.out.println("NUMBER: " + number);
			mapName = Level_1_Preset.NAME;
			break;
		case 2:
			System.out.println("NUMBER: " + number);
			mapName = Level_2_Preset.NAME;
			break;
		default:
			System.out.println("NUMBER: " + number);
			mapName = "";
			break;
		}
		this.currentLevel = mapName;
		System.out.println("Map: " + number + "CURRENT: " + currentLevel);
		return mapName;
	}

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

	public void startWave() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				player_1_Game_Controller.createWave();
				player_1_Game_Controller.startLoop();
			}
		}).start();;
		new Thread(new Runnable() {

			@Override
			public void run() {
				player_2_Game_Controller.createWave();
				player_2_Game_Controller.startLoop();
			}
		}).start();;
	}

	private void prepareGame() {
		player_1.setPlayerMoney(startMoney);
		player_2.setPlayerMoney(startMoney);
		player_1.setHealth(startHealth);
		player_2.setHealth(startHealth);
	}

	public void setOtherPlayer() {
		this.player_1.setOtherplayer(player_2);
		this.player_2.setOtherplayer(player_1);
	}

	public void pushToBoughtEnemies(int positionInLobby, Enemy pEnemy) {
		if (positionInLobby == 1) {
			this.boughtEnemies_Player_1.add(pEnemy);
		} else if (positionInLobby == 2) {
			this.boughtEnemies_Player_2.add(pEnemy);
		}
	}

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

	public void assembleWaves(int positionInLobby) {
		if (positionInLobby == 1) {
			player_1_Game_Controller.addPurchasedEnemies(boughtEnemies_Player_2);
		} else if (positionInLobby == 2) {
			player_2_Game_Controller.addPurchasedEnemies(boughtEnemies_Player_1);
		}
	}

	public void placeNewTowers(int positionInLobby) {
		if (positionInLobby == 1) {
			player_1_Game_Controller.addPurchasedTowers(boughtTowers_Player_1);
		} else if (positionInLobby == 2) {
			player_2_Game_Controller.addPurchasedTowers(boughtTowers_Player_2);
		}
	}

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

	public Game_Controller getGameController(int positionInLobby) {
		if (positionInLobby == 1) {
			return player_1_Game_Controller;
		} else if (positionInLobby == 2) {
			return player_2_Game_Controller;
		}
		return null;
	}

	public ArrayList<Tower> getBoughtTowerList(int positionInLobby) {
		if (positionInLobby == 1) {
			return boughtTowers_Player_1;
		} else if (positionInLobby == 2) {
			return boughtTowers_Player_2;
		}
		return null;
	}

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
	 * @param loopStopped the loopStopped to set
	 */
	public void setLoopStopped(boolean loopStopped) {
		this.loopStopped = loopStopped;
		this.player_1_Game_Controller.setLoopStopped(loopStopped);
		this.player_2_Game_Controller.setLoopStopped(loopStopped);
	}
}
