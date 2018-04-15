package objects;

import java.util.Timer;

import utility.TowerCooldownTimer;

public class Tower {

	private int posX;
	private int posY;

	private int cost;

	private int range;

	private Shot shot;

	private boolean onCooldown;

	private double cooldownTime;

	public Tower(int pPosX, int pPosY, int pRange, Double pStrength, double pSpeed, double pVelocity,
			double pCooldownTime) {
		this.onCooldown = false;
		this.cooldownTime = pCooldownTime;
		this.posX = pPosX;
		this.posY = pPosY;
		this.range = pRange;
		this.shot = new Shot(pStrength, pSpeed, pVelocity);
	}

	public void upgradeShot(double strength, double speed, double velocity) {
		this.shot.setStrength(strength);
		this.shot.setSpeed(speed);
		this.shot.setVelocity(velocity);
	}

	public void degradeShot(double strength, double speed, double velocity) {
		this.shot.setStrength(strength);
		this.shot.setSpeed(speed);
		this.shot.setVelocity(velocity);
	}

	public void startTimer() {
		Timer timer = new Timer();
		System.out.println(this.cooldownTime * 5000);
		timer.schedule(new TowerCooldownTimer(this, timer), (long) (this.cooldownTime * 5000));
	}

	public Double shoot() {
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
