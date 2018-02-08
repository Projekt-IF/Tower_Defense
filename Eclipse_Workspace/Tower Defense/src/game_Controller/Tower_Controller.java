package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import envoirement.Tile;
import objects.Tower;

public class Tower_Controller {
	
	private Grid grid;
	
	private ArrayList<Tower> towerList = new ArrayList<Tower>();
	
	public Tower_Controller() {
//		TODO:
	}
	
	
	public Tower setTower(int PPosX, int PPosY, int pRange, int pStrength, double pSpeed, double pVelocity) {
//		TODO: Testing if the tower is placed on a free Tile
		grid.getGridLayer()[PPosY][PPosX].setType(Tile.TYPE_TOWER);
		return new Tower(PPosX, PPosY, pRange, pStrength, pSpeed, pVelocity);
	}
	
	
	public void removeTower(int PPosX, int PPosY) {
//		TODO:
	}
	
	
	public void createTower() {
//		TODO:
	}
	
	
	public void shootTower() {
//		TODO:
	}
	
	
	public void checkTower(int PPosX, int PPosY) {
		
	}
	
	
	public void upgradeTower() {
//		TODO:
	}


	public Grid getGrid() {
		return grid;
	}


	public void setGrid(Grid grid) {
		this.grid = grid;
	}


	/**
	 * @return the towerList
	 */
	public ArrayList<Tower> getTowerList() {
		return towerList;
	}


	/**
	 * @param towerList the towerList to set
	 */
	public void setTowerList(ArrayList<Tower> towerList) {
		this.towerList = towerList;
	}
}
