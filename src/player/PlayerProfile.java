package player;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import client.ClientNetwork;
import game.Hand;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerProfile.
 */
public class PlayerProfile implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1;
	
	/** The user ID. */
	String userID = "";
	
	/** The username. */
	String username = "";
	
	/** The currency. */
	int currency;
	
	/** The client connection. */
	private transient Socket clientConnection; //Socket connection to client, will have a value of null client side.
	
	/** The decks. */
	ArrayList<Deck> decks = new ArrayList<Deck>();
	
	/** The card collection. */
	ArrayList<Card> cardCollection = new ArrayList<Card>();
	
	/** The points. */
	private transient int points =0;
	
	/** The Location. */
	private String Location;//This will always be North, East, South, West
	
	/** The hand. */
	private transient Hand hand;
	
	/** The account type. */
	private transient int accountType;
	
	/** The eliminated. */
	private boolean eliminated = false;
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlayerProfile [userID=" + userID + ", username=" + username + ", currency=" + currency
				+ ", clientConnection=" + clientConnection + ", decks=" + decks + ", cardCollection=" + cardCollection
				+ "]";
	}

	/**
	 * Instantiates a new player profile.
	 */
	public PlayerProfile(){
	}
	
	/**
	 * Gets the client connection.
	 *
	 * @return the client connection
	 */
	public Socket getClientConnection() {
		return clientConnection;
	}

	/**
	 * Sets the client connection.
	 *
	 * @param clientConnection the new client connection
	 */
	public void setClientConnection(Socket clientConnection) {
		this.clientConnection = clientConnection;
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
	 * @param userID the new user ID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the decks.
	 *
	 * @return the decks
	 */
	public ArrayList<Deck> getDecks() {
		return decks;
	}

	/**
	 * Sets the decks.
	 *
	 * @param decks the new decks
	 */
	public void setDecks(ArrayList<Deck> decks) {
		this.decks = decks;
	}

	/**
	 * Gets the card collection.
	 *
	 * @return the card collection
	 */
	public ArrayList<Card> getCardCollection() {
		return cardCollection;
	}

	/**
	 * Sets the card collection.
	 *
	 * @param cardCollection the new card collection
	 */
	public void setCardCollection(ArrayList<Card> cardCollection) {
		this.cardCollection = cardCollection;
	}
	
	/**
	 * Update points.
	 *
	 * @param change the change
	 */
	public void updatePoints(int change) {
		points += change;
	}
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Sets the points.
	 *
	 * @param st the new points
	 */
	public void setPoints(int st) {
		points = st;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param L the new location
	 */
	public void setLocation(String L) {
		Location = L;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return this.Location;
	}
	
	/**
	 * Gets the hand.
	 *
	 * @return the hand
	 */
	public Hand getHand() {
		return this.hand;
	}
	
	/**
	 * Sets the hand.
	 *
	 * @param h the new hand
	 */
	public void setHand(Hand h) {
		this.hand = h;
	}

	/**
	 * Sets the account type.
	 *
	 * @param i the new account type
	 */
	public void setAccountType(int i) {
		this.accountType = i;
	}
	
	/**
	 * Gets the account type.
	 *
	 * @return the account type
	 */
	public int getAccountType() {
		return this.accountType;
	}
	
	/**
	 * Sets the eliminate.
	 *
	 * @param status the new eliminate
	 */
	public void setEliminate(boolean status) {
		eliminated = status;
	}
	
	/**
	 * Gets the eliminate.
	 *
	 * @return the eliminate
	 */
	public boolean getEliminate() {
		return eliminated;
	}
}
