package objects;

/*
 * The Enemy Class is just apparent as the Enemy.
 * The movement is controlled by the Enemy_Controller.
 */
public class EnemyTypes {

	private int speed;
	private int life;
	private int type;

	public EnemyTypes(int pType) {
		this.type = pType;
		this.speed = calcSpeed();
		this.life = calcLife();
	}

	public int calcSpeed() {
		int calcedSpeed = 0;

		switch (type) {
		case '0':
			calcedSpeed = 1;
		case '1':
			calcedSpeed = 3;
		case '2':
			calcedSpeed = 5;
		default:
			calcedSpeed = 0;
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
		case '1':
			calcedLife = 30;
		case '2':
			calcedLife = 50;
		default:
			calcedLife = 0;
		}

		return calcedLife;

	}

	public int getLife() {
		return life;
	}

}