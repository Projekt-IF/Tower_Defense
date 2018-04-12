package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import envoirement.Tile;
import objects.Enemy;

/**
 * 
 */
public class Enemy_Controller {
	
	private Grid grid;
	
	private Tile spawnerTile;
	
	private ArrayList<Enemy> enemyList;
	
	/**
	 * 
	 */
	public Enemy_Controller(ArrayList<Enemy> pEnemyList) {
		this.enemyList = pEnemyList;
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
		e.setOnCooldown(true);
		e.startTimer();
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	public Boolean isMovable(Enemy e) {
		if(!enemyList.isEmpty()) {
			for(int i = 0; i < enemyList.size(); i++) {
				Enemy current = enemyList.get(i);
//				System.out.println(current + "  " + e);
//				System.out.print("C " + current.getPosX() + "  N: ");
//				System.out.println(grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile().getxPos());
//				System.out.print("C " + current.getPosY() + "  N: ");
//				System.out.println(grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile().getyPos());
//				System.out.println(current.getPosX() != grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile().getxPos());
//				System.out.println(current.getPosY() != grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile().getyPos());
				if(grid.getGridLayer()[e.getPosY()][e.getPosX()].getHasNextTile()&& !e.isOnCooldown()) {
					if(!((current.getPosX() == grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile().getxPos())&&
						(current.getPosY() == grid.getGridLayer()[e.getPosY()][e.getPosX()].getNextTile().getyPos()))) {
						return true;
					}
				}
			}
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
	 * @param enemyList the enemyList to set
	 */
	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
}
