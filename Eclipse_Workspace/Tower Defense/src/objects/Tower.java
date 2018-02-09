package objects;

import java.util.ArrayList;

import envoirement.Tile;

public class Tower {

	private int posX;
	private int posY;
	
	private int cost;
	
	private int range;
	private ArrayList<Tile> tilesInRange = new ArrayList<Tile>();
	
	private Shot shot;
	
	public Tower(int pPosX, int pPosY, int pRange, int pStrength, double pSpeed, double pVelocity) {
		this.posX = pPosX;
		this.posY = pPosY;
		this.range = pRange;
		this.shot= new Shot(pStrength, pSpeed, pVelocity);
	}
	
	public void upgradeShot(int strength, double speed, double velocity)
	{
		this.shot.setStrength(strength);
		this.shot.setSpeed(speed);
		this.shot.setVelocity(velocity);
	}

	public void degradeShot(int strength, double speed, double velocity)
	{
		this.shot.setStrength(strength);
		this.shot.setSpeed(speed);
		this.shot.setVelocity(velocity);
	}

	public void shoot()
	{
		
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
	
	
	/**
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * @param range the range to set
	 */
	public void setRange(int range) {
		this.range = range;
	}

	/**
	 * @return the tilesInRange
	 */
	public ArrayList<Tile> getTilesInRange() {
		return tilesInRange;
	}

	/**
	 * @param tilesInRange the tilesInRange to set
	 */
	public void setTilesInRange(ArrayList<Tile> tilesInRange) {
		this.tilesInRange = tilesInRange;
	}
	
	
	
}
