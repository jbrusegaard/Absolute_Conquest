package response;

import game.client.UnstartedGame;

// TODO: Auto-generated Javadoc
/**
 * The Class SendChatResponse.
 */
public class SendChatResponse extends Response{

	/** The location. */
	String location;
	
	/** The username. */
	String username;
	
	/** The message. */
	String message;
	
	/**
	 * Instantiates a new send chat response.
	 *
	 * @param location the location
	 * @param username the username
	 * @param message the message
	 */
	public SendChatResponse(String location, String username, String message) {
		super("sendChat");
		this.location = location;
		this.username = username;
		this.message = message;
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

