package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import envoirement.Tile;
import objects.Enemy;
import objects.Tower;

public class Tower_Controller {
	
	private Grid grid;
	
	private ArrayList<Tower> towerList = new ArrayList<Tower>();
	
	public Tower_Controller(ArrayList<Tower> pTowerList) {
//		TODO:
		this.towerList = pTowerList;
	}
	
	
	public void setTowerPosition(int pPosX, int pPosY,Tower pTower) {
//		TODO: Testing if the tower is placed on a free Tile
		grid.getGridLayer()[pTower.getPosY()][pTower.getPosX()].setType(Tile.TYPE_UNOC);
		grid.getGridLayer()[pPosY][pPosX].setType(Tile.TYPE_TOWER);
		pTower.setPosX(pPosX);
		pTower.setPosY(pPosY);
	}
	
	
	public void removeTower(int PPosX, int PPosY) {
//		TODO:
	}
	
	
	public Tower createTower(int pPosX, int pPosY, int pRange, int pStrength, double pSpeed, double pVelocity) {
//		TODO:
		grid.getGridLayer()[pPosY][pPosX].setType(Tile.TYPE_TOWER);
		return new Tower(pPosX, pPosY, pRange, pStrength, pSpeed, pVelocity);
	}
	
	
	public void shootTower() {
//		TODO:
	}
	
	
	public Boolean checkTowers(Enemy pEnemy) {
//		TODO:
		for(int a = 0; a < towerList.size(); a++) {
			//Check Tiles In Range if Enemy on them
			Tower current = towerList.get(a);
			System.out.println(towerList.size() + "  " + a);
			for(int y = -current.getRange(); y <= current.getRange(); y++) {
				for(int x = -current.getRange(); x <= current.getRange(); x++) {
					int currentY = y + current.getPosY();
					int currentX = x + current.getPosX();
					if((currentY >= 0)&&(currentY < grid.getLength())) {
						if((currentX >= 0)&&(currentX < grid.getLength())) {
//							System.out.println(current + "Test  Y: " + (y + current.getPosY()) + " X: " + (x + current.getPosX()));  
							if((pEnemy.getPosY() == currentY)&&(pEnemy.getPosX() == currentX)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public void checkRange() {
//		TODO:
		
	}
	
	
	public void upgradeTower() {
//		TODO:
	}
	
	public void clearTowerList() {
		towerList.clear();
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
