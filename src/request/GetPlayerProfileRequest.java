package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GetPlayerProfileRequest.
 */
public class GetPlayerProfileRequest extends Request implements Serializable {

	/** The user id. */
	private String userId;
	
	/**
	 * Instantiates a new gets the player profile request.
	 *
	 * @param userId the user id
	 */
	public GetPlayerProfileRequest(String userId) {
		super("getPlayerProfile");
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
