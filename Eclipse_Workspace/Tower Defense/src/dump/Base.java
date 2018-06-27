package dump;

import envoirement.Tile;

/**
 * 
 * @author Jonas
 *
 */
public class Base extends Tile {

	private int xPos;
	private int yPos;

	private Path previousPathTile;

	public Base(int pYPos, int pXPos) {
		super(pYPos, pXPos);
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

	public Path getPreviousPathTile() {
		return previousPathTile;
	}

	public void setPreviousPathTile(Path previousPathTile) {
		this.previousPathTile = previousPathTile;
	}

}
