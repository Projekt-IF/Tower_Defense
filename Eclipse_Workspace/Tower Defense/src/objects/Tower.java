package objects;

import java.util.Timer;

import utility.TowerCooldownTimerTask;

/**
 * The Tower class represents a Tower that can shoot an Enemy. It is limited by
 * the type with witch the TowerTypes class generates the cost, range, cool down
 * and damage. Furthermore is is set on to a Tile on a Grid.
 * 
 * @author Jonas Schröder, Ebba Siebold
 * @version 1.0
 */
public class Tower {

	private TowerTypes types;
	private int type;

	private int posX;
	private int posY;

	private int cost;

	private int range;

	private int damage;

	private boolean onCooldown;

	private int cooldownTime;

	/**
	 * Constructs a Tower of the given type that is placed on a Grid at the given X-
	 * and Y-coordinates.
	 * 
	 * @param pPosX
	 *            The X-coordinate the Tower is placed on.
	 * @param pPosY
	 *            The Y-coordinate the Tower is placed on.
	 * @param pType
	 *            The type to be applied to the Tower.
	 */
	public Tower(int pPosX, int pPosY, int pType) {
		this.types = new TowerTypes();
		this.onCooldown = false;
		this.cooldownTime = types.calcCooldown(pType);
		this.posX = pPosX;
		this.posY = pPosY;
		this.type = pType;
		this.range = types.calcRange(pType);
		this.cost = types.calcCost(pType);
		this.damage = types.calcStrength(pType);
	}

	/**
	 * Initializes and starts an Object of the Timer class with an
	 * TowerCooldownTimerTask. The duration of the Timer is calculated with the cool
	 * down in seconds.
	 */
	public void startTimer() {
		Timer timer = new Timer();
		timer.schedule(new TowerCooldownTimerTask(this, timer), (long) (this.cooldownTime * 1000));
	}

	/**
	 * Sets the Tower in cool down state and starts the Timer while returning the
	 * damage to be applied.
	 * 
	 * @return (int) damage The calculated Tower's damage.
	 */
	public int shoot() {
		this.onCooldown = true;
		startTimer();
		return damage;
	}

	/**
	 * 
	 * @return The posX.
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * 
	 * @param posX
	 *            The Tower's X-position.
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * 
	 * @return The posY.
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * 
	 * @param posY
	 *            The Tower's Y-position.
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * 
	 * @return The cost.
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * 
	 * @param cost
	 *            The Tower's cost.
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return The range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * @param range
	 *            The Tower's range.
	 */
	public void setRange(int range) {
		this.range = range;
	}

	/**
	 * @return The onCooldown
	 */
	public boolean isOnCooldown() {
		return onCooldown;
	}

	/**
	 * @param onCooldown
	 *            The state of the Tower's onCooldown.
	 */
	public void setOnCooldown(boolean onCooldown) {
		this.onCooldown = onCooldown;
	}

	/**
	 * @return The type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            The Tower's type.
	 */
	public void setType(int type) {
		this.type = type;
	}
}
