package game_Controller;

import envoirement.Grid;
import objects.Enemy;

public class Game_Controller {
	
	private String currentLevel;
	
	private Enemy_Controller eC;
	
	private Wave_Controller wC;
	
	public Game_Controller(Wave_Controller wC, Enemy_Controller eC) {
		this.wC = wC;
		this.eC = eC;
		iniciateGame();
	}
	
	/**
	 * 
	 */
	public void iniciateGame() {
		currentLevel = "One";
		Grid grid = new Grid("One");
		eC.setGrid(grid);
	}
	
	public static void main(String args[]) {
		
		Grid g = new Grid("test");
		Wave_Controller wC = new Wave_Controller();
		Enemy_Controller eC = new Enemy_Controller();
		eC.setGrid(g);
		eC.setSpawnerTile(g.getSpawnerTile());
		Game_Controller gC = new Game_Controller(wC, eC);
		Enemy e = eC.spawnEnemy(1, 1);
		System.out.println("PosX: " + e.getPosX() + " ; PosY: " + e.getPosY());
		eC.moveEnemy(e);
		System.out.println("PosX: " + e.getPosX() + " ; PosY: " + e.getPosY());
		eC.moveEnemy(e);
		System.out.println("PosX: " + e.getPosX() + " ; PosY: " + e.getPosY());
	}

}
