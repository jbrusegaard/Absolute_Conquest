package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SetProfilePictureRequest.
 */
public class SetProfilePictureRequest extends Request implements Serializable {

	/** The user id. */
	private String userId;
	
	/** The image. */
	private byte[] image;
	
	/**
	 * Instantiates a new sets the profile picture request.
	 *
	 * @param userId the user id
	 * @param image the image
	 */
	public SetProfilePictureRequest(String userId, byte[] image) {
		super("setProfilePicture");
		this.userId = userId;
		this.image = image;
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
	 * Gets the avatar.
	 *
	 * @return the avatar
	 */
	public byte[] getAvatar() {
		return image;
	}

	/**
	 * Sets the avatar.
	 *
	 * @param avatar the new avatar
	 */
	public void setAvatar(byte[] avatar) {
		this.image = avatar;
	}
}
