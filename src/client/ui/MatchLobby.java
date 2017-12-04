package client.ui;

/*
  @author Josh Brenneman
 */

import javax.swing.*;
import client.ClientNetwork;
import game.client.UnstartedGame;
import player.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 * The Class MatchLobby.
 */
public class MatchLobby extends JPanel{

	/** The chat. */
	//creates the textchat for the lobby
	public TextChat chat = new TextChat(600, 150, "lobby");
	
	/** The panel. */
	//stores the panel that will be displayed
	private JPanel panel;
	
	/** The player. */
	//hold the users playerprofile
	private  PlayerProfile player = TitleScreen.UserProfile;
	
	/** The decks. */
	//hold the users decks
	private ArrayList<Deck> decks = player.getDecks();
	
	/** The deck. */
	//holds the deck that the user is going to use ingame
	private Deck deck;
	
	/** The table. */
	//creates a table that will hold the data of the deck that is selected
	private String[][] table = new String[50][8];
	
	/** The numready. */
	//holds the number of players that are ready in the lobby
	private  int numready =0;
	
	/** The game. */
	//holds that game object that the user joined/created
	private  UnstartedGame game; 
	
	/** The ready players. */
	//holds Jcheckboxs that will display the users in the lobby
	private  ArrayList<JCheckBox> readyPlayers = new ArrayList<JCheckBox>();
	
	/** The btn check others. */
	//the button that allows players to enter the game after everyone has clicked the ready button
	private JButton btnCheckOthers = (JButton)CreateUI.MakeUIObject("Enter Game",715, 550, 150, 50,"button");
	
	/** The kick buttons. */
	//Buttons for kicking players
	JButton[] kickButtons = new JButton[4];

	//Table for displaying the currently selected deck.
	private JTable deckDisplay;
	
	/**
	 * Instantiates a new match lobby.
	 *
	 * @param g the g
	 */
	public MatchLobby(UnstartedGame g)
	{
		ClientNetwork.lobby = this;
		setGame(g);
		deck = decks.get(0);
		initUI();
	}

	/**
	 * Begin game.
	 */
	public void beginGame() {
		String[] args = null;
		GameUI.main(args,player, game.getPlayers(), game, deck);
		setVisible(false);
	}
	
	/**
	 * Check other player readyness.
	 */
	//checks to see if the other players are ready
	public void checkOtherPlayerReadyness()
	{
		ArrayList<PlayerProfile> players = getGame().getPlayers();
		refreshKickButtons();
		if(players == null)
		{
			System.out.println("no players");
			return;
		}
		int i =0;
		int k = 1;
		int readyAmount = 0;
		System.out.println("num players " +players.size());
		while( i < 4)
		{
			if((i < players.size() && !(players.get(i)== null)))
			{
				readyPlayers.get(i).setText(players.get(i).getUsername());
				
				
				//check if the player is ready
				if(game.getReady().get(i) == true) {
					readyPlayers.get(i).setSelected(true);
					readyAmount++;
				}
				else {
					readyPlayers.get(i).setSelected(false);
				}
				numready = readyAmount;
				readyPlayers.get(i).setVisible(true);
				k++;
			}
			else {
				readyPlayers.get(i).setVisible(false);
			}
			i++;
		}
	}

	/**
	 * Display decks.
	 *
	 * @param box the box
	 */
	//adds all of the decks in to the combobox
	public void displayDecks(JComboBox<String> box)
	{
		for(int i =0; i< decks.size(); i++)
		{
			box.addItem(decks.get(i).getDeckName());
		}
	}
	
	/**
	 * Gets the panel.
	 *
	 * @return the panel
	 */
	//return the screen panel
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Sets the panel.
	 *
	 * @param panel the new panel
	 */
	//sets the screen panel
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	/**
	 * Display cards.
	 *
	 * @return the string[][]
	 */
	//displays the cards in the selected deck in a table
	public String[][] displayCards()
	{
		ArrayList<Card> cards = deck.getCards();
		for(int i = 0; i< 50; i++)
		{
			table[i][0] = i+1+"";
			table[i][1] = cards.get(i).getName();
			table[i][2] = cards.get(i).getAttack()+"";
			table[i][3] = cards.get(i).getSpeed()+"";
			table[i][4] = cards.get(i).getHealth()+"";
			table[i][5] = cards.get(i).getType();
			table[i][6] = cards.get(i).getAbility();
			table[i][7] = cards.get(i).getDescription();
		}
	    revalidate();
	    repaint();
		return table;
	}
	
