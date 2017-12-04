package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GetCardIdByRarityAddToUserRequest.
 */
public class GetCardIdByRarityAddToUserRequest extends Request implements Serializable {

	/** The user id. */
	private String userId;
	
	/** The type. */
	private String type;
	
	/**
	 * Instantiates a new gets the card id by rarity add to user request.
	 *
	 * @param userID the user ID
	 * @param type the type
	 */
	public GetCardIdByRarityAddToUserRequest(String userID, String type) {
		super("getCardIdByRarityAddToUser");
		this.userId = userID;
		this.type = type;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
}
