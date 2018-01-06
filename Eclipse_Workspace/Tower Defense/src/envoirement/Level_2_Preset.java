package envoirement;

public class Level_2_Preset {
	
	/* O = Tile Field 		[Towers can be placed on here]
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
	
	
	public static final Character[][] layout = {{'B','P','P','P','P','P','P','P','P'},
												{'O','O','O','O','O','O','O','O','P'},
												{'P','P','P','P','P','P','P','P','P'},
												{'P','O','O','O','O','O','O','O','O'},
												{'P','P','P','P','P','P','P','P','P'},
												{'O','O','O','O','O','O','O','O','P'},
												{'P','P','P','P','P','P','P','P','P'},
												{'P','O','O','O','O','O','O','O','O'},
												{'P','P','P','P','P','P','P','P','S'}};
	
	public static final Integer hight = 9;
	
	public static final Integer length = 9;

}