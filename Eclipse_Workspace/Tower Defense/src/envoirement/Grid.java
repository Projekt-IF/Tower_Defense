package envoirement;
import envoirement.Tile;
import linear.List;

public class Grid {
	private Integer hight;
	private Integer length;
	
	private Tile[][] gridLayer;
	
	private Tile baseTile;
	private Tile spawnerTile;
	
	private List<Tile> path;
	
	private GridCreator gc;
	
	public Grid(String level) {
		gc = new GridCreator();
		gridLayer = null;
		
		if(level.equalsIgnoreCase("TEST")) {
			gridLayer = gc.presetToGrid(Level_Test_Preset.layout, Level_Test_Preset.hight, Level_Test_Preset.length);
			this.hight = Level_Test_Preset.hight;
			this.length = Level_Test_Preset.length;
			this.baseTile = gc.getBaseTile();
			this.spawnerTile = gc.getSpawnerTile();
		} else if(level.equalsIgnoreCase("ONE")) {
			gridLayer = gc.presetToGrid(Level_1_Preset.layout, Level_1_Preset.hight, Level_1_Preset.length);
			this.hight = Level_Test_Preset.hight;
			this.length = Level_Test_Preset.length;
			this.baseTile = gc.getBaseTile();
			this.spawnerTile = gc.getSpawnerTile();
		}
	}
	
	public void printGrid() {
		for(Integer y = 0; y <= hight-1; y++) {
			for(Integer x = 0; x <= length-1; x++) {
				System.out.print(gridLayer[y][x].getType() + " ");				
			}
			System.out.println();
		}
	}
	
	public static void main(String args[]) {
		Grid g = new Grid("Test");
		g.printGrid();
		g = new Grid("One");
		g.printGrid();
	}
}
