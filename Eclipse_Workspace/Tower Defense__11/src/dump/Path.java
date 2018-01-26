package dump;

public class Path {
	
	private Integer yPos;
	private Integer xPos;
	
	private Path nextPathTile;
	private Path previousPathTile;
	
	
	public Path(Integer pYPos, Integer pXPos) {
		this.setxPos(pYPos);
		this.setyPos(pXPos);
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

	public Path getPreviousPathTile() {
		return previousPathTile;
	}

	public void setPreviousPathTile(Path previousPathTile) {
		this.previousPathTile = previousPathTile;
	}

}
