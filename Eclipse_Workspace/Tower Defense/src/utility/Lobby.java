package utility;

import objects.Player;

public class Lobby {

	private GameFrameWork gameFrameWork;

	private Player player_1;
	private Player player_2;

	private boolean isFull;
	private boolean inGame;

	public Lobby() {
		player_1 = null;
		player_2 = null;
		isFull = false;
		inGame = false;
	}

	public boolean haveSameStats(Player testPlayer, Player lobbyPlayer) {
		if ((testPlayer.getPlayerIP().equals(lobbyPlayer.getPlayerIP()))
				&& ((int) testPlayer.getPlayerPort() == (int) lobbyPlayer.getPlayerPort())) {
			return true;
		} else {
			return false;
		}
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
	 * @param player_2
	 *            the player_2 to set
	 */
	public void setPlayer_2(Player player_2) {
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

}
