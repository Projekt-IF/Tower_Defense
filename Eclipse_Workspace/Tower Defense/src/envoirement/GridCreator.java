package envoirement;

/**
 * The GridCreator assembles the Array that Contains the Map and connects the
 * Path Tiles to for a Path from the Spawner to the Base Tile.
 */
public class GridCreator {

	private Tile baseTile;
	private Tile spawnerTile;

	/**
	 * The Constructor of the GridCreator
	 */
	public GridCreator() {

	}

	/**
	 * The method goes through the elements of the array of the specified level and
	 * initializes the Tile's Type's. Then it connects the path Tiles via the
	 * pathing method.
	 * 
	 * @param preset
	 * @param pHeight
	 * @param pLength
	 * @return
	 */
	public Tile[][] presetToGrid(Character[][] preset, int pHeight, int pLength) {
		Tile[][] grid = new Tile[pHeight][pLength];
		for (int y = 0; y <= pHeight - 1; y++) {
			for (int x = 0; x <= pLength - 1; x++) {
				Tile nT = new Tile(y, x);
				Character tmp = preset[y][x];
				switch (tmp.charValue()) {
				case 'O':
					nT.setType(Tile.TYPE_UNOC);
					break;
				case 'P':
					nT.setType(Tile.TYPE_PATH);
					break;
				case 'B':
					nT.setType(Tile.TYPE_BASE);
					nT.setNextTile(null);
					nT.setHasNextTile(false);
					this.setBaseTile(nT);
					break;
				case 'S':
					nT.setType(Tile.TYPE_SPAWN);
					this.setSpawnerTile(nT);
					break;
				default:
					nT.setType(Tile.TYPE_UNOC);
					break;
				}
				grid[y][x] = nT;
			}
		}
		this.pathing(grid, pHeight, pLength);
		return grid;
	}

	/**
	 * The method starts from the spawner Tile and follows the path tiles to the
	 * base Tile to form a path enemies can follow.
	 * 
	 * @param pGrid
	 * @param pHeight
	 * @param pLength
	 */
	private void pathing(Tile[][] pGrid, int pHeight, int pLength) {
		Tile current = this.getSpawnerTile();
		while (current != baseTile) {
			while ((current.getHasNextTile() != true) && (current != baseTile)) {
				// The Tile above the current Tile
				if ((0 <= current.getyPos() - 1) && (current.getyPos() - 1 < pHeight)) {
					if ((0 <= current.getxPos()) && (current.getxPos() < pLength)) {
						Tile tmp = pGrid[current.getyPos() - 1][current.getxPos()];
						if ((tmp.getType() == Tile.TYPE_PATH) || (tmp.getType() == Tile.TYPE_BASE)) {
							if (tmp.getHasNextTile() == false) {
								current.setNextTile(tmp);
								current = tmp;
								break;
							}
						}
					}
				}
				// The tile left of the current Tile
				if ((0 <= current.getyPos()) && (current.getyPos() < pHeight)) {
					if ((0 <= current.getxPos() - 1) && (current.getxPos() - 1 < pLength)) {
						Tile tmp = pGrid[current.getyPos()][current.getxPos() - 1];
						if ((tmp.getType() == Tile.TYPE_PATH) || (tmp.getType() == Tile.TYPE_BASE)) {
							if (tmp.getHasNextTile() == false) {
								current.setNextTile(tmp);
								current = tmp;
								break;
							}
						}
					}
				}
				// The Tile right of the current TIle
				if ((0 <= current.getyPos()) && (current.getyPos() < pHeight)) {
					if ((0 <= current.getxPos() + 1) && (current.getxPos() + 1 < pLength)) {
						Tile tmp = pGrid[current.getyPos()][current.getxPos() + 1];
						if ((tmp.getType() == Tile.TYPE_PATH) || (tmp.getType() == Tile.TYPE_BASE)) {
							if (tmp.getHasNextTile() == false) {
								current.setNextTile(tmp);
								current = tmp;
								break;
							}
						}
					}
				}
				// The Tile under the current Tile
				if ((0 <= current.getyPos() + 1) && (current.getyPos() + 1 < pHeight)) {
					if ((0 <= current.getxPos()) && (current.getxPos() < pLength)) {
						Tile tmp = pGrid[current.getyPos() + 1][current.getxPos()];
						if ((tmp.getType() == Tile.TYPE_PATH) || (tmp.getType() == Tile.TYPE_BASE)) {
							if (tmp.getHasNextTile() == false) {
								current.setNextTile(tmp);
								current = tmp;
								break;
							}
						}
					}
				}
			}
		}
	}

	public Tile getBaseTile() {
		return baseTile;
	}

	public void setBaseTile(Tile baseTile) {
		this.baseTile = baseTile;
	}

	public Tile getSpawnerTile() {
		return spawnerTile;
	}

	public void setSpawnerTile(Tile spawnerTile) {
		this.spawnerTile = spawnerTile;
	}
}
