package network;

public class Protocol {
	
	public static final String SEPARATOR = ":";
	
	public static final String //Server-Client communication
	
			SC_HELLO_WORLD = "hllwrld",
	
			//Not game important communication
			SC_LOGIN_CONFIRMED = "liconf",
			SC_LOGOUT_CONFIRMED = "locnfrmd",
			SC_PING_BACK = "pback",
			SC_KICK_PLAYER = "kplayer",
			SC_RECONNECT_PLAYER = "rcplayer",
			
			//In-Game communication
			SC_LOAD_MAP = "ldmp",
			SC_LOAD_ENEMIES = "ldnms",
			SC_LOAD_TOWER = "ldtwr",
			
			SC_CHANGE_POSITION_ENEMIE = "chngpstnnm",
			SC_CHANGE_POSITION_TOWER = "chngpstntwr",
			SC_CHANGE_MAP = "chngmp",
			
			SC_REMOVE_ENEMY = "rmvnmy",
			SC_REMOVE_TOWER = "rmvtwr",
			
			SC_SENDERRORMESSAGE = "sndmssg"
			;
	
	
	public static final String
			CS_HELLO_WORLD = "hllwrld",
			
			CS_LOGIN = "li",
			CS_LOGOUT = "lo",
			CS_GO = "go",
			CS_PURCHASE_TOWER = "prchstower",
			CS_PURCHASE_ENEMY = "prchsnmy",
			CS_SURRENDER = "srrndr"
			;

}
