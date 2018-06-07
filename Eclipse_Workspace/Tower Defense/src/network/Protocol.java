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

			/* SC_GAME_STARTING:<Level> */
			SC_GAME_STARTING = "gmstrtng",

			// In-Game communication
			/* SC_TOWER_NOT_AFFORDABLE:<Cost>:<Money> */
			SC_TOWER_NOT_AFFORDABLE = "twrntffrdbl",
			/* SC_TOWER_NOT_PLACABLE:<TowerPosX>:<TowerPosY>:<TileType> */
			SC_TOWER_NOT_PLACEABLE = "twrntplcbl",
			/* SC_ENEMY_NOT_AFFORDABLE:<Cost>:<Money> */
			SC_ENEMY_NOT_AFFORDABLE = "nmyntffrdbl",
			/* SC_LOAD_MAP:<Level> */
			SC_LOAD_MAP = "ldmp",
			/* SC_LOAD_ENEMIES:<ArrayList<Enemy>> */
			SC_LOAD_ENEMIES = "ldnms",
			/* SC_LOAD_TOWER:<ArrayList<Tower>> */
			SC_LOAD_TOWER = "ldtwr",

			// Graphical Changes to be made by the Client
			/* SC_UPDATE_PLAYER_MONEY:<Integer> */
			SC_UPDATE_PLAYER_MONEY = "pdtplyrmny",
			/* SC_UPDATE_POSITION_ENEMY:<PositionInList><PosX><PosY> */
			SC_UPDATE_POSITION_ENEMY = "pdtpstnnmy",
			/* SC_UPDATE_POSITION_TOWER:<PosX><PosY> */
			SC_UPDATE_POSITION_TOWER = "pdtpstntwr",
			/* SC_ROUND_OVER */
			SC_ROUND_OVER = "rndvr",
			/* SC_CHANGE_MAP:<Level> */
			SC_CHANGE_MAP = "chngmp",

			/* SC_REMOVE_ENEMY:<PositionInList> */
			SC_REMOVE_ENEMY = "rmvnmy",
			/* SC_REMOVE_TOWER:<PosotionInList> */
			SC_REMOVE_TOWER = "rmvtwr",

			// End of game messages
			/* SC_VICTORY:<String> */
			SC_VICTORY = "vctry",
			/* SC_LOSS:<String> */
			SC_LOSS = "lss",
			/* SC_ENDOFGAMESTATS:<Minutes><Points><Money> */
			SC_ENDOFGAMESTATS = "ndfgmstts",

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
			/* CS_GO:<Level> */
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
			CS_READY_ENEMIESPURCHASED = "rdynmsprchsd";

}
