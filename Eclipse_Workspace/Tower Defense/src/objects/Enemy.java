package objects;

/*
 * The Enemy Class is just apparent as the Enemy.
 * The movement is controlled by the Enemy_Controller.
 */
public class Enemy {
	
	/**
	 * 
	 */
	public static final int TYPE_NORMAL = 1;
	
	private int posX;
	private int posY;
	
	private int type;
	private int level;
	
	private Double life;
	private Double speed;
	
	private int cost;
	
	/*
	 * 
	 */
	public Enemy(int pPosX, int pPosY, int pType, int pLevel) {
		this.posX = pPosX;
		this.posY = pPosY;
		this.life = this.calcLife(pType, pLevel);
		this.speed = this.calcSpeed(pType, pLevel);
	}
	
	
	public Boolean checkAlife() {
		if(life <= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @param pType
	 * @param pLevel
	 * @return
	 */
	private Double calcLife(int pType, int pLevel) {
		Double tmpLife = (pType * 5.0) + (pLevel * 10);
		return tmpLife;
	}
	
	/**
	 * 
	 * @param pType
	 * @param pLevel
	 * @return
	 */
	private Double calcSpeed(int pType, int pLevel) {
		Double tmpSpeed = pType * 0.5 + pLevel * 0.25;
		return tmpSpeed;
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

	public Double getLife() {
		return life;
	}

	public void setLife(Double life) {
		this.life = life;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
