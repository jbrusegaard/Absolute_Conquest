package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class EndTurnRequest.
 */
public class EndTurnRequest extends Request implements Serializable {
	
	/** The user id. */
	String userId;
	
	/** The game id. */
	String gameId;

	/**
	 * Instantiates a new end turn request.
	 *
	 * @param userId the user id
	 * @param gameId the game id
	 */
	public EndTurnRequest(String userId, String gameId) {
		super("endTurn");
		this.userId = userId;
		this.gameId = gameId;
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
