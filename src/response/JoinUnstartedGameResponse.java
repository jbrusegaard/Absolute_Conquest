package response;

import java.util.ArrayList;

import game.client.UnstartedGame;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class JoinUnstartedGameResponse.
 */
public class JoinUnstartedGameResponse extends Response{

	/** The result. */
	String result;
	
	/** The game id. */
	String gameId;
	
	/** The player list. */
	ArrayList<PlayerProfile> playerList;
	
	/** The ready list. */
	ArrayList<Boolean> readyList;
	
	/**
	 * Instantiates a new join unstarted game response.
	 *
	 * @param gameId the game id
	 * @param playerList the player list
	 * @param readyList the ready list
	 */
	public JoinUnstartedGameResponse(String gameId, ArrayList<PlayerProfile> playerList, ArrayList<Boolean> readyList) {
		super("joinUnstartedGame");
		this.result = "success";
		this.gameId = gameId;
		this.playerList = playerList;
		this.readyList = readyList;
	}
	
	/**
	 * Gets the ready list.
	 *
	 * @return the ready list
	 */
	public ArrayList<Boolean> getReadyList() {
		return readyList;
	}
	
	/**
	 * Sets the ready list.
	 *
	 * @param readyList the new ready list
	 */
	public void setReadyList(ArrayList<Boolean> readyList) {
		this.readyList = readyList;
	}
	
	/**
	 * Instantiates a new join unstarted game response.
	 *
	 * @param result the result
	 */
	public JoinUnstartedGameResponse(String result) {
		super("joinUnstartedGame");
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

	/**
	 * Gets the player list.
	 *
	 * @return the player list
	 */
	public ArrayList<PlayerProfile> getPlayerList() {
		return playerList;
	}

	/**
	 * Sets the player list.
	 *
	 * @param playerList the new player list
	 */
	public void setPlayerList(ArrayList<PlayerProfile> playerList) {
		this.playerList = playerList;
	}


}
