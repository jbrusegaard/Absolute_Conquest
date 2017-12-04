package game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import player.Card;


// TODO: Auto-generated Javadoc
/**
 * The Class Tile.
 */
public class Tile implements Serializable{
	
	/** The column. */
	//holds the cordinates of the tile on the screen
	private int row, column;
	
	/** The location. */
	//whether the card is in the North, South, East, or West quadrant
	String location;
	
	/** The cards. */
	//hold the card that the tile contains
	private Card[] cards;
	
	/**
	 * The Enum cardType.
	 */
	private enum cardType {
	    
    	/** The terrain. */
    	TERRAIN,
	    
    	/** The trap. */
    	TRAP,
	    
    	/** The unit. */
    	UNIT;
	}
	
	/**
	 * Instantiates a new tile.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Tile(int x, int y)
	{
		row = x;
		column = y;
		location = findLocationFromCoords(x,y);
		this.cards = new Card[3];
		cards[0] = null;
		cards[1] = null;
		cards[2] = null;
	}
	 
	/**
	 * Find location from coords.
	 *
	 * @param row the row
	 * @param col the col
	 * @return the string
	 */
	private String findLocationFromCoords(int row, int col) {
		//North zone
		if(row == 0 || row == 1 || row == 2)
			return "North";
		if(row == 3 && col >= 3 && col <= 9)
			return "North";
		if((row == 4 || row == 5) && col >= 5 && col <= 7)
			return "North";
		
		//South zone
		if(row == 10 || row == 11)
			return "South";
		if(row == 9 && col >= 2 && col <= 10)
			return "South";
		if(row == 8 && col >= 4 && col <= 8)
			return "South";
		if(row == 7 && col >= 5 && col <= 7)
			return "South";
		if(row == 6 && col == 6)
			return "South";
		
		//West zone
		if(col == 0 || col == 1)
			return "West";
		if(col == 2 && row >= 3 && row <= 8)
			return "West";
		if(col == 3 && row >= 4 && row <= 8)
			return "West";
		if(col == 4 && row >= 4 && row <= 7)
			return "West";
		if(col == 5 && row == 6)
			return "West";
		
		//East zone
		if(col == 11 || col == 12)
			return "East";
		if(col == 10 && row >= 3 && row <= 8)
			return "East";
		if(col == 9 && row >= 4 && row <= 8)
			return "East";
		if(col == 8 && row >= 4 && row <= 7)
			return "East";
		if(col == 7 && row == 6)
			return "East";
		
		
		return null;
	}

	/**
	 * Sets the unit.
	 *
	 * @param card the new unit
	 */
	//sets the unit card
	public void setUnit(Card card)
	{
		cards[cardType.UNIT.ordinal()] = card;
	}
	
	/**
	 * Sets the terr.
	 *
	 * @param card the new terr
	 */
	//sets the terrain card
	public void setTerr(Card card)
	{
		cards[cardType.TERRAIN.ordinal()] = card;
	}
	
	/**
	 * Sets the trap.
	 *
	 * @param card the new trap
	 */
	//set the trapcard
	public void setTrap(Card card)
	{
		cards[cardType.TRAP.ordinal()] = card;
	}	
	
	/**
	 * Gets the trap card.
	 *
	 * @return the trap card
	 */
	//returns the trap card
	public Card getTrapCard() {
		return cards[cardType.TRAP.ordinal()];
	}
	
	/**
	 * Gets the unit card.
	 *
	 * @return the unit card
	 */
	//returns the unit card
	public Card getUnitCard() {
		return cards[cardType.UNIT.ordinal()];
	}
	
	/**
	 * Gets the terrain card.
	 *
	 * @return the terrain card
	 */
	//returns the terrain card
	public Card getTerrainCard() {
		return cards[cardType.TERRAIN.ordinal()];
	}
	
	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Gets the column.
	 *
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
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
}
	

