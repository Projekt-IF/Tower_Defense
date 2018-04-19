package objects;

public class Shot {

	private int strength; // strength of shots
	private int speed; // number of shots in 'time'
	private int velocity; // velocity of shots

	public Shot(int pStrength, int pSpeed, int pVelocity) {
		this.strength = pStrength;
		this.speed = pSpeed;
		this.velocity = pVelocity;
	}

	public int getStrength() // returns value of 'strength'
	{
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getSpeed() // returns value of 'speed'
	{
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getVelocity() // returns value of 'velocity'
	{
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
}
