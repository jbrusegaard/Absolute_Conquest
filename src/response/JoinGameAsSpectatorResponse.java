package response;

import game.Game;
import game.client.UnstartedGame;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class JoinGameAsSpectatorResponse.
 */
public class JoinGameAsSpectatorResponse extends Response{

	/** The g. */
	private Game g;
	
	/**
	 * Instantiates a new join game as spectator response.
	 *
	 * @param g the g
	 */
	public JoinGameAsSpectatorResponse(Game g) {
		super("joinGameAsSpectator");
		this.g = g;
	}

	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame() {
		return g;
	}

	/**
	 * Sets the game.
	 *
	 * @param g the new game
	 */
	public void setGame(Game g) {
		this.g = g;
	}



}

