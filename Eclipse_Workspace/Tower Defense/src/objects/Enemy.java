package objects;

/*
 * The Enemy Class is just apparent as the Enemy.
 * The movement is controlled by the Enemy_Controller.
 */
public class Enemy {
	
	/**
	 * 
	 */
	public static final Integer TYPE_NORMAL = 1;
	
	private Integer posX;
	private Integer posY;
	
	private Integer type;
	private Integer level;
	
	private Double life;
	private Double speed;
	
	private Integer cost;
	
	/*
	 * 
	 */
	public Enemy(Integer pPosX, Integer pPosY, Integer pType, Integer pLevel) {
		this.posX = pPosX;
		this.posY = pPosY;
		this.life = this.calcLife(pType, pLevel);
	}

	/**
	 * 
	 * @param pType
	 * @param pLevel
	 * @return
	 */
	private Double calcLife(Integer pType, Integer pLevel) {
		Double tmpLife = (pType * 5.0) + (pLevel * 10);
		return tmpLife;
	}

	public Integer getPosX() {
		return posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}

	public Integer getPosY() {
		return posY;
	}

	public void setPosY(Integer posY) {
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

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
