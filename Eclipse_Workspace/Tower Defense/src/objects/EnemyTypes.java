package objects;

/*
 * The Enemy Class is just apparent as the Enemy.
 * The movement is controlled by the Enemy_Controller.
 */
public class EnemyTypes {

	public EnemyTypes() {
	}

	public int calcSpeed(int pType) {
		int calcedSpeed = 0;

		switch (pType) {
		case 1:
			calcedSpeed = 1;
			break;
		case 2:
			calcedSpeed = 1;
			break;
		case 3:
			calcedSpeed = 1;
			break;
		default:
			calcedSpeed = 0;
			break;
		}
		return calcedSpeed;

	}

	public int calcLife(int pType) {
		int calcedLife = 0;

		switch (pType) {
		case 1:
			calcedLife = 5;
			break;
		case 2:
			calcedLife = 10;
			break;
		case 3:
			calcedLife = 15;
			break;
		default:
			calcedLife = 0;
			break;
		}
		return calcedLife;

	}

	public int calcDamage(int pType) {
		int calcedDamage = 0;

		switch (pType) {
		case 1:
			calcedDamage = 1;
			break;
		case 2:
			calcedDamage = 2;
			break;
		case 3:
			calcedDamage = 5;
			break;
		default:
			calcedDamage = 0;
			break;
		}
		return calcedDamage;
	}

	public int calcCost(int pType) {
		int calcedCost = 0;

		switch (pType) {
		case 1:
			calcedCost = 50;
			break;
		case 2:
			calcedCost = 100;
			break;
		case 3:
			calcedCost = 250;
			break;
		default:
			calcedCost = 0;
			break;
		}
		return calcedCost;
	}

	public int calcBounty(int pType) {
		int calcedBounty = 0;

		switch (pType) {
		case 1:
			calcedBounty = 25;
			break;
		case 2:
			calcedBounty = 50;
			break;
		case 3:
			calcedBounty = 75;
			break;
		default:
			calcedBounty = 0;
			break;
		}
		return calcedBounty;
	}

}