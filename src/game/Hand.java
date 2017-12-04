package game;

import java.util.ArrayList;
import java.util.Collections;


import client.ui.GameUI;
import player.Card;
import player.Deck;
// TODO: Auto-generated Javadoc

/**
 * The Class Hand.
 *
 * @author Jeremiah Brusegaard
 */
public class Hand {
	
	/** The deck. */
	private Deck deck;
	
	/** The cards in hand. */
	private ArrayList<Card> cardsInHand;
	
	/** The hand size. */
	private final int HAND_SIZE = 7;
	
	/** The max hand size. */
	private final int MAX_HAND_SIZE = 15;
	
	/** The owner. */
	private String owner;
	
	/** The in game. */
	private GameUI inGame;
	
	/**
	 * Instantiates a new hand.
	 *
	 * @param deck the deck
	 * @param inGame the in game
	 */
	public Hand(Deck deck,GameUI inGame) {
		this.deck = (Deck) deck.clone();
		this.cardsInHand =new ArrayList<Card>();
		owner = this.deck.getUserID();
		this.inGame = inGame;
		sortOutTerrain(this.deck);
		shuffleDeck();
	}
	
	/**
	 * Sort out terrain.
	 *
	 * @param deck the deck
	 */
	public void sortOutTerrain(Deck deck){
		ArrayList<Card> deckCards = this.deck.getCards();
		for(int i = 0;i<deckCards.size() || cardsInHand.size() < 15;i++) {
			if(deckCards.get(i).getType().equals("Terrain")) {
				this.cardsInHand.add((Card) deckCards.get(i).clone());
				deck.removeCard(deckCards.get(i));
				i--;
			}
		}
	}
	
	/**
	 * Shuffle deck.
	 */
	public void shuffleDeck() {
		Collections.shuffle(deck.getCards());
	}
	
	/**
	 * Return card to hand.
	 *
	 * @param card the card
	 */
	public void returnCardToHand(Card card) {
		cardsInHand.add(card);
		this.inGame.DisplayHand();
	}
	
	/**
	 * Play card from hand.
	 *
	 * @param cardNo the card no
	 * @return the card
	 */
	public Card playCardFromHand(int cardNo) {
		if(cardNo < 0 || cardNo > this.cardsInHand.size()) {
			return null;
		}
		Card card = (Card) this.cardsInHand.get(cardNo).clone();
		this.cardsInHand.get(cardNo).setOwnedby(this.owner);
		this.cardsInHand.remove(cardNo);
		this.inGame.DisplayHand();
		return card;
	}
	
	/**
	 * Inits the hand.
	 */
	public void initHand() {
		this.cardsInHand = createHand();
	}
	
	/**
	 * Creates the hand.
	 *
	 * @return the array list
	 */
	private ArrayList<Card> createHand(){
		int size = this.deck.getCards().size();
		if(size >= HAND_SIZE) {
			//System.out.println(deck.getCards().size());
			for(int i = 0;i < HAND_SIZE;i++) {
				//System.out.println(i);
				Card c = deck.getCards().get(0);
				c.setOwnedby(this.owner);
				cardsInHand.add(c);
				this.deck.getCards().remove(0);
			}
		}else {
			for(int i = 0;i < size;i++) {
				cardsInHand.add(this.deck.getCards().get(0));
				this.deck.getCards().remove(0);
			}
		}
		return this.cardsInHand;
	}
	
	/**
	 * Draw card.
	 */
	public void drawCard()
	{
		if(!this.deck.getCards().isEmpty() && this.cardsInHand.size() < MAX_HAND_SIZE) {
			Card card = deck.getCards().get(0);
			card.setOwnedby(this.owner);
			cardsInHand.add(card);
			deck.getCards().remove(0);
		}
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
	
	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public String getOwner() {
		return this.owner;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		Deck deck = new Deck("test", "234gbsef32df", "fueSHAIz6bj0hmnw");
		for(int i = 0;i<50;i++) {
			String type = "test";
			if(i<15) type = "Terrain";
			deck.addCard(new Card("test", "test", "test", "test", "test", "test", "test", "test", "test", type, 1, 1, 1, 1, 1, "test"));
		}
		Deck deckclone = (Deck) deck.clone();
		System.out.println(deck.getDeckID() + " " + deckclone.getDeckID());
		System.out.println(deck.getDeckName() + " " + deckclone.getDeckName());
		System.out.println(deck.getUserID() + " " + deckclone.getUserID());
		Hand hand = new Hand(deck,null);
		ArrayList<Card> cards = hand.getHand();
		for(Card card: cards) {
			System.out.println(card.getType());
		}
	}
}
