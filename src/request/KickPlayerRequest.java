package request;

// TODO: Auto-generated Javadoc
/**
 * The Class KickPlayerRequest.
 */
public class KickPlayerRequest extends Request {

	/** The user ID. */
	private String userID;
	
	/** The game ID. */
	private String gameID;
	
	/**
	 * Instantiates a new kick player request.
	 *
	 * @param userID the user ID
	 * @param gameID the game ID
	 */
	public KickPlayerRequest(String userID, String gameID) {
		super("kickPlayer");
		this.userID = userID;
		this.gameID = gameID;
		
	}
	
	/**
	 * Gets the user ID.
	 *
	 * @return the user ID
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * Sets the user ID.
	 *
	 * @param userID the new user ID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/**
	 * Gets the game ID.
	 *
	 * @return the game ID
	 */
	public String getGameID() {
		return gameID;
	}
	
	/**
	 * Sets the game ID.
	 *
	 * @param gameID the new game ID
	 */
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	
}
