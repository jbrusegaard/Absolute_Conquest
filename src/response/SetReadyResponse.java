package response;

import game.client.UnstartedGame;

// TODO: Auto-generated Javadoc
/**
 * The Class SetReadyResponse.
 */
public class SetReadyResponse extends Response{

	/** The user id. */
	String userId;
	
	/** The game id. */
	String gameId;
	
	/**
	 * Instantiates a new sets the ready response.
	 *
	 * @param gameId the game id
	 * @param userId the user id
	 */
	public SetReadyResponse(String gameId, String userId) {
		super("setReady");
		this.gameId = gameId;
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

	/**
	 * Gets the game id.
	 *
	 * @return the game id
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * Sets the game id.
	 *
	 * @param gameId the new game id
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}


}

