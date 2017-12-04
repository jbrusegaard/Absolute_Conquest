package player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class Deck.
 */
public class Deck implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1;
	
	/** The deck ID. */
	String deckID;
	
	/** The deck name. */
	String deckName;
	
	/** The user ID. */
	String userID;
	
	/** The cards. */
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	private int numTerrian = 0;
	
	/**
	 * Gets the deck ID.
	 *
	 * @return the deck ID
	 */
	public String getDeckID() {
		return deckID;
	}

	/**
	 * Sets the deck ID.
	 *
	 * @param deckID the new deck ID
	 */
	public void setDeckID(String deckID) {
		this.deckID = deckID;
	}

	/**
	 * Gets the deck name.
	 *
	 * @return the deck name
	 */
	public String getDeckName() {
		return deckName;
	}

	/**
	 * Sets the deck name.
	 *
	 * @param deckName the new deck name
	 */
	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}
	
	/**
	 * Gets the user ID.
	 *
	 * @return the user ID
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * Sets the user ID.
	 *
	 * @param uId the new user ID
	 */
	public void setUserID(String uId) {
		this.userID = uId;
	}
	
	/**
	 * Instantiates a new deck.
	 */
	public Deck() {
		
	}
	
	/**
	 * Instantiates a new deck.
	 *
	 * @param name the name
	 * @param DID the did
	 * @param UID the uid
	 */
	public Deck(String name, String DID, String UID) {
		deckName = name;
		deckID = DID;
		userID = UID;
	}

	/**
	 * Gets the cards.
	 *
	 * @return the cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}

	/**
	 * Sets the cards.
	 *
	 * @param cards the new cards
	 */
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	/**
	 * Adds the card.
	 *
	 * @param c the c
	 */
	public void addCard(Card c) {
		cards.add(c);
	}
	
	/**
	 * Removes the card.
	 *
	 * @param c the c
	 */
	public void removeCard(Card c) {
		cards.remove(c);
	}
	
	/**
	 * Serialize.
	 */
	public void serialize() {
		String deckSerial = "";
		deckSerial += deckID + "\0" + deckName + "\0";
		for(int i = 0; i < cards.size(); i++) {
			deckSerial +=  "\0" + cards.get(i).serialize() + "\0";
		}
	}
	
	/**
	 * Deserialize.
	 *
	 * @param s the s
	 * @return the deck
	 */
	public Deck deserialize(String s) {
		ArrayList<String> deckTokens = new ArrayList<String>(Arrays.asList(s.split("\0\0")));
		String deckInfo = deckTokens.get(0);
		ArrayList<String> deckInfoTokens = new ArrayList<String>(Arrays.asList(deckInfo.split("\0")));
		Deck d = new Deck();
		d.setDeckID(deckInfoTokens.get(0));
		d.setDeckID(deckInfoTokens.get(1));
		
		for(int i = 1; i < deckTokens.size(); i++) {
			cards.add(Card.deserialize(deckTokens.get(i)));
		}
		
		return d;
	}
	
	/**
	 * Clone.
	 *
	 * @author Jeremiah Brusegaard
	 * Clones object
	 * @return the object
	 */
		public Object clone() {
			Object clonedObj = null;
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			try {
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
				objectOutputStream.writeObject(this);
				objectOutputStream.flush();
				objectOutputStream.close();
				ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
				clonedObj = ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			return clonedObj;
		}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Deck [deckID=" + deckID + ", deckName=" + deckName + ", cards=" + cards + "]";
	}

	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid() {
		return (50 == cards.size()  && numTerrian == 15);
	}
	
	public void increaseTerrianAmount() {
		numTerrian++;
	}
	
	public void decreaseTerrianAmount() {
		numTerrian--;
		if(numTerrian<0) {numTerrian = 0;}
	}
	
	public int getNumTerrian() {
		return numTerrian;
	}

}
