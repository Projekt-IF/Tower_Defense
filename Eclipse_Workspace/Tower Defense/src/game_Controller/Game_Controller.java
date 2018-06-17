package game_Controller;

import java.util.ArrayList;
import java.util.Timer;

import envoirement.Grid;
import envoirement.Tile;
import network.Protocol;
import network.Server_TD;
import objects.Enemy;
import objects.Player;
import objects.Tower;

public class Game_Controller {

	private Server_TD server;

	private Player player;

	private Timer timer;

	private String currentLevel;

	private int currentWaveIndex;

	private ArrayList<Enemy> enemyList;
	private ArrayList<Enemy> generatedEnemyList;
	private ArrayList<Tower> towerList;

	private Enemy_Controller eC;
	private Wave_Controller wC;
	private Level_Controller lC;
	private Tower_Controller tC;

	private Grid globalGrid;

	private Tile spawnerTile;

	private Tile baseTile;

	public Game_Controller(Server_TD pServer) {
		this.server = pServer;
		this.currentLevel = "";
		this.enemyList = new ArrayList<Enemy>();
		this.generatedEnemyList = new ArrayList<Enemy>();
		this.towerList = new ArrayList<Tower>();
		this.lC = new Level_Controller(this.currentLevel/* , this */);
		this.wC = new Wave_Controller(this.generatedEnemyList/* this */);
		this.eC = new Enemy_Controller(this.server, this.enemyList, this.generatedEnemyList, this.player/* , this */);
		this.tC = new Tower_Controller(this.towerList, this);
		this.timer = new Timer();
	}

	/**
	 * 
	 */
	public void iniciateLevel() {
		System.out.println(currentLevel);
		changeLevel(currentLevel);
		updateGame();
	}

	public void createWave() {
		this.getGeneratedEnemyList().clear();
		this.setGeneratedEnemyList(wC.generateWave());
		this.currentWaveIndex = wC.getCurrentWaveIndex();
	}

	public void spawnEnemy() {
		if (!eC.isOnSpawnCooldown()) {
			eC.spawnEnemy();
			eC.printEnemyLists();
		}
	}

