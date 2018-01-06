package envoirement;

public class GridCreator {
	
	private Tile baseTile;
	private Tile spawnerTile;
	
	public GridCreator() {
		
	}

	public Tile[][] presetToGrid(Character[][] preset, Integer pHight, Integer pLength){
		Tile[][] grid = new Tile[pHight][pLength];
		
		for(Integer y = 0; y <= pHight-1; y++) {
			for(Integer x = 0; x <= pLength-1; x++) {
				Tile nT = new Tile(y, x);
				Character tmp = preset[y][x];
				switch(tmp.charValue()) {
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
		this.pathing(grid, pHight, pLength);
		return grid;
	}
	
	private void pathing(Tile[][] pGrid, Integer pHight, Integer pLength) {
		Tile current = this.getSpawnerTile();
		while(current != baseTile) {
			while((current.getHasNextTile() != true)&&(current != baseTile)) {
				//The Tile above the current Tile
				if((0 <= current.getyPos()-1)&&(current.getyPos()-1 < pHight)) {
					if((0 <= current.getxPos())&&(current.getxPos() < pLength)) {
						Tile tmp = pGrid[current.getyPos()-1][current.getxPos()];
						if((tmp.getType() == Tile.TYPE_PATH) || (tmp.getType() == Tile.TYPE_BASE)) {
							if(tmp.getHasNextTile() == false) {
								current.setNextTile(tmp);
								current = tmp;
								break;
							}
						}
					}
				}
				//The tile left of the current Tile
				if((0 <= current.getyPos())&&(current.getyPos() < pHight)) {
					if((0 <= current.getxPos()-1)&&(current.getxPos()-1 < pLength)) {
						Tile tmp = pGrid[current.getyPos()][current.getxPos()-1];
						if((tmp.getType() == Tile.TYPE_PATH) || (tmp.getType() == Tile.TYPE_BASE)) {
							if(tmp.getHasNextTile() == false) {
								current.setNextTile(tmp);
								current = tmp;
								break;
							}
						}
					}
				}
				//The Tile right of the current TIle
				if((0 <= current.getyPos())&&(current.getyPos() < pHight)) {
					if((0 <= current.getxPos()+1)&&(current.getxPos()+1 < pLength)) {
						Tile tmp = pGrid[current.getyPos()][current.getxPos()+1];
						if((tmp.getType() == Tile.TYPE_PATH) || (tmp.getType() == Tile.TYPE_BASE)) {
							if(tmp.getHasNextTile() == false) {
								current.setNextTile(tmp);
								current = tmp;
								break;
							}
						}
					}
				}
				//The Tile under the current Tile
				if((0 <= current.getyPos()+1)&&(current.getyPos()+1 < pHight)) {
					if((0 <= current.getxPos())&&(current.getxPos() < pLength)) {
						Tile tmp = pGrid[current.getyPos()+1][current.getxPos()];
						if((tmp.getType() == Tile.TYPE_PATH) || (tmp.getType() == Tile.TYPE_BASE)) {
							if(tmp.getHasNextTile() == false) {
								current.setNextTile(tmp);
								current = tmp;
								break;
							}
						}
					}
				}
			}
		}
		System.out.println("Pathing Complete");
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
