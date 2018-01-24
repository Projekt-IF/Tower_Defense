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
	
	
	public void moveEnemy(Enemy e) {
		Tile[][] g = grid.getGridLayer();
		e.setPosX(g[e.getPosX()][e.getPosY()].getNextTile().getxPos());
		e.setPosY(g[e.getPosX()][e.getPosY()].getNextTile().getyPos());

	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Tile getSpawnerTile() {
		return spawnerTile;
	}

	public void setSpawnerTile(Tile spawnerTile) {
		this.spawnerTile = spawnerTile;
	}
	
	
}
