package network;

public class Protocol {
	
	public static final String SEPARATOR = ":";
	
	public static final String //Server-Client communication
	
			/*SC_HELLO_WORLD:<String>*/
			SC_HELLO_WORLD = "hllwrld",
	
			//Not game important communication
			/*SC_LOGIN_CONFIRMED:<String>*/
			SC_LOGIN_CONFIRMED = "lgncnfrmd",
			/*SC_LOGOUT_CONFIRMED:<String>*/
			SC_LOGOUT_CONFIRMED = "lgtcnfrmd",
			/*SC_KICK_PLAYER:<String>*/
			SC_KICK_PLAYER = "kckplyr",
			/*SC_RECONNECT_PLAYER:<String>*/
			SC_RECONNECT_PLAYER = "rcnnctplyr",
			
			/*SC_LOBBY_USERS:<ArrayList<Player>>*/
			SC_LOBBY_USERS = "lbbyser",
			/*SC_PLAYER_JOINED:<Player>*/
			SC_PLAYER_JOINED = "plyrjind",
			/*SC_LOBBY_FULL:<String>*/
			SC_LOBBY_FULL = "lbbyfll",
			/*SC_PLAYER_READY:<Integer>:<boolean>*/
			SC_PLAYER_READY = "plyrrdy",
			/*SC_ALL_PLAYER_READY:<String>*/
			SC_ALL_PLAYER_READY = "llplyrrdy",
			
			/*SC_GAME_STARTING:<String>*/
			SC_GAME_STARTING = "gmstrtng",
			
			//In-Game communication
			/*SC_LOAD_MAP:<Level>*/
			SC_LOAD_MAP = "ldmp",
			/*SC_LOAD_ENEMIES:<ArrayList<Enemy>>*/
			SC_LOAD_ENEMIES = "ldnms",
			/*SC_LOAD_TOWER:<ArrayList<Tower>>*/
			SC_LOAD_TOWER = "ldtwr",
			
			//Graphical Changes to be made by the Client
			/*SC_UPDATE_PLAYER_MONEY:<Integer>*/
			SC_UPDATE_PLAYER_MONEY = "pdtplyrmny",
			/*SC_UPDATE_POSITION_ENEMY:<PositionInList><PosX><PosY>*/
			SC_UPDATE_POSITION_ENEMY = "pdtpstnnmy",
			/*SC_UPDATE_POSITION_TOWER:<PosX><PosY>*/
			SC_UPDATE_POSITION_TOWER = "pdtpstntwr",
			/*SC_ROUND_OVER*/
			SC_ROUND_OVER = "rndvr",
			/*SC_CHANGE_MAP:<Level>*/
			SC_CHANGE_MAP = "chngmp",
			
			/*SC_REMOVE_ENEMY:<PositionInList>*/
			SC_REMOVE_ENEMY = "rmvnmy",
			/*SC_REMOVE_TOWER:<PosotionInList>*/
			SC_REMOVE_TOWER = "rmvtwr",
			
			//End of game messages
			/*SC_VICTORY:<String>*/
			SC_VICTORY = "vctry",
			/*SC_LOSS:<String>*/
			SC_LOSS = "lss",
			/*SC_ENDOFGAMESTATS:<Minutes><Points><Money>*/
			SC_ENDOFGAMESTATS = "ndfgmstts",
			
			
			//Universal
			/*SC_SENDERRORMESSAGE:<String>*/
			SC_SENDERRORMESSAGE = "sndmssg",
			/*SC_SURRENDER_SUCCESSFUL:<String>*/
			SC_SURRENDER_SUCCESSFUL = "srrndrsccfl",
			/*SC_SURRENDER_UNSECCESSFUL:<String>*/
			SC_SURRENDER_UNSUCCESSFUL = "nsrrndrsccfl"
			;
	
	
	public static final String //Client-Server communication
	
			/*CS_HELLO_WORLD:<String>*/
			CS_HELLO_WORLD = "hllwrld",
			
			//Not game important communication
			/*CS_LOGIN:<Username><Password>*/
			CS_LOGIN = "lgn",
			/*CS_LOGOUT:<Username>*/
			CS_LOGOUT = "lgt",
			/*CS_PLAY*/
			CS_PLAY = "ply",
			/*CS_GO*/
			CS_GO = "g",
			/*CS_READY_LOBBY:<Boolean>*/
			CS_READY_LOBBY = "rdylbby",
			
			//In-Game communication
			/*CS_PURCHASE_TOWER:<ArrayList<Tower>>*/
			CS_PURCHASE_TOWER = "prchstwr",
			/*CS_PURCHASE_ENEMY:<ArrayList<Enemy>>*/
			CS_PURCHASE_ENEMY = "prchsnmy",
			/*CS_READY_TOWERPLACING*/
			CS_READY_TOWERPLACING = "rdytwrplcng",
			/*CS_READY_ENEMIESPURCHASED*/
			CS_READY_ENEMIESPURCHASED = "rdynmsprchsd",
			/*CS_SURRENDER*/
			CS_SURRENDER = "srrndr"
			;

}

