package objects;

public class Tower {

	private Integer posX;
	private Integer posY;
	
	private Integer cost;
	
	private Shot shot;
	
	public Tower(Integer pPosX, Integer pPosY, Integer pStrength, Double pSpeed, Double pVelocity) {
		this.posX = pPosX;
		this.posY = pPosY;
		this.shot= new Shot(pStrength, pSpeed, pVelocity);
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
	
	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	
	public void upgradeShot(Integer strength, Double speed, Double velocity)
	{
		this.shot.setStrength(strength);
		this.shot.setSpeed(speed);
		this.shot.setVelocity(velocity);
	}
	
	public void degradeShot(Integer strength, Double speed, Double velocity)
	{
		this.shot.setStrength(strength);
		this.shot.setSpeed(speed);
		this.shot.setVelocity(velocity);
	}
	
	public void shoot()
	{
		
	}
	
	
	
}
