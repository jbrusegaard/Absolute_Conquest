package response;

import game.client.UnstartedGame;

// TODO: Auto-generated Javadoc
/**
 * The Class GetCardIdByRarityAddToUserResponse.
 */
public class GetCardIdByRarityAddToUserResponse extends Response{

	/** The card id. */
	private String cardId;
	
	/**
	 * Instantiates a new gets the card id by rarity add to user response.
	 *
	 * @param cardId the card id
	 */
	public GetCardIdByRarityAddToUserResponse(String cardId) {
		super("getCardIdByRarityAddToUser");
		this.cardId = cardId;
	}

	/**
	 * Gets the card id.
	 *
	 * @return the card id
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * Sets the card id.
	 *
	 * @param cardId the new card id
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
