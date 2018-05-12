package game_Controller;

import java.util.ArrayList;
import java.util.Timer;

import envoirement.Grid;
import envoirement.Tile;
import objects.Enemy;
import objects.Player;
import objects.Tower;
import utility.Wave;

public class Game_Controller {

	private Player player;

	private Timer timer;

	private String currentLevel;
	private int currentWave;

	private ArrayList<Enemy> enemyList;
	private ArrayList<Tower> towerList;
	private ArrayList<Wave> waveList;

	private Enemy_Controller eC;
	private Wave_Controller wC;
	private Level_Controller lC;
	private Tower_Controller tC;

	private Grid globalGrid;

	private Tile spawnerTile;

	private Tile baseTile;

	// public Game_Controller(Wave_Controller pWC, Enemy_Controller pEC,
	// Level_Controller pLC, Tower_Controller pTC) {
	public Game_Controller(Player pPlayer) {
		// this.wC = pWC;
		// this.eC = pEC;
		// this.lC = pLC;
		// this.tC = pTC;
		this.player = pPlayer;
		this.currentLevel = "";
		this.enemyList = new ArrayList<Enemy>();
		this.towerList = new ArrayList<Tower>();
		this.waveList = new ArrayList<Wave>();
		this.lC = new Level_Controller(this.currentLevel/* , this */);
		this.wC = new Wave_Controller(this.waveList/* , this */);
		this.eC = new Enemy_Controller(this.enemyList/* , this */);
		this.tC = new Tower_Controller(this.towerList, this);
		this.timer = new Timer();
		// iniciateGame();
	}

	/**
	 * 
	 */
	public void iniciateGame() {
		// TODO: These lines have to be deleted and instead the globalGrid has to be set
		// while initialising the changeLevel
		currentLevel = "test";
		changeLevel(currentLevel);
		// getGlobalGrid().printGrid();
		updateGame();
	}

	public void createWave(int pNumber, int pType, int pLevel) {
		this.enemyList.clear();
		for (int i = 0; i < pNumber; i++) {
			System.out.println(i);
			Enemy e = eC.spawnEnemy(pType, pLevel);
			System.out.println(e.getPosX() + " " + e.getPosY());
			this.enemyList.add(e);
			System.out.println(enemyList.size());
		}
		// updateGame();
	}

	public void changeLevel(String pLevelName) {
		this.currentLevel = pLevelName;
		setGlobalGrid(this.lC.changeLevel(pLevelName));
		this.spawnerTile = getGlobalGrid().getSpawnerTile();
		this.baseTile = getGlobalGrid().getBaseTile();
		updateGame();
	}

	public void removeEnemyFromList(Enemy pKilledEnemy) {
		if (!enemyList.isEmpty()) {
			for (int i = 0; i < enemyList.size(); i++) {
				if (enemyList.get(i).equals(pKilledEnemy)) {
					enemyList.remove(pKilledEnemy);
				}
			}
		}
		updateGame();
	}

	public void checkEnemiesLife() {
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).getLife() <= 0) {
				removeEnemyFromList(enemyList.get(i));
			}
		}
	}

	public void updateGame() {
		// TODO: Updates The game
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
	 * @param globalGrid
	 *            the globalGrid to set
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
	 * @param spawnerTile
	 *            the spawnerTile to set
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
	 * @param baseTile
	 *            the baseTile to set
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
	 * @param towerList
	 *            the towerList to set
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
	 * @param waveList
	 *            the waveList to set
	 */
	public void setWaveList(ArrayList<Wave> waveList) {
		wC.setWaveList(waveList);
		this.waveList = waveList;
	}

	public Enemy_Controller getEnemyController() {
		return eC;
	}

	public void setEnemyController(Enemy_Controller eC) {
		this.eC = eC;
	}

	public Wave_Controller getWaveController() {
		return wC;
	}

	public void setWaveController(Wave_Controller wC) {
		this.wC = wC;
	}

	public Level_Controller getLevelController() {
		return lC;
	}

	public void setLevelController(Level_Controller lC) {
		this.lC = lC;
	}

	public Tower_Controller getTowerController() {
		return tC;
	}

	public void setTowerController(Tower_Controller tC) {
		this.tC = tC;
	}

	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer
	 *            the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	/**
	 * @return the currentWave
	 */
	public int getCurrentWave() {
		return currentWave;
	}

	/**
	 * @param currentWave
	 *            the currentWave to set
	 */
	public void setCurrentWave(int currentWave) {
		this.currentWave = currentWave;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void addPurchasedTower(int pPosX, int pPosY, int pType) {
		getTowerList().add(getTowerController().createTower(pPosX, pPosY, pType));
	}

	public void addPurchasedEnemies(ArrayList<Enemy> pAddedEnemies) {
		for (int i = 0; i < pAddedEnemies.size(); i++) {
			getEnemyList().add(pAddedEnemies.get(i));
		}
		pAddedEnemies.clear();
	}

	public void test(Player pPlayer) {
		Game_Controller gC = new Game_Controller(pPlayer);
		String[] waves = new String[3];
		waves[0] = "src/Waves/Wave_Test.txt";
		waves[1] = "src/Waves/Wave_One.txt";
		waves[2] = "src/Waves/Wave_Two.txt";
		// Setting the Grid
		gC.iniciateGame();
		for (int a = 0; a < waves.length; a++) {
			// Setting up tower
			// System.out.println();
			// System.out.println();
			// System.out.println();
			// System.out.println();
			System.out.println("Welle " + waves[a] + " Level: " + gC.getCurrentLevel());
			gC.getTowerController().clearTowerList();
			gC.getTowerList().add(gC.getTowerController().createTower(2, 1, 2));
			// Loading Waves
			// System.out.println("TEst2");
			// System.out.println(
			// "The Length " + gC.getWaveList().size() + " " +
			// gC.getWaveController().getWaveList().size());
			gC.getWaveController().loadWaves(waves[a]);
			// System.out.println(
			// "The Length " + gC.getWaveList().size() + " " +
			// gC.getWaveController().getWaveList().size());
			// System.out.println("TEst3");
			// Go Through Wave List
			while (!gC.getWaveController().getWaveList().isEmpty()) {
				gC.setCurrentWave(gC.getCurrentWave() + 1);
				// Create Wave
				gC.createWave(gC.getWaveController().getCurrentWave().getEnemyNumber(),
						gC.getWaveController().getCurrentWave().getEnemyType(),
						gC.getWaveController().getCurrentWave().getEnemyLevel());
				gC.checkEnemiesLife();
				while (!gC.getEnemyList().isEmpty()) {
					// System.out.println(enemyList.get(enemyList.size()-1));
					// If Tower in Range
					// TODO: Gegebenen Falls kann im überprüfen auf die Range der enemy gedamaged
					// werden
					// Check Towers
					gC.getTowerController().checkTowers();
					// Move The Enemies
					gC.getEnemyController().checkMoveEnemies();
					gC.checkEnemiesLife();
				}
				gC.getWaveController().setNextWave();
				System.out.println(gC.getWaveController().getWaveList().size());
			}
			// System.out.println("Elle Weg");
			gC.getWaveController().setNextWave();
			if (waves[a].toString().contains("Test")) {
				gC.changeLevel("one");
			} else if (waves[a].toString().contains("One")) {
				gC.changeLevel("two");
			}
		}
	}
}
// System.out.println("End");
