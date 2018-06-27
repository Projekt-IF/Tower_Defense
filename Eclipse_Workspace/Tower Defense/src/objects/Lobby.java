package objects;

import game_Controller.GameFrameWork;
import network.Server_TD;

/**
 * The Lobby class represents a storage and communication device for Players to
 * play a game of Tower Defense using the GameFrameWork. It is used to store Players split up
 * into groups of 2.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class Lobby {

	private Server_TD server;

	private GameFrameWork gameFrameWork;

	private Player player_1;
	private int player_1_Index;
	private Player player_2;
	private int player_2_Index;

	private String mapName;

	private boolean isFull;
	private boolean inGame;

	/**
	 * Constructs a Lobby on which the GameFrameWork organizes the players in game.
	 * Also a map is chosen.
	 * 
	 * @param pServer
	 *            The server the Lobby is run on and from which it send messages to
	 *            the Player's Clients.
	 */
	public Lobby(Server_TD pServer) {
		this.server = pServer;
		this.gameFrameWork = new GameFrameWork(this.server);
		player_1 = null;
		player_2 = null;
		isFull = false;
		inGame = false;
		mapName = "";
		chooseRandomMap();
	}

	/**
	 * Requests a random map of the map-possibilities from the gameFrameWork.
	 */
	public void chooseRandomMap() {
		setMapName(gameFrameWork.chooseRandomMap());
	}

	/**
	 * Initializes the game by setting the two Players and starting the game
	 * routine.
	 */
	public void initializeGame() {
		gameFrameWork.setPlayer_1(player_1);
		gameFrameWork.setPlayer_2(player_2);
		gameFrameWork.setOtherPlayer();
		gameFrameWork.startGame();
	}

	/**
	 * Returns the enemy Player.
	 * 
	 * @param player
	 *            The requested Player.
	 * @return (Player) otherPlayer The other Player.
	 */
	public Player getOtherPlayer(Player player) {
		if (player.equals(player_1)) {
			return player_2;
		} else if (player.equals(player_2)) {
			return player_1;
		}
		return null;
	}

	/**
	 * Returns weather all Players are ready with the buy phase.
	 * 
	 * @return (boolean) True: All Players are done buying. False: Not all Players
	 *         are done buying.
	 */
	public boolean allBuyDone() {
		if (player_1.isBuyDone() && player_2.isBuyDone()) {
			return true;
		}
		return false;
	}

	/**
	 * Returns weather all Players are done with the current round.
	 * 
	 * @return (boolean) True: All Players are done fighting. False: Not all Players
	 *         are done fighting.
	 */
	public boolean allRoundOver() {
		if (player_1.isRoundOver() && player_2.isRoundOver()) {
			return true;
		}
		return false;
	}

	/**
	 * Sets the Player_1 = null.
	 */
	public void resetPlayer_1() {
		this.player_1 = null;
	}

	/**
	 * Sets the Player_2 = null.
	 */
	public void resetPlayer_2() {
		this.player_2 = null;
	}

	/**
	 * @return the player_1
	 */
	public Player getPlayer_1() {
		return player_1;
	}

	/**
	 * @param player_1
	 *            the player_1 to set
	 */
	public void setPlayer_1(Player player_1, int player_1_Index) {
		this.player_1_Index = player_1_Index;
		this.player_1 = player_1;
	}

	/**
	 * @return the player_2
	 */
	public Player getPlayer_2() {
		return player_2;
	}

	/**
	 * @param player_2
	 *            the player_2 to set
	 */
	public void setPlayer_2(Player player_2, int player_2_Index) {
		this.player_2_Index = player_2_Index;
		this.player_2 = player_2;
	}

	/**
	 * @return the isFull
	 */
	public Boolean getIsFull() {
		return isFull;
	}

	/**
	 * @param isFull
	 *            the isFull to set
	 */
	public void setIsFull(Boolean isFull) {
		this.isFull = isFull;
	}

	/**
	 * @return the inGame
	 */
	public boolean isInGame() {
		return inGame;
	}

	/**
	 * @param inGame
	 *            the inGame to set
	 */
	public void setInGame(boolean inGame) {
		player_1.setInGame(inGame);
		player_2.setInGame(inGame);
		this.inGame = inGame;
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

	/**
	 * @return the player_1_Index
	 */
	public int getPlayer_1_Index() {
		return player_1_Index;
	}

	/**
	 * @param player_1_Index
	 *            the player_1_Index to set
	 */
	public void setPlayer_1_Index(int player_1_Index) {
		this.player_1_Index = player_1_Index;
	}

	/**
	 * @return the player_2_Index
	 */
	public int getPlayer_2_Index() {
		return player_2_Index;
	}

	/**
	 * @param player_2_Index
	 *            the player_2_Index to set
	 */
	public void setPlayer_2_Index(int player_2_Index) {
		this.player_2_Index = player_2_Index;
	}

	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @param mapName
	 *            the mapName to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

}
