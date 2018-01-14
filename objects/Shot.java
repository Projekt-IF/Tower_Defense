package objects;

public class Shot {
	
	private Integer strength;		//strength of shots
	private Double speed;			//number of shots in 'time'
	private Double velocity;		//velocity of shots
	
	
	public Shot(Integer pStrength, Double pSpeed, Double pVelocity)
	{
		this.strength=pStrength;
		this.speed=pSpeed;
		this.velocity=pVelocity;
	}
	
	public Integer getStrength()				//returns value of 'strength'
	{
		return strength;
	}
	
	public void setStrength(Integer strength)	
	{
		this.strength=strength;
	}
	
	public Double getSpeed() 					//returns value of 'speed'
	{
		return speed;
	}

	public void setSpeed(Double speed) 
	{
		this.speed = speed;
	}
	
	public Double getVelocity() 				//returns value of 'velocity'
	{
		return velocity;
	}

	public void setVelocity(Double velocity) 
	{
		this.velocity = velocity;
	}
	
}