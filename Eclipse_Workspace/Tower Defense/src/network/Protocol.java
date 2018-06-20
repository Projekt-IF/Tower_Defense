package network;

public class Protocol {

	public static final String SEPARATOR = ":";

	public static final String // Server-Client communication

	/* SC_HELLO_WORLD:<String> */
	SC_HELLO_WORLD = "hllwrld",

			// Not game important communication
			/* SC_LOGIN_USERNAME_CONFIRMED:<Username> */
			SC_LOGIN_USERNAME_CONFIRMED = "lgnsrnmcnfrmd",
			/* SC_LOGIN_USERNAME_DENIED:<Username> */
			SC_LOGIN_USERNAME_DENIED = "lgnsrnmdnd",
			/* SC_LOGIN_PASSWORD_CONFIRMED:<Username> */
			SC_LOGIN_PASSWORD_CONFIRMED = "lgnpsswrdcnfrmd",
			/* SC_LOGIN_PASSWORD_DENIED */
			SC_LOGIN_PASSWORD_DENIED = "lgnpsswrddnd",
			/* SC_LOGOUT_CONFIRMED:<String> */
			SC_LOGOUT_CONFIRMED = "lgtcnfrmd",

			/* SC_LOBBY_FOUND:<Position> */
			SC_LOBBY_FOUND = "lbbyfnd",

			/* SC_LOBBY_USERS:<Username_1>:<Ready_1>:<Username_2>:<Ready_2> */
			SC_LOBBY_USERS = "lbbyser",
			/* SC_PLAYER_JOINED:<Integer>:<boolean> */
			SC_PLAYER_JOINED = "plyrjnd",
			/* SC_LOBBY_FULL:<String> */
			SC_LOBBY_FULL = "lbbyfll",
			/* SC_PLAYER_READY:<Integer>:<boolean> */
			SC_PLAYER_READY = "plyrrdy",
			/* SC_ALL_PLAYER_READY:<String> */
			SC_ALL_PLAYER_READY = "llplyrrdy",

			/* SC_LOBBY_DISCONNECT:<Position> */
			SC_LOBBY_DISCONNECT = "lbbydscnnct",
			/* SC_GAME_STARTING */
			SC_GAME_STARTING = "gmstrtng",

			// In-Game communication
			/* SC_TOWER_NOT_AFFORDABLE:<Cost>:<Money> */
			SC_TOWER_NOT_AFFORDABLE = "twrntffrdbl",
			/* SC_TOWER_NOT_PLACABLE:<TowerPosX>:<TowerPosY>:<TileType> */
			SC_TOWER_NOT_PLACEABLE = "twrntplcbl",
			/* SC_ENEMY_NOT_AFFORDABLE:<Cost>:<Money> */
			SC_ENEMY_NOT_AFFORDABLE = "nmyntffrdbl",
			/* SC_LOAD_MAP_TYPE:<PosY>:<PosX>:<Type> */
			SC_LOAD_MAP_TYPE = "ldmptyp",
			/* SC_LOAD_MAP_DIMENSIONS:<Hight>:<Length> */
			SC_LOAD_MAP_DIMENSIONS = "ldmpdmnsns",
			/* SC_LOAD_ENEMIES:<ArrayList<Enemy>> */
			SC_LOAD_ENEMIES = "ldnms",
			/* SC_LOAD_TOWER:<ArrayList<Tower>> */
			SC_LOAD_TOWER = "ldtwr",

			// Graphical Changes to be made by the Client
			/* SC_UPDATE_PLAYER_MONEY:<Money> */
			SC_UPDATE_PLAYER_MONEY = "pdtplyrmny",
			/*
			 * SC_UPDATE_POSITION_ENEMY:<PreviousPosX>:<PreviousPosY>:<CurrentPosX>:<
			 * CurrentPosY>
			 */
			SC_UPDATE_POSITION_ENEMY = "pdtpstnnmy",
			/* SC_UPDATE_POSITION_TOWER:<PosX>:<PosY>:<Type> */
			SC_UPDATE_POSITION_TOWER = "pdtpstntwr",
			/* SC_UPDATE_PLAYER_HEALTH:<Health> */
			SC_UPDATE_PLAYER_HEALTH = "pdtplyrhlth",
			/* SC_ENEMY_BUY_SWAP */
			SC_ENEMY_BUY_SWAP = "nmybyswp",
			/* SC_ENEMY_BUY_ADD */
			SC_ENEMY_BUY_ADD = "nmybydd",

