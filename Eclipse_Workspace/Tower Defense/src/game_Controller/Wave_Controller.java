package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import objects.Enemy;
import utility.WaveGenerator;

/**
 * Controls the wave generation.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class Wave_Controller {

	private WaveGenerator wG;

	private Grid grid;

	private ArrayList<Enemy> currentWave;
	@SuppressWarnings("unused")
	private int currentWaveIndex;

	/**
	 * Constructs a Wave_Controller and initializes the WaveGenerator which can
	 * generate continuous Waves.
	 * 
	 * @param generatedEnemyList
	 *            The generatedEnemyList
	 */
	public Wave_Controller(ArrayList<Enemy> generatedEnemyList) {
		wG = new WaveGenerator();
	}

	/**
	 * Calling the generateWave Method of the Wave_Generator
	 * 
	 * @return (ArrayList<Enemy>) generatedEnemies
	 */
	public ArrayList<Enemy> generateWave() {
		return wG.generateWave();
	}

	/**
	 * Calling the setNumbers Method of the Wave_Generator.
	 */
	public void setNumbers() {
		wG.setNumbers();
	}

	/**
	 * Calling the setNextWave Method of the Wave_Generator.
	 */
	public void setNextWave() {
		wG.generateWave();
	}

	/**
	 * 
	 * @return the currentWave
	 */
	public ArrayList<Enemy> getCurrentWave() {
		return currentWave;
	}

	/**
	 * 
	 * @param currentWave
	 *            The currentWave ArrayList<Eneme>
	 */
	public void setCurrentWave(ArrayList<Enemy> currentWave) {
		this.currentWave = currentWave;
	}

	/**
	 * @return the currentWaveIndex
	 */
	public int getCurrentWaveIndex() {
		return wG.getCurrentWaveIndex();
	}

	/**
	 * @param currentWaveIndex
	 *            the currentWaveIndex to set
	 */
	public void setCurrentWaveIndex(int currentWaveIndex) {
		this.currentWaveIndex = currentWaveIndex;
	}

	/**
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * @param grid
	 *            the grid to set
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
		this.wG.setGrid(grid);
	}
}
