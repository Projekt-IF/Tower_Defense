package envoirement;

public class Level_1_Preset {
	
	/* O = Tile Field [Towers can be placed on here]
	 * B = BaseTile	  [Enemies want to go here]
	 * S = Spawner	  [Enemies spawn here]
	 * 
	 *     	  0 1 2 3 4
	 *   	0 O O O O O
	 *   	1 O X X X O
	 * 	 	2 B X O X O
	 *	 	3 O O O X S
	 * 	 	4 O O O O O
	 * 				 		*/
	
	
	public static final Character[][] layout = {{'O','O','O','O','O'},
												{'O','P','P','P','O'},
												{'B','P','O','P','O'},
												{'O','O','O','P','S'},
												{'O','O','O','O','O'}};
	
	public static final Integer hight = 5;
	
	public static final Integer length = 5;

}
