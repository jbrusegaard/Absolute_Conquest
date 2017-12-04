package response;

import game.client.UnstartedGame;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterResponse.
 */
public class RegisterResponse extends Response{

	/** The result. */
	String result;
	
	/**
	 * Instantiates a new register response.
	 *
	 * @param result the result
	 */
	public RegisterResponse(String result) {
		super("register");
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

}