	public void changeLevel(String pLevelName) {
		System.out.println(pLevelName);
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
				this.server.send(player.getPlayerIP(), player.getPlayerPort(),
						Protocol.SC_UPDATE_POSITION_ENEMY + Protocol.SEPARATOR + enemyList.get(i).getPosX()
								+ Protocol.SEPARATOR + enemyList.get(i).getPosY() + Protocol.SEPARATOR + null
								+ Protocol.SEPARATOR + null);
			}
		}
	}

	public void addPurchasedTowers(ArrayList<Tower> boughtTowers) {
		for (int i = 0; i < boughtTowers.size(); i++) {
			addPurchasedTower(boughtTowers.get(i).getPosX(), boughtTowers.get(i).getPosY(),
					boughtTowers.get(i).getType());
		}
		boughtTowers.clear();
	}

	public void addPurchasedTower(int pPosX, int pPosY, int pType) {
		getTowerList().add(getTowerController().createTower(pPosX, pPosY, pType));
	}

	public void addPurchasedEnemies(ArrayList<Enemy> pAddedEnemies) {
		for (int i = 0; i < pAddedEnemies.size(); i++) {
			getGeneratedEnemyList().add(pAddedEnemies.get(i));
		}
		eC.printEnemyLists();
		wC.setNumbers();
		pAddedEnemies.clear();
	}

	public void startLoop() {
		//TODO:LOOP IT!!
		while (true) {
			if (getEnemyController().getGeneratedEnemyList().isEmpty()) {
				spawnEnemy();
			}
			if (!getEnemyList().isEmpty()) {
				getTowerController().checkTowers();
				getEnemyController().checkMoveEnemies();
				checkEnemiesLife();
			}
		}
	}

	public void updateGame() {
		this.setCurrentLevel(this.getCurrentLevel());
		this.setGlobalGrid(this.getGlobalGrid());
		this.setSpawnerTile(this.getSpawnerTile());
		this.setBaseTile(this.getBaseTile());
		this.setEnemyList(this.getEnemyList());
		this.setGeneratedEnemyList(this.getGeneratedEnemyList());
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
		wC.setGrid(globalGrid);
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
	 * @return the currentWaveIndex
	 */
	public int getCurrentWaveIndex() {
		return currentWaveIndex;
	}

	/**
	 * @param currentWaveIndex
	 *            the currentWaveIndex to set
	 */
	public void setCurrentWaveIndex(int currentWaveIndex) {
		this.currentWaveIndex = currentWaveIndex;
	}

	/**
	 * @return the generatedEnemyList
	 */
	public ArrayList<Enemy> getGeneratedEnemyList() {
		return generatedEnemyList;
	}

	/**
	 * @param generatedEnemyList
	 *            the generatedEnemyList to set
	 */
	public void setGeneratedEnemyList(ArrayList<Enemy> generatedEnemyList) {
		eC.setGeneratedEnemyList(generatedEnemyList);
		this.generatedEnemyList = generatedEnemyList;
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

	public void test(Server_TD pServer, Player pPlayer) {
		Game_Controller gC = new Game_Controller(pServer);
		String[] waves = new String[3];
		waves[0] = "src/Waves/Wave_Test.txt";
		waves[1] = "src/Waves/Wave_One.txt";
		waves[2] = "src/Waves/Wave_Two.txt";
		// Setting the Grid
		gC.iniciateLevel();
		for (int a = 0; a < waves.length; a++) {
			// Setting up tower
			// System.out.println();
			// System.out.println();
			// System.out.println();
			// System.out.println();
			// Loading Waves
			// System.out.println("TEst2");
			// System.out.println(
			// "The Length " + gC.getWaveList().size() + " " +
			// gC.getWaveController().getWaveList().size());
			// gC.getWaveController().loadWaves(waves[a]);
			// System.out.println(
			// "The Length " + gC.getWaveList().size() + " " +
			// gC.getWaveController().getWaveList().size());
			// System.out.println("TEst3");
			// Go Through Wave List
			// while (!gC.getWaveController().getWaveList().isEmpty()) {
			// gC.setCurrentWave(gC.getCurrentWave() + 1);
			// Create Wave
			// gC.createWave(gC.getWaveController().getCurrentWave().getEnemyNumber(),
			// gC.getWaveController().getCurrentWave().getEnemyType(),
			// gC.getWaveController().getCurrentWave().getEnemyLevel());
			gC.spawnEnemy();
			gC.checkEnemiesLife();
			while (!gC.getEnemyList().isEmpty()) {
				// System.out.println(enemyList.get(enemyList.size()-1));
				// If Tower in Range
				// Check Towers
				gC.getTowerController().checkTowers();
				// Move The Enemies
				gC.getEnemyController().checkMoveEnemies();
				gC.checkEnemiesLife();
			}
			gC.getWaveController().setNextWave();
			// System.out.println(gC.getWaveController().getWaveList().size());
		}
		// System.out.println("Elle Weg");
		gC.getWaveController().setNextWave();
		// if (waves[a].toString().contains("Test")) {
		gC.changeLevel("one");
		// } else if (waves[a].toString().contains("One")) {
		gC.changeLevel("two");
	}
	// }

	public static void main(String[] args) {
		Game_Controller gC = new Game_Controller(new Server_TD(15679));
		gC.iniciateLevel();
		gC.createWave();
		ArrayList<Enemy> eL = new ArrayList<Enemy>();
		for (int i = 0; i < 5; i++) {
			Enemy e = new Enemy(null, null, 3);
			eL.add(e);
		}
		gC.addPurchasedEnemies(eL);
		// gC.spawnEnemy();
		// gC.spawnEnemy();
		// gC.spawnEnemy();
		// gC.spawnEnemy();
		// while (true) {
		// if (!gC.getEnemyController().getGeneratedEnemyList().isEmpty()) {
		// gC.spawnEnemy();
		// }
		// }
		while (true) {
			if (!gC.getEnemyController().getGeneratedEnemyList().isEmpty()) {
				gC.spawnEnemy();
			}
			if (!gC.getEnemyList().isEmpty()) {
				gC.getTowerController().checkTowers();
				gC.getEnemyController().checkMoveEnemies();
				gC.checkEnemiesLife();
			}
		}
	}
}
