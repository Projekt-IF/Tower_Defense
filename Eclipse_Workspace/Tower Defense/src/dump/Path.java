package dump;

public class Path {
	
	private int yPos;
	private int xPos;
	
	private Path nextPathTile;
	private Path previousPathTile;
	
	
	public Path(int pYPos, int pXPos) {
		this.setxPos(pYPos);
		this.setyPos(pXPos);
	}
	
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
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
