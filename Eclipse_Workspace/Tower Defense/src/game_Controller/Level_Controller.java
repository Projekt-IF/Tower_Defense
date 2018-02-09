package game_Controller;

import envoirement.Grid;

public class Level_Controller {
	
	private String currentLevel;
	
	public Level_Controller(String pCurrentLevel) {
		this.currentLevel = pCurrentLevel;
	}

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
	 * @param currentLevel the currentLevel to set
	 */
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}
	
}
