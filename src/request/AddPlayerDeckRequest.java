package request;

/**
 * @author Jeremiah Brusegaard
 */

import java.io.Serializable;

import player.Deck;
import response.Response;

// TODO: Auto-generated Javadoc
/**
 * The Class AddPlayerDeckRequest.
 */
public class AddPlayerDeckRequest extends Request implements Serializable {

	/** The deck. */
	private Deck deck;
	
	/**
	 * Instantiates a new adds the player deck request.
	 *
	 * @param deck the deck
	 */
	public AddPlayerDeckRequest(Deck deck) {
		super("addPlayerDeck");
		this.deck = deck;
	}
	
	/**
	 * Sets the deck.
	 *
	 * @param deck the new deck
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	/**
	 * Gets the deck.
	 *
	 * @return the deck
	 */
	public Deck getDeck() {
		return this.deck;
	}

}
