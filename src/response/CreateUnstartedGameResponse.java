package response;

import game.client.UnstartedGame;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateUnstartedGameResponse.
 */
public class CreateUnstartedGameResponse extends Response{

	/** The game ID. */
	String gameID;
	
	/**
	 * Instantiates a new creates the unstarted game response.
	 *
	 * @param gameID the game ID
	 */
	public CreateUnstartedGameResponse(String gameID) {
		super("createUnstartedGame");
		this.gameID = gameID;
	}

	/**
	 * Gets the game ID.
	 *
	 * @return the game ID
	 */
	public String getGameID() {
		return gameID;
	}

	/**
	 * Sets the game ID.
	 *
	 * @param gameID the new game ID
	 */
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}


}

