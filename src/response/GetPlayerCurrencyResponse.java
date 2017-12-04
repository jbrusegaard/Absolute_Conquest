package response;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GetPlayerCurrencyResponse.
 */
public class GetPlayerCurrencyResponse extends Response implements Serializable {

	/** The currency. */
	private int currency;
	
	/**
	 * Instantiates a new gets the player currency response.
	 *
	 * @param currency the currency
	 */
	public GetPlayerCurrencyResponse(int currency){
		super("getPlayerCurrency");
		this.currency = currency;
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
