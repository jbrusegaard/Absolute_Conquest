package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class TestGameRequest.
 */
public class TestGameRequest extends Request implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1;
	
	/** The player. */
	String player;

	/**
	 * Instantiates a new test game request.
	 *
	 * @param player the player
	 */
	public TestGameRequest(String player) {
		super("testGame");
		this.player = player;

	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public String getPlayer() {
		return player;
	}
	
}
