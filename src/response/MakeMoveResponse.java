package response;

import game.MoveInfo;
import game.client.UnstartedGame;

// TODO: Auto-generated Javadoc
/**
 * The Class MakeMoveResponse.
 */
public class MakeMoveResponse extends Response{

	/** The user id. */
	String userId;
	
	/** The info. */
	MoveInfo info;
	
	/**
	 * Instantiates a new make move response.
	 *
	 * @param userId the user id
	 * @param info the info
	 */
	public MakeMoveResponse(String userId, MoveInfo info) {
		super("makeMove");
		this.userId = userId;
		this.info = info;
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
	 * Gets the move info.
	 *
	 * @return the move info
	 */
	public MoveInfo getMoveInfo() {
		return info;
	}

	/**
	 * Sets the move info.
	 *
	 * @param m the new move info
	 */
	public void setMoveInfo(MoveInfo m) {
		this.info = m;
	}

}

