package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import objects.Enemy;
import utility.WaveGenerator;

public class Wave_Controller {

	// private Game_Controller game_Controller;

	private WaveGenerator wG;

	private Grid grid;

	private ArrayList<Enemy> currentWave;
	@SuppressWarnings("unused")
	private int currentWaveIndex;

	public Wave_Controller(ArrayList<Enemy> generatedEnemyList/* Game_Controller pGame_Controller */) {
		// this.game_Controller = pGame_Controller;
		wG = new WaveGenerator();
	}

	public ArrayList<Enemy> generateWave() {
		return wG.generateWave();
	}

	public void setNumbers() {
		wG.setNumbers();
	}

	public void setNextWave() {
		wG.generateWave();
	}

	public ArrayList<Enemy> getCurrentWave() {
		return currentWave;
	}

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
