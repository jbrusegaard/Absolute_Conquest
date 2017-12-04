package request;

import java.io.Serializable;

import game.MoveInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class MakeMoveRequest.
 */
public class MakeMoveRequest extends Request implements Serializable {
	
	/** The game id. */
	private String gameId;
	
	/** The user id. */
	private String userId;
	
	/** The Move in. */
	private MoveInfo MoveIn;

	/**
	 * Instantiates a new make move request.
	 *
	 * @param userId the user id
	 * @param gameId the game id
	 * @param mi the mi
	 */
	public MakeMoveRequest(String userId, String gameId, MoveInfo mi) {
		super("makeMove");
		this.userId = userId;
		this.gameId = gameId;
		MoveIn = mi;
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
	
	/**
	 * Gets the move info.
	 *
	 * @return the move info
	 */
	public MoveInfo getMoveInfo() {
		return this.MoveIn; //remove the this later when jerry isn't looking
	}
	
}
