package game;

import java.io.Serializable;

import player.Card;

// TODO: Auto-generated Javadoc
/**
 * The Class Unit.
 *
 * @author Harry Mitchell
 * @author Jeremiah Brusegaard
 */

public class Unit implements Serializable{

	/** The max move. */
	private int maxMove;
	
	/** The move left. */
	private int moveLeft;
	
	/** The row. */
	private int row;
	
	/** The col. */
	private int col;
	
	/** The c. */
	private Card c;
	
	/**
	 * Instantiates a new unit.
	 *
	 * @param c the c
	 * @param row the row
	 * @param col the col
	 */
	public Unit(Card c,int row,int col) {
		this.maxMove = c.getSpeed();
		this.moveLeft = 0;
		this.row = row;
		this.col = col;
		this.c = c;
	}
	
	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol() {
		return this.col;
	}
	
	/**
	 * Reset move.
	 */
	public void resetMove() {
		this.moveLeft = this.maxMove;
	}
	
	/**
	 * Make move.
	 *
	 * @param cost the cost
	 * @return the int
	 */
	public int makeMove(int cost) {
		this.moveLeft -= cost;
		return moveLeft;
	}
	
	/**
	 * Gets the remaining movement.
	 *
	 * @return the remaining movement
	 */
	public int getRemainingMovement() {
		return this.moveLeft;
	}
	
	/**
	 * Gets the card.
	 *
	 * @return the card
	 */
	public Card getCard() {
		return c;
	}
	
	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Sets the col.
	 *
	 * @param col the new col
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * Sets the card.
	 *
	 * @param c the new card
	 */
	public void setCard(Card c) {
		this.c = c;
	}
}
