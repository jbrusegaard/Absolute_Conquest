package response;

import game.client.UnstartedGame;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginResponse.
 */
public class LoginResponse extends Response{

	/** The p. */
	PlayerProfile p;
	
	/** The result. */
	String result;
	
	/**
	 * Instantiates a new login response.
	 *
	 * @param p the p
	 * @param result the result
	 */
	public LoginResponse(PlayerProfile p, String result) {
		super("login");
		this.p = p;
		this.result = result;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(String result) {
		this.result = result;
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

