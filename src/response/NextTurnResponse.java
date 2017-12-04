package response;

import game.client.UnstartedGame;

// TODO: Auto-generated Javadoc
/**
 * The Class NextTurnResponse.
 */
public class NextTurnResponse extends Response{

	/** The user id. */
	String userId;
	
	/**
	 * Instantiates a new next turn response.
	 *
	 * @param userId the user id
	 */
	public NextTurnResponse(String userId) {
		super("nextTurn");
		this.userId = userId;

	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}