	/**
	 * Inits the UI.
	 */
	//initializes the lobby screen
	public void initUI()
	{
		String[] args = new String[0];
		String code = "";
		MainWindow.displayPanel(chat.getPanel(), 1000, 700, "Lobby");		
		panel = chat.getPanel();
		panel.setLayout(null);
	    setSize(1000,700);
//	    panel.setBackground(Color.decode("#3498db"));
	    code = FindGame.gameCode;
	    
	    JLabel gameCode = (JLabel)CreateUI.MakeUIObject("ROOM CODE: "+code,450, 600, 400, 100,"label");
	    panel.add(gameCode);
	    
	    JLabel deckPreview = (JLabel)CreateUI.MakeUIObject("Select your Deck:",10, 10, 500, 25,"label");
	    
	    JLabel playerPreview = (JLabel)CreateUI.MakeUIObject("Players:",700, 10, 100, 25,"label");
	    panel.add(playerPreview);
	    
	    JButton btnBack = (JButton)CreateUI.MakeUIObject("Back",10, 550, 100, 50,"button");
	    btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				leaveGame();
	          }	
	       });
	    panel.add(btnBack);

	    //creates the player ready boxes 
	    int x = 0;
	    for(int i = 0; i < 4; i++) {
	    	readyPlayers.add(new JCheckBox());
	    	readyPlayers.get(i).setEnabled(false);
	    	readyPlayers.get(i).setBounds(700, 30+x, 1000, 19);
	    	readyPlayers.get(i).setVisible(false);
	    	panel.add(readyPlayers.get(i));
	    	x +=20;
	    }

	    checkOtherPlayerReadyness();
	 
	    for(int i =0; i < readyPlayers.size();i++)
	    	panel.add(readyPlayers.get(i));
	    
	    JButton btnReady = (JButton)CreateUI.MakeUIObject("READY",870, 550, 100, 50,"button");
	    btnReady.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					ClientNetwork.setReady(game.getGameID(), player.getUserID());
				}	
		       });
	    
	    btnCheckOthers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					checkOtherPlayerReadyness();
					if(numready>= 4)
					{
						GameUI.main(args,player, game.getPlayers(), game, deck);
						panel.removeAll();
						setVisible(false);
					}
				}	
		       });
	    
	    String[] cols = new String[] {"#", "Card", "ATK","Speed", "HP", "Type", "Abillity/Effect", "Description"};

	    JComboBox<String> deckSelect = new JComboBox<String>();
	    deckSelect.setBounds(250, 15, 200, 20);
	    for(int i =0; i< decks.size(); i++)
		{
			deckSelect.addItem(decks.get(i).getDeckName());
		}
	    
	    deckSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for(int i =0; i< decks.size(); i++)
				{
					if(decks.get(i).getDeckName()== deckSelect.getSelectedItem()) {
						deck = decks.get(i);
						displayCards();
						deckDisplay.repaint();
					}
				}
			}	
	       });
	
	    deckDisplay = new JTable(table, cols);
	    deckDisplay.setEnabled(false);
	    
	    JScrollPane realDD = new JScrollPane(deckDisplay);
	    realDD.setBounds(10,50,500,500);
	    if(player.getAccountType() != 2)
	    {
	    	//readyPlayers.get(0).setText(player.getUsername());
	    	//readyPlayers.get(0).setVisible(true);
	    	panel.add(realDD);
	    	panel.add(btnReady);
	    	panel.add(deckPreview);
	    	panel.add(deckSelect);
	    	displayCards();
	    }

	    if(player.getAccountType() == 1) {
	    	System.out.println("ya boi");
		    ImageIcon i = new ImageIcon("pics/Missing.png");
	    	kickButtons[0] = (JButton)CreateUI.MakeUIObject(i,625, 30, 50, 19,"button");
	    	kickButtons[1] = (JButton)CreateUI.MakeUIObject(i,625, 50, 50, 19,"button");
	    	kickButtons[2] = (JButton)CreateUI.MakeUIObject(i,625, 70, 50, 19,"button");
	    	kickButtons[3] = (JButton)CreateUI.MakeUIObject(i,625, 90, 50, 19,"button");
	    	panel.add(kickButtons[0]);
	    	panel.add(kickButtons[1]);
	    	panel.add(kickButtons[2]);
	    	panel.add(kickButtons[3]);
	    	kickButtons[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					ArrayList<PlayerProfile> players = getGame().getPlayers();
					ClientNetwork.KickPlayerFromLobby(players.get(0).getUserID(), game.getGameID());
				}	
		       });
	    	kickButtons[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					ArrayList<PlayerProfile> players = getGame().getPlayers();
					ClientNetwork.KickPlayerFromLobby(players.get(1).getUserID(), game.getGameID());
				}	
		       });
	    	kickButtons[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					ArrayList<PlayerProfile> players = getGame().getPlayers();
					ClientNetwork.KickPlayerFromLobby(players.get(2).getUserID(), game.getGameID());
				}	
		       });
	    	kickButtons[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					ArrayList<PlayerProfile> players = getGame().getPlayers();
					ClientNetwork.KickPlayerFromLobby(players.get(3).getUserID(), game.getGameID());
				}	
		       });
	    	refreshKickButtons();
	    }
	  
	}
	
	/**
	 * Refresh kick buttons.
	 */
	public void refreshKickButtons() {
		if(kickButtons[0] != null) {
			for(int i = 0; i < kickButtons.length; i++) {
				if(i < game.getPlayers().size()) {
					kickButtons[i].setVisible(true);
				}
				else {
					kickButtons[i].setVisible(false);
				}
				
			}
		}
	}
	
	

//	static Thread readyCheck = new Thread() {
//		public void run() {
//			while(numready < 4)
//			{
//				checkOtherPlayerReadyness();
//				int i = 0;
//				while(i < 100)
//				{
//					i++;
//				}
//			}
//		}
//	};
	
	/**
 * Main.
 *
 * @param args the args
 * @param g the g
 */
public static void main(String[] args, UnstartedGame g)
	{

		MatchLobby screen = new MatchLobby(g);
		screen.setVisible(true);
	
//		readyCheck.start();
//		try {
//			readyCheck.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		InGame.main(args);
//		screen.setVisible(false);
	}

	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public UnstartedGame getGame() {
		return game;
	}

	/**
	 * Sets the game.
	 *
	 * @param game the new game
	 */
	public void setGame(UnstartedGame game) {
		ClientNetwork.lobby.game = game;
	}
	
	/**
	 * Leave game.
	 */
	public void leaveGame() {
		ClientNetwork.leaveUnstartedGame(game.getGameID(), player.getUserID());
		FindGame.main(new String[0]);
		panel.removeAll();
		setVisible(false);
	}
}
