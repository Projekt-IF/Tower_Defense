package objects;

import java.util.Timer;

import utility.EnemyCooldownTimerTask;

/**
 * The Enemy class represents the Enemy the Player has to defend by placing
 * Towers onto the Grid. The Enemies move over the Grid from their Spawn to the
 * Player's Base over the given Path.
 * 
 * @author Jonas Schröder, Ebba Siebold
 * @version 1.0
 */
public class Enemy {

	private int number;

	private EnemyTypes types;
	private int type;

	private Integer posX;
	private Integer posY;

	private int level;

	private int life;
	private int speed;
	private int damage;

	private int cost;
	private int bounty;

	private boolean onCooldown;

	/**
	 * Constructs an Enemy and sets its position on the Grid and its type.
	 * 
	 * @param pPosX
	 *            The starting X-Position.
	 * @param pPosY
	 *            The starting Y-Position.
	 * @param pType
	 *            The Enemy's type.
	 */
	public Enemy(Integer pPosX, Integer pPosY, int pType) {
		this.posX = pPosX;
		this.posY = pPosY;
		this.setType(pType);
		this.types = new EnemyTypes();
		this.life = types.calcLife(pType);
		this.speed = types.calcSpeed(pType);
		this.damage = types.calcDamage(pType);
		this.setCost(types.calcCost(pType));
		this.bounty = types.calcBounty(pType);
		this.onCooldown = false;
	}

	/**
	 * Returns weather the Enemy has life <= 0 or life > 0.
	 * 
	 * @return (boolean) True: Enemy has life > 0. False: Enemy has life <= 0.
	 */
	public Boolean checkAlife() {
		if (life <= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Initializes and starts an Object of the Timer class with an
	 * EnemyCooldownTimerTask. The duration of the Timer is calculated with the
	 * speed in seconds.
	 */
	public void startTimer() {
		Timer timer = new Timer();
		timer.schedule(new EnemyCooldownTimerTask(this, timer), (long) (this.speed * 1000));
	}

	/**
	 * Sets the Enemy in cool down state and starts the Timer.
	 */
	public void move() {
		this.onCooldown = true;
		startTimer();
	}

	/**
	 * 
	 * @return The Enemy's X-position.
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * 
	 * @param posX
	 *            The Enemy's X-position.
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * 
	 * @return The Enemy's Y-position.
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * 
	 * @param posX
	 *            The Enemy's Y-position.
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * 
	 * @return The Enemy's life.
	 */
	public int getLife() {
		return life;
	}

	/**
	 * 
	 * @return The Enemy's speed.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * 
	 * @param speed
	 *            The Enemy's speed.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the onCooldown
	 */
	public boolean isOnCooldown() {
		return onCooldown;
	}

	/**
	 * @param onCooldown
	 *            the onCooldown to set
	 */
	public void setOnCooldown(boolean onCooldown) {
		this.onCooldown = onCooldown;
	}

	public void setLife(int pLife) {
		this.life = pLife;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage
	 *            the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the bounty
	 */
	public int getBounty() {
		return bounty;
	}

	/**
	 * @param bounty
	 *            the bounty to set
	 */
	public void setBounty(int bounty) {
		this.bounty = bounty;
	}

}
