package client.ui;

import javax.imageio.ImageIO;

/*
 * @author Josh Brenneman
 */

import javax.swing.*;

import client.ClientNetwork;
import game.Board;
import game.client.UnstartedGame;
import player.Card;
import player.Deck;
import player.PlayerProfile;
import response.LoginResponse;
import game.Game;
import game.Hand;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class GameUI.
 */
public class GameUI extends JPanel{

	/** The turn count. */
	//holds the turn count of the game
	private int turnCount=0;
	
	/** The hand. */
	//hold the users hand
	private Hand hand;
	
	/** The player. */
	//holds the player profile of the user
	private PlayerProfile player;
	
	/** The players. */
	//holds the players in the game
	private ArrayList<PlayerProfile> players;
	
	/** The game. */
	//holds the game object
	private static UnstartedGame game; 
	
	/** The turn C. */
	//label the displays the turn count
	private JLabel turnC;
	
	/** The lbl player turn. */
	//label that displays whose turn it is
	private JLabel lblPlayerTurn;
	
	/** The time. */
	//displays the timer for the turn
	private JLabel time; 
	
	/** The timer. */
	//keeps track of the time that the 
	private Timer timer;
	
	/** The panel. */
	//hold the screen panel
	private JPanel panel;
	
	/** The cards in hand. */
	//holds the cards in that players hand
	private ArrayList<Card> cardsInHand;	
	
	/** The card display. */
	//holds the buttons that will display the cards in the players hand
	private ArrayList<JButton> cardDisplay = new ArrayList<JButton>();
	
	/** The deck. */
	//holds the players deck
	private static Deck deck;
	
	/** The ok. */
	//i dont even know what this does
	private static int ok = 1;
	
	/** The board. */
	//stores the gameboard that the user will interact with
	private BoardPanel board;
	
	/** The game. */
	//stores the game that will be used
	private Game theGame;
	
	/** The btn end. */
	//holds the button that will be used to end the turn
	private JButton btnEnd;
	
	/** The chat. */
	//holds the chat client
	private TextChat chat = new TextChat(200, 100, "ingame");
	
	/** The selectedcard. */
	//Selected card from hand
	private int selectedcard = -1;
	
	/** The selected tile. */
	//Selected tile on board
	private Point selectedTile = null;
	//We need a counter to be private to this class for the button actions listeners
	/** The counter. */
	//to be initialized.
	private int counter = 0;
	
	/** The score table. */
	public String[][] scoreTable;
	
	/** The kick buttons. */
	//Buttons for kicking players
	JButton[] kickButtons = new JButton[4];
	
	/**
	 * Instantiates a new game UI.
	 *
	 * @param player the player
	 * @param players the players
	 */
	public GameUI(PlayerProfile player, ArrayList<PlayerProfile> players)
	{
		this.player = player;
		player.setEliminate(false);
		for(PlayerProfile p : players) {
			p.setEliminate(false);
		}
		this.players = players;
		theGame = new Game(12,13, players.get(0),players.get(1), players.get(2), players.get(3), game.getGameID(), ClientNetwork.getCardLibraryMap());
		for(PlayerProfile p : players) {
			if(player.getUserID().equals(p.getUserID()))
				this.player = p;
		}
		scoreTable = new String[4][2];
		ClientNetwork.game = this;
		initUI();
	}
	
	/**
	 * Instantiates a new game UI.
	 *
	 * @param p the p
	 * @param game the game
	 */
	//For use in spectator mode
	public GameUI(PlayerProfile p, Game game) {
		this.player = p;
		this.players = game.getPlayers();
		this.theGame = game;
		scoreTable = new String[4][2];
	}
	
	/**
	 * Gets the turn count.
	 *
	 * @return the turn count
	 */
	public int getTurnCount()
	{
		return turnCount/4;
	}
	
	/**
	 * Gets the total turn count.
	 *
	 * @return the total turn count
	 */
	public int getTotalTurnCount() {
		return this.turnCount;
	}
	
