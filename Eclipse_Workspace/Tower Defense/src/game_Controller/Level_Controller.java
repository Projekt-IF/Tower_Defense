package game_Controller;

import envoirement.Grid;

public class Level_Controller {
	
	public Level_Controller() {
		
	}

	public Grid changeLevel(String pLevelName) {
		Grid g = new Grid(pLevelName);
		return g;
	}
}
