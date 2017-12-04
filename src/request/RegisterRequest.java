package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterRequest.
 */
public class RegisterRequest extends Request implements Serializable {
	
	/** The username. */
	String username;
	
	/** The password. */
	char[] password;
	
	/**
	 * Instantiates a new register request.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public RegisterRequest(String username, char[] password) {
		super("register");
		this.username = username;
		this.password = password;
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
	 * Gets the password.
	 *
	 * @return the password
	 */
	public char[] getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(char[] password) {
		this.password = password;
	}

}
