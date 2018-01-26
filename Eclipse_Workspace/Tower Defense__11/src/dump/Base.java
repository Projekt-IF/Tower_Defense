package dump;

import envoirement.Tile;

public class Base extends Tile{
	
	private Integer xPos;
	private Integer yPos;
	
	private Path previousPathTile;
	
	public Base(Integer pYPos, Integer pXPos) {
		super(pYPos,pXPos);
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

	public Path getPreviousPathTile() {
		return previousPathTile;
	}

	public void setPreviousPathTile(Path previousPathTile) {
		this.previousPathTile = previousPathTile;
	}

}
