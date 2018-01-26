package objects;

public class Tower {

	private int posX;
	private int posY;
	
	private int cost;
	
	private Shot shot;
	
	public Tower(int pPosX, int pPosY, int pStrength, Double pSpeed, Double pVelocity) {
		this.posX = pPosX;
		this.posY = pPosY;
		this.shot= new Shot(pStrength, pSpeed, pVelocity);
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
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
	public void upgradeShot(int strength, Double speed, Double velocity)
	{
		this.shot.setStrength(strength);
		this.shot.setSpeed(speed);
		this.shot.setVelocity(velocity);
	}
	
	public void degradeShot(int strength, Double speed, Double velocity)
	{
		this.shot.setStrength(strength);
		this.shot.setSpeed(speed);
		this.shot.setVelocity(velocity);
	}
	
	public void shoot()
	{
		
	}
	
	
	
}
