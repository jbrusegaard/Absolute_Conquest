package response;

import java.util.ArrayList;

import game.client.UnstartedGame;

// TODO: Auto-generated Javadoc
/**
 * The Class GetPlayerCardsByIdResponse.
 */
public class GetPlayerCardsByIdResponse extends Response{

	/** The card amounts. */
	ArrayList<String> cardAmounts;
	
	/**
	 * Instantiates a new gets the player cards by id response.
	 *
	 * @param cardAmounts the card amounts
	 */
	public GetPlayerCardsByIdResponse(ArrayList<String> cardAmounts) {
		super("getPlayerCardsById");
		this.cardAmounts = cardAmounts;
	}

	/**
	 * Gets the card amounts.
	 *
	 * @return the card amounts
	 */
	public ArrayList<String> getCardAmounts() {
		return cardAmounts;
	}

	/**
	 * Sets the card amounts.
	 *
	 * @param cardAmounts the new card amounts
	 */
	public void setCardAmounts(ArrayList<String> cardAmounts) {
		this.cardAmounts = cardAmounts;
	}



}

