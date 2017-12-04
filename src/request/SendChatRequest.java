package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SendChatRequest.
 */
public class SendChatRequest extends Request implements Serializable {
	
	/** The game id. */
	String gameId;
	
	/** The username. */
	String username;
	
	/** The message. */
	String message;
	
	/** The location. */
	String location;

	/**
	 * Instantiates a new send chat request.
	 *
	 * @param gameId the game id
	 * @param location the location
	 * @param username the username
	 * @param message the message
	 */
	public SendChatRequest(String gameId, String location, String username, String message) {
		super("sendChat");
		this.gameId = gameId;
		this.location = location;
		this.message = message;
		this.username = username;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
