package game_Controller;

import envoirement.Grid;

/**
 * The Level_Controler changes and creates the level's grid.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class Level_Controller {

	// private Game_Controller game_Controller;

	private String currentLevel;

	/**
	 * Constructs a Level_Controller with a certain level name.
	 * 
	 * @param pCurrentLevel
	 *            The name of the level
	 */
	public Level_Controller(String pCurrentLevel) {
		this.currentLevel = pCurrentLevel;
	}

	/**
	 * Changes the level to the level with the levelName and creates a new grid.
	 * 
	 * @param pLevelName
	 *            The new level's name.
	 * @return (Grid) grid The created grid.
	 */
	public Grid changeLevel(String pLevelName) {
		Grid g = new Grid(pLevelName);
		this.currentLevel = pLevelName;
		return g;
	}

	/**
	 * @return the currentLevel
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * @param currentLevel
	 *            the currentLevel to set
	 */
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}

}
