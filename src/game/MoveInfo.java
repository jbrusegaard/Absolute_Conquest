package game;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class MoveInfo.
 *
 * @author Jeremiah Brusegaard
 */

public class MoveInfo implements Serializable{

	/** The row 1. */
	private int row1;
	
	/** The row 2. */
	private int row2;
	
	/** The col 1. */
	private int col1;
	
	/** The col 2. */
	private int col2;
	
	/** The player id mover. */
	private String playerIdMover;
	
	/** The card name moving. */
	private String cardNameMoving;
	
	/** The move type. */
	private String moveType;
	
	/** The game ID. */
	private String gameID;
	
	/**
	 * Instantiates a new move info.
	 *
	 * @param playerID the player ID
	 * @param cardName the card name
	 * @param atkRow the atk row
	 * @param atkCol the atk col
	 * @param defRow the def row
	 * @param defCol the def col
	 * @param type the type
	 * @param gi the gi
	 */
	public MoveInfo(String playerID,String cardName,int atkRow,int atkCol,int defRow,int defCol, String type, String gi) {
		this.row1 = atkRow;
		this.row2 = defRow;
		this.col1 = atkCol;
		this.col2 = defCol;
		System.out.println("Row " + atkRow + ", Col " + atkCol);
		this.playerIdMover = playerID;
		this.cardNameMoving = cardName;
		this.moveType = type;
		this.gameID = gi;
	}
	
	/**
	 * Gets the atk row.
	 *
	 * @return the atk row
	 */
	public int getAtkRow() {
		return this.row1;
	}
	
	/**
	 * Gets the def row.
	 *
	 * @return the def row
	 */
	public int getDefRow() {
		return this.row2;
	}
	
	/**
	 * Gets the atk col.
	 *
	 * @return the atk col
	 */
	public int getAtkCol() {
		return this.col1;
	}
	
	/**
	 * Gets the def col.
	 *
	 * @return the def col
	 */
	public int getDefCol() {
		return this.col2;
	}
	
	/**
	 * Gets the player ID.
	 *
	 * @return the player ID
	 */
	public String getPlayerID() {
		return this.playerIdMover;
	}
	
	/**
	 * Gets the card name.
	 *
	 * @return the card name
	 */
	public String getCardName() {
		return this.cardNameMoving;
	}
	
	/**
	 * Gets the move type.
	 *
	 * @return the move type
	 */
	public String getMoveType() {
		return this.moveType;
	}
	
	/**
	 * Gets the game ID.
	 *
	 * @return the game ID
	 */
	public String getGameID() {
		return this.gameID;
	}
}
