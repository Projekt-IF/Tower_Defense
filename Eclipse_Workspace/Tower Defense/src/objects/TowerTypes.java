package objects;

/*
 * The Enemy Class is just apparent as the Enemy.
 * The movement is controlled by the Enemy_Controller.
 */
public class TowerTypes {

	private int cost;
	private int range;
	private int strength;
	private int speed;
	private int type;
	private int cooldown;

	public TowerTypes(int pType) {
		this.type = pType;
		this.speed = calcSpeed();
		this.cost = calcCost();
		this.strength = calcStrength();
		this.range = calcRange();
		this.cooldown = calcCooldown();
	}

	public int calcCooldown() {
		int calcedCooldown = 0;

		switch (type) {
		case '0':
			calcedCooldown = 2;
		case '1':
			calcedCooldown = 4;
		case '2':
			calcedCooldown = 6;
		default:
			calcedCooldown = 0;
		}

		return calcedCooldown;

	}

	public int getCooldown() {
		return cooldown;
	}

	public int calcSpeed() {
		int calcedSpeed = 0;

		switch (type) {
		case '0':
			calcedSpeed = 2;
		case '1':
			calcedSpeed = 4;
		case '2':
			calcedSpeed = 6;
		default:
			calcedSpeed = 0;
		}

		return calcedSpeed;

	}

	public int getSpeed() {
		return speed;
	}

	public int calcStrength() {
		int calcedStrength = 0;

		switch (type) {
		case '0':
			calcedStrength = 2;
		case '1':
			calcedStrength = 5;
		case '2':
			calcedStrength = 10;
		default:
			calcedStrength = 0;
		}

		return calcedStrength;

	}

	public int getStrength() {
		return strength;
	}

	public int calcRange() {
		int calcedRange = 0;

		switch (type) {
		case '0':
			calcedRange = 2;
		case '1':
			calcedRange = 3;
		case '2':
			calcedRange = 5;
		default:
			calcedRange = 0;
		}

		return calcedRange;

	}

	public int getRange() {
		return range;
	}

	public int calcCost() {
		int calcedCost = 0;

		switch (type) {
		case '0':
			calcedCost = 100;
		case '1':
			calcedCost = 300;
		case '2':
			calcedCost = 500;
		default:
			calcedCost = 0;
		}

		return calcedCost;

	}

	public int getCost() {
		return cost;
	}

}