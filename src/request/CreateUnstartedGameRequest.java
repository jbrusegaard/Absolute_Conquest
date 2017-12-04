package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateUnstartedGameRequest.
 */
public class CreateUnstartedGameRequest extends Request implements Serializable {

	/** The user ID. */
	private String userID;
	
	/**
	 * Instantiates a new creates the unstarted game request.
	 *
	 * @param userID the user ID
	 */
	public CreateUnstartedGameRequest(String userID) {
		super("createUnstartedGame");
		this.userID = userID;
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
	 * @param username the new user ID
	 */
	public void setUserID(String username) {
		this.userID = username;
	}
}
