package utility;

import game_Controller.Game_Controller;
import objects.Player;

public class GameFrameWork {
	
	private Player player_1;
	private Player player_2;
	
	private Game_Controller player_1_Game_Controller;
	private Game_Controller player_2_Game_COntroller;
	
	public GameFrameWork() {
		
	}
	
	public void setLevel(String pLevel) {
		player_1_Game_Controller.setCurrentLevel(pLevel);
		player_2_Game_COntroller.setCurrentLevel(pLevel);
	}
	
	public void startGame() {
	}

	/**
	 * @return the player_1
	 */
	public Player getPlayer_1() {
		return player_1;
	}

	/**
	 * @param player_1 the player_1 to set
	 */
	public void setPlayer_1(Player player_1) {
		this.player_1 = player_1;
	}

	/**
	 * @return the player_2
	 */
	public Player getPlayer_2() {
		return player_2;
	}

	/**
	 * @param player_2 the player_2 to set
	 */
	public void setPlayer_2(Player player_2) {
		this.player_2 = player_2;
	}

	/**
	 * @return the player_1_Game_Controller
	 */
	public Game_Controller getPlayer_1_Game_Controller() {
		return player_1_Game_Controller;
	}

	/**
	 * @param player_1_Game_Controller the player_1_Game_Controller to set
	 */
	public void setPlayer_1_Game_Controller(Game_Controller player_1_Game_Controller) {
		this.player_1_Game_Controller = player_1_Game_Controller;
	}

	/**
	 * @return the player_2_Game_COntroller
	 */
	public Game_Controller getPlayer_2_Game_COntroller() {
		return player_2_Game_COntroller;
	}

	/**
	 * @param player_2_Game_COntroller the player_2_Game_COntroller to set
	 */
	public void setPlayer_2_Game_COntroller(Game_Controller player_2_Game_COntroller) {
		this.player_2_Game_COntroller = player_2_Game_COntroller;
	}

}