	/**
	 * Gets the player num.
	 *
	 * @return the player num
	 */
	public int getPlayerNum()
	{
		for(int i = 0; i < players.size(); i++)
		{
			if(player.equals(players.get(i)))
				return i;
		}
		return -1;
	}
	
//	public int getNumEle()
//	{
//		int j=0;
//		for(int i= 0; i< players.size(); i++)
//		{
//			if(players.get(i).getEliminate())
//				j++;
//		}
//		return j;
//	}
/**
 * Gets the selected card.
 *
 * @return the selected card
 */
//	
	public int getSelectedCard() {
		return selectedcard;
	}
	
	/**
	 * Selected tile.
	 *
	 * @return the point
	 */
	public Point selectedTile() {
		return selectedTile;
	}
	
	/**
	 * Enable buttons.
	 */
	//enables all of the buttons  on the screen
	public void enableButtons()
	{
		if(players.get((turnCount)%4).getEliminate()){
			ClientNetwork.endTurn(player.getUserID(), game.getGameID());
			return;
		}
		for(int i =0; i< 15; i++)
		{
			cardDisplay.get(i).setEnabled(true);
		}
		//btnDraw.setEnabled(true);
		btnEnd.setEnabled(true);

	}
	
	/**
	 * Disable buttons.
	 */
	//disables all buttons on the screen
	public void disableButtons()
	{
		for(int i =0; i< 15; i++)
		{
			cardDisplay.get(i).setEnabled(false);
		}
		//btnDraw.setEnabled(false);
		btnEnd.setEnabled(false);
	}
	
	/**
	 * Change turn.
	 */
	//updates turnC and lblPlayerTurn
	public void ChangeTurn()
	{
		turnCount++;
		lblPlayerTurn.setText(players.get(turnCount%players.size()).getUsername()+"'s turn");
		turnC.setText("Turn: "+ (1+turnCount/4));
		ok =0;
		if(!(players.get(turnCount%players.size()).getUsername().equals(player.getUsername())))
		{
			disableButtons();
		}
		else {
			enableButtons();
			if(hand.getHand().size()<15) {
				hand.drawCard();
				if((turnCount/4 ==1))
					for(int i = 0; i < 6; i++)
						hand.drawCard();
				DisplayHand();
			}
			theGame.resetAllUnits();
//			theGame.endTurnServer();
		}
		repaint();
	}
	
	/**
	 * Gets the your game on.
	 *
	 * @return the your game on
	 */
	//returns the game object
	public Game getYourGameOn()
	{
		return theGame;
	}
	
	/**
	 * Update timer.
	 */
	//updates the UI timer
	public void updateTimer()
	{
		time.setText("Time Remaining: "+ ((timer.getDelay()/1000)-ok));
		ok++;
		repaint();
	}
	
