package response;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountTypeGetResponse.
 */
public class AccountTypeGetResponse extends Response{

	/** The user ID. */
	private String userID;
	
	/** The account type. */
	private int accountType;
	
	/**
	 * Instantiates a new account type get response.
	 *
	 * @param userID the user ID
	 * @param accountType the account type
	 */
	public AccountTypeGetResponse(String userID,int accountType) {
		super("accountTypeGetResponse");
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
