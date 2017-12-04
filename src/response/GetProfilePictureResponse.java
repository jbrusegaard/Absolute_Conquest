package response;

import game.client.UnstartedGame;

// TODO: Auto-generated Javadoc
/**
 * The Class GetProfilePictureResponse.
 */
public class GetProfilePictureResponse extends Response{


	/** The image. */
	private byte[] image;
	
	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * Instantiates a new gets the profile picture response.
	 *
	 * @param image the image
	 */
	public GetProfilePictureResponse(byte[] image) {
		super("getProfilePicture");
		this.image = image;
	}
}
