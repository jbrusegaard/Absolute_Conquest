package response;

import game.client.UnstartedGame;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class GetPlayerProfileResponse.
 */
public class GetPlayerProfileResponse extends Response{

	/** The p. */
	private PlayerProfile p;
	
	/**
	 * Instantiates a new gets the player profile response.
	 *
	 * @param p the p
	 */
	public GetPlayerProfileResponse(PlayerProfile p) {
		super("getPlayerProfile");
		this.p = p;
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
	public void setP(PlayerProfile p){
		this.p = p;
	}
}
