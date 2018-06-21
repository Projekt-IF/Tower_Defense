package utility;

import java.util.ArrayList;

import envoirement.Grid;
import objects.Enemy;

public class WaveGenerator {

	private Grid grid;

	private ArrayList<Enemy> generatedEnemies;

	private int currentMaxNumber;

	private int currentWaveIndex;

	private int currentType1;
	private int currentType2;
	private int currentType3;

	public WaveGenerator() {
		generatedEnemies = new ArrayList<Enemy>();
		currentMaxNumber = 5;
		currentWaveIndex = 0;
		currentType3 = 0;
		currentType2 = 0;
		currentType1 = 0;
	}

	public ArrayList<Enemy> generateWave() {
		generatedEnemies.clear();
		currentWaveIndex++;
		if (checkMaxNumber()) {
			currentType1++;
		} else {
			currentType1 = 0;
			if (checkMaxNumber()) {
				currentType2++;
			} else {
				currentType2 = 0;
				if (checkMaxNumber()) {
					currentType3++;
				} else {
					increaseCurrentNumber();
				}
			}
		}
		generateEnemies((currentType1 * 3), 1);
		generateEnemies((currentType2 * 3), 2);
		generateEnemies((currentType3 * 3), 3);
		setNumbers();
		printConstalation(currentWaveIndex);
		return generatedEnemies;
	}

	public boolean checkMaxNumber() {
		if ((currentType1 + currentType2 + currentType3) < currentMaxNumber) {
			return true;
		}
		return false;
	}

	public void generateEnemies(Integer n, int pType/* , int pLevel */) {
		for (int a = 0; a < n; a++) {
			Enemy e = new Enemy(null, null, pType);
			generatedEnemies.add(e);
		}
	}

	public void setNumbers() {
		for (int a = 0; a < generatedEnemies.size(); a++) {
			generatedEnemies.get(a).setNumber(a + 1);
		}
	}

	public void setNumbers(ArrayList<Enemy> list) {
		for (int a = 0; a < list.size(); a++) {
			list.get(a).setNumber(a + 1);
		}
	}

	public void increaseCurrentNumber() {
		if (currentType3 == currentMaxNumber) {
			currentMaxNumber += 5;
		}
	}

	/*
	 * Type3: [] + Type2: [] + Type1: []
	 */
	public void printConstalation(int i) {
		print("Wave Number: " + i);
		print("Type3:	" + currentType3 * 3 + " !");
		print("	 +");
		print("Type2:	  " + currentType2 * 3 + " !");
		print("	   +");
		print("Type1:	    " + currentType1 * 3 + " !");
	}

	public void print(String pString) {
		System.out.println(pString);
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

	// public static void main(String[] args) {
	// WaveGenerator wG = new WaveGenerator();
	// for (int i = 0; i < 100; i++) {
	// wG.printConstalation(i);
	// wG.generateWave();
	// }
	// }
}
