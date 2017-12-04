package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GetProfilePictureRequest.
 */
public class GetProfilePictureRequest extends Request implements Serializable {

	/** The user id. */
	private String userId;
	
	/**
	 * Instantiates a new gets the profile picture request.
	 *
	 * @param userId the user id
	 */
	public GetProfilePictureRequest(String userId) {
		super("getProfilePicture");
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
