package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import envoirement.Tile;
import objects.Enemy;
import objects.Tower;
import utility.Wave;

public class Game_Controller {

	private String currentLevel;

	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	
	private ArrayList<Tower> towerList = new ArrayList<Tower>();

	private ArrayList<Wave> waveList = new ArrayList<Wave>();
	
	private Enemy_Controller eC;

	private Wave_Controller wC;

	private Level_Controller lC;
	
	private Tower_Controller tC;
	
	private Grid globalGrid;
	
	private Tile spawnerTile;
	
	private Tile baseTile;

	public Game_Controller(Wave_Controller pWC, Enemy_Controller pEC, Level_Controller pLC, Tower_Controller pTC) {
		this.wC = pWC;
		this.eC = pEC;
		this.lC = pLC;
		this.tC = pTC;
		iniciateGame();
	}

	/**
	 * 
	 */
	public void iniciateGame() {
		//TODO: These lines have to be deleted and instead the globalGrid has to be set while initialising the changeLevel
		currentLevel = "One";
		Grid grid = changeLevel(currentLevel);
		grid.printGrid();
		this.setGlobalGrid(grid);
	}

	public void createWave(int pNumber, int pType, int pLevel) {
		this.enemyList.removeAll(enemyList);
		for (int i = 0; i <= pNumber; i++) {
			Enemy e = eC.spawnEnemy(pType, pLevel);
			System.out.println(e.getPosX() + " " + e.getPosY());
			this.enemyList.add(e);
		}
	}

	public Grid changeLevel(String pLevelName) {
		this.currentLevel = pLevelName;
		return lC.changeLevel(pLevelName);
	}

	public void removeEnemyFromList(Enemy pKilledEnemy) {
		if (!enemyList.isEmpty()) {
			for (int i = 0; i < enemyList.size(); i++) {
				if (enemyList.get(i).equals(pKilledEnemy)) {
					enemyList.remove(pKilledEnemy);
				}
			}
		}
	}

	public void checkEnemiesLife() {
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).getLife() <= 0) {
				removeEnemyFromList(enemyList.get(i));
			}
		}
	}
	
	public void updateGame() {
		//TODO: Updates The game
		this.setCurrentLevel(this.getCurrentLevel());
		this.setGlobalGrid(this.getGlobalGrid());
		this.setSpawnerTile(this.getSpawnerTile());
		this.setBaseTile(this.getBaseTile());
		this.setWaveList(this.getWaveList());
		this.setEnemyList(this.getEnemyList());
		this.setTowerList(this.getTowerList());
	}

	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		eC.setEnemyList(enemyList);
		this.enemyList = enemyList;
	}

	/**
	 * @return the globalGrid
	 */
	public Grid getGlobalGrid() {
		return globalGrid;
	}

	/**
	 * @param globalGrid the globalGrid to set
	 */
	public void setGlobalGrid(Grid globalGrid) {
		eC.setGrid(globalGrid);
		tC.setGrid(globalGrid);
		this.globalGrid = globalGrid;
	}

	public String getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(String currentLevel) {
		lC.setCurrentLevel(currentLevel);
		this.currentLevel = currentLevel;
	}

	/**
	 * @return the spawnerTile
	 */
	public Tile getSpawnerTile() {
		return spawnerTile;
	}

	/**
	 * @param spawnerTile the spawnerTile to set
	 */
	public void setSpawnerTile(Tile spawnerTile) {
		eC.setSpawnerTile(spawnerTile);
		this.spawnerTile = spawnerTile;
	}

	/**
	 * @return the baseTile
	 */
	public Tile getBaseTile() {
		return baseTile;
	}

	/**
	 * @param baseTile the baseTile to set
	 */
	public void setBaseTile(Tile baseTile) {
		this.baseTile = baseTile;
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
		tC.setTowerList(towerList);
		this.towerList = towerList;
	}

	/**
	 * @return the waveList
	 */
	public ArrayList<Wave> getWaveList() {
		return waveList;
	}

	/**
	 * @param waveList the waveList to set
	 */
	public void setWaveList(ArrayList<Wave> waveList) {
		wC.setWaveList(waveList);
		this.waveList = waveList;
	}

	public static void main(String args[]) {
		Wave_Controller wC = new Wave_Controller();
		Enemy_Controller eC = new Enemy_Controller();
		Level_Controller lC = new Level_Controller();
		Tower_Controller tC = new Tower_Controller();
		Game_Controller gC = new Game_Controller(wC, eC, lC, tC);
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		String[] waves = new String[3];
		waves[0] = "src/Waves/Wave_1.txt";
		waves[1] = "src/Waves/Wave_Test.txt";
		waves[2] = "src/Waves/Wave_2.txt";
		for (int a = 0; a < waves.length; a++) {
			tC.setTower( 0, 1, 1, 5, 5, 5);;
			eC.getGrid().printGrid();
			System.out.println("Changed Level to: " + gC.currentLevel);
			wC.loadWaves(waves[a]);
			wC.readWaves();
			while (!wC.getWaveList().isEmpty()) {
				gC.createWave(wC.getCurrentWave().getEnemyNumber() - 1, wC.getCurrentWave().getEnemyType(),
						wC.getCurrentWave().getEnemyLevel());
				enemyList = gC.getEnemyList();
				for (int o = 0; o < enemyList.size(); o++) {
					Enemy current = enemyList.get(o);
					System.out.println("test " + enemyList.size() + " " + eC.isMovable(current) + " " + o);
					if (eC.isMovable(current)) {
						System.out.println("Num: " + enemyList.indexOf(current) + " PosX: " + current.getPosX()
								+ " ; PosY: " + current.getPosY() + " Life: " + current.getLife());
						if(current.getLife() >= 2) {
							for(int h = 0; h <=gC.getGlobalGrid().getGridLayer().length; h++) {
								if(true) {
									
								}
//								c
							}
//							current.setLife(current.getLife() - 1);
						}
						gC.checkEnemiesLife();
						eC.moveEnemy(current);
					} else {
						System.out.println("Enemy " + enemyList.size() + " reached the Base!" + "  On Map: "
								+ waves[a].toString());
						enemyList.remove(current);
					}
				}
				while (!enemyList.isEmpty()) {
					for (int o = 0; o < enemyList.size(); o++) {
						Enemy current = enemyList.get(o);
						System.out.println("move " + enemyList.size() + " " + eC.isMovable(current) + " " + o);
						if (eC.isMovable(current)) {
							System.out.println("Num: " + enemyList.indexOf(current) + " PosX: " + current.getPosX()
									+ " ; PosY: " + current.getPosY() + " Life: " + current.getLife());
							if(current.getLife() >= 2) {
//								current.setLife(current.getLife() - 1);
							}
							gC.checkEnemiesLife();
							eC.moveEnemy(current);
						} else {
							System.out.println("Enemy " + enemyList.size() + " reached the Base!" + "  On Map: "
									+ waves[a].toString());
							enemyList.remove(current);
						}
					}
				}
				if (!wC.getWaveList().isEmpty()) {
					wC.setNextWave();
				}
			}
			if (waves[a].toString().contains("1")) {
				eC.setGrid(gC.changeLevel("test"));
				tC.setGrid(gC.changeLevel("test"));
				eC.getGrid().printGrid();
			} else if (waves[a].toString().contains("Test")) {
				eC.setGrid(gC.changeLevel("two"));
				tC.setGrid(gC.changeLevel("two"));
				eC.getGrid().printGrid();
			}
		}
	}
}
