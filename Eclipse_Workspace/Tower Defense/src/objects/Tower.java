package objects;

import java.util.Timer;

import utility.TowerCooldownTimer;

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

	public void startTimer() {
		Timer timer = new Timer();
		timer.schedule(new TowerCooldownTimer(this, timer), (long) (this.cooldownTime * 1000));
	}

	public int shoot() {
		this.onCooldown = true;
		startTimer();
		return damage;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * @param range
	 *            the range to set
	 */
	public void setRange(int range) {
		this.range = range;
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
}
