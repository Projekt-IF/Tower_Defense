package envoirement;

public class GridCreator {
	
//	private Tile[][] gridLayer;
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
//		gridLayer = grid;
//		printGrid(pHight, pLength);
		return grid;
	}
	/*TODO: Complete automated pathfinding and get the Tile to be accurate about the inheritant */
	/*TODO: Or let the enemy fin their way trough the level*/
//	private void setPath_BaseToSpwan(Tile[][] grid, Integer pHight, Integer pLength) {
//		Tile current = this.getBaseTile();
//		while(current != this.getSpawnerTile()) {
//			Integer yPos = current.getyPos();
//			Integer xPos = current.getxPos();
//			for(int y = yPos-1;y<yPos+1;y++) {
//				if((y>=0)&&(y<=pHight-1)) {
//					for(int x = xPos-1; x<xPos+1;x++) {
//						if((x>=0)&&(x<=pLength-1)) {
//							if((y==yPos-1)&&(x==xPos-1)||(y==yPos-1)&&(x!=xPos+1)||(y==yPos+1)&&(x==xPos-1)||(y==yPos+1)&&(x==xPos+1)) {}
//							else {
//								if(current == this.getBaseTile()) {
//									if(grid[y][x].getContraction() == 'P') {
//										
//									}
//								}
//								else if(current == this.getSpawnerTile()) {
//										
//								}
//								else if(current.getContraction() == 'P') {
//										
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//	}

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
	
//	private void printGrid(Integer hight, Integer length) {
//		for(Integer y = 0; y <= hight-1; y++) {
//			for(Integer x = 0; x <= length-1; x++) {
//				System.out.print(gridLayer[y][x].getContraction());				
//			}
//			System.out.println();
//		}
//	}
//	
//	public static void main(String args[]) {
//		GridCreator g = new GridCreator();
//		g.presetToGrid(Level_Test_Preset.layout, Level_Test_Preset.hight, Level_Test_Preset.length);
//	}
}
