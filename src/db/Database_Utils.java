package db;

//@author Jeremiah Brusegaard
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.UnknownFormatFlagsException;
import java.util.Map;
import java.util.Map.Entry;
import java.security.InvalidKeyException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;


import player.Card;
import player.Deck;
import server.*;
import java.io.*;
import java.nio.file.Files;


// TODO: Auto-generated Javadoc
/**
 * The Class Database_Utils.
 */
public class Database_Utils {

	/** The db user name. */
	private static String dbUserName = "dbu309rbc3";
	
	/** The db password. */
	private static String dbPassword = "#cbd332!";
	
	/** The db name. */
	private static String dbName = "db309rbc3";
	
	/** The db options. */
	private static String dbOptions = "?verifyServerCertificate=false&autoReconnect=true&useSSL=false&allowMultiQueries=true";
	
	/** The db url. */
	private static String dbUrl = "jdbc:mysql://mysql.cs.iastate.edu/" + dbName + dbOptions;
	
	/** The conn. */
	private Connection conn = null;
	
	/** The statement. */
	private Statement statement = null;
	
	/** The encoder. */
	private Base64.Encoder encoder;
	
	/** The algorithm. */
	private String algorithm = "Blowfish";
	
	/** The cipher. */
	private Cipher cipher;
	
	/** The default avatar. */
	private String defaultAvatar = "pics/Missing.png";

	/**
	 * Constructor for Database creates connection and creates tables for the
	 * database if they don't already exists for portability's sake.
	 */
	
