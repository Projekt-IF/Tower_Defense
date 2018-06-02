package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import envoirement.Tile;
import objects.Enemy;
import objects.Player;

/**
 * 
 */
public class Enemy_Controller {

	// private Game_Controller game_Controller;

	private Player player;

	private Grid grid;

	private Tile spawnerTile;

	private ArrayList<Enemy> enemyList;

	/**
	 * 
	 */
	public Enemy_Controller(ArrayList<Enemy> pEnemyList, Player pPlayer/* , Game_Controller pGame_Controller */) {
		// this.game_Controller = pGame_Controller;
		this.enemyList = pEnemyList;
		this.player = pPlayer;
	}

	/**
	 * 
	 * @param pType
	 * @param pLevel
	 * @return
	 */
	public Enemy spawnEnemy(Integer pType, Integer pLevel) {
		Enemy e = new Enemy(this.spawnerTile.getxPos(), this.spawnerTile.getyPos(), pType, pLevel);
		return e;
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
				System.out.println("Enemy Here: " + a);
				System.out.println("Y: " + enemyList.get(a).getPosY() + " X: " + enemyList.get(a).getPosX() + " Life: "
						+ enemyList.get(a).getLife());
			} else {
				if (grid.getGridLayer()[enemyList.get(a).getPosY()][enemyList.get(a).getPosX()]
						.getHasNextTile() == false) {
					if ((grid.getBaseTile().getxPos() == enemyList.get(a).getPosX())
							&& (grid.getBaseTile().getyPos() == enemyList.get(a).getPosY())) {
						player.setHealth(player.getHealth() - enemyList.get(a).getDamage());
					}
					enemyList.remove(enemyList.get(a));
				}
			}
		}
	}
	
	public boolean isEnemyAffordable(int pCost) {
		if(player.getPlayerMoney() >= pCost) {
			return true;
		}
		return false;
	}

	public void clearEnemyList() {
		enemyList.clear();
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.spawnerTile = grid.getSpawnerTile();
		this.grid = grid;
	}

	public Tile getSpawnerTile() {
		return spawnerTile;
	}

	public void setSpawnerTile(Tile spawnerTile) {
		this.spawnerTile = spawnerTile;
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
}
