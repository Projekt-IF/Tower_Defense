package objects;

import java.util.Timer;

import utility.TowerCooldownTimer;

public class Tower {

	private TowerTypes type;

	private int posX;
	private int posY;

	private int cost;

	private int range;

	private Shot shot;

	private boolean onCooldown;

	private int cooldownTime;

	public Tower(int pPosX, int pPosY, int pType) {
		this.type = new TowerTypes(pType);
		this.onCooldown = false;
		this.cooldownTime = type.getCooldown();
		this.posX = pPosX;
		this.posY = pPosY;
		this.range = type.getRange();
		this.shot = new Shot(type.getStrength(), type.getSpeed());
	}

	public void upgradeShot(int strength, int speed, int velocity) {
		this.shot.setStrength(strength);
		this.shot.setSpeed(speed);
	}

	public void degradeShot(int strength, int speed, int velocity) {
		this.shot.setStrength(strength);
		this.shot.setSpeed(speed);
	}

	public void startTimer() {
		Timer timer = new Timer();
		System.out.println(this.cooldownTime * 5000);
		timer.schedule(new TowerCooldownTimer(this, timer), (long) (this.cooldownTime * 5000));
	}

	public int shoot() {
		this.onCooldown = true;
		startTimer();
		return this.shot.getStrength();
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
}
