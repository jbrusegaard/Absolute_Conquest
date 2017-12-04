package game;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.rmi.activation.UnknownObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.ClientNetwork;
import client.ui.PostGame;
import player.Card;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
/*
 * @author Jeremiah Brusegaard
 */
public class Game implements Serializable{
	
	/** The gameboard. */
	private Tile[][] gameboard;
	
	/** The cards. */
	private HashMap<String, Card> cards;
	
	/** The panel. */
	private final JPanel panel = new JPanel();
	
	/** The players. */
	private ArrayList<PlayerProfile> players;
	
	/** The turn count. */
	private int turnCount;
	
	/** The Player turn. */
	private int PlayerTurn;
	
	/** The Constant MAX_TURN_COUNT. */
	private static final int MAX_TURN_COUNT = 50;
	
	/** The game ID. */
	private String gameID;
	
	/** The mover. */
	private Move mover;
	
	/**
	 * Instantiates a new game.
	 *
	 * @param row the row
	 * @param col the col
	 * @param player1 the player 1
	 * @param player2 the player 2
	 * @param player3 the player 3
	 * @param player4 the player 4
	 * @param gameID the game ID
	 * @param cardMap the card map
	 */
	public Game(int row, int col, PlayerProfile player1, PlayerProfile player2, PlayerProfile player3,
			PlayerProfile player4, String gameID, HashMap<String, Card> cardMap) {
		gameboard = new Tile[row][col];
		this.players = new ArrayList<>();
		mover = new Move(this);
		cards = cardMap;
//		initializeGameBoard();
		this.gameID = gameID;
		this.players.add(player1);
		players.get(0).setLocation("North");
		this.players.add(player2);
		players.get(1).setLocation("East");
		this.players.add(player3);
		players.get(2).setLocation("South");
		this.players.add(player4);
		players.get(3).setLocation("West");
		initializeGameBoard();
		placeTowns();
		this.turnCount = 1;
		this.PlayerTurn = 1;
	}

	/**
	 * Gets the num ele.
	 *
	 * @return the num ele
	 */
	public int getNumEle()
	{
		int j=0;
		for(int i= 0; i< players.size(); i++)
		{
			if(players.get(i).getEliminate())
				j++;
		}
		return j;
	}
	
