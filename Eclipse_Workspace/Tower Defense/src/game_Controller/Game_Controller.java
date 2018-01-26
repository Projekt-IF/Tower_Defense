package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import objects.Enemy;

public class Game_Controller {

	private String currentLevel;

	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

	private Enemy_Controller eC;

	private Wave_Controller wC;

	public Game_Controller(Wave_Controller wC, Enemy_Controller eC) {
		this.wC = wC;
		this.eC = eC;
		iniciateGame();
	}

	/**
	 * 
	 */
	public void iniciateGame() {
		currentLevel = "One";
		Grid grid = new Grid("One");
		eC.setGrid(grid);
		eC.setSpawnerTile(grid.getSpawnerTile());
	}

	public void createWave(int pNumber, int pType, int pLevel) {
		for (int i = 0; i <= pNumber; i++) {
			Enemy e = new Enemy(eC.getSpawnerTile().getxPos(), eC.getSpawnerTile().getyPos(), pType, pLevel);
			this.enemyList.add(e);
		}
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
		Game_Controller gC = new Game_Controller(wC, eC);
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
