package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GetCountOfPlayersCardsRequest.
 */
public class GetCountOfPlayersCardsRequest extends Request implements Serializable {

	/** The user id. */
	private String userId;
	
	/**
	 * Instantiates a new gets the count of players cards request.
	 *
	 * @param userId the user id
	 */
	public GetCountOfPlayersCardsRequest(String userId) {
		super("getCountOfPlayerCards");
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
