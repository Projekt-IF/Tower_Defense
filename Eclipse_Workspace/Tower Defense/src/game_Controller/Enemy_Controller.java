package game_Controller;

import java.util.ArrayList;
import java.util.Timer;

import envoirement.Grid;
import network.Protocol;
import network.Server_TD;
import objects.Enemy;
import objects.Player;
import utility.EnemySpawnTimerTask;

/**
 * The Enemy_Controller manages the spawning and movement of the enemies.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class Enemy_Controller {

	private Game_Controller game_Controller;

	private Server_TD server;

	private Player player;

	private Grid grid;

	private ArrayList<Enemy> generatedEnemyList;
	private ArrayList<Enemy> enemyList;

	private boolean isOnSpawnCooldown;

	/**
	 * Constructs an Enemy_Controller with a designated server to control the enemy
	 * spawn and movement.
	 * 
	 * @param pServer
	 *            The designated server.
	 * @param pEnemyList
	 *            The enemyList.
	 * @param pGeneratedEnemyList
	 *            The generatedEnemyList.
	 * @param pPlayer
	 *            The player.
	 * @param pGame_Controller
	 *            The Game_Controller.
	 */
	public Enemy_Controller(Server_TD pServer, ArrayList<Enemy> pEnemyList, ArrayList<Enemy> pGeneratedEnemyList,
			Player pPlayer, Game_Controller pGame_Controller) {
		this.game_Controller = pGame_Controller;
		this.server = pServer;
		this.generatedEnemyList = pGeneratedEnemyList;
		this.enemyList = pEnemyList;
		this.player = pPlayer;
		this.isOnSpawnCooldown = false;
		;
	}

	/**
	 * Starting the spawn cool down.
	 */
	public void startTimer() {
		Timer timer = new Timer();
		timer.schedule(new EnemySpawnTimerTask(this, timer), (long) (2000));
	}

	/**
	 * Spawning a new Enemy from the generatedEnemyList.
	 */
	public void spawnEnemy() {
		Enemy e = generatedEnemyList.get(0);
		e.setPosX(grid.getSpawnerTile().getxPos());
		e.setPosY(grid.getSpawnerTile().getyPos());
		enemyList.add(e);
		generatedEnemyList.remove(e);
		this.server.send(player.getPlayerIP(), player.getPlayerPort(),
				Protocol.SC_UPDATE_POSITION_ENEMY + Protocol.SEPARATOR + "null" + Protocol.SEPARATOR + "null"
						+ Protocol.SEPARATOR + e.getPosX() + Protocol.SEPARATOR + e.getPosY());
		spawn();
	}

	/**
	 * Starting the move cool down.
	 */
	public void spawn() {
		this.isOnSpawnCooldown = true;
		startTimer();
	}

	/**
	 * Changes the enemies position and starts the enemies cool down. A message with
	 * the updated position is send to the server.
	 * 
	 * @param e
	 *            The enemy.
	 */
	public void moveEnemy(Enemy e) {
		int currentXPos = e.getPosX();
		int currentYPos = e.getPosY();
		e.setPosX(grid.getGridLayer()[currentYPos][currentXPos].getNextTile().getxPos());
		e.setPosY(grid.getGridLayer()[currentYPos][currentXPos].getNextTile().getyPos());
		this.server.send(player.getPlayerIP(), player.getPlayerPort(),
				Protocol.SC_UPDATE_POSITION_ENEMY + Protocol.SEPARATOR + currentXPos + Protocol.SEPARATOR + currentYPos
						+ Protocol.SEPARATOR + e.getPosX() + Protocol.SEPARATOR + e.getPosY());
		e.move();
	}

	/**
	 * Checks if the ENemy is on cool down, no enemies ion the List or not next tile
	 * available.
	 * 
	 * @param e
	 *            The enemy to check.
	 * @return True: Is movable. False: Is not movable.
	 */
	public Boolean isMovable(Enemy e) {
		if (!e.isOnCooldown()) {
			if (!enemyList.isEmpty()) {
				for (int i = 0; i < enemyList.size(); i++) {
					Enemy current = enemyList.get(i);
					if (grid.getGridLayer()[e.getPosY()][e.getPosX()].getHasNextTile()) {
						if (!((current.getPosX() == grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile()
								.getxPos())
								&& (current.getPosY() == grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile()
										.getyPos()))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Checks to move the enemies in the enemyList. Otherwise the enemy is at the
	 * base and damages the player or the player looses.
	 */
	public void checkMoveEnemies() {
		for (int a = 0; a < enemyList.size(); a++) {
			// checks for cool down and life and if there is a next tile.
			if (isMovable(enemyList.get(a))) {
				moveEnemy(enemyList.get(a));
			} else {
				if (grid.getGridLayer()[enemyList.get(a).getPosY()][enemyList.get(a).getPosX()]
						.getHasNextTile() == false) {
					if ((grid.getBaseTile().getxPos() == enemyList.get(a).getPosX())
							&& (grid.getBaseTile().getyPos() == enemyList.get(a).getPosY())) {
						if (player.getHealth() > 0) {
							player.setHealth(player.getHealth() - enemyList.get(a).getDamage());
						}
						if (player.getHealth() <= 0) {
							this.game_Controller.getGameFrameWork().stopGame();
							this.game_Controller.getGameFrameWork().evaluateGameResults();
						}
						this.server.send(player.getPlayerIP(), player.getPlayerPort(),
								Protocol.SC_UPDATE_PLAYER_HEALTH + Protocol.SEPARATOR + player.getHealth()
										+ Protocol.SEPARATOR + player.getOtherplayer().getHealth());
						this.server.send(player.getOtherplayer().getPlayerIP(), player.getOtherplayer().getPlayerPort(),
								Protocol.SC_UPDATE_PLAYER_HEALTH + Protocol.SEPARATOR
										+ player.getOtherplayer().getHealth() + Protocol.SEPARATOR
										+ player.getHealth());
					}
					this.server.send(player.getPlayerIP(), player.getPlayerPort(),
							Protocol.SC_UPDATE_POSITION_ENEMY + Protocol.SEPARATOR + enemyList.get(a).getPosX()
									+ Protocol.SEPARATOR + enemyList.get(a).getPosY() + Protocol.SEPARATOR + "null"
									+ Protocol.SEPARATOR + "null");
					enemyList.remove(enemyList.get(a));
				}
			}
		}
	}

	/**
	 * Checks weather the enemy is affordable.
	 * 
	 * @param pCost
	 *            The enemy's cost.
	 * @return True: Money >= cost. False: Money < cost.
	 */
	public boolean isEnemyAffordable(int pCost) {
		if (player.getPlayerMoney() >= pCost) {
			return true;
		}
		return false;
	}

	/**
	 * Clearing the enemyList.
	 */
	public void clearEnemyList() {
		enemyList.clear();
	}

	/**
	 * Printing the generatedNEmeyLista and the enemyList to the Command Prompt.
	 */
	public void printEnemyLists() {
		System.out.println("The GeneratedEnemyList: ");
		for (int a = 0; a < generatedEnemyList.size(); a++) {
			Enemy tmp = generatedEnemyList.get(a);
			System.out.println("Enemy: " + (a + 1) + ";  PosY: null;  PosX: null;  Type: " + tmp.getType() + ";  Life: "
					+ tmp.getLife() + ";  Speed: " + tmp.getSpeed());
		}
		System.out.println("The EnemyList: ");
		for (int a = 0; a < enemyList.size(); a++) {
			Enemy tmp = enemyList.get(a);
			System.out.println("Enemy: " + (a + 1) + ";  PosY: " + tmp.getPosY() + ";  PosX: " + tmp.getPosX()
					+ ";  Type: " + tmp.getType() + ";  Life: " + tmp.getLife() + ";  Speed: " + tmp.getSpeed());
		}
	}

	/**
	 * 
	 * @return The grid.
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * 
	 * @param grid
	 *            The grid.
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * 
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * 
	 * @param player
	 *            The player.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the enemyList
	 */
	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	/**
	 * @param enemyList
	 *            the enemyList to set
	 */
	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	/**
	 * @return the isOnSpawnCooldown
	 */
	public boolean isOnSpawnCooldown() {
		return isOnSpawnCooldown;
	}

	/**
	 * @param isOnSpawnCooldown
	 *            the isOnSpawnCooldown to set
	 */
	public void setOnSpawnCooldown(boolean isOnSpawnCooldown) {
		this.isOnSpawnCooldown = isOnSpawnCooldown;
	}

	/**
	 * @return the generatedEnemyList
	 */
	public ArrayList<Enemy> getGeneratedEnemyList() {
		return generatedEnemyList;
	}

	/**
	 * @param generatedEnemyList
	 *            the generatedEnemyList to set
	 */
	public void setGeneratedEnemyList(ArrayList<Enemy> generatedEnemyList) {
		this.generatedEnemyList = generatedEnemyList;
	}
}
