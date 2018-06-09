package envoirement;

/**
 *	The Class Level_2_Preset contains the static final properties the Level's Grid will have.
 */
public class Level_2_Preset {
	
	/* Legend for the Grid Mapping
	 * O = Tile Field 		[Towers can be placed on here]
	 * B = BaseTile	  		[Enemies want to go here]
	 * S = SpawnerTile	  	[Enemies spawn here]
	 * P = Path				[Enemies follow these Tiles to the Base]
	 * 
	 *     	  0 1 2 3 4 5 6 7 8
	 *   	0 B P P P P P P P P
	 *   	1 O O O O O O O O P
	 * 	 	2 P P P P P P P P P
	 *	 	3 P O O O O O O O O
	 * 	 	4 P P P P P P P P P
	 * 		5 O O O O O O O O P
	 * 		6 P P P P P P P P P
	 * 		7 P O O O O O O O O
	 * 		8 P P P P P P P P S
	 * 				 		*/
	
	//The Array represents the type of the Tiles in the Grid
	public static final Character[][] LAYOUT = {{'B','P','P','P','P','P','P','P','P'},
												{'O','O','O','O','O','O','O','O','P'},
												{'P','P','P','P','P','P','P','P','P'},
												{'P','O','O','O','O','O','O','O','O'},
												{'P','P','P','P','P','P','P','P','P'},
												{'O','O','O','O','O','O','O','O','P'},
												{'P','P','P','P','P','P','P','P','P'},
												{'P','O','O','O','O','O','O','O','O'},
												{'P','P','P','P','P','P','P','P','S'}};
	
	//The Grid's height
	public static final int HEIGHT = 9;
	
	//The Grid's length
	public static final int LENGTH = 9;
	
	//The Name
	public static final String NAME = "Level_2_Preset";
	
	//The Wave Files Name as String
//	public static final String WAVEFILENAME = "Wave_2";

}