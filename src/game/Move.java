package game;

import java.io.Serializable;
import java.rmi.activation.UnknownObjectException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import client.ClientNetwork;
import player.Card;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class Move.
 *
 * @author Harry Mitchell
 * @author Jacob Aspinall
 * @author Jeremiah Brusegaard
 */

public class Move implements Serializable{

	/** The game. */
	private Game theGame;

	/**
	 * Instantiates a new move.
	 *
	 * @param g the g
	 */
	public Move(Game g) {
		theGame = g;
	}

	/**
	 * Move unit.
	 *
	 * rowc and colc are the row and column the unit is currently at rowm and colm
	 * are the row and column the unit is attempting to move too
	 *
	 * @param rowc the rowc
	 * @param colc the colc
	 * @param rowm the rowm
	 * @param colm the colm
	 * @param c the c
	 * @param pl the pl
	 * @return the string
	 */
	
	public String moveUnit(int rowc, int colc, int rowm, int colm, Card c, PlayerProfile pl) {
		String result = theGame.moveUnitAndAttack(rowm, colm, rowc, colc, pl.getUserID());
		if (result.startsWith("Happy")||result.startsWith("CAP")) {
//			MoveInfo mi = new MoveInfo(pl.getUserID(), c.getName(), rowm, colm, rowc, rowc, "move", theGame.getGameID());
//			ClientNetwork.makeMove(pl.getUserID(), theGame.getGameID(), mi);
			if(result.substring(0, 3).equals("CAP"))
			{
				return result;
			}
			
			return "PASS"+ result.substring(5);
		} else {
//			JOptionPane.showMessageDialog(null,
//					"You can not move your selected unit onto that tile\nfor the following reason: " + result);
			return result;
		}
	}

	/**
	 * Move terrain card.
	 *
	 * @param rowc the rowc
	 * @param colc the colc
	 * @param rowm the rowm
	 * @param colm the colm
	 * @param pl the pl
	 * @return the string
	 */
	public String moveTerrainCard(int rowc, int colc, int rowm, int colm, PlayerProfile pl) {
		Tile[][] t = theGame.getGameboard();
		try {
			if (inRange(rowc, colc, pl) && inRange(rowm, colm, pl) && t[rowm][colm].getTerrainCard().getName().equals("DefaultTerrain")) {
				Card c = theGame.removeCardFromTile(rowc, colc, t[rowc][colc].getTerrainCard().getName(), pl.getUserID());
				if (!c.equals(null)) {
					String result = theGame.addCardToTile(rowm, colm, c.getName(), pl.getUserID());
					if (result.equals("Happy")) {
						return "PASS";
					}
					return result;
				} else {
					return "That space does not have a card";
				}
			}else {
				return "That space does not belong to you!\nYou may only place terrain on the "
						+ pl.getLocation() + " side of the board!";
			}
		} catch (UnknownObjectException e) {
			return "That space does not have a card that is moveable";
		}
		
	}

	/*
	 * This method determines if a terrain card can be placed on a certain tile and
	 * places it on that tile and sends the info to the server if it can
	 * 
	 * row and col are the row and column where it will be placed c is the card
	 * being placed pl is the playerprofile of the player placing this card
	 */
	/**
	 * Place unit.
	 *
	 * @param row the row
	 * @param col the col
	 * @param c the c
	 * @param pl the pl
	 * @return the string
	 */
	//TODO
	public String placeUnit(int row, int col, Card c, PlayerProfile pl) {
		if (inRange(row, col, pl)) {
			String temp = theGame.addCardToTile(row, col, c.getName(), pl.getUserID());
			if (temp.equals("Happy")) {
//				MoveInfo mi = new MoveInfo(pl.getUserID(), c.getName(), row, col, -1, -1, "addUnit", theGame.getGameID());
//				ClientNetwork.makeMove(pl.getUserID(), theGame.getGameID(), mi);
				return "PASS";
			} else {
//				JOptionPane.showMessageDialog(null,
//						"You can not place your selected unit on that tile\nfor the following reason: " + temp);
				return temp;
			}
		} else {
//			JOptionPane.showMessageDialog(null, "That space does not belong to you!\nYou may only place units on the "
//					+ pl.getLocation() + " side of the board!");
			return "That space does not belong to you!\nYou may only place terrain cards in the " + pl.getLocation()
			+ " side of the board!";
		}
	}

