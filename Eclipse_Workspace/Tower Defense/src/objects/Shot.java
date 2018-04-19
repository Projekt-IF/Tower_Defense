package objects;

public class Shot {

	private int strength; // strength of shots
	private int speed; // speed of shots

	public Shot(int pStrength, int pSpeed) {
		this.strength = pStrength;
		this.speed = pSpeed;
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

}
