package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import envoirement.Tile;
import objects.Enemy;
import objects.Tower;

public class Tower_Controller {

	private Game_Controller game_Controller;

	private Grid grid;

	private ArrayList<Tower> towerList;

	public Tower_Controller(ArrayList<Tower> pTowerList, Game_Controller pGame_Controller) {
		// TODO:
		this.game_Controller = pGame_Controller;
		this.towerList = pTowerList;
	}

	public void setTowerPosition(int pPosX, int pPosY, Tower pTower) {
		// TODO: Testing if the tower is placed on a free Tile
		grid.getGridLayer()[pTower.getPosY()][pTower.getPosX()].setType(Tile.TYPE_UNOC);
		grid.getGridLayer()[pPosY][pPosX].setType(Tile.TYPE_TOWER);
		pTower.setPosX(pPosX);
		pTower.setPosY(pPosY);
	}

	public void removeTower(int PPosX, int PPosY) {
		// TODO:
	}

	public Tower createTower(int pPosX, int pPosY, int pType) {
		// TODO:
		grid.getGridLayer()[pPosY][pPosX].setType(Tile.TYPE_TOWER);
		return new Tower(pPosX, pPosY, pType);
	}

	public void shootTower() {
		// TODO:
	}

	public void checkTowers() {
		// TODO: Gegebenen Falls kann auch hier schon das Leben abgezogen werden
		// Check Tiles In Range if Enemy on them
		if (!game_Controller.getEnemyController().getEnemyList().isEmpty()) {
			for (int w = 0; w < towerList.size(); w++) {
				Tower current = towerList.get(w);
				if (!current.isOnCooldown()) {
					for (int y = -current.getRange(); y <= current.getRange(); y++) {
						for (int x = -current.getRange(); x <= current.getRange(); x++) {
							int currentY = y + current.getPosY();
							int currentX = x + current.getPosX();
							if ((currentY >= 0) && (currentY < grid.getLength())) {
								if ((currentX >= 0) && (currentX < grid.getLength())) {
									for (int z = 0; z < this.game_Controller.getEnemyList().size(); z++) {
										Enemy tmpEnemy = game_Controller.getEnemyList().get(z);
										if ((tmpEnemy.getPosY() == currentY) && (tmpEnemy.getPosX() == currentX)) {
											tmpEnemy.setLife(tmpEnemy.getLife() - current.shoot());
										}
									}

								}

							}
						}

					}

				}
			}
		}
	}

	public void checkRange() {
		// TODO:

	}

	public void upgradeTower() {
		// TODO:
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
	 * @param towerList
	 *            the towerList to set
	 */
	public void setTowerList(ArrayList<Tower> towerList) {
		this.towerList = towerList;
	}
}
