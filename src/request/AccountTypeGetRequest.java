package request;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountTypeGetRequest.
 */
public class AccountTypeGetRequest extends Request{

	/** The user ID. */
	private String userID;
	
	/**
	 * Instantiates a new account type get request.
	 *
	 * @param userID the user ID
	 */
	public AccountTypeGetRequest(String userID) {
		super("getAccountType");
		this.userID = userID;
	}
	
	/**
	 * Gets the user ID.
	 *
	 * @return the user ID
	 */
	public String getUserID() {
		return this.userID;
	}
	
}
