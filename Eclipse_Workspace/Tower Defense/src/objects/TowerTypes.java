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
			calcedCooldown = 2;
		case 2:
			calcedCooldown = 4;
		case 3:
			calcedCooldown = 6;
		default:
			calcedCooldown = 0;
		}

		return calcedCooldown;

	}

	public int calcSpeed(int pType) {
		int calcedSpeed = 0;

		switch (pType) {
		case 1:
			calcedSpeed = 2;
		case 2:
			calcedSpeed = 4;
		case 3:
			calcedSpeed = 6;
		default:
			calcedSpeed = 0;
		}

		return calcedSpeed;

	}

	public int calcStrength(int pType) {
		int calcedStrength = 0;

		switch (pType) {
		case 1:
			calcedStrength = 2;
		case 2:
			calcedStrength = 5;
		case 3:
			calcedStrength = 10;
		default:
			calcedStrength = 0;
		}

		return calcedStrength;

	}

	public int calcRange(int pType) {
		int calcedRange = 0;

		switch (pType) {
		case 1:
			calcedRange = 2;
		case 2:
			calcedRange = 3;
		case 3:
			calcedRange = 5;
		default:
			calcedRange = 0;
		}

		return calcedRange;

	}

	public int calcCost(int pType) {
		int calcedCost = 0;

		switch (pType) {
		case 1:
			calcedCost = 100;
		case 2:
			calcedCost = 300;
		case 3:
			calcedCost = 500;
		default:
			calcedCost = 0;
		}

		return calcedCost;

	}
}