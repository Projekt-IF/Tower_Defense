package envoirement;

public class Tile {
	
	
	public static final int TYPE_UNOC = 0;
	public static final int TYPE_BASE = 1;
	public static final int TYPE_PATH = 2;
	public static final int TYPE_SPAWN = 3;
	public static final int TYPE_TOWER = 4;
	
	private int type;
	
	private boolean statusOccupied;
	
	private Tile nextTile;
	
	private Boolean hasNextTile;
	
	private Integer yPos;
	private Integer xPos;
	
	public Tile(Integer pYPos, Integer pXPos) {
		this.setStatusOccupied(false);
		this.setHasNextTile(false);
		this.yPos = pYPos;
		this.xPos = pXPos;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		this.setStatusOccupied(true);
	}

	public Tile getNextTile() {
		return nextTile;
	}

	public void setNextTile(Tile nextTile) {
		this.setHasNextTile(true);
		this.nextTile = nextTile;
	}

	public boolean isStatusOccupied() {
		return statusOccupied;
	}

	public void setStatusOccupied(boolean statusOccupied) {
		this.statusOccupied = statusOccupied;
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

	public Boolean getHasNextTile() {
		return hasNextTile;
	}

	public void setHasNextTile(Boolean hasNextTile) {
		this.hasNextTile = hasNextTile;
	}
	
}
