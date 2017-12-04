package game.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import player.PlayerProfile;
import response.*;
import response.UserLeftUnstartedGameResponse;
import server.ServerNetwork;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerUnstartedGame.
 */
public class ServerUnstartedGame{
	
	/** The game ID. */
	private String gameID; //unique game ID
	
	/** The players. */
	private ArrayList<PlayerProfile> players = new ArrayList<PlayerProfile>(); //Players in game
	
	/** The ready list. */
	private ArrayList<Boolean> readyList = new ArrayList<Boolean>();
	
	/** The current player. */
	private int currentPlayer; //index of player who is currently doing their turn
	
	/** The start counter alive. */
	private boolean startCounterAlive = false;
	
	/**
	 * Gets the ready list.
	 *
	 * @return the ready list
	 */
	public ArrayList<Boolean> getReadyList() {
		return readyList;
	}

	/**
	 * Sets the ready list.
	 *
	 * @param readyList the new ready list
	 */
	public void setReadyList(ArrayList<Boolean> readyList) {
		this.readyList = readyList;
	}

	/**
	 * Instantiates a new server unstarted game.
	 */
	public ServerUnstartedGame(){

		disconnectCheck.start();
		
	}
	
	 /** The disconnect check. */
 	Thread disconnectCheck = new Thread() {
		public synchronized void run() {

			boolean remove = false;
			while(true) {
				ArrayList<String> usersToRemove = new ArrayList<String>();
				Iterator<PlayerProfile> i = players.iterator();
				while(i.hasNext()) {
					PlayerProfile p = i.next();
					if(!ServerNetwork.onlinePlayers.containsKey(p.getUserID())) {
						usersToRemove.add(p.getUserID());
						
					}	
				}
				for(String s : usersToRemove) {
					removePlayer(s);
					broadcast(new UserLeftUnstartedGameResponse(s));
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	 /** The start game counter. */
 	Thread startGameCounter = new Thread() {
		public synchronized void run() {
			startCounterAlive = true;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean allReady = true;
			for(Boolean b : readyList) {
				if( b == false) {
					allReady = false;
				}
			}
			if(allReady){
				broadcast(new BeginGameResponse());
				ServerNetwork.currentGames.put(gameID, new ServerGame(players,gameID) );
				ServerNetwork.unstartedGames.remove(gameID);
				
			}
			startCounterAlive = false;
		}
	};
	
	

	/**
	 * Instantiates a new server unstarted game.
	 *
	 * @param ownerProfile the owner profile
	 */
	public ServerUnstartedGame(PlayerProfile ownerProfile) {
		gameID = generateGameID();
		players.add(ownerProfile);
		readyList.add(false);
		disconnectCheck.start();
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
		return gameID;
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
	 * @return the int
	 */
	public synchronized int addPlayer(PlayerProfile p) {
		if(players.size() < 4) {
			if(p.getAccountType() != 2)
			{
				players.add(p);
				readyList.add(false);
			}
			return 1;
		}
		else {
			return -1;//Already 4 players in group
		}
	}
	
	/**
	 * Generate game ID.
	 *
	 * @return the string
	 */
	private String generateGameID() {
		int idSize = 4;
		String chars = "AbcdEFGHJKLmnOPQRSTUVWXYZaBCDefghijkMNopqrstuvwxyz0125436789";
		Random random = new Random();
		String id = "";
		for (int i = 0; i < idSize; i++) {
			id += chars.charAt(random.nextInt(chars.length()));
		}
		return id;
	}
	
	/**
	 * Broadcast.
	 *
	 * @param r the r
	 */
	public void broadcast(Response r) {
		for(PlayerProfile p : players) {
			System.out.println(p.getUsername());
			System.out.println(p.getClientConnection());
			ServerNetwork.send(p.getClientConnection(), r);
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
				readyList.remove(i);
			}
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
				readyList.set(i, !readyList.get(i));
				boolean allReady = true;
				for(Boolean b : readyList) {
					if( b == false) {
						allReady = false;
					}
				}
				if(allReady && readyList.size() == 4){
					if(!startCounterAlive)
						new Thread(startGameCounter).start();
				}
			}
		}
	}
	

}
