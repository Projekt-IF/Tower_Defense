package dump;

public class Spawner {

	private int yPos;
	private int xPos;

	private Path nextPathTile;

	public Spawner(int pYPos, int pXPos) {
		this.setyPos(pYPos);
		this.setxPos(pXPos);
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
}
