package utility;

import java.util.ArrayList;

import envoirement.Grid;
import objects.Enemy;

/**
 * The WaveGenerator is capable of generating ongoing Waves of Enemies
 * 
 * @author Jonas
 * @version 1.0
 */
public class WaveGenerator {

	private Grid grid;

	private ArrayList<Enemy> generatedEnemies;

	private int currentMaxNumber;

	private int currentWaveIndex;

	private int currentType1;
	private int currentType2;
	private int currentType3;

	/**
	 * Constructs an object of the WaveGenerator.
	 */
	public WaveGenerator() {
		generatedEnemies = new ArrayList<Enemy>();
		currentMaxNumber = 5;
		currentWaveIndex = 0;
		currentType3 = 0;
		currentType2 = 0;
		currentType1 = 0;
	}

	/**
	 * Generates ongoing waves. The pattern determines how many Enemies of what type
	 * are generated. It goes up from 1 to 5 and increments the number of Enemies
	 * for each type based on the amount of enemies form other types. If the
	 * maxAmount is reached it increments the next up type. This loops until the
	 * highest type is at the max number and then the max number is incremented.
	 * 
	 * @return (ArrayList<Enemy>) generatedEnemies
	 */
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
		// Generating the Wave based on the amount of enemies of each type.
		generateEnemies((currentType1 * 3), 1);
		generateEnemies((currentType2 * 3), 2);
		generateEnemies((currentType3 * 3), 3);
		setNumbers();
		printConstalation(currentWaveIndex);
		return generatedEnemies;
	}

	/**
	 * Checks weather the amount of enemies all together is lower or higher of the
	 * maximum amount of enemies.
	 * 
	 * @return (boolean) maxNumberReached
	 */
	public boolean checkMaxNumber() {
		if ((currentType1 + currentType2 + currentType3) < currentMaxNumber) {
			return true;
		}
		return false;
	}

	/**
	 * Generates a certain amount of enemies based on their type and adds them to
	 * the generatedEnemiesList.
	 * 
	 * @param n
	 *            The amount of enemies to be generated.
	 * @param pType
	 *            The type of the Enemies.
	 */
	public void generateEnemies(Integer n, int pType) {
		for (int a = 0; a < n; a++) {
			Enemy e = new Enemy(null, null, pType);
			generatedEnemies.add(e);
		}
	}

	/**
	 * Sets the dedicated position of the Enemy in the Wave.w
	 */
	public void setNumbers() {
		for (int a = 0; a < generatedEnemies.size(); a++) {
			generatedEnemies.get(a).setNumber(a + 1);
		}
	}

	/**
	 * Increases the maximum possible number of enemies.
	 */
	public void increaseCurrentNumber() {
		if (currentType3 == currentMaxNumber) {
			currentMaxNumber += 5;
		}
	}

	/*
	 * Prints out the number of enemies per type in the following pattern.
	 * Type3: [] 
	 * 		+ 
	 * 		Type2: [] 
	 * 			+ 
	 * 			Type1: []
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
	 * Returns the designated Grid for the WaveGenerator.
	 * 
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
}
