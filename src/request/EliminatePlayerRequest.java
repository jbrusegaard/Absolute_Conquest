package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class EliminatePlayerRequest.
 */
public class EliminatePlayerRequest extends Request implements Serializable {
	
	/** The user ID. */
	private String userID;
	
	/** The game ID. */
	private String gameID;

	/**
	 * Instantiates a new eliminate player request.
	 *
	 * @param userID the user ID
	 * @param gameID the game ID
	 */
	public EliminatePlayerRequest(String userID, String gameID) {
		super("eliminatePlayer");
		this.userID = userID;
		this.gameID = gameID;
	}
	
	/**
	 * Gets the user ID.
	 *
	 * @return the user ID
	 */
	public String getUserID() {
		return this.userID;
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
		return this.gameID;
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
