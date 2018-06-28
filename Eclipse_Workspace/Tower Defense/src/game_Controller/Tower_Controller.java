package game_Controller;

import java.util.ArrayList;

import envoirement.Grid;
import envoirement.Tile;
import network.Protocol;
import network.Server_TD;
import objects.Enemy;
import objects.Player;
import objects.Tower;

/**
 * The Tower_Controller controls the towers shooting and placing.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class Tower_Controller {

	private Server_TD server;

	private Player player;

	private Game_Controller game_Controller;

	private Grid grid;

	private ArrayList<Tower> towerList;

	/**
	 * Constructs a Tower_Controller initializing the server, towerList and
	 * game_Controller.
	 * 
	 * @param pServer
	 *            The game's server.
	 * @param pTowerList
	 *            The game's towerList.
	 * @param pGame_Controller
	 *            The game's gameController
	 */
	public Tower_Controller(Server_TD pServer, ArrayList<Tower> pTowerList, Game_Controller pGame_Controller) {
		this.server = pServer;
		this.game_Controller = pGame_Controller;
		this.towerList = pTowerList;
	}

	/**
	 * Sets the towers position by its x and y coordinates.
	 * 
	 * @param pPosX
	 *            The x coordinate.
	 * @param pPosY
	 *            The y coordinate.
	 * @param pTower
	 *            The Tower.
	 */
	public void setTowerPosition(int pPosX, int pPosY, Tower pTower) {
		grid.getGridLayer()[pTower.getPosY()][pTower.getPosX()].setType(Tile.TYPE_UNOC);
		grid.getGridLayer()[pPosY][pPosX].setType(Tile.TYPE_TOWER);
		pTower.setPosX(pPosX);
		pTower.setPosY(pPosY);
	}

	/**
	 * Creating a new tower and changing the grid's type at that position.
	 * 
	 * @param pPosX
	 *            The x coordinate.
	 * @param pPosY
	 *            The y coordinate.
	 * @param pType
	 *            The tower's type.
	 * @return (Tower) The created Tower.s
	 */
	public Tower createTower(int pPosX, int pPosY, int pType) {
		grid.getGridLayer()[pPosY][pPosX].setType(Tile.TYPE_TOWER);
		player.setTowersPlaced(player.getTowersPlaced() + 1);
		return (new Tower(pPosX, pPosY, pType));
	}

	/**
	 * Checks the towerList for the towers to shoot
	 */
	public void checkTowers() {
		if (!game_Controller.getEnemyController().getEnemyList().isEmpty()) {
			for (int w = 0; w < towerList.size(); w++) {
				Tower current = towerList.get(w);
				// is tower on cool down
				if (!current.isOnCooldown()) {
					// checking the x and y coordinates of the range for enemies
					for (int y = -current.getRange(); y <= current.getRange(); y++) {
						for (int x = -current.getRange(); x <= current.getRange(); x++) {
							int currentY = y + current.getPosY();
							int currentX = x + current.getPosX();
							if ((currentY >= 0) && (currentY < grid.getLength())) {
								if ((currentX >= 0) && (currentX < grid.getLength())) {
									for (int z = 0; z < this.game_Controller.getEnemyList().size(); z++) {
										Enemy tmpEnemy = game_Controller.getEnemyList().get(z);
										if (!current.isOnCooldown()) {
											if ((tmpEnemy.getPosY() == currentY) && (tmpEnemy.getPosX() == currentX)) {
												tmpEnemy.setLife(tmpEnemy.getLife() - current.shoot());
												if (!tmpEnemy.checkAlife()) {
													// Enemy is dead
													// adding bounty to money
													player.setPlayerMoney(
															player.getPlayerMoney() + tmpEnemy.getBounty());
													this.server.send(player.getPlayerIP(), player.getPlayerPort(),
															Protocol.SC_UPDATE_PLAYER_MONEY + Protocol.SEPARATOR
																	+ player.getPlayerMoney());
													this.server.send(player.getPlayerIP(), player.getPlayerPort(),
															Protocol.SC_UPDATE_POSITION_ENEMY + Protocol.SEPARATOR
																	+ tmpEnemy.getPosX() + Protocol.SEPARATOR
																	+ tmpEnemy.getPosY() + Protocol.SEPARATOR + "null"
																	+ Protocol.SEPARATOR + "null");
													// increases the killed enemy count
													player.setEnemiesKilled(player.getEnemiesKilled() + 1);
													this.game_Controller.getEnemyList().remove(tmpEnemy);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Clears the towerList
	 */
	public void clearTowerList() {
		towerList.clear();
	}

	/**
	 * 
	 * @return grid The game grid.
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * 
	 * @param grid
	 *            The game Grid.
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * @return the towerList
	 */
	public ArrayList<Tower> getTowerList() {
		return towerList;
	}

	/**
	 * @param towerList
	 *            the towerList to set
	 */
	public void setTowerList(ArrayList<Tower> towerList) {
		this.towerList = towerList;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
