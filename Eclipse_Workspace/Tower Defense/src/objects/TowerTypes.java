package objects;

/**
 * The TowerTypes class is used to calculate different attribute values.
 * 
 * @author Jonas Schröder, Ebba Siebold
 * @version 1.0
 * 
 */
public class TowerTypes {

	/**
	 * Constructs an Object of the TowerTypes class.
	 */
	public TowerTypes() {
	}

	/**
	 * Calculates the Towers cool down by its type which determines how fast the
	 * Tower can shoot. It is given as seconds.
	 * 
	 * @param pType
	 *            The type of the Tower.
	 * @return (int) calcedCooldown
	 */
	public int calcCooldown(int pType) {
		int calcedCooldown = 0;

		switch (pType) {
		case 1:
			calcedCooldown = 2;
			break;
		case 2:
			calcedCooldown = 4;
			break;
		case 3:
			calcedCooldown = 7;
			break;
		default:
			calcedCooldown = 0;
			break;
		}

		return calcedCooldown;

	}

	/**
	 * Calculates the Towers Strength by its type which determines how much damage
	 * is dealt.
	 * 
	 * @param pType
	 *            The type of the Tower.
	 * @return (int) calcedStength
	 */
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

	/**
	 * Calculates the Towers Range by its type which determines how far the Tower
	 * can shoot. It is given as the number of Tiles around it.
	 * 
	 * @param pType
	 *            The type of the Tower.
	 * @return (int) calcedRange
	 */
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

	/**
	 * Calculates the Towers Cost by its type which determines how much the Player
	 * has to pay.
	 * 
	 * @param pType
	 *            The type of the Tower.
	 * @return (int) calcedCost
	 */
	public int calcCost(int pType) {
		int calcedCost = 0;

		switch (pType) {
		case 1:
			calcedCost = 100;
			break;
		case 2:
			calcedCost = 450;
			break;
		case 3:
			calcedCost = 1000;
			break;
		default:
			calcedCost = 0;
			break;
		}

		return calcedCost;

	}
}