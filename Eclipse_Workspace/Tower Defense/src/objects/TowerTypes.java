package objects;

/*
 * The Enemy Class is just apparent as the Enemy.
 * The movement is controlled by the Enemy_Controller.
 */
public class TowerTypes {

	public TowerTypes() {
	}

	public int calcCooldown(int pType) {
		int calcedCooldown = 0;

		switch (pType) {
		case 1:
			calcedCooldown = 5;
			break;
		case 2:
			calcedCooldown = 7;
			break;
		case 3:
			calcedCooldown = 10;
			break;
		default:
			calcedCooldown = 0;
			break;
		}

		return calcedCooldown;

	}

	public int calcStrength(int pType) {
		int calcedStrength = 0;

		switch (pType) {
		case 1:
			calcedStrength = 5;
			break;
		case 2:
			calcedStrength = 10;
			break;
		case 3:
			calcedStrength = 15;
			break;
		default:
			calcedStrength = 0;
			break;
		}

		return calcedStrength;

	}

	public int calcRange(int pType) {
		int calcedRange = 0;

		switch (pType) {
		case 1:
			calcedRange = 1;
			break;
		case 2:
			calcedRange = 2;
			break;
		case 3:
			calcedRange = 3;
			break;
		default:
			calcedRange = 0;
			break;
		}

		return calcedRange;

	}

	public int calcCost(int pType) {
		int calcedCost = 0;

		switch (pType) {
		case 1:
			calcedCost = 100;
			break;
		case 2:
			calcedCost = 300;
			break;
		case 3:
			calcedCost = 500;
			break;
		default:
			calcedCost = 0;
			break;
		}

		return calcedCost;

	}
}