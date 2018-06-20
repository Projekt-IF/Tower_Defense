package envoirement;

/**
 *	The Class Level_Test_Preset contains the static final properties the Level's Grid will have.
 */
public class Level_Test_Preset {
	
	/* Legend for the Grid Mapping
	 * O = Tile Field 		[Towers can be placed on here]
	 * B = BaseTile	  		[Enemies want to go here]
	 * S = SpawnerTile	  	[Enemies spawn here]
	 * P = Path				[Enemies follow these Tiles to the Base]
	 * 
	 *     	  0 1 2 3 4 5 6 7 8 9
	 *   	0 P P P P O P P P P O
	 *   	1 P O O P O P O O P P
	 * 	 	2 B O O P O P O O O P
	 *	 	3 O P P P O S O P P P
	 * 	 	4 P P O O O O O P O O
	 * 		5 P O O P P P P P O O
	 * 		6 P O O P O O O O O O
	 * 		7 P O O P P P P P P O
	 * 		8 P O O O O O O O P O
	 * 		9 P P P P P P P P P O
	 * 				 			 */
	
	//The Array represents the type of the Tiles in the Grid
	public static final Character[][] LAYOUT = {{'P','P','P','P','O','P','P','P','P','O'},
												{'P','O','O','P','O','P','O','O','P','P'},
												{'B','O','O','P','O','P','O','O','O','P'},
												{'O','P','P','P','O','S','O','P','P','P'},
												{'P','P','O','O','O','O','O','P','O','O'},
												{'P','O','O','P','P','P','P','P','O','O'},
												{'P','O','O','P','O','O','O','O','O','O'},
												{'P','O','O','P','P','P','P','P','P','O'},
												{'P','O','O','O','O','O','O','O','P','O'},
												{'P','P','P','P','P','P','P','P','P','O'}};
	
	//The Grid's height
	public static final int HEIGHT = 10;
	
	//The Grid's length
	public static final int LENGTH = 10;
	
	//The Name
	public static final String NAME = "Level_Test_Preset";
	
	//The Wave Files Name as String
//	public static final String WAVEFILeNAME = "Wave_Test";

}