	public Database_Utils() {
		String initLoginQuery = "CREATE TABLE IF NOT EXISTS LOGIN(username VARCHAR(255),password VARCHAR(255),id VARCHAR(255),accountType tinyint,avatar MEDIUMBLOB,currency int,wins int, losses int,"
				+ "PRIMARY KEY(id),dateCreated DATETIME,modified DATETIME,lastLogin DATETIME)";
		String initCardDB = "CREATE TABLE IF NOT EXISTS CARD(name VARCHAR(255),id VARCHAR(255),imgfilename VARCHAR(255),attack VARCHAR(255), "
				+ "speed VARCHAR(255), health VARCHAR(255), description VARCHAR(255), type VARCHAR(255),ability VARCHAR(1000), cost VARCHAR(255), "
				+ "value VARCHAR(255),rarity VARCHAR(255),rnge VARCHAR(255),dateCreated DATETIME,modified DATETIME)";
		String initPlayerCardNumbers = "CREATE TABLE IF NOT EXISTS PLAYERSCARD(playerid VARCHAR(255),PRIMARY KEY(playerid))";
		String initDeckCollection = "CREATE TABLE IF NOT EXISTS DECK(playerid VARCHAR(255),deckid VARCHAR(255),deckname VARCHAR(255),PRIMARY KEY(deckid))";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			this.conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			this.statement = this.conn.createStatement();
			executeCreationQuery(initLoginQuery);
			executeCreationQuery(initCardDB);
			executeCreationQuery(initPlayerCardNumbers);
			executeCreationQuery(initDeckCollection);
			this.encoder = Base64.getEncoder();
			this.cipher = Cipher.getInstance(algorithm);
			System.out.println("[" + getSQLDate() + "] Database Connection Successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Logins into user account with preprocessed strings to prevent SQL injection.
	 *
	 * @param username            username of player
	 * @param password            password of player
	 * @return Unique id of player
	 */
	public String login(String username, String password) {
		String usernameQuery = "SELECT password FROM LOGIN WHERE username = '" + username + "'";
		ResultSet rs = null;
		String playerID = null;
		try {
			rs = executeNonCreationQuery(usernameQuery);
			if (!rs.next()) {
				return "INVALID USERNAME";
			} else {
				String encryptedPword = rs.getString("password");
				playerID = getIDByUname(username);
				String dbPassword = encrypt(password, playerID);
				if (dbPassword.equals(encryptedPword)) {
					String updateLoginQuery = "UPDATE LOGIN SET lastLogin = '" + getSQLDate() + "' WHERE id = '"
							+ playerID + "'";
					executeCreationQuery(updateLoginQuery);
					return playerID;
				} else {
					return "INVALID PASSWORD OR USERNAME";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Changes the password of a given user.
	 *
	 * @param username            username of player
	 * @param newPassword            password of a player
	 * @return Success message
	 */
	public String changePassword(String username, String newPassword) {
		String changePwordQuery = "UPDATE LOGIN SET password = '" + encrypt(newPassword, getIDByUname(username))
				+ "' , modified = " + getSQLDate() + " WHERE username = '" + username;
		executeCreationQuery(changePwordQuery);
		return "PASSWORD CHANGED SUCCESSFULLY";
	}

	/**
	 * Register player in game and creates deck tables.
	 *
	 * @param username            player's username
	 * @param password            player's password
	 * @return success or error message
	 */
	public String register(String username, String password) {
		String id = generateUserID(16);
		String createUserQuery = "INSERT INTO LOGIN (username,password,id,accountType,currency,wins,losses,dateCreated,modified,lastLogin) "
				+ "VALUES ('" + username + "','" + encrypt(password, id) + "','" + id + "'," + "0,0,0,0,'" + getSQLDate()
				+ "','" + getSQLDate() + "','" + getSQLDate() + "')";
		String validateQuery = "SELECT * FROM LOGIN WHERE username = '" + username + "'";
		String cardDataQuery = "INSERT INTO PLAYERSCARD(playerid) VALUES('" + id + "')";
		ResultSet rSet = null;
		try {
			rSet = executeNonCreationQuery(validateQuery);
			if (rSet.next()) {
				return "USERNAME ALREADY TAKEN";
			} else {
				executeCreationQuery(createUserQuery);
				executeCreationQuery(cardDataQuery);
				storePlayerAvatar(getFileInBytes(this.defaultAvatar), id);
//				initNewDeckForUser(id);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// /**
	// * Helper method to create deck table for a given user id
	// * @param userID player user id
	// */
	// private void createDecks(String userID) {
	// ArrayList<String> cardIDS = getAllCardIDs();
	// String columns = "";
	// String column = "";
	// String values = "";
	// if(!cardIDS.isEmpty()) {
	// for(String id: cardIDS) {
	// columns +="," + id + " int";
	// column += "," + id;
	// values +=",0";
	// }
	// }
	// String deckTable = "CREATE TABLE deck" + userID + " (deckName
	// VARCHAR(255),PRIMARY KEY(deckname)" + columns + ")";
	// System.out.println(executeCreationQuery(deckTable));
	// deckTable = "INSERT INTO PLAYERSCARD (playerid" + column + ") VALUES('"
	// +userID+ "'" + values + ")" ;
	// System.out.println(deckTable);
	// System.out.println(executeCreationQuery(deckTable));
	// }
	/**
	 * Gets all card IDs.
	 *
	 * @return arraylist of card ids in strings
	 */
	public ArrayList<String> getAllCardIDs() {
		String query = "SELECT id FROM CARD";
		ResultSet rSet = null;
		ArrayList<String> ids = new ArrayList<>();
		try {
			rSet = executeNonCreationQuery(query);
			while (rSet.next()) {
				ids.add(rSet.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	/**
	 * Gets all card names.
	 *
	 * @return arraylist of card names in strings
	 */
	public ArrayList<String> getAllCardNames() {
		String query = "SELECT name FROM CARD";
		ResultSet rSet = null;
		ArrayList<String> names = new ArrayList<>();
		try {
			rSet = executeNonCreationQuery(query);
			while (rSet.next()) {
				names.add(rSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return names;
	}
	
	/**
	 * Helper method to get all player ids.
	 *
	 * @return arraylist of player ids in strings
	 */
	private ArrayList<String> getAllPlayerIDS() {
		String query = "Select id FROM LOGIN";
		ResultSet resultSet = null;
		ArrayList<String> idStrings = new ArrayList<>();
		try {
			resultSet = executeNonCreationQuery(query);
			while (resultSet.next()) {
				idStrings.add(resultSet.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idStrings;
	}

	/**
	 * Method to get card info.
	 *
	 * @param field            card column
	 * @param id            card id
	 * @return desired column value for a given id
	 */
	public String getCardFieldByID(String field, String id) {
		String query = "SELECT " + field + " FROM CARD WHERE id = '" + id + "'";
		try {
			return executeNonCreationQuery(query).getString(field);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Changes card column value given and id.
	 *
	 * @param field            column to change
	 * @param value            value to change column to
	 * @param id            card id that is being changed
	 * @return success statement
	 */
	public String changeCardFieldByID(String field, String value, String id) {
		String query = "UPDATE CARD SET " + field + " = '" + value + "', modified = '" + getSQLDate() + "' WHERE id = '"
				+ id + "'";
		executeCreationQuery(query);
		return "QUERY SUCCESSFUL";
	}

	/**
	 * Creates a hashmap for card in the card table with the keys being the column
	 * names.
	 *
	 * @param name the name
	 * @return hashmap of all the given card ids information
	 */
	public HashMap<String, String> getCardInfoByName(String name) {
		HashMap<String, String> map = new HashMap<>();
		String query = "Select * FROM CARD WHERE name = '" + name + "'";
		ResultSet rSet = null;
		try {
			rSet = executeNonCreationQuery(query);
			if (rSet.next()) {
				ResultSetMetaData metaData = rSet.getMetaData();
				int colCount = metaData.getColumnCount();
				for (int i = 0; i < colCount; i++) {
					map.put(metaData.getColumnName(i + 1), rSet.getString(i + 1));
				}
				return map;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets a given player's currency.
	 *
	 * @param playerID            player id to be selected
	 * @return currency that the player has
	 */
	public String getPlayerCurrency(String playerID) {
		if (!isAlphanumeric(playerID))
			return "ERROR INVALID ID: " + playerID;
		String query = "SELECT currency FROM LOGIN WHERE id = '" + playerID + "'";
		ResultSet resultSet = executeNonCreationQuery(query);
		try {
			if (resultSet.next()) {
				return resultSet.getString("currency");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "INVALID ID";
	}

	/**
	 * Change the player's currency value.
	 *
	 * @param playerID            player id
	 * @param currency            new currency value
	 * @return success or fail message
	 */
	public String updatePlayerCurrency(String playerID, String currency) {
		if (!isAlphanumeric(playerID))
			return "ERROR INVALID ID: " + playerID;
		String query = "UPDATE LOGIN SET currency = " + Integer.parseInt(currency) + " ,modified = '" + getSQLDate()
				+ "' WHERE id = '" + playerID + "'";
		return "Currency updated: " + executeCreationQuery(query);
	}

	/**
	 * Gets the card ID by rarity add to user.
	 *
	 * @param userID the user ID
	 * @param rarity the rarity
	 * @return the card ID by rarity add to user
	 */
	// TODO
	public String getCardIDByRarityAddToUser(String userID, String rarity) {
		switch (rarity) {
		case "Common":
			break;
		case "Legendary":
			break;
		case "Rare":
			break;
		case "Epic":
			break;
		default:
			return "INVALID CARD RARITY";
		}
		String query = "SELECT name FROM CARD WHERE rarity = '" + rarity + "' ORDER BY RAND()";
		ResultSet rSet = executeNonCreationQuery(query);
		String cardName = "";
		try {
			if (rSet.next()) {
				cardName = rSet.getString("name");
				int cardnum = 1;
				query = "SELECT " + cardName + " FROM PLAYERSCARD WHERE playerid = '" + userID + "'";
				rSet = executeNonCreationQuery(query);
				if (rSet.next()) {
					cardnum = rSet.getInt(cardName) + 1;
				}
				query = "UPDATE PLAYERSCARD SET " + cardName + " = " + cardnum + " WHERE playerid = '" + userID + "'";
				executeCreationQuery(query);
				//cardName = id;
/*				query = "SELECT name FROM CARD WHERE id = '" + id + "'";
				rSet = executeNonCreationQuery(query);
				rSet.next();
				cardName = rSet.getString("name");*/
			} else {
				return "ERROR NO CARD WITH GIVEN RARITY: " + rarity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cardName;
	}

	/**
	 * Gets the count of players cards.
	 *
	 * @param userID the user ID
	 * @return the count of players cards
	 */
	public String getCountOfPlayersCards(String userID) {
		if (!isAlphanumeric(userID))
			return "ERROR INVALID ID: " + userID;
		ArrayList<String> cardNames = getAllCardNames();
		int count = 0;
		String query = "";
		ResultSet rSet;
		for (String name : cardNames) {
			query = "SELECT " + name + " FROM PLAYERSCARD WHERE playerid = '" + userID + "'";
			rSet = executeNonCreationQuery(query);
			try {
				if(rSet.next()) {
					count += rSet.getInt(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Integer.toString(count);
	}
	
	/**
	 * Gets the count of players deck cards.
	 *
	 * @param deckID the deck ID
	 * @return the count of players deck cards
	 */
	public String getCountOfPlayersDeckCards(String deckID) {
		if (!isAlphanumeric(deckID))
			return "ERROR INVALID ID: " + deckID;
		ArrayList<String> cardNames = getAllCardNames();
		int count = 0;
		String query = "";
		ResultSet rSet;
		for (String name : cardNames) {
			query = "SELECT " + name + " FROM DECK WHERE deckid = '" + deckID + "'";
			rSet = executeNonCreationQuery(query);
			try {
				if(rSet.next()) {
//					System.out.println("Card " + name + ": " + rSet.getInt(name));
					count += rSet.getInt(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Integer.toString(count);
	}
	
	/**
	 * Creates a new card and adds it to players deck tables.
	 *
	 * @param file the file
	 * @param userID the user ID
	 * @return Success message
	 */
//	public String createCard(String name, String imgFileName, String attack, String speed, String health,
//			String description, String type, String ability, String cost, String value, String rarity,String range) {
//		String id = generateCardID(16);
//		String query = "INSERT INTO CARD (name,id,imgfilename,attack,speed,health,description,"
//				+ "type,ability,cost,value,rarity,rnge,dateCreated,modified) VALUES ('" + name + "', '" + id + "','"
//				+ imgFileName + "','" + attack + "','" + speed + "','" + health + "','" + description + "','" + type
//				+ "','" + ability + "','" + cost + "','" + value + "','" + rarity + "','" + range + "','" + getSQLDate() + "','"
//				+ getSQLDate() + "')";
//		updateDeckTablesWithNewCard(name);
//		return "Query Success: " + executeCreationQuery(query);
//	}

	public String storePlayerAvatar(byte[] file, String userID) {
		if (!isAlphanumeric(userID))return "ERROR INVALID ID: " + userID;
		reconnectDatabase();
		try {
			String query = "UPDATE LOGIN SET avatar = ? WHERE id = '" + userID + "'";
			PreparedStatement pStatement = this.conn.prepareStatement(query);
			pStatement.setBytes(1, file);
			pStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERROR";
		}
		return "Profile Picture Updated";
	}

	/**
	 * Gets time.
	 *
	 * @return time in sql sever format
	 */
	public Timestamp getSQLDate() {
		java.util.Date curDate = new Date();
		return new Timestamp(curDate.getTime());
	}

	/**
	 * Gets all the cards in the current database.
	 *
	 * @return arraylist of hashmaps with card's values
	 */
	public ArrayList<HashMap<String, String>> getCards() {
		ArrayList<String> names = getAllCardNames();
		ArrayList<HashMap<String, String>> cards = new ArrayList<>();
		for (String name : names) {
			cards.add(getCardInfoByName(name));
		}
		return cards;
	}

	/**
	 * Creates a new unique card id.
	 *
	 * @param idSize            character size of the card id
	 * @return unique id for the card
	 */
	private String generateCardID(int idSize) {
		ResultSet resultSet = null;
		String id = null;
		while (true) {
			id = generateID(idSize);
			String getCardsQuery = "SELECT id FROM CARD WHERE id = '" + id + "'";
			try {
				resultSet = executeNonCreationQuery(getCardsQuery);
				if (!resultSet.next()) {
					return id;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * generates a unique user id.
	 *
	 * @param idSize            size of id to be generated
	 * @return unique user id
	 */
	private String generateUserID(int idSize) {
		ResultSet resultSet = null;
		String id = null;
		while (true) {
			id = generateID(idSize);
			String getUsersQuery = "SELECT id FROM LOGIN WHERE id = '" + id + "'";
			try {
				resultSet = executeNonCreationQuery(getUsersQuery);
				if (!resultSet.next()) {
					return id;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * Generate deck ID.
	 *
	 * @param idSize the id size
	 * @return the string
	 */
	public String generateDeckID(int idSize) {
		ResultSet resultSet = null;
		String id = null;
		while (true) {
			id = generateID(idSize);
			String getUsersQuery = "SELECT deckid FROM DECK WHERE deckid = '" + id + "'";
			try {
				resultSet = executeNonCreationQuery(getUsersQuery);
				if (!resultSet.next()) {
					return id;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * updates all the player's deck tables when a new card gets created.
	 *
	 * @param name the name
	 */
	private void updateDeckTablesWithNewCard(String name) {
		String query = "";
		query = "ALTER TABLE PLAYERSCARD ADD " + name + " int";
		executeCreationQuery(query);
		query = "ALTER TABLE DECK ADD " + name + " int";
		executeCreationQuery(query);
	}

	/**
	 * generates an id of idSize.
	 *
	 * @param idSize            size of id to be generated
	 * @return newly generated id
	 */
	private String generateID(int idSize) {
		String chars = "AbcdEFGHIJKLmnOPQRSTUVWXYZaBCDefghijklMNopqrstuvwxyz0125436789";
		Random random = new Random();
		String id = "";
		for (int i = 0; i < idSize; i++) {
			id += chars.charAt(random.nextInt(chars.length()));
		}
		return id;
	}

	/**
	 * executes a query with a result set.
	 *
	 * @param query            given query
	 * @return result set for the given query
	 */
	private ResultSet executeNonCreationQuery(String query) {
		reconnectDatabase();
//		System.out.println("Query: " + query);
		try {
			return this.statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Helper method to execute a creation query.
	 *
	 * @param query            given query
	 * @return success or fail boolean
	 */
	private Boolean executeCreationQuery(String query) {
		reconnectDatabase();
//		System.out.println("Query: " + query);
		try {
			return !this.statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Encrypts a given string.
	 *
	 * @param text            string to be encrypted
	 * @param salt            saturation for encryption
	 * @return encrypted string
	 */
	private String encrypt(String text, String salt) {
		SecretKeySpec key = new SecretKeySpec(salt.getBytes(), this.algorithm);
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			return this.encoder.encodeToString(encrypted);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Reconnects the sql database to prevent time out.
	 */
	private void reconnectDatabase() {
		try {
			this.conn.close();
			this.conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			this.statement = this.conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the players deck names.
	 *
	 * @param userID the user ID
	 * @return the players deck names
	 */
	public ArrayList<String> getPlayersDeckNames(String userID){
		if (!isAlphanumeric(userID)) return null;
		ArrayList<String> deckNames = new ArrayList<>();
		String query = "SELECT deckname FROM DECK WHERE playerid = '" + userID + "'";
		ResultSet rSet = executeNonCreationQuery(query);
		try {
			while(rSet.next()) {
				deckNames.add(rSet.getString("deckname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return deckNames;
	}
	
	/**
	 * Gets a player id with given username.
	 *
	 * @param username            player username
	 * @return player id
	 */
	public String getIDByUname(String username) {
		ResultSet rSet = executeNonCreationQuery("SELECT id FROM LOGIN WHERE username = '" + username + "'");
		try {
			if (rSet.next()) {
				return rSet.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets a player username with given id.
	 *
	 * @param id            player id
	 * @return player username
	 */
	public String getUnameByID(String id) {
		ResultSet rSet = executeNonCreationQuery("SELECT username FROM LOGIN WHERE id = '" + id + "'");
		try {
			if (rSet.next()) {
				return rSet.getString("username");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Inits the new deck for user.
	 *
	 * @param userID the user ID
	 * @return the string
	 */
	//TODO
	private String initNewDeckForUser(String userID) {
		if (!isAlphanumeric(userID))
			return "ERROR INVALID ID: " + userID;
		String id = generateDeckID(16);
		int totalCardCount = 0;
		String deckName = "Beginner Deck";
		String query = "INSERT INTO DECK(playerid,deckid,deckname) VALUES('" + userID + "','" + id + "','" + deckName
				+ "')";
		executeCreationQuery(query);
		query = "SELECT * FROM CARD WHERE name = 'Capital'";
		
		try {
			String cardname = "";
			ResultSet rSet = executeNonCreationQuery(query);
			if (rSet.next()) {
				cardname = rSet.getString("name");
				addCardToDeck(id, cardname);
				addCardToPlayersCollection(userID, cardname);
				totalCardCount++;
			}

			query = "Select * FROM CARD WHERE type = 'Terrain' and name != 'Capital' order by RAND()";
			for(int i = totalCardCount; i < 15;i++,totalCardCount++) {
				ResultSet resultSet = executeNonCreationQuery(query);
				if (resultSet.next()) {
					cardname = resultSet.getString("name");
					addCardToDeck(id, cardname);
					addCardToPlayersCollection(userID, cardname);
				}
			}
			query = "SELECT * FROM CARD WHERE type = 'Land' OR type = 'Air' OR type = 'Aquatic' order by RAND()";
			for(int i = totalCardCount; i < 40;i++,totalCardCount++) {
				ResultSet resultSet = executeNonCreationQuery(query);
				if (resultSet.next()) {
					cardname = resultSet.getString("name");
					addCardToDeck(id, cardname);
					addCardToPlayersCollection(userID, cardname);
				}
			}
			query = "SELECT * FROM CARD WHERE type = 'Land Trap' OR type = 'Mountain Trap' order by RAND()";
			for(int i = totalCardCount;i<50;i++,totalCardCount++) {
				ResultSet resultSet = executeNonCreationQuery(query);
				if (resultSet.next()) {
					cardname = resultSet.getString("name");
					addCardToDeck(id, cardname);
					addCardToPlayersCollection(userID, cardname);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "";
	}
	
	/**
	 * Inits the cards.
	 */
	//Every player gets 15 terrain cards
	protected void initCards() {
//		try {	
//		createCard("Noob", "Noob", "25", "1", "50", "Just your regular noob", "Land", "0", "10", "1", "Common","1");
//		createCard("PitfallLand", "Landpitfall", "15", "0", "0", "Land Trap", "Land Trap", "Springs on Enemy Units", "10", "10", "Common", "0");
//		createCard("Tank", "LandTank", "75", "2", "200", "Land Tank", "Land", "0", "10", "10", "Rare", "2");
//		createCard("Avalanche", "Avalanche", "100", "0", "0", "Mountain Trap", "Mountain Trap", "Triggers on units in mountains", "10", "10", "Epic", "1");
//		createCard("Helicopter", "Helicopter", "10", "3", "50", "Air Transport Unit", "Air", "Transports up to 2 ground units", "10", "10", "Common", "2");
//		createCard("Dragon", "Dragon", "200", "3", "25", "A dragon what else is there to say", "Air", "0", "100", "100", "Legendary", "1");
//		createCard("BigDaddy", "BigDaddy", "25", "1", "75", "He is a large father", "Aquatic", "Can traverse water and land", "15", "25", "Epic", "1");
//		createCard("Forest", "Forest", "0", "0", "0", "Forest terrain", "Terrain", "Troops can hide in forest", "0", "10", "Common", "0");
//		createCard("Town", "Town", "0", "0", "0", "Town terrain", "Terrain", "Troops can resupply here", "0", "10", "Common", "0");
//		createCard("Capital", "Capital", "0", "0", "0", "Capital terrain", "Terrain", "If troops occupy 5 turns you lose", "0", "10", "Default", "0");
//		createCard("Swamp", "Swamp", "0", "0", "0", "Swamp terrain", "Terrain", "Troops take 1 damage on this tile", "0", "10", "Rare", "0");
//		createCard("Mountains", "Mountains", "0", "0", "0", "Mountains terrain", "Terrain", "Reduces troop movement speed, Enhances view distance"
//				, "0", "10", "Common", "0");
//		createCard("Desert", "Desert", "0", "0", "0", "Desert terrain", "Terrain", "Aquatic troops speed is halved", "0", "10", "Common", "0");
//		createCard("Roads", "Roads", "0", "0", "0", "Roads terrain", "Terrain", "Increases land units movement speed", "0", "10", "Common", "0");
//		createCard("River", "River", "0", "0", "0", "River terrain", "Terrain", "Aquatic units do not use movement", "0", "10", "Common", "0");
//		createCard("Volcano", "Volcano", "0", "0", "0", "Volcano terrain", "Terrain", "Every X turns troops nearby take Y damage", "0", "10", "Rare", "0");
//		createCard("Lake", "Lake", "0", "0", "0", "Lake terrain", "Terrain", "Aquatic units do not use movement,Land units can not move"
//				+ "onto this tile", "0", "10", "Common", "0");
//		createCard("Arctic", "Arctic", "0", "0", "0", "Arctic terrain", "Terrain", "Every X turns troops nearby take Y damage,"
//				+ "Consumes 2 movement to step across", "0", "10", "Rare", "0");
//		createCard("DefaultTerrain", "Default Terrain", "0", "0", "0", "Default Terrain", "Default Terrain", "0", "0", "0", "Default", "0");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("Successfully created cards");
	}
	
	/**
	 * Inits the cards from file.
	 */
	protected void initCardsFromFile() {
		File file = new File("cards.txt");
		ArrayList<String> card = new ArrayList<>();
		try {
			Scanner in = new Scanner(file);
			String query = "";
			String id = "";
			while(in.hasNextLine()) {
				card.add(in.nextLine());
				if(card.size()==12) {
					if(!card.get(0).equals("name")) {
//						createCard(card.get(0), card.get(1), card.get(2), card.get(3), card.get(4), card.get(5), card.get(6), card.get(7), card.get(8), 
//								card.get(9), card.get(10), card.get(11));	
						id = generateCardID(16);
						query += "INSERT INTO CARD (name,id,imgfilename,attack,speed,health,description,"
								+ "type,ability,cost,value,rarity,rnge,dateCreated,modified) VALUES ('" + card.get(0) + "', '" + id + "','"
								+ card.get(1) + "','" + card.get(2) + "','" + card.get(3) + "','" + card.get(4) + "','" + card.get(5) + "','" + card.get(6)
								+ "','" + card.get(7) + "','" + card.get(8) + "','" + card.get(9) + "','" + card.get(10) + "','" + card.get(11) + "','" + getSQLDate() + "','"
								+ getSQLDate() + "');";
						updateDeckTablesWithNewCard(card.get(0));
					}
					card.clear();
				}
			}
			in.close();
			executeCreationQuery(query);
			System.out.println("Cards created");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the avatar.
	 *
	 * @param userID the user ID
	 * @return the avatar
	 */
	public byte[] getAvatar(String userID) {
		if (!isAlphanumeric(userID))return null;
		String query = "SELECT avatar FROM LOGIN WHERE id = '" + userID + "'";
		ResultSet rSet = executeNonCreationQuery(query);
		
		try {
			if(rSet.next()) {
				Blob imageBlob = rSet.getBlob("avatar");
//				InputStream binaryStream = imageBlob.getBinaryStream(0,imageBlob.length());
				byte[] imageBytes = imageBlob.getBytes(1, (int)imageBlob.length());
				return imageBytes;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns the cards a player has in their collection.
	 *
	 * @param userID the user ID
	 * @return string of the cards a player owns
	 */
	public ArrayList<String> getCardNumbersForUser(String userID) {
		ArrayList<String> values = new ArrayList<String>();
		if (!isAlphanumeric(userID)) {
			values.add("INVALID ID");
			return values;
		}
		String query = "SELECT * FROM PLAYERSCARD WHERE playerid = '" + userID + "'";
		ResultSet rSet = executeNonCreationQuery(query);
		try {
			if(rSet.next()) {
				ResultSetMetaData metadata = rSet.getMetaData();
				for(int i = 2;i<=metadata.getColumnCount();i++) {
					values.add(metadata.getColumnName(i));
					values.add(Integer.toString(rSet.getInt(i)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return values;
	}
	
	/**
	 * Adds the card to deck.
	 *
	 * @param deckID the deck ID
	 * @param name the name
	 * @return the string
	 */
	public String addCardToDeck(String deckID,String name) {
		if (!isAlphanumeric(deckID)) return "INVALID DECKID";
		if (!isAlphanumeric(name)) return "INVALID CARDNAME";
		String countOfCard = "SELECT " + name + " FROM DECK WHERE deckid = '" + deckID + "'";
		ResultSet rSet = executeNonCreationQuery(countOfCard);
		int cardCount = 0;
		try {
			if(rSet.next()) {
				cardCount = rSet.getInt(name);
			}
			String query = "UPDATE DECK SET " + name + " = " + (cardCount + 1) + " WHERE deckid = '" + deckID + "'";
			executeCreationQuery(query);
			return "Card added to deck";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Adds the deck to DB.
	 *
	 * @param deck the deck
	 * @return the string
	 */
	//TODO
	public String addDeckToDB(Deck deck) {
		String id = deck.getDeckID();
		String uId = deck.getUserID();
		String deckName = deck.getDeckName();
		if(id==null || id.equals("")) {
			deck.setDeckID(generateDeckID(16));
			id = deck.getDeckID();
			String query = "INSERT INTO DECK(playerid,deckid,deckname) VALUES('" + uId + "','" + id + "','" + deckName + "')";
			if(executeCreationQuery(query)) {
				ArrayList<Card> cards = deck.getCards();
				for(int i = 0; i < cards.size();i++) {
					addCardToDeck(id, cards.get(i).getName());
				}
			}else {
				return "Error";
			}
		}else {
			String query = "DELETE FROM DECK WHERE playerid = '" + uId + "' AND deckid = '" + id + "'";
			executeCreationQuery(query);
			query = "INSERT INTO DECK(playerid,deckid,deckname) VALUES('" + uId + "','" + id + "','" + deckName + "')";
			if(executeCreationQuery(query)) {
				ArrayList<Card> cards = deck.getCards();
				for(int i = 0; i < cards.size();i++) {
					addCardToDeck(id, cards.get(i).getName());
				}
			}
		}
		
		
	
		
		return "Successfully added deck";
	}
	
	/**
	 * Gets the file in bytes.
	 *
	 * @param filename the filename
	 * @return the file in bytes
	 */
	private byte[] getFileInBytes(String filename) {
		File file = new java.io.File(filename);
		
		 if(((file.getName().contains(".jpg")) || (file.getName().contains(".jpeg")||(file.getName().contains(".png")))) && file.exists())
        {
			try {
				return Files.readAllBytes(file.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		 return null;
	}
	
	/**
	 * Gets the picture back.
	 *
	 * @param pic the pic
	 */
	public void getPictureBack(byte[] pic) {
		try {
			File file = new File("playeravatar.png");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(pic);
			fos.close();
			}catch(Exception e) {
			}
	}
	
	/**
	 * Adds the card to players collection.
	 *
	 * @param playerID the player ID
	 * @param cardName the card name
	 * @return the string
	 */
	public String addCardToPlayersCollection(String playerID, String cardName) {
		if (!isAlphanumeric(playerID)) return "INVALID PLAYERID";
		if (!isAlphanumeric(cardName)) return "INVALID CARDNAME";
		String query = "SELECT " + cardName + " FROM PLAYERSCARD WHERE playerid = '" + playerID + "'";
		ResultSet rSet = executeNonCreationQuery(query);
		try {
			int cardCount = 0;
			if(rSet.next()) {
				cardCount = rSet.getInt(cardName);
			}else {
				return "INVALID CARDNAME";
			}
			query = "UPDATE PLAYERSCARD SET " + cardName + " = " + (cardCount + 1) + " WHERE playerid = '" + playerID + "'";
			executeCreationQuery(query);
			return "Card added to collection";
		} catch (SQLException e) {
			e.printStackTrace();
			return "INVALID CARDNAME";
		}
		
	}
	
	/**
	 * Gets the players deck I ds.
	 *
	 * @param playerID the player ID
	 * @return the players deck I ds
	 */
	public ArrayList<String> getPlayersDeckIDs(String playerID){
		String query = "SELECT deckid FROM DECK WHERE playerid = '" + playerID + "'";
		ResultSet rSet = executeNonCreationQuery(query);
		ArrayList<String> idStrings = new ArrayList<>();
		try {
			while(rSet.next()) {
				idStrings.add(rSet.getString("deckid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idStrings;
	}
	
	/**
	 * Gets the deck name.
	 *
	 * @param deckid the deckid
	 * @return the deck name
	 */
	public String getDeckName(String deckid) {
		String query = "SELECT deckname FROM DECK WHERE deckid = '" + deckid + "'";
		String name = "";
		ResultSet rSet = executeNonCreationQuery(query);
		try {
			if(rSet.next()) {
				name = rSet.getString("deckname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	
	/**
	 * Deck id taken.
	 *
	 * @param deckid the deckid
	 * @return true, if successful
	 */
	public boolean deckIdTaken(String deckid) {
		String query = "SELECT deckid FROM DECK WHERE deckid = '" + deckid + "'";
		ResultSet rSet = executeNonCreationQuery(query);
		try {
			if(rSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Gets the cards in deck.
	 *
	 * @param deckid the deckid
	 * @return the cards in deck
	 */
	public ArrayList<String> getCardsInDeck(String deckid){
		ArrayList<String> cardNames = new ArrayList<>();
		String query = "SELECT * FROM DECK WHERE deckid = '" + deckid +  "'";
		String curCardName = "";
		ResultSet rSet = executeNonCreationQuery(query);
		try {
			if(rSet.next()) {
				ResultSetMetaData metadata = rSet.getMetaData();
				for(int i = 4;i<=metadata.getColumnCount();i++) {
					curCardName = metadata.getColumnName(i);
//					System.out.println(curCardName + ": " + rSet.getInt(i));
					for(int j = 0;j<rSet.getInt(i);j++) {
						cardNames.add(curCardName);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cardNames;
	}
	
	/**
	 * Gets the count of player terrain cards deck.
	 *
	 * @param userID the user ID
	 * @param deckID the deck ID
	 * @return the count of player terrain cards deck
	 */
	public int getCountOfPlayerTerrainCardsDeck(String userID,String deckID) {
		String query = "SELECT * FROM DECK d WHERE d.playerid = '" + userID + "' AND d.deckid = '" + deckID + "'";
		HashMap<String, Card> cardLibrary = getCardMap();
		ResultSet rSet = executeNonCreationQuery(query);
		int count = 0;
		try {
			if(rSet.next()) {
				ResultSetMetaData metaData = rSet.getMetaData();
				for(int i=4;i<=metaData.getColumnCount();i++) {
					int cardNumber = rSet.getInt(i);
					Card card = cardLibrary.get(metaData.getColumnName(i));
					if(card.getType().equals("Terrain")) count +=cardNumber;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * Gets the count of player unit cards deck.
	 *
	 * @param userID the user ID
	 * @param deckID the deck ID
	 * @return the count of player unit cards deck
	 */
	public int getCountOfPlayerUnitCardsDeck(String userID,String deckID) {
		String query = "SELECT * FROM DECK d WHERE d.playerid = '" + userID + "' AND d.deckid = '" + deckID + "'";
		HashMap<String, Card> cardLibrary = getCardMap();
		ResultSet rSet = executeNonCreationQuery(query);
		int count = 0;
		try {
			if(rSet.next()) {
				ResultSetMetaData metaData = rSet.getMetaData();
				for(int i=4;i<=metaData.getColumnCount();i++) {
					int cardNumber = rSet.getInt(i);
					Card card = cardLibrary.get(metaData.getColumnName(i));
					if(card.getType().equals("Land") || card.getType().equals("Aquatic") 
							|| card.getType().equals("Air")) count +=cardNumber;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * NEEDS UPDATE IF NEW TRAPS ARE CREATED.
	 *
	 * @param userID the user ID
	 * @param deckID the deck ID
	 * @return the count of player trap cards deck
	 */
	public int getCountOfPlayerTrapCardsDeck(String userID,String deckID) {
		String query = "SELECT * FROM DECK d WHERE d.playerid = '" + userID + "' AND d.deckid = '" + deckID + "'";
		HashMap<String, Card> cardLibrary = getCardMap();
		ResultSet rSet = executeNonCreationQuery(query);
		int count = 0;
		try {
			if(rSet.next()) {
				ResultSetMetaData metaData = rSet.getMetaData();
				for(int i=4;i<=metaData.getColumnCount();i++) {
					int cardNumber = rSet.getInt(i);
					Card card = cardLibrary.get(metaData.getColumnName(i));
					if(card.getType().equals("Land Trap") || card.getType().equals("Mountain Trap")) count +=cardNumber;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * Account type.
	 *
	 * @param userID the user ID
	 * @return the int
	 */
	public int accountType(String userID) {
		String query= "SELECT accountType FROM LOGIN WHERE id = '" + userID + "'";
		ResultSet rSet = executeNonCreationQuery(query);
		try {
			if(rSet.next()) {
				int accountType = rSet.getInt("accountType");
				return accountType;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Sets the account type.
	 *
	 * @param userID the user ID
	 * @param accountType the account type
	 */
	public void setAccountType(String userID,int accountType) {
		String query = "UPDATE LOGIN SET accountType = " + accountType + " WHERE id = '" + userID + "'";
		executeCreationQuery(query);
		if(accountType == 0 || accountType == 1) {
			initNewDeckForUser(userID);
		}
	}
	
	/**
	 * Gets the card map.
	 *
	 * @return the card map
	 */
	private HashMap<String, Card> getCardMap(){
		HashMap<String, Card> map = new HashMap<String, Card>();
		
		ArrayList<HashMap<String, String>> cards = getCards();
		for(HashMap<String, String> cardMap : cards) {
			Card c = new Card(
					cardMap.get("name"),
					cardMap.get("id"),
					cardMap.get("imgfilename"),
					cardMap.get("description"),
					cardMap.get("rarity"),
					cardMap.get("dateCreated"),
					cardMap.get("modified"),
					cardMap.get("ability"),
					cardMap.get("value"),
					cardMap.get("type"),
					Integer.parseInt(cardMap.get("attack")),
					Integer.parseInt(cardMap.get("speed")),
					Integer.parseInt(cardMap.get("health")),
					Integer.parseInt(cardMap.get("cost")),
					Integer.parseInt(cardMap.get("rnge")),
					null
					);
			map.put(c.getName(), c);
		}
		return map;
	}
	public static boolean isAlphanumeric(String s) {
		String alphanumeric = "AbcdEFGHIJKLmnOPQRSTUVWXYZaBCDefghijklMNopqrstuvwxyz0125436789";
		for(int i = 0; i < s.length();i++) {
			if(!alphanumeric.contains(String.valueOf(s.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Main method for testing new functions.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String args[]) throws InterruptedException {
		// long startTime = System.currentTimeMillis();
		Database_Utils db = new Database_Utils();
		db.initCardsFromFile();
//		System.out.println(db.register("bob", "d"));
//		System.out.println(db.register("Admin_bob", "b"));
//		System.out.println(db.register("dkfsdslfdsjf", "d"));
//		db.initCards();
//		db.initCards();
//		System.out.println(db.getCountOfPlayerTerrainCardsDeck("kEzzSg0hB8TBUnZR", "QkrOMSv0Uxz9BHxh"));
//		System.out.println(db.getCountOfPlayerUnitCardsDeck("kEzzSg0hB8TBUnZR", "QkrOMSv0Uxz9BHxh"));
//		System.out.println(db.getCountOfPlayerTrapCardsDeck("kEzzSg0hB8TBUnZR", "QkrOMSv0Uxz9BHxh"));
//		System.out.println(db.register("a", "a"));
//		ArrayList<String> cards = db.getCardsInDeck("YyQGUFjjOvgZKodc");
//		for(String s:cards) {
//			System.out.println(s);
//		}
//		System.out.println(cards.size());
//		db.initCards();
//		System.out.println(db.register("dog", "dog"));
//		System.out.println(db.login("dog", "dog"));
//		db.register("bob1235", "bob1235");
//		System.out.println("CONSTRUCTOR DONE");
//		db.initCards();
//		System.out.println("CARDS DONE");
//		long start = System.currentTimeMillis();
//		db.register("jerry2", "jerry2");
//		 System.out.println("Time: " + (System.currentTimeMillis() - start) + " ms");
		
//		db.register("bosadfdsfab2", "bobdsfad2");
//		db.getPictureBack(db.getAvatar("np90D1q2eLCGnkw5"));
//		System.out.println(db.login("bob", "bob"));
//		System.out.println(db.getCountOfPlayersDeckCards("Kwva1NXYEkNyz8RC"));
//		db.register("by2sdfsadfdfasfdsafdsaassfdae", "b2ysdadsfsadffdsafdssdfafe");
//		db.register("1", "1");

//		System.out.println(db.getCountOfPlayersCards("4kpc83a9pgancQMB"));
//		System.out.println(db.getCardNumbersForUser("VmlpOuKqH2I0wsxi"));
		// db.register("1", "1");
//		 db.createCard("name", "common", "attack", "speed", "health", "desc", "type",
//		 "ability", "cost", "value",
//		 "Common");
//		 db.createCard("name", "Epic", "attack", "speed", "health", "desc", "type",
//		 "ability", "cost", "value",
//		 "Epic");
//		 db.createCard("name", "Legend", "attack", "speed", "health", "desc", "type",
//		 "ability", "cost", "value",
//		 "Legendary");
//		 db.createCard("name", "Rare", "attack", "speed", "health", "desc", "type",
//		 "ability", "cost", "value",
//		 "Rare");
//		System.out.println(db.getCardIDByRarityAddToUser("VmlpOuKqH2I0wsxi", "Common"));
		// db.register("zoey6932423423", "zoey36");
		// // db.register("bob", "billy");
		// // System.out.println(db.login("bob", "billy"));
		// // db.login("Bob", "bob");
		// // db.createCard("name", "file", "attack", "speed", "health", "desc", "type",
		// // "ability", "cost", "value",
		// // "rarity");
		// // System.out.println(db.register("sdfsd", "dsf"));
		// Random random = new Random();
		// for(int i = 0;i<3;i++) {
		// db.createCard("name" +random.nextInt(), "file"+random.nextInt(),
		// "attack"+random.nextInt(), "speed"+random.nextInt(),
		// "health"+random.nextInt(), "desc"+random.nextInt(), "type"+random.nextInt(),
		// "ability"+random.nextInt(), "cost"+random.nextInt(),
		// "value"+random.nextInt(),
		// "rarity"+random.nextInt());
		// }
		// ArrayList<String> cardIDs = db.getAllCardIDs();
		// for (String c : cardIDs) {
		// HashMap<String, String> card = new HashMap<>();
		// card = db.getCardInfoByID(c);
		// if (card != null) {
		// Iterator<?> iterator = card.entrySet().iterator();
		// while (iterator.hasNext()) {
		// Map.Entry<String, String> p = (Entry<String, String>) iterator.next();
		// System.out.println("Column: " + p.getKey() + " Value: " + p.getValue());
		// }
		// }
		// }
//		 int cardNum = 1;
//		ArrayList<HashMap<String, String>> cards = db.getCards();
//		for (HashMap<String, String> card : cards) {
//			if (card != null) {
//				Iterator<?> iterator = card.entrySet().iterator();
//				while (iterator.hasNext()) {
//					Map.Entry<String, String> p = (Entry<String, String>) iterator.next();
//					System.out.println(cardNum + ".) Column: " + p.getKey() + " Value: " + p.getValue());
//				}
//				System.out.println("----------------------------------------------------------------------");
//			}
//			cardNum++;
//		}
		// long endTime = System.currentTimeMillis();
		// System.out.println("Took "+(endTime - startTime)/1000.0 + " s");
		
		
		

	}
}
