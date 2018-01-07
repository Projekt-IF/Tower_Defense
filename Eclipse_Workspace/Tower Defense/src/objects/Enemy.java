package objects;

/*
 * The Enemy Class is just apparent as the Enemy.
 * The movement is controlled by the Enemy_Controller.
 */
public class Enemy {
	
	private Integer posX;
	private Integer posY;
	
	private Double life;
	private Double speed;
	
	private Integer cost;
	
	/*
	 * 
	 */
	public Enemy() {
		
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
	
}
