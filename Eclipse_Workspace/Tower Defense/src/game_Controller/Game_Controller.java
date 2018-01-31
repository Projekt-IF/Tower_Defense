package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import objects.Enemy;

public class Game_Controller {

	private String currentLevel;

	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

	private Enemy_Controller eC;

	private Wave_Controller wC;
	
	private Level_Controller lC;

	public Game_Controller(Wave_Controller pWC, Enemy_Controller pEC, Level_Controller pLC) {
		this.wC = pWC;
		this.eC = pEC;
		this.lC = pLC;
		iniciateGame();
	}

	/**
	 * 
	 */
	public void iniciateGame() {
		currentLevel = "One";
		Grid grid = lC.changeLevel(currentLevel);
		eC.setGrid(grid);
		eC.setSpawnerTile(grid.getSpawnerTile());
	}

	public void createWave(int pNumber, int pType, int pLevel) {
		for (int i = 0; i <= pNumber; i++) {
			Enemy e = new Enemy(eC.getSpawnerTile().getxPos(), eC.getSpawnerTile().getyPos(), pType, pLevel);
			this.enemyList.add(e);
		}
	}
	
	public Grid changeLevel(String pLevelName) {
		return lC.changeLevel(pLevelName);
	}
	
	public Boolean isEnemyListEmpty() {
		if(enemyList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removeEnemyFromList(Enemy pKilledEnemy) {
		if(!enemyList.isEmpty()) {
			for (int i = 0;i < enemyList.size();i++) {
				if(enemyList.get(i).equals(pKilledEnemy)) {
					enemyList.remove(pKilledEnemy);
				}
			}
		}
	}
	
	public void checkEnemiesLife() {
		for (int i = 0;i < enemyList.size();i++) {
			if(enemyList.get(i).getLife() <= 0) {
				removeEnemyFromList(enemyList.get(i));
			}
		}
	}
	
	public Boolean isWaveListEmpty() {
		return wC.getWaveList().isEmpty();
	}

	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public static void main(String args[]) {
		Wave_Controller wC = new Wave_Controller();
		Enemy_Controller eC = new Enemy_Controller();
		Level_Controller lC = new Level_Controller();
		Game_Controller gC = new Game_Controller(wC, eC, lC);
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		wC.loadWaves("src/Waves/Wave_1.txt");
		while(!wC.getWaveList().isEmpty()) {
			gC.createWave(wC.getCurrentWave().getEnemyNumber()-1, wC.getCurrentWave().getEnemyType(),wC.getCurrentWave().getEnemyLevel());
			enemyList = gC.getEnemyList();
			for (int o = 0; o < enemyList.size(); o++) {
				Enemy current = enemyList.get(o);
				if (eC.isMovable(current)) {
//					System.out.println("Num: " + enemyList.indexOf(current) + " PosX: " + current.getPosX() + " ; PosY: "+ current.getPosY());
					eC.moveEnemy(current);
				} else {
					System.out.println("Enemy " + enemyList.size() + " reached the Base!");
					enemyList.remove(current);
				}
			}
			while (!enemyList.isEmpty()) {
				for (int o = 0; o < enemyList.size(); o++) {
					Enemy current = enemyList.get(o);
					if (eC.isMovable(current)) {
//						System.out.println("Num: " + enemyList.indexOf(current) + " PosX: " + current.getPosX()+ " ; PosY: " + current.getPosY());
						eC.moveEnemy(current);
					} else {
						System.out.println("Enemy " + enemyList.size() + " reached the Base!");
						enemyList.remove(current);
					}
				}
			}
			if(!wC.getWaveList().isEmpty()) {
				wC.setNextWave();
			}
		}
	}
}
