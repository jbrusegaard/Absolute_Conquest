package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class JoinGameAsSpectatorRequest.
 */
public class JoinGameAsSpectatorRequest extends Request implements Serializable {

	/** The user id. */
	private String userId;
	
	/** The game ID. */
	private String gameID;
		
	/**
	 * Instantiates a new join game as spectator request.
	 *
	 * @param userId the user id
	 * @param gameId the game id
	 */
	public JoinGameAsSpectatorRequest(String userId, String gameId) {
		super("joinGameAsSpectator");
		this.userId = userId;
		this.gameID = gameId;
		
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
