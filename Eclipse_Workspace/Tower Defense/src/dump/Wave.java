package dump;

public class Wave {

	private int enemyNumber;
	private int enemyLevel;
	private int enemyType;

	public Wave(int pEnemyNumber, int pEnemyLevel, int pEnemyType) {
		this.setEnemyNumber(pEnemyNumber);
		this.setEnemyLevel(pEnemyLevel);
		this.setEnemyType(pEnemyType);
	}

	public int getEnemyNumber() {
		return enemyNumber;
	}

	public void setEnemyNumber(int enemyNumber) {
		this.enemyNumber = enemyNumber;
	}

	public int getEnemyLevel() {
		return enemyLevel;
	}

	public void setEnemyLevel(int enemyLevel) {
		this.enemyLevel = enemyLevel;
	}

	public int getEnemyType() {
		return enemyType;
	}

	public void setEnemyType(int enemyType) {
		this.enemyType = enemyType;
	}

}