	/**
	 * Place terrain.
	 * This method determines if a terrain card can be placed on a certain tile and
	 * places it on that tile
	 * row and col are the row and column where it will be placed c is the card
	 * being placed pl is the playerprofile of the player placing this card
	 * @param row the row
	 * @param col the col
	 * @param c the c
	 * @param pl the pl
	 * @return the string
	 */
	public String placeTerrain(int row, int col, Card c, PlayerProfile pl) {
		if (inRange(row, col, pl)) {
			String temp = theGame.addCardToTile(row, col, c.getName(), pl.getUserID());
			if (temp.equals("Happy")) {
				return "PASS";
			} else {
				return "You can not place your selected terrain card on that square\nfor the following reason: "+ temp;
			}
		} else {
			return "That space does not belong to you!\nYou may only place terrain cards in the " + pl.getLocation()
					+ " side of the board!";
		}
	}
	
	/**
	 * Place trap.
	 *
	 * @param row the row
	 * @param col the col
	 * @param c the c
	 * @param pl the pl
	 * @return the string
	 */
	public String placeTrap(int row, int col, Card c, PlayerProfile pl) {
		if (inRange(row, col, pl)) {
			String temp = theGame.addCardToTile(row, col, c.getName(), pl.getUserID());
			if (temp.equals("Happy")) {
//				MoveInfo mi = new MoveInfo(pl.getUserID(), c.getName(), row, col, -1, -1, "addTrap", theGame.getGameID());
//				ClientNetwork.makeMove(pl.getUserID(), theGame.getGameID(), mi);
				return "PASS";
			} else {
//				JOptionPane.showMessageDialog(null,
//						"You can not place your selected trap card on that square\nfor the following reason: "
//								+ temp);
				return temp;
			}
		} else {
//			JOptionPane.showMessageDialog(null,
//					"That space does not belong to you!\nYou may only place terrain cards in the " + pl.getLocation()
//							+ " side of the board!");
			return "That space does not belong to you!\nYou may only place terrain cards in the " + pl.getLocation()
			+ " side of the board!";
		}
	}
	
	/**
	 * Clear tile.
	 *This method allows players to remove their already placed terrain cards from
	 * the board and place that card back in their hand during the terrain building
	 * phase only the row and col are the location where the terrain is going to be removed pl
	 * is the player who is trying to remove the selected terrain
	 * @param row the row
	 * @param col the col
	 * @param pl the pl
	 * @return the string
	 */
	public String clearTile(int row, int col, PlayerProfile pl) {
		if (inRange(row, col, pl)) {
			try {
				Tile[][] t = theGame.getGameboard();
				Card c = theGame.removeCardFromTile(row, col, t[row][col].getTerrainCard().getName(), pl.getUserID());
				pl.getHand().returnCardToHand(c);
				return "PASS";
			} catch (UnknownObjectException e) {
//				JOptionPane.showMessageDialog(null, "This action causes an Unknown Object Exception");
				return "FAIL";
			}
		} else {
//			JOptionPane.showMessageDialog(null, "That tile doesn't belong to you!");
			return "FAIL";
		}
	}

	/**
	 * Do range combat.
	 * This method allows a ranged unit to attack another unit
	 * rowa and cola are the row and column of the attacker rowd and cold are the
	 * row and column of the defender pl is the playerProfile of the actor
	 * @param rowa the rowa
	 * @param cola the cola
	 * @param rowd the rowd
	 * @param cold the cold
	 * @param pl the pl
	 * @return the string
	 */
	public String doRangeCombat(int rowa, int cola, int rowd, int cold, PlayerProfile pl) {

		
		return "FAIL";
	}

