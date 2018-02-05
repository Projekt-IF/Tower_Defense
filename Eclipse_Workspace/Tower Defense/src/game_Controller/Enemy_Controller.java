package game_Controller;

import envoirement.Grid;
import envoirement.Tile;
import objects.Enemy;

/**
 * 
 */
public class Enemy_Controller {
	
	private Grid grid;
	
	private Tile spawnerTile;
	
	/**
	 * 
	 */
	public Enemy_Controller() {
		
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
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	public Boolean isMovable(Enemy e) {
		return grid.getGridLayer()[e.getPosY()][e.getPosX()].getHasNextTile();
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
}
