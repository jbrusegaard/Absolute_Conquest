package response;

import java.util.ArrayList;

import game.client.UnstartedGame;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class TestGameResponse.
 */
public class TestGameResponse extends Response{
	
	/** The players. */
	ArrayList<PlayerProfile> players = new ArrayList<PlayerProfile>();
	
	/** The game id. */
	String gameId;
	
	/**
	 * Instantiates a new test game response.
	 */
	public TestGameResponse() {
		super("testGame");
	}
	
	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public ArrayList<PlayerProfile> getPlayers() {
		return players;
	}
	
	/**
	 * Sets the players.
	 *
	 * @param players the new players
	 */
	public void setPlayers(ArrayList<PlayerProfile> players) {
		this.players = players;
	}
	
	/**
	 * Gets the game id.
	 *
	 * @return the game id
	 */
	public String getGameId() {
		return gameId;
	}
	
	/**
	 * Sets the game id.
	 *
	 * @param gameId the new game id
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}


}

