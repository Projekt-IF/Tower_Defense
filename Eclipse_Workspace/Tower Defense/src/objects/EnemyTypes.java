package objects;

/*
 * The Enemy Class is just apparent as the Enemy.
 * The movement is controlled by the Enemy_Controller.
 */
public class EnemyTypes {

	private int speed;
	private int life;
	private int damage;
	private int cost;
	private int type;

	public EnemyTypes(int pType) {
		this.type = pType;
		this.speed = calcSpeed();
		this.life = calcLife();
		this.damage = calcDamage();
		this.cost = calcCost();
	}

	public int calcSpeed() {
		int calcedSpeed = 0;

		switch (type) {
		case '0':
			calcedSpeed = 1;
			break;
		case '1':
			calcedSpeed = 3;
			break;
		case '2':
			calcedSpeed = 5;
			break;
		default:
			calcedSpeed = 0;
			break;
		}

		return calcedSpeed;

	}

	public int getSpeed() {
		return speed;
	}

	public int calcLife() {
		int calcedLife = 0;

		switch (type) {
		case '0':
			calcedLife = 10;
			break;
		case '1':
			calcedLife = 30;
			break;
		case '2':
			calcedLife = 50;
			break;
		default:
			calcedLife = 0;
			break;
		}

		return calcedLife;

	}

	public int getLife() {
		return life;
	}

	public int calcDamage() {
		int calcedDamage = 0;

		switch (type) {
		case '0':
			calcedDamage = 1;
			break;
		case '1':
			calcedDamage = 2;
			break;
		case '2':
			calcedDamage = 5;
			break;
		default:
			calcedDamage = 0;
			break;
		}

		return calcedDamage;
	}

	public int getDamage() {
		return this.damage;
	}

	public int calcCost() {
		int calcedCost = 0;

		switch (type) {
		case '0':
			calcedCost = 50;
			break;
		case '1':
			calcedCost = 100;
			break;
		case '2':
			calcedCost = 250;
			break;
		default:
			calcedCost = 0;
			break;
		}

		return calcedCost;
	}

	public int getCost() {
		return this.cost;
	}

}