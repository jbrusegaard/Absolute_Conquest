package response;

import game.client.UnstartedGame;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class UserJoinedUnstartedGameResponse.
 */
public class UserJoinedUnstartedGameResponse extends Response{

	/** The p. */
	private PlayerProfile p;
	
	/**
	 * Instantiates a new user joined unstarted game response.
	 *
	 * @param p the p
	 */
	public UserJoinedUnstartedGameResponse(PlayerProfile p) {
		super("userJoinedUnstartedGame");
		this.p = p;
		//this.p.setClientConnection(null);
	}

	/**
	 * Gets the p.
	 *
	 * @return the p
	 */
	public PlayerProfile getP() {
		return p;
	}

	/**
	 * Sets the p.
	 *
	 * @param p the new p
	 */
	public void setP(PlayerProfile p) {
		this.p = p;
	}



}

