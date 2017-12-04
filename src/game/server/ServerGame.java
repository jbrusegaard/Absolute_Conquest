package game.server;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.rmi.activation.UnknownObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import client.ClientNetwork;
import client.ui.BoardPanel;
import client.ui.CreateUI;
import client.ui.GameUI;
import client.ui.TextChat;
import game.Game;
import game.Hand;
import game.MoveInfo;
import game.Tile;
import game.client.UnstartedGame;
import player.Card;
import player.Deck;
import player.PlayerProfile;
import response.Response;
import response.UserLeftUnstartedGameResponse;
import server.ServerNetwork;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerGame.
 */
/*
 * @author Jacob Aspinall
 */
public class ServerGame {
	
	/** The turn count. */
	//holds the turn count of the game
	private int turnCount=0;
	
	/** The players. */
	//holds the players in the game
	private ArrayList<PlayerProfile> players;
	
	/** The spectators. */
	//holds spectators
	private  ArrayList<PlayerProfile> spectators;
	
	/** The game. */
	//stores the game object
	private Game game;
	
	/** The score table. */
	//stores user scores
	public String[][] scoreTable;
	
	/**
	 * Instantiates a new server game.
	 *
	 * @param players the players
	 * @param gameId the game id
	 */
	public ServerGame(ArrayList<PlayerProfile> players, String gameId)
	{
		this.players = players;
		this.spectators = new ArrayList<PlayerProfile>();
		game = new Game(12, 13,players.get(0), players.get(1), players.get(2), players.get(3), gameId, ServerNetwork.getCardMap());
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
	 * Change turn.
	 */
	//updates turnC and lblPlayerTurn
	public void ChangeTurn()
	{
		turnCount++;

	}
	
	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Sets the game.
	 *
	 * @param game the new game
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Adds the spectator.
	 *
	 * @param userId the user id
	 */
	public void addSpectator(String userId) {
		spectators.add(ServerNetwork.onlinePlayers.get(userId));
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
	 * Gets the player by id.
	 *
	 * @param playerID the player ID
	 * @return the player by id
	 */
	public PlayerProfile getPlayerById(String playerID) {
		for(PlayerProfile p: this.players) {
			if(p.getUserID().equals(playerID)) return p;
		}
		return null;
	}
	
	/**
	 * Gets the game ID.
	 *
	 * @return the game ID
	 */
	public String getGameID() {
		return game.getGameID();
	}
	
	/**
	 * Make move.
	 *
	 * @param mi the mi
	 * @param p the p
	 */
	public void makeMove(MoveInfo mi, PlayerProfile p) {
		game.getMover().makeMove(p, mi);
	}
	
	
	/**
	 * Broadcast.
	 *
	 * @param r the r
	 */
	public void broadcast(Response r) {
		for(PlayerProfile p : players) {
			if(p.getClientConnection() != null) {
				System.out.println(p.getUsername());
				System.out.println(p.getClientConnection());
				ServerNetwork.send(p.getClientConnection(), r);
			}
		}
	}
	
	/**
	 * Broadcast spectators.
	 *
	 * @param r the r
	 */
	public void broadcastSpectators(Response r) {
		for(PlayerProfile s : spectators) {
			if(s.getClientConnection() != null) {
				System.out.println(s.getUsername());
				System.out.println(s.getClientConnection());
				ServerNetwork.send(s.getClientConnection(), r);
			}
		}
	}

}