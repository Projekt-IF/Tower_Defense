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
	 *     	  0 1 2 3 4
	 *   	0 O O O O O
	 *   	1 O O O O O
	 * 	 	2 B P P P S
	 *	 	3 O O O O O
	 * 	 	4 O O O O O
	 * 				 		*/
	
	//The Array represents the type of the Tiles in the Grid
	public static final Character[][] layout = {{'O','O','O','O','O'},
												{'O','O','O','O','O'},
												{'B','P','P','P','S'},
												{'O','O','O','O','O'},
												{'O','O','O','O','O'}};
	
	//The Grid's height
	public static final Integer height = 5;
	
	//The Grid's length
	public static final Integer length = 5;

}
