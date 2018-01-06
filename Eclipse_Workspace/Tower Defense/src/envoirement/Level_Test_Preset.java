package envoirement;

public class Level_Test_Preset {
	
	/* O = Tile Field 		[Towers can be placed on here]
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
	
	
	public static final Character[][] layout = {{'O','O','O','O','O'},
												{'O','O','O','O','O'},
												{'B','P','P','P','S'},
												{'O','O','O','O','O'},
												{'O','O','O','O','O'}};
	
	public static final Integer hight = 5;
	
	public static final Integer length = 5;

}
