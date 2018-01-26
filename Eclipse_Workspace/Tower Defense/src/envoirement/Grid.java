package envoirement;
import envoirement.Tile;

/**
 * The Grid Class represents the Level the Player is playing on.
 * Therefore the Map is represented as an Array.
 */
public class Grid {
	private int HEIGHT;
	private int LENGTH;
	
	private Tile[][] gridLayer;

	//Hallo
	private Tile baseTile;
	private Tile spawnerTile;
	
	private GridCreator gc;
	
	/**
	 * The Constructor initializes the GridCreator and loads the given level.
	 * @param level 
	 */
	public Grid(String level) {
		gc = new GridCreator();
		this.gridLayer = null;
		loadLevel(level);
	}
	
	/**
	 * The loadLevel Method loads the Specialized level and initializes the Class variables 
	 * to the grid
	 * 
	 * The level is saved as an Array.
	 * @param level
	 */
	private void loadLevel(String level) {
		if(level.equalsIgnoreCase("TEST")) {
			this.gridLayer = gc.presetToGrid(Level_Test_Preset.LAYOUT, Level_Test_Preset.HEIGHT, Level_Test_Preset.LENGTH);
			this.HEIGHT = Level_Test_Preset.HEIGHT;
			this.LENGTH = Level_Test_Preset.LENGTH;
			this.setBaseTile(gc.getBaseTile());
			this.setSpawnerTile(gc.getSpawnerTile());
		} else if(level.equalsIgnoreCase("ONE")) {
			this.gridLayer = gc.presetToGrid(Level_1_Preset.LAYOUT, Level_1_Preset.HEIGHT, Level_1_Preset.LENGTH);
			this.HEIGHT = Level_1_Preset.HEIGHT;
			this.LENGTH = Level_1_Preset.LENGTH;
			this.setBaseTile(gc.getBaseTile());
			this.setSpawnerTile(gc.getSpawnerTile());
		} else if(level.equalsIgnoreCase("TWO")) {
			this.gridLayer = gc.presetToGrid(Level_2_Preset.LAYOUT, Level_2_Preset.HEIGHT, Level_2_Preset.LENGTH);
			this.HEIGHT = Level_2_Preset.HEIGHT;
			this.LENGTH = Level_2_Preset.LENGTH;
			this.setBaseTile(gc.getBaseTile());
			this.setSpawnerTile(gc.getSpawnerTile());
		}
	}
	
	private void printGrid() {
		for(int y = 0; y <= HEIGHT-1; y++) {
			for(int x = 0; x <= LENGTH-1; x++) {
				System.out.print(this.gridLayer[y][x].getType() + " ");				
			}
			System.out.println();
		}
	}
	
	private void printPath() {
		Tile current = this.spawnerTile;
		while(current.getNextTile() != null) {
			System.out.print("(" + current.getxPos() + "/" + current.getyPos() + ")");
			current = current.getNextTile();
		}
		System.out.print("(" + current.getxPos() + "/" + current.getyPos() + ")");
		System.out.println();
	}
	
//	public static void main(String args[]) {
//		Grid g = new Grid("Test");
//		g.printGrid();
//		g.printPath();
//		g = new Grid("One");
//		g.printGrid();
//		g.printPath();
//		g = new Grid("tWo");
//		g.printGrid();
//		g.printPath();
//	}
	
	public Tile[][] getGridLayer() {
		return gridLayer;
	}

	public void setGridLayer(Tile[][] gridLayer) {
		this.gridLayer = gridLayer;
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
