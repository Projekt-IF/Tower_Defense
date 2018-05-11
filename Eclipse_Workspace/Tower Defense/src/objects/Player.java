package objects;

public class Player {

	private String playerIP;
	private Integer playerPort;
	
	private int lobbyIndex;
	private int positionIndex;

	private long playerMoney;
	private long health;

	private boolean isReady;
	
	private boolean isConnected;
	private boolean isInLobby;
	private boolean isInGame;

	public Player(String pPlayerIP, Integer pPlayerPort) {
		this.setPlayerIP(pPlayerIP);
		this.setPlayerPort(pPlayerPort);
	}
	
	public boolean haveSameStats(Player testPlayer, Player listPlayer) {
		if ((testPlayer.getPlayerIP().equals(listPlayer.getPlayerIP()))
				&& ((int) testPlayer.getPlayerPort() == (int) listPlayer.getPlayerPort())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the playerIP
	 */
	public String getPlayerIP() {
		return playerIP;
	}

	/**
	 * @param playerIP
	 *            the playerIP to set
	 */
	public void setPlayerIP(String playerIP) {
		this.playerIP = playerIP;
	}

	/**
	 * @return the playerPort
	 */
	public Integer getPlayerPort() {
		return playerPort;
	}

	/**
	 * @param playerPort
	 *            the playerPort to set
	 */
	public void setPlayerPort(Integer playerPort) {
		this.playerPort = playerPort;
	}

	/**
	 * @return the playerMoney
	 */
	public long getPlayerMoney() {
		return playerMoney;
	}

	/**
	 * @param playerMoney
	 *            the playerMoney to set
	 */
	public void setPlayerMoney(long playerMoney) {
		this.playerMoney = playerMoney;
	}

	/**
	 * @return the isConnected
	 */
	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * @param isConnected
	 *            the isConnected to set
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	/**
	 * @return the isInLobby
	 */
	public boolean isInLobby() {
		return isInLobby;
	}

	/**
	 * @param isInLobby the isInLobby to set
	 */
	public void setInLobby(boolean isInLobby) {
		this.isInLobby = isInLobby;
	}

	/**
	 * @return the isInGame
	 */
	public boolean isInGame() {
		return isInGame;
	}

	/**
	 * @param isInGame
	 *            the isInGame to set
	 */
	public void setInGame(boolean isInGame) {
		this.isInGame = isInGame;
	}

	/**
	 * @return the isReady
	 */
	public boolean isReady() {
		return isReady;
	}

	/**
	 * @param isReady the isReady to set
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	/**
	 * @return the health
	 */
	public long getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(long health) {
		this.health = health;
	}

	/**
	 * @return the lobbyIndex
	 */
	public int getLobbyIndex() {
		return lobbyIndex;
	}

	/**
	 * @param lobbyIndex the lobbyIndex to set
	 */
	public void setLobbyIndex(int lobbyIndex) {
		this.lobbyIndex = lobbyIndex;
	}

	/**
	 * @return the positionIndex
	 */
	public int getPositionIndex() {
		return positionIndex;
	}

	/**
	 * @param positionIndex the positionIndex to set
	 */
	public void setPositionIndex(int positionIndex) {
		this.positionIndex = positionIndex;
	}

}
