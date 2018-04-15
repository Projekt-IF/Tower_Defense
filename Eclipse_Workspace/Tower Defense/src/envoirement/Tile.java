package envoirement;

/**
 * The Tile Class represents the content of the specific point on the level's
 * map. It also saves the path's, or spawner's connected path Tile.
 */
public class Tile {

	// The viable types of Tiles
	public static final int TYPE_UNOC = 0;
	public static final int TYPE_BASE = 1;
	public static final int TYPE_PATH = 2;
	public static final int TYPE_SPAWN = 3;
	public static final int TYPE_TOWER = 4;

	private int type;

	private boolean statusOccupied;

	private Tile nextTile;

	private Boolean hasNextTile;

	// The Tiles position in the level
	private int yPos;
	private int xPos;

	/**
	 * The Constructor initializes the Tile and gives the position in the level.
	 * 
	 * @param pYPos
	 * @param pXPos
	 */
	public Tile(int pYPos, int pXPos) {
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

	public Boolean getHasNextTile() {
		return hasNextTile;
	}

	public void setHasNextTile(Boolean hasNextTile) {
		this.hasNextTile = hasNextTile;
	}

}
