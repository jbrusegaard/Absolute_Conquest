package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class AddCardToDeckRequest.
 */
public class AddCardToDeckRequest extends Request implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1;
	
	/** The deck id. */
	String deckId;
	
	/** The card name. */
	String cardName;

	/**
	 * Instantiates a new adds the card to deck request.
	 *
	 * @param deckId the deck id
	 * @param cardName the card name
	 */
	public AddCardToDeckRequest(String deckId, String cardName) {
		super("addCardToDeck");
		this.deckId = deckId;
		this.cardName = cardName;
	}
	
	/**
	 * Gets the deck id.
	 *
	 * @return the deck id
	 */
	public String getDeckId() {
		return deckId;
	}

	/**
	 * Sets the deck id.
	 *
	 * @param deckId the new deck id
	 */
	public void setDeckId(String deckId) {
		this.deckId = deckId;
	}

	/**
	 * Gets the card name.
	 *
	 * @return the card name
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * Sets the card name.
	 *
	 * @param cardName the new card name
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
}
