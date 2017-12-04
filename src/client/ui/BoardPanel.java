package client.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.ClientNetwork;
import game.Game;
import game.MoveInfo;
import player.Card;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class BoardPanel.
 */
public class BoardPanel extends JPanel {
	
	/** The board height. */
	double BOARD_HEIGHT = 687.5;
	
	/** The board width. */
	double BOARD_WIDTH = 640;
	
	/** The board image. */
	public BoardImage boardImage;
	
	/** The game. */
	public Game game;
	
	/** The zoom amount. */
	public double zoomAmount = 1;
	
	/** The ig. */
	//public Point selectedTile = new Point();
	private GameUI ig;
	
	/** The j. */
	private static JFrame j;
	
	/**
	 * Instantiates a new board panel.
	 */
	public BoardPanel() {
		this.boardImage = new BoardImage();
		//game = new Game(12,13);
		this.setSize(2000, 2000);
		//BufferedImage h = boardImage.getBoardImage();
		Graphics g = this.getGraphics();
		this.repaint();
		//System.out.println(h.getWidth() + " " + h.getHeight());
		addMouseWheelListener(new MouseWheelListener() {
			
			  public void mouseWheelMoved(MouseWheelEvent e) {
				  //int notches = e.getWheelRotation();
				  //zoomAmount += notches;
				 //getGraphics().clearRect(0, 0, getWidth(), getHeight());
				  //paintComponent(getGraphics());
			  }
			
		});
		
		addMouseListener(new MouseListener(){
			
			Card cardInfo;
			@Override
			public void mousePressed(MouseEvent e){

				if(e.getButton() == MouseEvent.BUTTON1) {
					int row = mouseClickToRow(e);
					int column = mouseClickToColumn(e);
					//System.out.println("row: " + row + "  column: " + column);
					//System.out.println("");
					Point oldSelectedTile = ig.getSelectedTile();
					Point newSelectedTile = new Point(column, row);
					try {
						if(row >= 12 || column >= 13 || game.getGameboard()[row][column].getTerrainCard().getName().equals("InvalidTerrain")) {
							ig.setSelectedTile(null);
							ig.setSelectedcard(-1);
							return;
						}
					}catch (ArrayIndexOutOfBoundsException oob) {
						oob.printStackTrace();
					}
					System.out.println(ig.getTurnCount() % ig.getPlayers().size());
					//Moving cards from one a tile to another
					if(ig.getSelectedTile() == null) {
						ig.setSelectedTile(newSelectedTile);
					}	//
					else if(ig.getTotalTurnCount() % ig.getPlayers().size() == ig.getPlayerNum()){
						String result = "PASS";
						Card unitCard = game.getGameboard()[(int) oldSelectedTile.getY()][(int) oldSelectedTile.getX()].getUnitCard();
						Card terrainCard = game.getGameboard()[(int) oldSelectedTile.getY()][(int) oldSelectedTile.getX()].getTerrainCard();
						if(unitCard != null && (unitCard.getType().equals("Air") || unitCard.getType().equals("Aquatic") || unitCard.getType().equals("Land"))) {
							MoveInfo mi = new MoveInfo(ig.getPlayer().getUserID(), unitCard.getName(), (int)oldSelectedTile.getY(),(int) oldSelectedTile.getX()
									,row, column, "moveUnit", ig.getGame().getGameID());
							result = game.getMover().makeMove(ig.getPlayer(), mi);
							if(result.startsWith("PASS")) 
							{
								ClientNetwork.sendChatMessage(ClientNetwork.game.getGame().getGameID(), "ingame", "GM", result.substring(4));
								ClientNetwork.makeMove(ig.getPlayer().getUserID(), ig.getGame().getGameID(), mi);
							}
							else if(result.startsWith("CAP")) {


								ArrayList<PlayerProfile> a = ig.getPlayers();
								//a.get((ig.getTotalTurnCount())%4).updatePoints(1);
								int eliminated = 0;
								for(int i =0; i < a.size(); i++)
								{
									if(a.get(i).getEliminate())
										eliminated++;
								}
								//ig.scoreTable[ig.getPlayerNum()][1] = (Integer.parseInt(ig.scoreTable[ig.getPlayerNum()][1])+1)+"";
								if(eliminated == 3) {
									ig.turnOff();
								}
								ClientNetwork.sendChatMessage(ClientNetwork.game.getGame().getGameID(), "ingame", "GM", result.substring(19));
								ClientNetwork.makeMove(ig.getPlayer().getUserID(), ig.getGame().getGameID(), mi);
							}
							else 
							{
								JOptionPane.showMessageDialog(null,result);
							}
						}
						if(ig.getTurnCount() == 0) {
							if (terrainCard != null
									&& (terrainCard.getType().equals("Terrain") || terrainCard.getType().equals("Town")
											|| terrainCard.getType().equals("Capital"))) {
								MoveInfo mi = new MoveInfo(ig.getPlayer().getUserID(), terrainCard.getName(),
										(int) oldSelectedTile.getY(), (int) oldSelectedTile.getX(), row, column,
										"moveTerrain", ig.getGame().getGameID());
								result = game.getMover().makeMove(ig.getPlayer(), mi);
								if (result.equals("PASS")) {
									ClientNetwork.makeMove(ig.getPlayer().getUserID(), ig.getGame().getGameID(), mi);
								} else {
									JOptionPane.showMessageDialog(null, result);
								}
							}
						}
						if(result.equals("FAIL")) {
							
						}
						ig.setSelectedTile(null);
						paintComponent(getGraphics());

					}
					
					//Moving cards from Hand to Board
					//Placing units
					if(ig.getSelectedCard() != -1) {
						String result = "FAIL";
						Card c = ig.getHand().playCardFromHand(ig.getSelectedCard());
						if(c.getType().equals("Terrain") || c.getType().equals("Town") || c.getType().equals("Capital")) {
							MoveInfo mi = new MoveInfo(ig.getPlayer().getUserID(), c.getName(), row, column, -1, -1, "addTile", ig.getGame().getGameID());
							result = game.getMover().makeMove(ig.getPlayer(), mi);
							if(result.equals("PASS")) {
								ClientNetwork.makeMove(ig.getPlayer().getUserID(), ig.getGame().getGameID(), mi);
							} else {
								JOptionPane.showMessageDialog(null,result);
							}
						}
						if(c.getType().equals("Air") || c.getType().equals("Aquatic") || c.getType().equals("Land")) {
							MoveInfo mi = new MoveInfo(ig.getPlayer().getUserID(), c.getName(), row, column, -1, -1, "addUnit", ig.getGame().getGameID());
							result = game.getMover().makeMove(ig.getPlayer(), mi);
							if(result.equals("PASS")) {
								ClientNetwork.makeMove(ig.getPlayer().getUserID(), ig.getGame().getGameID(), mi);
							}else {
								JOptionPane.showMessageDialog(null,result);
							}
						}
						if(c.getType().equals("Land Trap") || c.getType().equals("Mountain Trap") || c.getType().equals("Aquatic Trap")) {
							MoveInfo mi = new MoveInfo(ig.getPlayer().getUserID(), c.getName(), row, column, -1, -1, "addTrap", ig.getGame().getGameID());
							result = game.getMover().makeMove(ig.getPlayer(), mi);
							if(result.equals("PASS")) {
								ClientNetwork.makeMove(ig.getPlayer().getUserID(), ig.getGame().getGameID(), mi);
							}else {
								JOptionPane.showMessageDialog(null,result);
							}
						}
						if(!result.equals("PASS")) {
							ig.getHand().returnCardToHand(c);
						}
						ig.setSelectedTile(null);
						paintComponent(getGraphics());

					}
					
					
					
					if(newSelectedTile.equals(oldSelectedTile)) {
						ig.setSelectedTile(null);
					}
					
					ig.setSelectedcard(-1);
					
					//boardImage.getTileImages()[row][column].setImage("Dragon", "Volcano");
					
				}

			
			}

			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}
	
	/**
	 * Mouse click to row.
	 *
	 * @param e the e
	 * @return the int
	 */
	protected int mouseClickToRow(MouseEvent e) {
		double x = e.getX() * zoomAmount;
		double y = e.getY() * zoomAmount;
		System.out.println("x: " + x + " y: " + y +  " ");
		int halfRow = (int) (y / (BOARD_HEIGHT / 25));
		int colZone = (int) (x / (BOARD_WIDTH / 40));
		System.out.println("halfrow: " + halfRow + " colZone: " + colZone +  " ");
		
		if(halfRow % 2 == 1) {
			return (halfRow - 1) / 2;
		}
		
		if(colZone % 6 == 1 || colZone % 6 == 2 || colZone % 6 == 4 || colZone % 6 == 5) {
			if( halfRow == 0)
				return 0;
			else {
				if( colZone % 6 == 1 || colZone % 6 == 2)
					return (halfRow - 1) / 2;
				else
					return (halfRow) / 2;
			}
		}
		//if slope of hexagon edge is positive
		if((colZone % 6 == 0 && halfRow % 2 == 1) || (colZone % 6 == 3 && halfRow % 2 == 0)) {
			double localY = y - (BOARD_HEIGHT / 25) * halfRow;
			double localX = x - (BOARD_WIDTH / 40) * colZone;
			if( -1.7 * localX + (BOARD_HEIGHT / 25) < localY) {
				return (halfRow) / 2;
			}
			else {
				return (halfRow - 1) / 2;
			}
		}
		
		//if slope of hexagon is negative
		if((colZone % 6 == 0 && halfRow % 2 == 0) || (colZone % 6 == 3 && halfRow % 2 == 1)) {
			double localY = y - (BOARD_HEIGHT / 25) * halfRow;
			double localX = x - (BOARD_WIDTH / 40) * colZone;
			if(1.7 * localX< localY) {
				return (halfRow) / 2;
			}
			else {
				return (halfRow - 1) / 2;
			}
		}
		return 0;
	}
	
	/**
	 * Mouse click to column.
	 *
	 * @param e the e
	 * @return the int
	 */
	private int mouseClickToColumn(MouseEvent e) {
		double x = e.getX() * zoomAmount;
		double y = e.getY() * zoomAmount;
		//System.out.println("x: " + x + " y: " + y +  " ");
		int halfRow =(int) (y / (BOARD_HEIGHT / 25));
		int colZone =(int) (x / (BOARD_WIDTH / 40));
		//System.out.println("halfrow: " + halfRow + " colZone: " + colZone +  " ");
		
		if(colZone % 6 == 1 || colZone % 6 == 2 || colZone % 6 == 4 || colZone % 6 == 5) {
			return colZone / 3;
		}
		//if slope of hexagon edge is positive
		if((colZone % 6 == 0 && halfRow % 2 == 1) || (colZone % 6 == 3 && halfRow % 2 == 0)) {
			double localY = y - (BOARD_HEIGHT / 25) * halfRow;
			double localX = x - (BOARD_WIDTH / 40) * colZone;
			if( -1.7 * localX + (BOARD_HEIGHT / 25) < localY) {
				return (colZone) / 3;
			}
			else {
				return (colZone - 1) / 3;
			}
		}
		
		//if slope of hexagon is negative
		if((colZone % 6 == 0 && halfRow % 2 == 0) || (colZone % 6 == 3 && halfRow % 2 == 1)) {
			double localY = y - (BOARD_HEIGHT / 25) * halfRow;
			double localX = x - (BOARD_WIDTH / 40) * colZone;
			if(1.7 * localX< localY) {
				return (colZone - 1) / 3;
			}
			else {
				return (colZone) / 3;
			}
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		boardImage.draw(g,ig.getPlayers(), ig.getPlayer());

	}
	
	/**
	 * Sets the in game.
	 *
	 * @param ig the new in game
	 */
	public void setInGame(GameUI ig) {
		this.ig = ig;
		this.game = ig.getGame();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		ClientNetwork.connect();
		BoardPanel b = new BoardPanel();
		j = new JFrame();
		j.setSize(2000, 2000);
		j.setVisible(true);
		j.add(b);
	}

}
