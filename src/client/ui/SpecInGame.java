package client.ui;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import client.ClientNetwork;
import game.Game;
import game.Hand;
import game.client.UnstartedGame;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class SpecInGame.
 */
public class SpecInGame extends GameUI{

	/** The chat. */
	TextChat chat = new TextChat(200, 100, "spectator");
	
	/**
	 * Instantiates a new spec in game.
	 *
	 * @param player the player
	 * @param players the players
	 * @param game the game
	 */
	public SpecInGame(PlayerProfile player, ArrayList<PlayerProfile> players, Game game) {
		super(player, game);
		ClientNetwork.game = this;
		initUI();
	}
	
	/* (non-Javadoc)
	 * @see client.ui.GameUI#getChat()
	 */
	public TextChat getChat() {
		return chat;
	}

	
	/* (non-Javadoc)
	 * @see client.ui.GameUI#initUI()
	 */
	public void initUI()
	{
		JPanel panel = new JPanel();
		BoardPanel board;
		
		board = new BoardPanel();
		board.setInGame(this);
		this.setLayout(new GridLayout(1, 2));
		
		panel = board;
		this.add(board);
		panel = chat.getPanel();
		this.add(panel);
		//hand = new Hand(deck, this);
		
		MainWindow.displayPanel(this, 1500, 850, "ABSOLUTE CONQUEST: NOOBS, BIG DADDYS, AND DRAGONS- "+ "Specator Mode");
		panel.setLayout(null);
	    setSize(1500,850);
        setVisible(true);
	}
	
	public void ChangeTurn()
	{
		super.getGame().resetAllUnits();
	}
	
}
