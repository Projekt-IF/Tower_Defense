package game_Controller;

import java.util.ArrayList;
import java.util.Timer;

import envoirement.Grid;
import network.Protocol;
import network.Server_TD;
import objects.Enemy;
import objects.Player;
import utility.EnemySpawnTimer;

/**
 * 
 */
public class Enemy_Controller {

	// private Game_Controller game_Controller;

	private Server_TD server;

	private Player player;

	private Grid grid;

	private ArrayList<Enemy> generatedEnemyList;
	private ArrayList<Enemy> enemyList;

	private boolean isOnSpawnCooldown;

	/**
	 * 
	 */
	public Enemy_Controller(Server_TD pServer, ArrayList<Enemy> pEnemyList, ArrayList<Enemy> pGeneratedEnemyList,
			Player pPlayer/* , Game_Controller pGame_Controller */) {
		// this.game_Controller = pGame_Controller;
		this.server = pServer;
		this.generatedEnemyList = pGeneratedEnemyList;
		this.enemyList = pEnemyList;
		this.player = pPlayer;
		this.isOnSpawnCooldown = false;
		;
	}

	public void startTimer() {
		Timer timer = new Timer();
		System.out.println("TIMER FOR: " + 5000 / 1000 + "Seconds");
		timer.schedule(new EnemySpawnTimer(this, timer), (long) (5000));
	}

	/**
	 * 
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

	public void spawn() {
		this.isOnSpawnCooldown = true;
		startTimer();
	}

	/**
	 * 
	 * @param e
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
	 * 
	 * @param e
	 * @return
	 */
	public Boolean isMovable(Enemy e) {
		if (e.checkAlife()) {
			if (!e.isOnCooldown()) {
				if (!enemyList.isEmpty()) {
					for (int i = 0; i < enemyList.size(); i++) {
						Enemy current = enemyList.get(i);
						// System.out.println(current + " " + e);
						// System.out.print("C " + current.getPosX() + " N: ");
						// System.out.println(grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile().getxPos());
						// System.out.print("C " + current.getPosY() + " N: ");
						// System.out.println(grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile().getyPos());
						// System.out.println(current.getPosX() !=
						// grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile().getxPos());
						// System.out.println(current.getPosY() !=
						// grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile().getyPos());
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
		}
		return false;
	}

	public void checkMoveEnemies() {
		for (int a = 0; a < enemyList.size(); a++) {
			if (isMovable(enemyList.get(a))) {
				moveEnemy(enemyList.get(a));
				System.out.println("Enemy Here: " + enemyList.get(a).getNumber());
				System.out.println("Y: " + enemyList.get(a).getPosY() + " X: " + enemyList.get(a).getPosX() + " Life: "
						+ enemyList.get(a).getLife());
			} else {
				if (grid.getGridLayer()[enemyList.get(a).getPosY()][enemyList.get(a).getPosX()]
						.getHasNextTile() == false) {
					if ((grid.getBaseTile().getxPos() == enemyList.get(a).getPosX())
							&& (grid.getBaseTile().getyPos() == enemyList.get(a).getPosY())) {
						if (player.getHealth() > 0) {
							player.setHealth(player.getHealth() - enemyList.get(a).getDamage());
							this.server.send(player.getPlayerIP(), player.getPlayerPort(),
									Protocol.SC_UPDATE_PLAYER_HEALTH + Protocol.SEPARATOR + player.getHealth()
											+ Protocol.SEPARATOR + player.getOtherplayer().getHealth());
						} else if (player.getHealth() <= 0) {
							this.server.send(player.getPlayerIP(), player.getPlayerPort(),
									Protocol.SC_UPDATE_PLAYER_HEALTH + Protocol.SEPARATOR + player.getHealth()
											+ Protocol.SEPARATOR + player.getOtherplayer().getHealth());
						}
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

	public boolean isEnemyAffordable(int pCost) {
		if (player.getPlayerMoney() >= pCost) {
			return true;
		}
		return false;
	}

	public void clearEnemyList() {
		enemyList.clear();
	}

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

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Player getPlayer() {
		return player;
	}

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
