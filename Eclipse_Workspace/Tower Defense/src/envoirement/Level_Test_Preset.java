package envoirement;

public class Level_Test_Preset {
	
	/* O = Tile Field [Towers can be placed on here]
	 * B = BaseTile	  [Enemies want to go here]
	 * S = Spawner	  [Enemies spawn here]
	 * 
	 *     	  0 1 2 3 4
	 *   	0 O O O O O
	 *   	1 O O O O O
	 * 	 	2 B X X X S
	 *	 	3 O O O O O
	 * 	 	4 O O O O O
	 * 				 		*/
	
	
	public static final Character[][] layout = {{'O','O','O','O','O'},
												{'O','O','O','O','O'},
												{'B','P','P','P','S'},
												{'O','O','O','O','O'},
												{'O','O','O','O','O'}};
	
	public static final String path = "0,2:1,2:2,2:3,2:4,2";
	
	public static final Integer hight = 5;
	
	public static final Integer length = 5;

}