	/**
	 * In range.
	 *
	 * @param row the row
	 * @param col the col
	 * @param pl the pl
	 * @return true, if successful
	 */
	/*
	 * row and col are the row and column to be checked The playerProfile is for the
	 * profile being checked
	 * 
	 * The goal of this method is to check if the current user can place a terrain
	 * card in the given row and column
	 */
	public boolean inRange(int row, int col, PlayerProfile pl) {
		if (row < 0 || row > 11) {
			return false;
		}
		if (col < 0 || col > 12) {
			return false;
		}

		if (pl.getLocation().equals("North")) {
			if (row > 5) {
				return false;
			}
			if (col < 2 || col > 10) {
				return false;
			}

			switch (row) {
			case 0:
				if (col != 6) {
					return false;
				}
				break;
			case 1:
				if (col < 4 || col > 8) {
					return false;
				}
				break;
			case 2:
				if (col < 2 || col > 10) {
					return false;
				}
				break;
			case 3:
				if (col < 3 || col > 9) {
					return false;
				}
				break;
			case 4:
				if (col < 5 || col > 7) {
					return false;
				}
				break;
			case 5:
				if (col < 5 || col > 7) {
					return false;
				}
				break;
			}
		} else if (pl.getLocation().equals("East")) {
			if (row < 3 || row > 9) {
				return false;
			}
			if (col < 7) {
				return false;
			}

			switch (col) {
			case 7:
				if (row != 6) {
					return false;
				}
				break;
			case 9:
				if (row < 4 || row > 8) {
					return false;
				}
				break;
			case 8:
				if (row < 4 || row > 7) {
					return false;
				}
				break;
			case 10:
				if (row < 3 || row > 8) {
					return false;
				}
				break;
			case 11:
				if (row < 3 || row > 9) {
					return false;
				}
				break;
			case 12:
				if (row < 3 || row > 8) {
					return false;
				}
				break;
			}
		} else if (pl.getLocation().equals("South")) {
			if (row < 6) {
				return false;
			}
			if (col < 2 || col > 10) {
				return false;
			}

			switch (row) {
			case 6:
				if (col != 6) {
					return false;
				}
				break;
			case 11:
				if (col < 5 || col > 7) {
					return false;
				}
				break;
			case 10:
				if (col < 3 || col > 9) {
					return false;
				}
				break;
			case 9:
				if (col < 2 || col > 10) {
					return false;
				}
				break;
			case 8:
				if (col < 4 || col > 8) {
					return false;
				}
				break;
			case 7:
				if (col < 5 || col > 7) {
					return false;
				}
				break;
			}
		} else if (pl.getLocation().equals("West")) {
			if (col > 5) {
				return false;
			}
			if (row < 3 || row > 9) {
				return false;
			}

			switch (col) {
			case 5:
				if (row != 6) {
					return false;
				}
				break;
			case 3:
				if (row < 4 || row > 8) {
					return false;
				}
				break;
			case 4:
				if (row < 4 || row > 7) {
					return false;
				}
				break;
			case 2:
				if (row < 3 || row > 8) {
					return false;
				}
				break;
			case 1:
				if (row < 3 || row > 9) {
					return false;
				}
				break;
			case 0:
				if (row < 3 || row > 8) {
					return false;
				}
				break;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Make move.
	 *
	 * @param p the p
	 * @param moveInfo the move info
	 * @return the string
	 */
	public String makeMove(PlayerProfile p, MoveInfo moveInfo) {
		// TODO Auto-generated method stub
		String result = "FAIL";
		if (moveInfo.getMoveType().equals("moveUnit")) {
			result = moveUnit(moveInfo.getAtkRow(),moveInfo.getAtkCol() , moveInfo.getDefRow(), moveInfo.getDefCol(),
					theGame.getCards().get(moveInfo.getCardName()), p);
			if(result.startsWith("CAP"))
			{
				String userToElm = result.substring(3,19);
				ArrayList<PlayerProfile> a = theGame.getPlayers();
				for(int i =0; i < a.size(); i++)
				{
					if(a.get(i).getUserID().equals(userToElm))
						a.get(i).setEliminate(true);
				}
				
				int count = 0;
				for(int i=0; i<a.size();i++) {
					if(a.get(i).getEliminate() == false) {count++;}
				}
				if(count<2) {theGame.endGame();}
			}
		} else if(moveInfo.getMoveType().equals("moveTerrain")) {
			result = moveTerrainCard(moveInfo.getAtkRow(),moveInfo.getAtkCol() , moveInfo.getDefRow(), moveInfo.getDefCol(), p);
		} else if (moveInfo.getMoveType().equals("addUnit")) {
			result = placeUnit(moveInfo.getAtkRow(), moveInfo.getAtkCol(), theGame.getCards().get(moveInfo.getCardName()), p);
		} else if (moveInfo.getMoveType().equals("addTile")) {
			result = placeTerrain(moveInfo.getAtkRow(), moveInfo.getAtkCol(), theGame.getCards().get(moveInfo.getCardName()), p);
		}else if(moveInfo.getMoveType().equals("addTrap")) {
			result = placeTrap(moveInfo.getAtkRow(), moveInfo.getAtkCol(), theGame.getCards().get(moveInfo.getCardName()), p);
		}
		
		return result;

	}

}
