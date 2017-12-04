package response;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class GetUsernamesResponse.
 */
public class GetUsernamesResponse extends Response implements Serializable{

	/** The usernames. */
	ArrayList<String> usernames;
	
	/**
	 * Instantiates a new gets the usernames response.
	 *
	 * @param usernames the usernames
	 */
	public GetUsernamesResponse(ArrayList<String> usernames) {
		super("getUsernames");
		this.usernames = usernames;
	}

	/**
	 * Gets the usernames.
	 *
	 * @return the usernames
	 */
	public ArrayList<String> getUsernames() {
		return usernames;
	}

	/**
	 * Sets the usernames.
	 *
	 * @param usernames the new usernames
	 */
	public void setUsernames(ArrayList<String> usernames) {
		this.usernames = usernames;
	}
}
