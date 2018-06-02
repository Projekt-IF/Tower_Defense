package objects;

import java.util.Timer;

import utility.EnemyCooldownTimer;

/*
 * The Enemy Class is just apparent as the Enemy.
 * The movement is controlled by the Enemy_Controller.
 */
public class Enemy {

	/**
	 * 
	 */
	public static final int TYPE_NORMAL = 1;
	
	private EnemyTypes types;
	private int type;

	private int posX;
	private int posY;

	private int level;

	private int life;
	private int speed;
	private int damage;

	private int cost;

	private boolean onCooldown;

	/*
	 * 
	 */
	public Enemy(int pPosX, int pPosY, int pType, int pLevel) {
		this.posX = pPosX;
		this.posY = pPosY;
		this.setType(pType);
		this.types = new EnemyTypes(pType);
		this.life = types.getLife();
		this.speed = types.getSpeed();
		this.damage = types.getDamage();
		this.cost = types.getCost();
		this.onCooldown = false;
	}

	public Boolean checkAlife() {
		if (life <= 0) {
			return false;
		} else {
			return true;
		}
	}

	public void startTimer() {
		Timer timer = new Timer();
		System.out.println(this.speed * 2500);
		timer.schedule(new EnemyCooldownTimer(this, timer), (long) (this.speed * 2500));
	}
	
	public void move() {
		this.onCooldown = true;
		startTimer();
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

	public int getLife() {
		return life;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getLevel() {
		return level;
	}

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
	 * @param type the type to set
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
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

}
