package request;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountTypeSetRequest.
 */
public class AccountTypeSetRequest extends Request{
	
	/** The account type. */
	private int accountType;
	
	/** The user ID. */
	private String userID;
	
	/**
	 * Instantiates a new account type set request.
	 *
	 * @param userID the user ID
	 * @param accountType the account type
	 */
	public AccountTypeSetRequest(String userID,int accountType) {
		super("setAdminStatus");
		this.userID = userID;
		this.accountType = accountType;
	}
	
	/**
	 * Gets the account type.
	 *
	 * @return the account type
	 */
	public int getAccountType() {
		return this.accountType;
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
