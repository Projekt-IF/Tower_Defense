package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import envoirement.Tile;
import network.Protocol;
import network.Server_TD;
import objects.Enemy;
import objects.Player;
import objects.Tower;

/**
 * The Game_Controller manages the game for one player.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class Game_Controller {

	private Server_TD server;

	private Player player;

	private GameFrameWork gameFrameWork;

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

	private boolean loopStopped;

	/**
	 * Constructs a Game_Controller with a player and all other Controllers.
	 * 
	 * @param pServer
	 *            The designated server.
	 * @param gFW
	 *            The gameFrameWork.
	 */
	public Game_Controller(Server_TD pServer, GameFrameWork gFW) {
		this.server = pServer;
		this.setGameFrameWork(gFW);
		this.currentLevel = "";
		this.enemyList = new ArrayList<Enemy>();
		this.generatedEnemyList = new ArrayList<Enemy>();
		this.towerList = new ArrayList<Tower>();
		this.lC = new Level_Controller(this.currentLevel/* , this */);
		this.wC = new Wave_Controller(this.generatedEnemyList/* this */);
		this.eC = new Enemy_Controller(this.server, this.enemyList, this.generatedEnemyList, this.player, this);
		this.tC = new Tower_Controller(this.server, this.towerList, this);
		this.loopStopped = false;
	}

	/**
	 * Changes the grid.
	 */
	public void iniciateLevel() {
		changeLevel(currentLevel);
		updateGame();
	}

	/**
	 * Creates the next Wave.
	 */
	public void createWave() {
		addEnemies(wC.generateWave());
		eC.printEnemyLists();
		wC.setNumbers();
		this.currentWaveIndex = wC.getCurrentWaveIndex();
	}

	/**
	 * Starts the in-game loop where the enemies move and the towers shoot without
	 * player interaction.
	 */
	public void startLoop() {
		loopStopped = false;
		while (!loopStopped) {
			if (!getEnemyController().getGeneratedEnemyList().isEmpty()) {
				spawnEnemy();
			}
			if ((!getEnemyList().isEmpty() || !getGeneratedEnemyList().isEmpty()) && !loopStopped) {
				getTowerController().checkTowers();
				getEnemyController().checkMoveEnemies();
			} else {
				loopStopped = true;
				player.setRoundOver(true);
				player.setPlayerMoney(player.getPlayerMoney() + (currentWaveIndex * 20));
				this.server.send(player.getPlayerIP(), player.getPlayerPort(),
						Protocol.SC_UPDATE_PLAYER_MONEY + Protocol.SEPARATOR + player.getPlayerMoney());
				this.server.send(player.getPlayerIP(), player.getPlayerPort(), Protocol.SC_ROUND_OVER);
			}
		}
	}

	/**
	 * Spawning an enemy from the generatedEnemyList
	 */
	public void spawnEnemy() {
		if (!eC.isOnSpawnCooldown()) {
			eC.spawnEnemy();
			eC.printEnemyLists();
		}
	}

	/**
	 * Changes the level and grid.
	 * 
	 * @param pLevelName
	 *            The new level's name.
	 */
	public void changeLevel(String pLevelName) {
		setGlobalGrid(this.lC.changeLevel(pLevelName));
		this.spawnerTile = getGlobalGrid().getSpawnerTile();
		this.baseTile = getGlobalGrid().getBaseTile();
		updateGame();
	}

	/**
	 * Removing the given enemy from the enemyList.
	 * 
	 * @param pKilledEnemy
	 *            The enemy to be removed.
	 */
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

	/**
	 * Adding the boughtTowers List to the purchasedTowerList.
	 * 
	 * @param boughtTowers
	 *            The Towers to be added.
	 */
	public void addPurchasedTowers(ArrayList<Tower> boughtTowers) {
		for (int i = 0; i < boughtTowers.size(); i++) {
			addPurchasedTower(boughtTowers.get(i).getPosX(), boughtTowers.get(i).getPosY(),
					boughtTowers.get(i).getType());
		}
		boughtTowers.clear();
	}

	/**
	 * Adding the tower to the towerList.
	 * 
	 * @param pPosX
	 *            The x coordinate.
	 * @param pPosY
	 *            The y coordinate.
	 * @param pType
	 *            The tower's type.
	 */
	public void addPurchasedTower(int pPosX, int pPosY, int pType) {
		getTowerList().add(getTowerController().createTower(pPosX, pPosY, pType));
	}

	/**
	 * Adding the pAddedEnemies List to the generatedEnemyList.
	 * 
	 * @param pAddedEnemies
	 *            The enemies to be added.
	 */
	public void addEnemies(ArrayList<Enemy> pAddedEnemies) {
		for (int i = 0; i < pAddedEnemies.size(); i++) {
			getGeneratedEnemyList().add(pAddedEnemies.get(i));
		}
		eC.printEnemyLists();
		wC.setNumbers();
		pAddedEnemies.clear();
	}

	/**
	 * Updating everything game important.
	 */
	public void updateGame() {
		this.setCurrentLevel(this.getCurrentLevel());
		this.setGlobalGrid(this.getGlobalGrid());
		this.setSpawnerTile(this.getSpawnerTile());
		this.setBaseTile(this.getBaseTile());
		this.setEnemyList(this.getEnemyList());
		this.setGeneratedEnemyList(this.getGeneratedEnemyList());
		this.setTowerList(this.getTowerList());
	}

	/**
	 * 
	 * @return The enemyList.
	 */
	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	/**
	 * Sets the enemyLIst for the Game/Enemy Controller.
	 * 
	 * @param enemyList
	 *            The enemyList.
	 */
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
	 * Sets the gris for the Enemy/Tower/Wave/Game Controller
	 * 
	 * @param globalGrid
	 *            the globalGrid to set
	 */
	public void setGlobalGrid(Grid globalGrid) {
		eC.setGrid(globalGrid);
		tC.setGrid(globalGrid);
		wC.setGrid(globalGrid);
		this.globalGrid = globalGrid;
	}

	/**
	 * 
	 * @return The Game_Controller's currentLevel.
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * Sets the currentLevel for the Game_Controller and the Level_Controller.
	 * 
	 * @param currentLevel
	 *            The Game_Controller's currentLevel.
	 */
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

	/**
	 * 
	 * @return The Enemy_Controller
	 */
	public Enemy_Controller getEnemyController() {
		return eC;
	}

	/**
	 * 
	 * @param eC
	 *            The Enemy_Controller
	 */
	public void setEnemyController(Enemy_Controller eC) {
		this.eC = eC;
	}

	/**
	 * 
	 * @return The Wave_Controller
	 */
	public Wave_Controller getWaveController() {
		return wC;
	}

	/**
	 * 
	 * @param wC
	 *            The Wave_Controller
	 */
	public void setWaveController(Wave_Controller wC) {
		this.wC = wC;
	}

	/**
	 * 
	 * @return The Level_Controller
	 */
	public Level_Controller getLevelController() {
		return lC;
	}

	/**
	 * 
	 * @param lC
	 *            The Level_Controller
	 */
	public void setLevelController(Level_Controller lC) {
		this.lC = lC;
	}

	/**
	 * 
	 * @return tower_Controller
	 */
	public Tower_Controller getTowerController() {
		return tC;
	}

	/**
	 * 
	 * @param tC
	 *            The Tower_Controller
	 */
	public void setTowerController(Tower_Controller tC) {
		this.tC = tC;
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
		this.eC.setPlayer(player);
		this.tC.setPlayer(player);
	}

	public boolean isLoopStopped() {
		return loopStopped;
	}

	public void setLoopStopped(boolean loopStopped) {
		this.loopStopped = loopStopped;
	}

	/**
	 * @return the gameFrameWork
	 */
	public GameFrameWork getGameFrameWork() {
		return gameFrameWork;
	}

	/**
	 * @param gameFrameWork
	 *            the gameFrameWork to set
	 */
	public void setGameFrameWork(GameFrameWork gameFrameWork) {
		this.gameFrameWork = gameFrameWork;
	}
}
