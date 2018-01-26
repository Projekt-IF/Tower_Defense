package dump;

public class Spawner {
	
	private Integer yPos;
	private Integer xPos;
	
	private Path nextPathTile;
	
	public Spawner(Integer pYPos, Integer pXPos) {
		this.setyPos(pYPos);
		this.setxPos(pXPos);
	}

	public Integer getyPos() {
		return yPos;
	}

	public void setyPos(Integer yPos) {
		this.yPos = yPos;
	}

	public Integer getxPos() {
		return xPos;
	}

	public void setxPos(Integer xPos) {
		this.xPos = xPos;
	}

	public Path getNextPathTile() {
		return nextPathTile;
	}

	public void setNextPathTile(Path nextPathTile) {
		this.nextPathTile = nextPathTile;
	}
}
