package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginRequest.
 */
public class LoginRequest extends Request implements Serializable {

	/** The username. */
	private String username;
	
	/** The password. */
	private char[] password;
	
	/**
	 * Instantiates a new login request.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public LoginRequest(String username, char[] password) {
		super("login");
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
