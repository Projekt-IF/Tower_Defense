package utility;

import java.util.ArrayList;

import game_Controller.Game_Controller;
import objects.Enemy;
import objects.Player;
import objects.Tower;

public class GameFrameWork {

	public static final int startMoney = 500;

	public static final int startHealth = 100;

	private Player player_1;
	private Player player_2;

	private Game_Controller player_1_Game_Controller;
	private Game_Controller player_2_Game_Controller;

	private String currentLevel;

	private ArrayList<Enemy> boughtEnemies_Player_1;
	private ArrayList<Enemy> boughtEnemies_Player_2;

	private ArrayList<Tower> boughtTowers_Player_1;
	private ArrayList<Tower> boughtTowers_Player_2;

	public GameFrameWork(Player pPlayer_1, Player pPlayer_2) {
		this.player_1 = pPlayer_1;
		this.player_2 = pPlayer_2;
		this.player_1_Game_Controller = new Game_Controller(player_1);
		this.player_2_Game_Controller = new Game_Controller(player_2);
		this.currentLevel = "";
	}

	public void setLevel(String pLevel) {
		this.currentLevel = pLevel;
		player_1_Game_Controller.setCurrentLevel(pLevel);
		player_1_Game_Controller.iniciateGame();
		player_2_Game_Controller.setCurrentLevel(pLevel);
		player_2_Game_Controller.iniciateGame();
	}

	/**
	 * The Game is engaged for both players. The grid gets set. Then the possibility
	 * to buy towers and enemies.
	 */
	public void startGame(String pLevel) {
		prepareGame(pLevel);
	}

	private void startLoop() {
		// TODO Auto-generated method stub

	}

	private void prepareGame(String pLevel) {
		// TODO
		setLevel(pLevel);
		player_1.setPlayerMoney(startMoney);
		player_2.setPlayerMoney(startMoney);
		player_1.setHealth(startHealth);
		player_2.setHealth(startHealth);
	}

	public void assembleWaves() {
		// The Enemies Player 2 bought are put together with the wave of Player 1
		player_1_Game_Controller.addPurchasedEnemies(boughtEnemies_Player_2);
		// The Enemies Player 1 bought are put together with the wave of Player 2
		player_2_Game_Controller.addPurchasedEnemies(boughtEnemies_Player_1);
	}

	public void placeNewTowers() {
		// The Tower Player 1 bought are put on the Map
		player_1_Game_Controller.addPurchasedTowers(boughtTowers_Player_1);
		// The Tower Player 2 bought are put on the Map
		player_2_Game_Controller.addPurchasedTowers(boughtTowers_Player_2);
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

}
