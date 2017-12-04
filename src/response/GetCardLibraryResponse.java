package response;

import java.util.ArrayList;
import java.util.HashMap;

import game.client.UnstartedGame;
import player.Card;

// TODO: Auto-generated Javadoc
/**
 * The Class GetCardLibraryResponse.
 */
public class GetCardLibraryResponse extends Response{

	/** The card library. */
	private HashMap<String, Card> cardLibrary;
	
	/**
	 * Instantiates a new gets the card library response.
	 *
	 * @param hashMap the hash map
	 */
	public GetCardLibraryResponse(HashMap<String, player.Card> hashMap) {
		super("getCardLibrary");
		this.cardLibrary = hashMap;
	}

	/**
	 * Gets the card library.
	 *
	 * @return the card library
	 */
	public HashMap<String, Card> getCardLibrary() {
		return cardLibrary;
	}

	/**
	 * Sets the card library.
	 *
	 * @param cardLibrary the card library
	 */
	public void setCardLibrary(HashMap<String, Card> cardLibrary) {
		this.cardLibrary = cardLibrary;
	}
}
