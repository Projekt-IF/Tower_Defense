package objects;

/**
 * The Player class represents the communication parter of the Sever, as well as
 * the Player in game.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class Player {

	private String playerIP;
	private Integer playerPort;
	
	private Player otherplayer;
	
	private String username;
	
	private int lobbyIndex;
	private int positionInLobby;

	private int enemiesKilled;
	private int towersPlaced;

	private long playerMoney;
	private int health;

	private boolean isReady;

	private boolean isBuyDone;
	private boolean isRoundOver;

	private boolean isConnected;
	private boolean isInLobby;
	private boolean isInGame;

	/**
	 * Constructs a Player Object.
	 * 
	 * @param pPlayerIP
	 *            The Plsyer's IP for networking.
	 * @param pPlayerPort
	 *            The Player's Port for networking.
	 */
	public Player(String pPlayerIP, Integer pPlayerPort) {
		this.setPlayerIP(pPlayerIP);
		this.setPlayerPort(pPlayerPort);
	}

	/**
	 * Compares the IP and Port of two Players.
	 * 
	 * @param testPlayer
	 *            The Player to be compared.
	 * @param listPlayer
	 *            The Player to be compared to.
	 * @return (boolean) True: both Players have same IP and Port, False: both Players have not the
	 *         same IP and Port.
	 */
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
	 * @param isInLobby
	 *            the isInLobby to set
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
	 * @param isReady
	 *            the isReady to set
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health
	 *            the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the lobbyIndex
	 */
	public int getLobbyIndex() {
		return lobbyIndex;
	}

	/**
	 * @param lobbyIndex
	 *            the lobbyIndex to set
	 */
	public void setLobbyIndex(int lobbyIndex) {
		this.lobbyIndex = lobbyIndex;
	}

	/**
	 * @return the positionInLobby
	 */
	public int getPositionInLobby() {
		return positionInLobby;
	}

	/**
	 * @param positionInLobby
	 *            the positionInLobby to set
	 */
	public void setPositionInLobby(int positionInLobby) {
		this.positionInLobby = positionInLobby;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the isBuyDone
	 */
	public boolean isBuyDone() {
		return isBuyDone;
	}

	/**
	 * @param isBuyDone
	 *            the isBuyDone to set
	 */
	public void setBuyDone(boolean isBuyDone) {
		this.isBuyDone = isBuyDone;
	}

	/**
	 * @return the otherplayer
	 */
	public Player getOtherplayer() {
		return otherplayer;
	}

	/**
	 * @param otherplayer
	 *            the otherplayer to set
	 */
	public void setOtherplayer(Player otherplayer) {
		this.otherplayer = otherplayer;
	}

	/**
	 * @return the isRoundOver
	 */
	public boolean isRoundOver() {
		return isRoundOver;
	}

	/**
	 * @param isRoundOver
	 *            the isRoundOver to set
	 */
	public void setRoundOver(boolean isRoundOver) {
		this.isRoundOver = isRoundOver;
	}

	/**
	 * @return the enemiesKilled
	 */
	public int getEnemiesKilled() {
		return enemiesKilled;
	}

	/**
	 * @param enemiesKilled
	 *            the enemiesKilled to set
	 */
	public void setEnemiesKilled(int enemiesKilled) {
		this.enemiesKilled = enemiesKilled;
	}

	/**
	 * @return the towersPlaced
	 */
	public int getTowersPlaced() {
		return towersPlaced;
	}

	/**
	 * @param towersPlaced
	 *            the towersPlaced to set
	 */
	public void setTowersPlaced(int towersPlaced) {
		this.towersPlaced = towersPlaced;
	}

}