	/**
	 * Initialize game board.
	 */
	private void initializeGameBoard() {
		for(int row = 0; row < 12; row++) {
			for(int column = 0; column < 13; column++) {
				gameboard[row][column] = new Tile(row, column);
				
				if(row == 0) {
					if(column < 6 || column > 6) {
						gameboard[row][column].setTerr((Card) cards.get("InvalidTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
					else {
						gameboard[row][column].setTerr((Card) cards.get("DefaultTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
				}
				if(row == 1) {
					if(column < 4 || column > 8) {
						gameboard[row][column].setTerr((Card) cards.get("InvalidTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
					else {
						gameboard[row][column].setTerr((Card) cards.get("DefaultTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
				}
				if(row == 2) {
					if(column < 2 || column > 10) {
						gameboard[row][column].setTerr((Card) cards.get("InvalidTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
					else {
						gameboard[row][column].setTerr((Card) cards.get("DefaultTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
				}
				if(row == 9) {
					if(column < 1 || column > 11) {
						gameboard[row][column].setTerr((Card) cards.get("InvalidTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
					else {
						gameboard[row][column].setTerr((Card) cards.get("DefaultTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
				}
				if(row == 10) {
					if(column < 3 || column > 9) {
						gameboard[row][column].setTerr((Card) cards.get("InvalidTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
					else {
						gameboard[row][column].setTerr((Card) cards.get("DefaultTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
				}
				if(row == 11) {
					if(column < 5 || column > 7) {
						gameboard[row][column].setTerr((Card) cards.get("InvalidTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
					else {
						gameboard[row][column].setTerr((Card) cards.get("DefaultTerrain").clone());
						gameboard[row][column].setUnit(null);
						gameboard[row][column].setTrap(null);
					}
				}
				if(row > 2 && row < 9) {
					gameboard[row][column].setTerr((Card) cards.get("DefaultTerrain").clone());
					gameboard[row][column].setUnit(null);
					gameboard[row][column].setTrap(null);
				}

			}
		}
		
	}

	/**
	 * Place towns.
	 */
	public void placeTowns()
	{
		//Player 1
		gameboard[3][5].setTerr((Card) cards.get("Town").clone());
		gameboard[3][5].getTerrainCard().setOwnedby(players.get(0).getUserID());
		gameboard[3][7].setTerr((Card) cards.get("Town").clone());
		gameboard[3][7].getTerrainCard().setOwnedby(players.get(0).getUserID());
		//Player 2
		gameboard[5][9].setTerr((Card) cards.get("Town").clone());
		gameboard[5][9].getTerrainCard().setOwnedby(players.get(1).getUserID());
		gameboard[7][9].setTerr((Card) cards.get("Town").clone());
		gameboard[7][9].getTerrainCard().setOwnedby(players.get(1).getUserID());
		//Player 3
		gameboard[9][5].setTerr((Card) cards.get("Town").clone());
		gameboard[9][5].getTerrainCard().setOwnedby(players.get(2).getUserID());
		gameboard[9][7].setTerr((Card) cards.get("Town").clone());
		gameboard[9][7].getTerrainCard().setOwnedby(players.get(2).getUserID());
		//Player 4
		gameboard[5][3].setTerr((Card) cards.get("Town").clone());
		gameboard[5][3].getTerrainCard().setOwnedby(players.get(3).getUserID());
		gameboard[7][3].setTerr((Card) cards.get("Town").clone());
		gameboard[7][3].getTerrainCard().setOwnedby(players.get(3).getUserID());
	
	}
	
	/**
	 * Gets the gameboard.
	 *
	 * @return the gameboard
	 */
	public Tile[][] getGameboard() {
		return gameboard;
	}

	/**
	 * Inits the board terrain at pos.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void initBoardTerrainAtPos(int row, int col) {
		gameboard[row][col].setTerr(null);
		gameboard[row][col].setTerr((Card) cards.get("DefaultTerrain").clone());
	}

	/**
	 * Adds the card to tile.
	 *
	 * @param row the row
	 * @param col the col
	 * @param cardName the card name
	 * @param playerID the player ID
	 * @return the string
	 */
	public String addCardToTile(int row, int col, String cardName, String playerID) {
		if (gameboard[row][col].getTerrainCard().getName().equals("InvalidTerrain"))
			return "INVALID CARD PLACEMENT";
		Card card = cards.get(cardName);
		switch (card.getType()) {
		case "Land":
		case "Aquatic":
		case "Air":
			if (gameboard[row][col].getUnitCard() == null && (gameboard[row][col].getTerrainCard().getName().equals("Town")
							|| gameboard[row][col].getTerrainCard().getName().equals("Capital"))) {
				gameboard[row][col].setUnit((Card) card.clone());
				gameboard[row][col].getUnitCard().setOwnedby(playerID);
				gameboard[row][col].getUnitCard().move(gameboard[row][col].getUnitCard().getMoveLeft());
			} else {
				return "INVALID CARD PLACEMENT REMOVE CURRENT CARD";
			}
			break;
		case "Terrain":
			if (gameboard[row][col].getTerrainCard().getName().equals("DefaultTerrain")) {
				gameboard[row][col].setTerr(null);
				gameboard[row][col].setTerr((Card) card.clone());
				gameboard[row][col].getTerrainCard().setOwnedby(playerID);
			} else {
				return "INVALID CARD PLACEMENT REMOVE CURRENT CARD";
			}
			break;
		case "Land Trap":
		case "Mountain Trap":
		case "Aquatic Trap":
			if (gameboard[row][col].getTrapCard()== null && TerrainInfo.placeTrapVal(card, gameboard[row][col].getTerrainCard()) ) {
				gameboard[row][col].setTrap((Card) card.clone()); 	
				gameboard[row][col].getTrapCard().setOwnedby(playerID);
			} else {
				return "INVALID CARD PLACEMENT";
			}
			break;
		case "Town":
			if (gameboard[row][col].getTerrainCard().getName().equals("DefaultTerrain")) {
				gameboard[row][col].setTerr(null);
				gameboard[row][col].setTerr((Card) card.clone());
				gameboard[row][col].getTerrainCard().setOwnedby(playerID);
			}
			break;
		default:
			return "INVALID CARD TYPE";
		}
		return "Happy";

	}

	/**
	 * Removes the card from tile.
	 *
	 * @param row the row
	 * @param col the col
	 * @param cardName the card name
	 * @param playerID the player ID
	 * @return the card
	 * @throws UnknownObjectException the unknown object exception
	 */
	public Card removeCardFromTile(int row, int col, String cardName, String playerID) throws UnknownObjectException {
		if (gameboard[row][col].getTerrainCard().getName().equals("InvalidTerrain"))
			return null;
		Card card = cards.get(cardName);
		Card cardRemoved = null;
		switch (card.getType()) {
		case "Land":
		case "Aquatic":
		case "Air":
			if (gameboard[row][col].getUnitCard() != null) {
				cardRemoved = (Card) gameboard[row][col].getUnitCard().clone();
				gameboard[row][col].setUnit(null);
			} else {
				throw new UnknownObjectException("No card present");
			}
			break;
		case "Terrain":
			if (gameboard[row][col].getTerrainCard() != null) {
				cardRemoved = (Card) gameboard[row][col].getTerrainCard().clone();
				gameboard[row][col].setTerr(null);
				gameboard[row][col].setTerr((Card) cards.get("DefaultTerrain").clone());
			} else {
				throw new UnknownObjectException("No card present");
			}
			break;
		case "Land Trap":
		case "Mountain Trap":
			if (gameboard[row][col].getTrapCard() != null) {
				cardRemoved = (Card) gameboard[row][col].getTrapCard().clone();
				gameboard[row][col].setTrap(null);
			} else {
				throw new UnknownObjectException("No card present");
			}
			break;
		case "Town":
			throw new UnknownObjectException("Can't move towns!");
		default:
			throw new UnknownObjectException("Cardname invalid");
		}
		return cardRemoved;
	}

	/**
	 * NOTE: only will be called with units with one range and they are adjacent to
	 * the target they are attacking.
	 *
	 * @param destRow the dest row
	 * @param destCol the dest col
	 * @param curRow the cur row
	 * @param curCol the cur col
	 * @param playerID the player ID
	 * @return the string
	 */
	public String moveUnitAndAttack(int destRow, int destCol, int curRow, int curCol, String playerID) {
		Card card = gameboard[curRow][curCol].getUnitCard();
		String atkPlayer =getUserName(playerID);
		String defPlayer = "";
		String message = "";
		String valid = moveValidation(destRow, destCol,curRow,curCol, card, playerID);
		if(valid.equals("Happy")) {
			if (gameboard[destRow][destCol].getTrapCard() != null
					&& !gameboard[destRow][destCol].getTrapCard().getOwnedBy().equals(playerID)) {
				defPlayer = getUserName(gameboard[destRow][destCol].getTrapCard().getOwnedBy());
				message += ChatGameInfo.sendTrapMessage(atkPlayer,defPlayer , gameboard[curRow][curCol].getUnitCard(),
						gameboard[destRow][destCol].getTrapCard());
				doCombatWithTrap(destRow, destCol, curRow, curCol, card);

			} else if (gameboard[destRow][destCol].getUnitCard() != null) {
				defPlayer = getUserName(gameboard[destRow][destCol].getUnitCard().getOwnedBy());
				if(players.get(this.PlayerTurn-1).getUserID().equals(playerID))
				{
					message += ChatGameInfo.sendAtkMessage(atkPlayer, defPlayer, gameboard[curRow][curCol].getUnitCard(),
							gameboard[destRow][destCol].getUnitCard());
				}
				doCombatBetweenUnits(curRow, curCol, destRow, destCol);
				if(players.get(this.PlayerTurn-1).getUserID().equals(playerID) && gameboard[curRow][curCol].getUnitCard() != null)
				{
					message += ChatGameInfo.sendAtkMessage(defPlayer, atkPlayer, gameboard[destRow][destCol].getUnitCard(),
							gameboard[curRow][curCol].getUnitCard());
				}
				else if (gameboard[destRow][destCol].getUnitCard().getOwnedBy().equals(playerID)) {
					message +=ChatGameInfo.sendKillMessage(atkPlayer, defPlayer,gameboard[destRow][destCol].getUnitCard());
					addScore(1, atkPlayer);
				} else {
					//TODO
					if(gameboard[destRow][destCol].getUnitCard() != null && gameboard[curRow][curCol].getUnitCard() != null) {
						message += ChatGameInfo.sendAtkMessage(atkPlayer,defPlayer, gameboard[curRow][curCol].getUnitCard(),gameboard[destRow][destCol].getUnitCard());
						message += ChatGameInfo.sendAtkMessage(defPlayer,atkPlayer, gameboard[destRow][destCol].getUnitCard(),gameboard[curRow][curCol].getUnitCard());
					}
				}
			} else {
				gameboard[destRow][destCol].setUnit((Card) card.clone());
				gameboard[destRow][destCol].getUnitCard().setOwnedby(playerID);
				card = null;
				gameboard[curRow][curCol].setUnit(null);	
				handleTerrain(destRow, destCol, curRow, curCol, gameboard[destRow][destCol].getTerrainCard(),gameboard[destRow][destCol].getUnitCard());
			}
			//TODO
//			if(gameboard[destRow][destCol].getTerrainCard().getName().equals("Town") && 
//					(gameboard[destRow][destCol].getTerrainCard().getOwnedBy() == null ||!gameboard[destRow][destCol].getTerrainCard().getOwnedBy().equals(playerID)) )
//			{
//				for(int i = 0; i< players.size();i++)
//				{
//					if(players.get(i).getUsername().equals(atkPlayer))
//					{
//						players.get(i).updatePoints(1);
//						break;
//					}
//				}
//				gameboard[destRow][destCol].getTerrainCard().setOwnedby(playerID);
//			}
			if(gameboard[destRow][destCol].getTerrainCard().getName().equals("Capital") && !gameboard[destRow][destCol].getTerrainCard().getOwnedBy().equals(playerID) &&
					gameboard[destRow][destCol].getUnitCard() != null && gameboard[destRow][destCol].getUnitCard().getOwnedBy().equals(playerID))
			{
				addScore(3, atkPlayer);
				String ownedBy = gameboard[destRow][destCol].getTerrainCard().getOwnedBy();
				defPlayer = getUserName(gameboard[destRow][destCol].getTerrainCard().getOwnedBy());
				message += ChatGameInfo.sendEleMessage(atkPlayer, defPlayer,gameboard[destRow][destCol].getUnitCard());
				elimPlayer(ownedBy);
				
				gameboard[destRow][destCol].setTerr(null);
				gameboard[destRow][destCol].setTerr((Card) cards.get("DefaultTerrain").clone());
				gameboard[destRow][destCol].getTerrainCard().setOwnedby("");
				return "CAP"+ownedBy+message;
			}
			return "Happy"+ message;
		}else if(gameboard[curRow][curCol].getUnitCard() != null && getDistance(destRow, destCol, curRow, curCol) <= gameboard[curRow][curCol].getUnitCard().getRange()
				&& getDistance(destRow, destCol, curRow, curCol) > 1) {
			String validRg = rangedAttackValidation(destRow, destCol, curRow, curCol, card, playerID);
			if(validRg.equals("Happy") && gameboard[destRow][destCol].getUnitCard() != null) {
				String msg = doRangedAttackBetweenUnits(curRow, curCol, destRow, destCol);
				if(!msg.equals("FAIL")) message += msg;
			}
			return validRg + message;
		}
		return valid;
	}
	private void addScore(int score,String atkPlayer) {
		for(int i = 0; i< players.size();i++)
		{
			if(players.get(i).getUsername().equals(atkPlayer))
			{
				players.get(i).updatePoints(score);
				break;
			}
		}
	}
	/**
	 * Handle terrain.
	 *
	 * @param destRow the dest row
	 * @param destCol the dest col
	 * @param curRow the cur row
	 * @param curCol the cur col
	 * @param terrain the terrain
	 * @param unit the unit
	 */
	private void handleTerrain(int destRow, int destCol, int curRow, int curCol,Card terrain,Card unit) {
		boolean alive = TerrainInfo.getTerrainInfo(unit, terrain);
		if(!alive) {
			gameboard[destRow][destCol].setUnit(null);
		}
	}
	
	/**
	 * Gets the user name.
	 *
	 * @param playerID the player ID
	 * @return the user name
	 */
	private String getUserName(String playerID) {
		for(PlayerProfile p: players) {
			if(playerID.equals(p.getUserID())) {
				return p.getUsername();
			}
		}
		return "";
	}
	
	/**
	 * Ranged attack validation.
	 *
	 * @param destRow the dest row
	 * @param destCol the dest col
	 * @param curRow the cur row
	 * @param curCol the cur col
	 * @param card the card
	 * @param playerID the player ID
	 * @return the string
	 */
	private String rangedAttackValidation(int destRow, int destCol,int curRow,int curCol, Card card, String playerID) {
		if(!gameboard[curRow][curCol].getUnitCard().getOwnedBy().equals( playerID)) {
			return "CAN'T MOVE UNITS YOU DON'T OWN!";
		}
		if (card == null)
			return "Invalid card being moved";
		if(gameboard[destRow][destCol].getUnitCard() != null) {
			if(card.getOwnedBy().equals(gameboard[destRow][destCol].getUnitCard().getOwnedBy())) {
				return "Can't move onto your own unit";
			}
		}
		if(!card.canMove()) {
			return "No movement left";
		}
		return "Happy";
	}
	
	/**
	 * Do combat with trap.
	 *
	 * @param destRow the dest row
	 * @param destCol the dest col
	 * @param curRow the cur row
	 * @param curCol the cur col
	 * @param card the card
	 */
	private void doCombatWithTrap(int destRow, int destCol, int curRow, int curCol,Card card) {
		Card trap = gameboard[destRow][destCol].getTrapCard();
		int cardHealth = card.getHealth();
		if (cardHealth <= trap.getAttack()) {
			card.setHealth(0);
			gameboard[curRow][curCol].setUnit(null);
		} else {
			card.setHealth(card.getHealth() - trap.getAttack());
			if (gameboard[destRow][destCol].getUnitCard() != null) {
				doCombatBetweenUnits(curRow, curCol, destRow, destCol);
			}else {
				gameboard[destRow][destCol].setUnit((Card) card.clone());
				card = null;
				gameboard[curRow][curCol].setUnit(null);
				handleTerrain(destRow, destCol, curRow, curCol, gameboard[destRow][destCol].getTerrainCard(),gameboard[destRow][destCol].getUnitCard());
			}
		}
		gameboard[destRow][destCol].setTrap(null);
		
	}
	
	/**
	 * Move validation.
	 *
	 * @param destRow the dest row
	 * @param destCol the dest col
	 * @param curRow the cur row
	 * @param curCol the cur col
	 * @param card the card
	 * @param playerID the player ID
	 * @return the string
	 */
	private String moveValidation(int destRow, int destCol,int curRow,int curCol, Card card, String playerID) {
		if(gameboard[curRow][curCol].getUnitCard()== null) {
			return "Invalid Unit";
		}
		if (gameboard[destRow][destCol].getTerrainCard().getName().equals("InvalidTerrain")) {

			JOptionPane.showMessageDialog(panel, "Trying to move to invalid terrain!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return "INVALID CARD MOVEMENT";
		}
		if(!gameboard[curRow][curCol].getUnitCard().canMove())
		{
			return "NO MOVEMENT LEFT";
		}
		if(!gameboard[curRow][curCol].getUnitCard().getOwnedBy().equals( playerID)) {
			return "CAN'T MOVE UNITS YOU DON'T OWN!";
		}
		if (card == null)
			return "Invalid card being moved";
//		if (!card.getType().equals("Land") || !card.getType().equals("Air") || !card.getType().equals("Aquatic")) {
//			return "Invalid card type " + card.getType();
//		}
//		if (card.getRange() > 1) {
//			return "Can only be used for units with one range";
//		}
		if(getDistance(curRow, curCol, destRow, destCol) != 1) {
			return "May only move one space at a time";
		}
		if(gameboard[destRow][destCol].getUnitCard() != null) {
			if(card.getOwnedBy().equals(gameboard[destRow][destCol].getUnitCard().getOwnedBy())) {
				return "Can't move onto your own unit";
			}
		}
		return "Happy";
	}

	/**
	 * Do combat between units.
	 *
	 * @param atkRow the atk row
	 * @param atkCol the atk col
	 * @param defRow the def row
	 * @param defCol the def col
	 */
	public void doCombatBetweenUnits(int atkRow, int atkCol, int defRow, int defCol) {
		if (gameboard[defRow][defCol].getTerrainCard().getName().equals("InvalidTerrain")) {
			JOptionPane.showMessageDialog(panel, "That is an invalid movement!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (getDistance(atkRow, atkCol, defRow, defCol) != 1) {
			JOptionPane.showMessageDialog(panel, "You must select an enemy unit that is 1 tile away!", "Error",
					JOptionPane.ERROR_MESSAGE);

			return;
		}
		Card atk = gameboard[atkRow][atkCol].getUnitCard();
		Card def = gameboard[defRow][defCol].getUnitCard();
		atk.move(1);
		if (atk.getAttack() >= def.getHealth()) {
			def.setHealth(0);
			def = null;
			gameboard[defRow][defCol].setUnit(null);
			gameboard[defRow][defCol].setUnit((Card) atk.clone());	
			atk = null;
			gameboard[atkRow][atkCol].setUnit(null);
			handleTerrain(defRow, defCol, atkRow, atkCol,gameboard[defRow][defCol].getTerrainCard(),gameboard[defRow][defCol].getUnitCard());
		} else {
			def.setHealth(def.getHealth() - atk.getAttack());
			if (def.getAttack() >= atk.getHealth()) {
				atk.setHealth(0);
				atk = null;
				gameboard[atkRow][atkCol].setUnit(null);
			} else {
				atk.setHealth(atk.getHealth() - def.getAttack());
			}
		}
	}

	/**
	 * Do ranged attack between units.
	 *
	 * @param atkRow the atk row
	 * @param atkCol the atk col
	 * @param defRow the def row
	 * @param defCol the def col
	 * @return the string
	 */
	public String doRangedAttackBetweenUnits(int atkRow, int atkCol, int defRow, int defCol) {
		if (gameboard[defRow][defCol].getTerrainCard().getName().equals("InvalidTerrain")) {
			JOptionPane.showMessageDialog(panel, "Invalid Terrain", "Error",
					JOptionPane.ERROR_MESSAGE);
			return "FAIL";
		}
		if (getDistance(atkRow, atkCol, defRow, defCol) > gameboard[atkRow][atkCol].getUnitCard().getRange()) {
			JOptionPane.showMessageDialog(panel, "Invalid Unit,  distance farther than range of attacker", "Error",
					JOptionPane.ERROR_MESSAGE);
			return "FAIL";
		}
		String message = "";
		Card atk = gameboard[atkRow][atkCol].getUnitCard();
		Card def = gameboard[defRow][defCol].getUnitCard();
		atk.move(1);
		if (atk.getAttack() >= def.getHealth()) {
			message += ChatGameInfo.sendKillMessage(getUserName(atk.getOwnedBy()), getUserName(def.getOwnedBy()), atk);
			def.setHealth(0);
			def = null;
			gameboard[defRow][defCol].setUnit(null);
			
		} else {
			def.setHealth(def.getHealth() - atk.getAttack());
			message += ChatGameInfo.sendAtkMessage(getUserName(atk.getOwnedBy()), getUserName(def.getOwnedBy()), atk, def);
			if (getDistance(atkRow, atkCol, defRow, defCol) <= def.getRange()) {
				if (def.getAttack() >= atk.getHealth()) {
					message += ChatGameInfo.sendKillMessage(getUserName(def.getOwnedBy()), getUserName(atk.getOwnedBy()), def);
					atk.setHealth(0);
					atk = null;
					gameboard[atkRow][atkCol].setUnit(null);
				} else {
					message += ChatGameInfo.sendAtkMessage(getUserName(def.getOwnedBy()), getUserName(atk.getOwnedBy()), def, atk);
					atk.setHealth(atk.getHealth() - def.getAttack());
				}
			}
		}
		return message;
	}
	
	/**
	 * Reset all units.
	 */
	public void resetAllUnits() {
		for(int i = 0;i < gameboard.length ;i++) {
			for(int j = 0; j < gameboard[0].length;j++) {
				Card unit = gameboard[i][j].getUnitCard();
				if(unit != null) {
					unit.resetSpeed();
					for(int k = 0; k< players.size();k++) {
						if(players.get(k).getEliminate()) {
							unit = null;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Elim player.
	 *
	 * @param playerID the player ID
	 */
	public void elimPlayer(String playerID)
	{
		for(int i =0; i < gameboard.length; i++)
		{
			for(int j = 0; j < gameboard[0].length; j++)
			{
				Card unit = gameboard[i][j].getUnitCard();
				if(unit != null)
					if(unit.getOwnedBy().equals(playerID))
						gameboard[i][j].setUnit(null);
			}
		}
	}
//	private Unit getUnit(int row,int col) {
//		Unit unit = null;
//		for(int i = 0; i < this.units.size();i++)
//		{
//			if(this.units.get(i).getRow() == row && this.units.get(i).getCol() == col) {
//				return this.units.get(i);
//			}
//		}
//		return unit;
/**
 * Gets the distance.
 *
 * @param row1 the row 1
 * @param col1 the col 1
 * @param row2 the row 2
 * @param col2 the col 2
 * @return the distance
 */
//	}
	public static int getDistance(int row1, int col1, int row2, int col2) {
		col1 ++;
		col2++;
		int x0 = (int) (row1 - Math.floor(col1 / 2));
		int y0 = col1;
		int x1 = (int) (row2 - Math.floor(col2 / 2));
		int y1 = col2;
		int dx = x1 - x0;
		int dy = y1 - y0;
		int max = Math.max(Math.abs(dx), Math.abs(dy));
		int dist = Math.max(max, Math.abs(dx + dy));
		return dist;
	}
/*	public void endTurnLocal() {
		ClientNetwork.endTurn(players.get(PlayerTurn).getUserID(), this.gameID);
		this.PlayerTurn++;
		if (this.PlayerTurn > 4) {
			this.PlayerTurn = 1;
			this.turnCount++;
			if (turnCount >= MAX_TURN_COUNT) {
				endGame();
			}
		}

	}*/

	/**
 * End turn server.
 */
public void endTurnServer() {
		this.PlayerTurn++;
		if (this.PlayerTurn > 4) {
			this.PlayerTurn = 1;
			this.turnCount++;
			if (turnCount >= MAX_TURN_COUNT) {
				endGame();
			}
		}
	}

	/**
	 * End game.
	 */
	public void endGame() {
		String[] args = { "But why though" };
		panel.removeAll();
		PostGame.main(args, this.players);
	}

	/**
	 * Gets the player by id.
	 *
	 * @param playerID the player ID
	 * @return the player by id
	 */
	public PlayerProfile getPlayerById(String playerID) {
		for (PlayerProfile p : this.players) {
			if (p.getUserID().equals(playerID))
				return p;
		}
		return null;
	}

	/**
	 * Sets the tile.
	 *
	 * @param row the row
	 * @param col the col
	 * @param tile the tile
	 */
	public void setTile(int row, int col, Tile tile) {
		this.gameboard[row][col] = tile;
	}

	/**
	 * Gets the game ID.
	 *
	 * @return the game ID
	 */
	public String getGameID() {
		return this.gameID;
	}
	
	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public ArrayList<PlayerProfile> getPlayers()
	{
		return players;
	}
	
	/**
	 * Gets the cards.
	 *
	 * @return the cards
	 */
	public HashMap<String, Card> getCards() {
		return cards;
	}

	/**
	 * Gets the mover.
	 *
	 * @return the mover
	 */
	public Move getMover() {
		return mover;
	}
	
	/**
	 * Eliminate.
	 *
	 * @param userID the user ID
	 */
	public void eliminate(String userID) {
		for(PlayerProfile p : players) {
			if(p.getUserID().equals(userID)) {
				p.setEliminate(true);
			}
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {

		System.out.println(getDistance(11, 5, 2, 9));
	}


}