	/**
	 * Inits the hand buttons.
	 */
	//creates all of the buttons of the cards in the hand
	public void initHandButtons()
	{
	    for(counter =0; counter< 15; counter++)
	    {
	    	cardDisplay.add((JButton)CreateUI.MakeUIObject("", 200+(50*(counter%8)), 600+(50*(counter/8)), 50, 50,"button"));
	    	//cardDisplay.get(i).setBounds(200+(50*(i%8)),600+(50*(i/8)), 50, 50);
	    	cardDisplay.get(counter).setVisible(false);
	    	panel.add(cardDisplay.get(counter));
//	    	cardDisplay.get(counter).addActionListener(new ActionListener() {
//	  			public void actionPerformed(ActionEvent event) {
//	  				//hand.playCardFromHand(0);
//	  				if(selectedcard != counter)
//	  					selectedcard = counter;
//	  				else
//	  					selectedcard = -1;
//	  				
//	  				selectedTile = null;
//	  				DisplayHand();
//	  				
//	  			}
//	  	    });
	    }
	  
	    cardDisplay.get(0).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//hand.playCardFromHand(0);
  				if(selectedcard != 0)
  					selectedcard = 0;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(1).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//prompt users to find where to place the card
  				//hand.playCardFromHand(1);
  				if(selectedcard != 1)
  					selectedcard = 1;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(2).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//prompt users to find where to place the card
  				//hand.playCardFromHand(2);
  				if(selectedcard != 2)
  					selectedcard = 2;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(3).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//prompt users to find where to place the card
  				//hand.playCardFromHand(3);
  				if(selectedcard != 3)
  					selectedcard = 3;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(4).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//prompt users to find where to place the card
  				//hand.playCardFromHand(4);
  				if(selectedcard != 4)
  					selectedcard = 4;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(5).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//prompt users to find where to place the card
  				//hand.playCardFromHand(5);
  				if(selectedcard != 5)
  					selectedcard = 5;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(6).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//prompt users to find where to place the card
  				//hand.playCardFromHand(6);
  				if(selectedcard != 6)
  					selectedcard = 6;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(7).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//hand.playCardFromHand(0);
  				if(selectedcard != 7)
  					selectedcard = 7;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(8).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//hand.playCardFromHand(0);
  				if(selectedcard != 8)
  					selectedcard = 8;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    
	    cardDisplay.get(9).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//hand.playCardFromHand(0);
  				if(selectedcard != 9)
  					selectedcard = 9;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(10).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//hand.playCardFromHand(0);
  				if(selectedcard != 10)
  					selectedcard = 10;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(11).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//hand.playCardFromHand(0);
  				if(selectedcard != 11)
  					selectedcard = 11;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(12).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//hand.playCardFromHand(0);
  				if(selectedcard != 12)
  					selectedcard = 12;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(13).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//hand.playCardFromHand(0);
  				if(selectedcard != 13)
  					selectedcard = 13;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	    cardDisplay.get(14).addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
  				//hand.playCardFromHand(0);
  				if(selectedcard != 14)
  					selectedcard = 14;
  				else
  					selectedcard = -1;
  				
  				selectedTile = null;
  				DisplayHand();
  				
  			}
  	    });
	}
	
	/**
	 * Display hand.
	 */
	//displays the cards in the players hand on the screen
	public void DisplayHand()
	{
		cardsInHand = hand.getHand();
		int i;
		for(i=0; i < cardsInHand.size();i++)
		{
			Image image;
			try {
				image = ImageIO.read(new File("pics/"+cardsInHand.get(i).getFilename()+".png"));
				Image resized = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
				ImageIcon cardImg = new ImageIcon(resized);
				cardDisplay.get(i).setIcon(cardImg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cardDisplay.get(i).setToolTipText(cardsInHand.get(i).getName());
			cardDisplay.get(i).setVisible(true);
		}
		while( i < 15)
		{
			cardDisplay.get(i).setVisible(false);
			i++;
			
		}
	}
	
	/**
	 * Update score.
	 */
	public void updateScore()
	{
		for(int i = 0; i < players.size(); i++)
		{
			scoreTable[i][0] = players.get(i).getUsername();
			scoreTable[i][1] = players.get(i).getPoints()+"";
		}
	}
	
	
	/**
	 * Inits the UI.
	 */
	//initulizes the UI
	public void initUI()
	{	
		board = new BoardPanel();
		board.setInGame(this);
		this.setLayout(new GridLayout(1, 2));
		
		panel = board;
		this.add(board);
		panel = chat.getPanel();
		this.add(panel);
		hand = new Hand(deck, this);
		
		MainWindow.displayPanel(this, 1500, 850, "ABSOLUTE CONQUEST: NOOBS, BIG DADDYS, AND DRAGONS- "+ player.getUsername());
		panel.setLayout(null);
	    setSize(1500,850);
		   
	    initHandButtons();
	    
	    //labels
	    
	    JLabel lblHand =(JLabel) CreateUI.MakeUIObject("Cards in Hand:", 200, 580, 100, 10, "label");
	    //lblHand.setBounds(200, 580, 100, 10);
	    panel.add(lblHand);

	    DisplayHand();
	    
	    turnC = new JLabel("Turn: "+ (turnCount-(turnCount%4)));
	    turnC.setBounds(400, 30, 100, 25);
	    panel.add(turnC);
	    
		lblPlayerTurn = new JLabel();
		lblPlayerTurn.setText(players.get(turnCount%4).getUsername()+"'s turn");
		lblPlayerTurn.setBounds(400,1, 150,25);
		panel.add(lblPlayerTurn);
	    
	    //buttons
		btnEnd = new JButton("End Turn");
	    btnEnd.setBounds(200, 550, 100, 25);
	    btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//ChangeTurn();
				ArrayList<Card> curHand  = hand.getHand();
				boolean playedCap = true;
				boolean playedTer = true;
				
				for(Card c: curHand) {
					if(c.getName().equals("Capital")) playedCap = false;
					if(c.getType().equals("Terrain")) playedTer = false; 
				}
				if(!playedCap)
				{
					JOptionPane.showMessageDialog(panel, "Must Place Capital!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!playedTer) {
					JOptionPane.showMessageDialog(panel, "Must Place All Terrain!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					ClientNetwork.endTurn(player.getUserID(), game.getGameID());
			
				}
			}
	    });
	    panel.add(btnEnd);
	        
	    
	    //timers
		timer = new Timer(60000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//ChangeTurn();
				ClientNetwork.endTurn(player.getUserID(), game.getGameID());
			}
		});
		//timer.start();
		
		time = new JLabel("Time Remaining: "+ (timer.getDelay()/1000));
		time.setBounds(500, 1, 200, 25);
		//panel.add(time);
		
		Timer timeTime = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				updateTimer();
			}
		});
		timeTime.start();
		
		if (TitleScreen.UserProfile.getAccountType() == 1) {
			ImageIcon i = new ImageIcon("pics/Missing.png");
/*			JButton kick1 = (JButton)CreateUI.MakeUIObject(i,115, 25, 100, 10, "button");
			JButton kick2 = (JButton)CreateUI.MakeUIObject(i,115, 41, 100, 10, "button");
			JButton kick3 = (JButton)CreateUI.MakeUIObject(i,115, 57, 100, 10, "button");
			JButton kick4 = (JButton)CreateUI.MakeUIObject(i,115, 73, 100, 10, "button");*/
	    	kickButtons[0] = (JButton)CreateUI.MakeUIObject(i,100, 50, 50, 16,"button");
	    	kickButtons[1] = (JButton)CreateUI.MakeUIObject(i,100, 65, 50, 16,"button");
	    	kickButtons[2] = (JButton)CreateUI.MakeUIObject(i,100, 80, 50, 16,"button");
	    	kickButtons[3] = (JButton)CreateUI.MakeUIObject(i,100, 95, 50, 16,"button");
	    	panel.add(kickButtons[0]);
	    	panel.add(kickButtons[1]);
	    	panel.add(kickButtons[2]);
	    	panel.add(kickButtons[3]);
			ArrayList<PlayerProfile> players = getGame().getPlayers();
			kickButtons[0].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	ClientNetwork.EliminatePlayer(players.get(0).getUserID(), game.getGameID());
			    }
			});
			kickButtons[1].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	ClientNetwork.EliminatePlayer(players.get(1).getUserID(), game.getGameID());
			    }
			});
			kickButtons[2].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	ClientNetwork.EliminatePlayer(players.get(2).getUserID(), game.getGameID());
			    }
			});
			kickButtons[3].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	ClientNetwork.EliminatePlayer(players.get(3).getUserID(), game.getGameID());
			    }
			});
			panel.add(kickButtons[0]);
			panel.add(kickButtons[0]);
			panel.add(kickButtons[0]);
			panel.add(kickButtons[0]);
		}
		String[] cols = {"Name", "Score"};
		scoreTable = new String[4][2];

		for(int i = 0; i < 4; i++)
		{
			scoreTable[i][0] = players.get(i).getUsername();
			scoreTable[i][1] = players.get(i).getPoints()+"";
		}
		JTable pScoreTable = new JTable(scoreTable, cols);
		pScoreTable.setEnabled(false);
		//pScoreTable.setBounds(1, 25, 75, 75);
		JScrollPane sPane = new JScrollPane(pScoreTable);
		sPane.setBounds(1, 25, 100, 104);
		panel.add(sPane);
		
		if(players.get(turnCount%4).getUsername().equals(player.getUsername()))
			enableButtons();
		else
			disableButtons();
		
		if(player.getEliminate())
		{
			//ChangeTurn();
			ClientNetwork.endTurn(player.getUserID(), game.getGameID());
		}
		
	}
	
	
	/**
	 * Gets the selectedcard.
	 *
	 * @return the selectedcard
	 */
	//returns the index of the selected card in the hand
	public int getSelectedcard() {
		return selectedcard;
	}

	/**
	 * Sets the selectedcard.
	 *
	 * @param selectedcard the new selectedcard
	 */
	//sets the selected card to the card at the index in the hand
	public void setSelectedcard(int selectedcard) {
		this.selectedcard = selectedcard;
	}

	/**
	 * Gets the selected tile.
	 *
	 * @return the selected tile
	 */
	//returns the point on the board where the user has clicked
	public Point getSelectedTile() {
		return selectedTile;
	}

	/**
	 * Sets the selected tile.
	 *
	 * @param selectedTile the new selected tile
	 */
	//sets the selected tile to the tile that the user clicked on
	public void setSelectedTile(Point selectedTile) {
		this.selectedTile = selectedTile;
	}

	/**
	 * Gets the hand.
	 *
	 * @return the hand
	 */
	//returns the players hand
	public Hand getHand() {
		return hand;
	}

	/**
	 * Sets the hand.
	 *
	 * @param hand the new hand
	 */
	//sets the players hand
	public void setHand(Hand hand) {
		this.hand = hand;
	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	//returnds the board
	public BoardPanel getBoard() {
		return board;
	}

	/**
	 * Sets the board.
	 *
	 * @param board the new board
	 */
	//sets the board
	public void setBoard(BoardPanel board) {
		this.board = board;
	}

	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame() {
		return this.theGame;
	}

	/**
	 * Sets the game.
	 *
	 * @param game the new game
	 */
	public void setGame(Game game) {
		this.theGame = game;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public  PlayerProfile getPlayer() {
		return player;
	}
	
	/**
	 * Sets the player.
	 *
	 * @param p the new player
	 */
	public void setPlayer(PlayerProfile p) {
		player = p;
	}
	
	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public ArrayList<PlayerProfile> getPlayers() {
		return players;
	}

	/**
	 * Sets the players.
	 *
	 * @param players the new players
	 */
	public void setPlayers(ArrayList<PlayerProfile> players) {
		this.players = players;
	}

	/**
	 * Gets the chat.
	 *
	 * @return the chat
	 */
	public TextChat getChat() {
		return chat;
	}

	/**
	 * Main.
	 *
	 * @param args the args
	 * @param p the p
	 * @param ps the ps
	 * @param g the g
	 * @param d the d
	 */
	public static void main(String[] args, PlayerProfile p, ArrayList<PlayerProfile> ps, UnstartedGame g, Deck d)
	{
		game = g;
		deck = d;
		GameUI menu = new GameUI(p,ps);
		menu.setVisible(true);
	}
	
	/**
	 * Turn off.
	 */
	public void turnOff() {
		panel.removeAll();
		this.setVisible(false);
		System.out.println("ingame");
	}

	/**
	 * Check for end.
	 */
	public void CheckForEnd() {
		int eliminated = 0;
		for(int i =0; i < players.size(); i++)
		{
			if(players.get(i).getEliminate())
				eliminated++;
		}
		updateScore();
		//scoreTable[getPlayerNum()][1] = (Integer.parseInt(scoreTable[getPlayerNum()][1])+1)+"";
		if(eliminated == 3) {
			turnOff();
		}
		
	}
	
}