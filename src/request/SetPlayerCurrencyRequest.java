package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SetPlayerCurrencyRequest.
 */
public class SetPlayerCurrencyRequest extends Request implements Serializable {
	
	/** The user id. */
	String userId;
	
	/** The currency. */
	int currency;

	/**
	 * Instantiates a new sets the player currency request.
	 *
	 * @param userId the user id
	 * @param currency the currency
	 */
	public SetPlayerCurrencyRequest(String userId, int currency) {
		super("setPlayerCurrency");
		this.userId = userId;
		this.currency = currency;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the currency.
	 *
	 * @return the currency
	 */
	public int getCurrency() {
		return currency;
	}

	/**
	 * Sets the currency.
	 *
	 * @param currency the new currency
	 */
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	
}
