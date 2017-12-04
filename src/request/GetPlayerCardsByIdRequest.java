package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GetPlayerCardsByIdRequest.
 */
public class GetPlayerCardsByIdRequest extends Request implements Serializable {
	
	/** The user id. */
	String userId;

	/**
	 * Instantiates a new gets the player cards by id request.
	 *
	 * @param userId the user id
	 */
	public GetPlayerCardsByIdRequest(String userId) {
		super("getPlayerCardsById");
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
