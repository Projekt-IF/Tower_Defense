package utility;

import network.Server_TD;
import objects.Player;

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
	
	public void chooseRandomMap() {
		setMapName(gameFrameWork.chooseRandomMap());
	}
	
	public void initializeGame() {
		gameFrameWork.setPlayer_1(player_1);
		gameFrameWork.setPlayer_2(player_2);
		gameFrameWork.setOtherPlayer();
		gameFrameWork.startGame();
	}

	public boolean haveSamePortIP(Player testPlayer, Player lobbyPlayer) {
		if ((testPlayer.getPlayerIP().equals(lobbyPlayer.getPlayerIP()))
				&& ((int) testPlayer.getPlayerPort() == (int) lobbyPlayer.getPlayerPort())) {
			return true;
		} else {
			return false;
		}
	}
	
	public Player getOtherPlayer(Player player) {
		if(player.equals(player_1)) {
			return player_2;
		} else if(player.equals(player_2)) {
			return player_1;
		}
		return null;
	}
	
	public boolean allBuyDone() {
		if(player_1.isBuyDone() && player_2.isBuyDone()) {
			return true;
		}
		return false;
	}
	
	public boolean allRoundOver() {
		if(player_1.isRoundOver() && player_2.isRoundOver()) {
			return true;
		}
		return false;
	}

	public void resetPlayer_1() {
		this.player_1 = null;
	}

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
	 * @param mapName the mapName to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

}
