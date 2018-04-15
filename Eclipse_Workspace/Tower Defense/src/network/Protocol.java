package network;

public class Protocol {
	
	public static final String SEPARATOR = ":";
	
	public static final String //Server-Client communication
	
			SC_HELLO_WORLD = "hllwrld",
	
			//Not game important communication
			SC_LOGIN_CONFIRMED = "lgncnfrmd",
			SC_LOGOUT_CONFIRMED = "lgtcnfrmd",
			SC_PING_BACK = "pngbck",
			SC_KICK_PLAYER = "kckplyr",
			SC_RECONNECT_PLAYER = "rcnnctplyr",
			
			SC_GAME_STARTING = "gmstrtng",
			
			//In-Game communication
			SC_LOAD_MAP = "ldmp",
			SC_LOAD_ENEMIES = "ldnms",
			SC_LOAD_TOWER = "ldtwr",
			
			//Graphical Changes to be made by the Client
			SC_UPDATE_PLAYER_MONEY = "pdtplyrmny",
			SC_UPDATE_POSITION_ENEMY = "pdtpstnnmy",
			SC_UPDATE_POSITION_TOWER = "pdtpstntwr",
			SC_CHANGE_MAP = "chngmp",
			
			SC_REMOVE_ENEMY = "rmvnmy",
			SC_REMOVE_TOWER = "rmvtwr",
			
			//End of game messages
			SC_VICTORY = "vctry",
			SC_LOSS = "lss",
			
			//Universal
			SC_SENDERRORMESSAGE = "sndmssg",
			SC_SURRENDER_SUCCESSFUL = "srrndrsccfl",
			SC_SURRENDER_UNSUCCESSFUL = "nsrrndrsccfl"
			;
	
	
	public static final String //Client-Server communication
			CS_HELLO_WORLD = "hllwrld",
			
			//Not game important communication
			CS_LOGIN = "lgn",
			CS_LOGOUT = "lgt",
			CS_GO = "g",
			
			//In-Game communication
			CS_PURCHASE_TOWER = "prchstwr",
			CS_PURCHASE_ENEMY = "prchsnmy",
			CS_SURRENDER = "srrndr"
			;

}
