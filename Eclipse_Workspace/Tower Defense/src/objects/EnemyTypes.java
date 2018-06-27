package objects;

/**
 * The EnemyTypes class is used to calculate different attribute values.
 * 
 * @author Jonas Schröder, Ebba Siebold
 * @version 1.0
 */
public class EnemyTypes {

	/**
	 * Constructs an Object of the EnemyTypes Class.
	 */
	public EnemyTypes() {
	}

	/**
	 * Calculates the Enemy's speed by its type which determines how fast the Enemy
	 * can move. It is given as the Cool down in seconds.
	 * 
	 * @param pType
	 *            The Enemy's type.
	 * @return (int) calcedSpeed
	 */
	public int calcSpeed(int pType) {
		int calcedSpeed = 0;

		switch (pType) {
		case 1:
			calcedSpeed = 1;
			break;
		case 2:
			calcedSpeed = 2;
			break;
		case 3:
			calcedSpeed = 4;
			break;
		default:
			calcedSpeed = 0;
			break;
		}
		return calcedSpeed;

	}

	/**
	 * Calculates the Enemy's life by its type.
	 * 
	 * @param pType
	 *            The Enemy's type.
	 * @return (int) calcedLife
	 */
	public int calcLife(int pType) {
		int calcedLife = 0;

		switch (pType) {
		case 1:
			calcedLife = 15;
			break;
		case 2:
			calcedLife = 20;
			break;
		case 3:
			calcedLife = 25;
			break;
		default:
			calcedLife = 0;
			break;
		}
		return calcedLife;

	}

	/**
	 * Calculates the Enemy's damage by its type. It is given as the amount of
	 * health points the player looses.
	 * 
	 * @param pType
	 *            The Enem's type.
	 * @return (int) calcedDamage
	 */
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

	/**
	 * Calculates the Enemy's cost by its type which determines how much the player
	 * has to pay for an extra 3 Enemies of that type.
	 * 
	 * @param pType
	 *            The Enemy's type.
	 * @return (int) calcedCost
	 */
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

	/**
	 * Calculates the Enemy's bounty by its type which determines how much money the
	 * player gets granted when killing the Enemy.
	 * 
	 * @param pType
	 *            The Enmey's type.
	 * @return (int) calcedBounty
	 */
	public int calcBounty(int pType) {
		int calcedBounty = 0;

		switch (pType) {
		case 1:
			calcedBounty = 5;
			break;
		case 2:
			calcedBounty = 10;
			break;
		case 3:
			calcedBounty = 20;
			break;
		default:
			calcedBounty = 0;
			break;
		}
		return calcedBounty;
	}

}