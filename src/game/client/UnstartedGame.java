package game.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class UnstartedGame.
 */
public class UnstartedGame{
	
	/** The game ID. */
	private String gameID; //unique game ID
	
	/** The players. */
	private ArrayList<PlayerProfile> players = new ArrayList<PlayerProfile>(); //Players in game
	
	/** The ready. */
	private ArrayList<Boolean> ready = new ArrayList<Boolean>(); //Player ready status
	

	/**
	 * Instantiates a new unstarted game.
	 */
	public UnstartedGame(){
		
	}

	/**
	 * Instantiates a new unstarted game.
	 *
	 * @param gameID the game ID
	 */
	public UnstartedGame(String gameID) {
		this.gameID = gameID;
	}

	/**
	 * Instantiates a new unstarted game.
	 *
	 * @param gameID the game ID
	 * @param ownerProfile the owner profile
	 */
	public UnstartedGame(String gameID, PlayerProfile ownerProfile) {
		this.gameID = gameID;
		players.add(ownerProfile);
		ready.add(false);
	}
	
	/**
	 * Gets the ready.
	 *
	 * @return the ready
	 */
	public ArrayList<Boolean> getReady() {
		return ready;
	}
	
	/**
	 * Sets the ready.
	 *
	 * @param ready the new ready
	 */
	public void setReady(ArrayList<Boolean> ready) {
		this.ready = ready;
	}

	/**
	 * Sets the game ID.
	 *
	 * @param gameID the new game ID
	 */
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	
	/**
	 * Gets the game ID.
	 *
	 * @return the game ID
	 */
	public String getGameID() {
		return gameID;
	}

/*	public void setGameID(String gameID) {
		this.gameID = gameID;
	}*/

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
	 * Adds the player.
	 *
	 * @param p the p
	 */
	public void addPlayer(PlayerProfile p) {
		if(players.size() < 4) {
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getUserID().equals(p.getUserID()))
				return;
			}
			players.add(p);
		}
	}
	
	/**
	 * Sets the ready.
	 *
	 * @param userId the new ready
	 */
	public void setReady(String userId) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getUserID().equals(userId)) {
				ready.set(i, !ready.get(i));
			}
		}
	}

	/**
	 * Removes the player.
	 *
	 * @param userId the user id
	 */
	public void removePlayer(String userId) {
		// TODO Auto-generated method stub
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getUserID().equals(userId)) {
				players.remove(i);
				ready.remove(i);
			}
		}
		
	}
}
