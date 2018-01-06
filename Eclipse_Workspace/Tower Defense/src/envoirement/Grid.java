package envoirement;
import envoirement.Tile;

public class Grid {
	private Integer hight;
	private Integer length;
	
	private Tile[][] gridLayer;
	
	private Tile baseTile;
	private Tile spawnerTile;
	
	private GridCreator gc;
	
	public Grid(String level) {
		gc = new GridCreator();
		this.gridLayer = null;
		
		if(level.equalsIgnoreCase("TEST")) {
			this.gridLayer = gc.presetToGrid(Level_Test_Preset.layout, Level_Test_Preset.hight, Level_Test_Preset.length);
			this.hight = Level_Test_Preset.hight;
			this.length = Level_Test_Preset.length;
			this.baseTile = gc.getBaseTile();
			this.spawnerTile = gc.getSpawnerTile();
		} else if(level.equalsIgnoreCase("ONE")) {
			this.gridLayer = gc.presetToGrid(Level_1_Preset.layout, Level_1_Preset.hight, Level_1_Preset.length);
			this.hight = Level_1_Preset.hight;
			this.length = Level_1_Preset.length;
			this.baseTile = gc.getBaseTile();
			this.spawnerTile = gc.getSpawnerTile();
		} else if(level.equalsIgnoreCase("TWO")) {
			this.gridLayer = gc.presetToGrid(Level_2_Preset.layout, Level_2_Preset.hight, Level_2_Preset.length);
			this.hight = Level_2_Preset.hight;
			this.length = Level_2_Preset.length;
			this.baseTile = gc.getBaseTile();
			this.spawnerTile = gc.getSpawnerTile();
		}
	}
	
	public void printGrid() {
		for(Integer y = 0; y <= hight-1; y++) {
			for(Integer x = 0; x <= length-1; x++) {
				System.out.print(this.gridLayer[y][x].getType() + " ");				
			}
			System.out.println();
		}
	}
	
	public void printPath() {
		Tile current = this.spawnerTile;
		while(current.getNextTile() != null) {
			System.out.print("(" + current.getxPos() + "/" + current.getyPos() + ")");
			current = current.getNextTile();
		}
		System.out.print("(" + current.getxPos() + "/" + current.getyPos() + ")");
		System.out.println();
	}
	
	public static void main(String args[]) {
		Grid g = new Grid("Test");
		g.printGrid();
		g.printPath();
		g = new Grid("One");
		g.printGrid();
		g.printPath();
		g = new Grid("tWo");
		g.printGrid();
		g.printPath();
	}
}
