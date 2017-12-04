package game.server;

import java.util.ArrayList;
import java.util.Collections;

import player.Card;
import player.Deck;
// TODO: Auto-generated Javadoc

/**
 * The Class ServerHand.
 *
 * @author Jeremiah Brusegaard
 */
public class ServerHand {
	
	/** The deck. */
	private Deck deck;
	
	/** The cards in hand. */
	private ArrayList<Card> cardsInHand =new ArrayList<Card>();
	
	/** The hand size. */
	private final int HAND_SIZE = 7;
	
	/**
	 * Instantiates a new server hand.
	 *
	 * @param deck the deck
	 */
	public ServerHand(Deck deck) {
		this.deck = (Deck) deck.clone();
	}
	
	/**
	 * Gets the hand.
	 *
	 * @return the hand
	 */
	public ArrayList<Card> getHand()
	{
		return this.cardsInHand;
	}
	
}
