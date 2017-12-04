package response;

import game.client.UnstartedGame;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLeftUnstartedGameResponse.
 */
public class UserLeftUnstartedGameResponse extends Response{

	/** The user id. */
	private String userId;
	
	/**
	 * Instantiates a new user left unstarted game response.
	 *
	 * @param userId the user id
	 */
	public UserLeftUnstartedGameResponse(String userId) {
		super("userLeftUnstartedGame");
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