			/* SC_BUY_DONE */
			SC_BUY_DONE = "bydn",

			/* SC_BUY_ALL_READY */
			SC_BUY_ALL_READY = "byllrdy",

			/* SC_ROUND_OVER */
			SC_ROUND_OVER = "rndvr",
			/* SC_ALL_ROUND_OVER_TRUE */
			SC_ALL_ROUND_OVER_TRUE = "llrndvrtr",
			/* SC_ALL_ROUND_OVER_FALSE */
			SC_ALL_ROUND_OVER_FALSE = "llrndvrfls",
			/* SC_CHANGE_MAP:<Level> */
			SC_CHANGE_MAP = "chngmp",

			// End of game messages
			/* SC_CHANGE_ENDSCREEN */
			SC_CHANGE_ENDSCREEN = "chngndscrn",
			/* SC_UPDATE_ENDSCREEN_LEVEL:<Level> */
			SC_UPDATE_ENDSCREEN_LEVEL = "pdtndscrnlvl",
			/* SC_EXIT_ENDSCREEN */
			SC_EXIT_ENDSCREEN = "xtndscrn",
			/* SC_UPDATE_ENDSCREEN_OWN:<State>:<Name>:<Health>:<Money>:<Enemies>:<Towers> */
			SC_UPDATE_ENDSCREEN_OWN = "pdtndscrnwn",
			/*
			 * SC_UPDATE_ENDSCREEN_OTHER:<State>:<Name>:<Health>:<Money>:<Enemies>:<Towers>
			 */
			SC_UPDATE_ENDSCREEN_OTHER = "pdtndscrnthr",

			// Universal
			/* SC_SENDERRORMESSAGE:<String> */
			SC_SENDERRORMESSAGE = "sndmssg",
			/* SC_SURRENDER_SUCCESSFUL:<String> */
			SC_SURRENDER_SUCCESSFUL = "srrndrsccfl",
			/* SC_SURRENDER_UNSECCESSFUL:<String> */
			SC_SURRENDER_UNSUCCESSFUL = "nsrrndrsccfl";

	public static final String // Client-Server communication

	/* CS_HELLO_WORLD */
	CS_HELLO_WORLD = "hllwrld",

			// Not game important communication
			/* CS_LOGIN_USERNAME:<Username> */
			CS_LOGIN_USERNAME = "lgnsrnm",
			/* CS_LOGIN_PASSWORD:<Password> */
			CS_LOGIN_PASSWORD = "lgnpsswrd",
			/* CS_SEARCH_LOBBY */
			CS_SEARCH_LOBBY = "srchlbby",
			/* CS_PLAYER_SET_NAME:<Name> */
			CS_PLAYER_SET_NAME = "plyrstnm",
			/* CS_LOGOUT:<Username> */
			CS_LOGOUT = "lgt",
			/* CS_PLAY */
			CS_PLAY = "ply",
			/* CS_GO */
			CS_GO = "g",
			/* CS_READY_LOBBY */
			CS_READY_LOBBY = "rdylbby",

			// In-Game communication
			/* CS_PURCHASE_TOWER:<TowerPosX>:<TowerPosY>:<TowerType> */
			CS_PURCHASE_TOWER = "prchstwr",
			/* CS_PURCHASE_ENEMY:<EnemyType> */
			CS_PURCHASE_ENEMY = "prchsnmy",
			/* CS_READY_TOWERPLACING */
			CS_READY_TOWERPLACING = "rdytwrplcng",
			/* CS_READY_ENEMIESPURCHASED */
			CS_READY_ENEMIESPURCHASED = "rdynmsprchsd",
			/* SC_ARE_ALL_ROUND_OVER */
			CS_ARE_ALL_ROUND_OVER = "rllrndvr",
			/* CS_EXIT_ENDSCREEN */
			CS_EXIT_ENDSCREEN = "xtndscrn";

}
