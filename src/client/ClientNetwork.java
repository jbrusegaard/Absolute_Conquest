package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import client.ui.GameUI;
import client.ui.MatchLobby;
import client.ui.TitleScreen;
import game.Game;
import game.MoveInfo;
import player.Card;
import player.Deck;
import player.PlayerProfile;
import request.AccountTypeGetRequest;
import request.AccountTypeSetRequest;
import request.AddPlayerDeckRequest;
import request.CreateUnstartedGameRequest;
import request.EliminatePlayerRequest;
import request.EndTurnRequest;
import request.GetCardIdByRarityAddToUserRequest;
import request.GetCardLibraryRequest;
import request.GetCountOfPlayersCardsRequest;
//import request.GetGameboardRequest;
import request.GetPlayerCardsByIdRequest;
import request.GetPlayerCurrencyRequest;
import request.GetPlayerProfileRequest;
import request.GetProfilePictureRequest;
import request.GetUsernamesRequest;
import request.JoinGameAsSpectatorRequest;
import request.JoinUnstartedGameRequest;
import request.KickPlayerRequest;
import request.LeaveUnstartedGameRequest;
import request.LoginRequest;
import request.MakeMoveRequest;
import request.RegisterRequest;
import request.Request;
import request.SendChatRequest;
import request.SetPlayerCurrencyRequest;
import request.SetProfilePictureRequest;
import request.SetReadyRequest;
import request.TestGameRequest;
import response.AccountTypeGetResponse;
import response.CreateUnstartedGameResponse;
import response.GetCardIdByRarityAddToUserResponse;
import response.GetCardLibraryResponse;
import response.GetCountOfPlayersCardsResponse;
import response.GetPlayerCardsByIdResponse;
import response.GetPlayerCurrencyResponse;
import response.GetPlayerProfileResponse;
import response.GetProfilePictureResponse;
import response.GetUsernamesResponse;
import response.JoinGameAsSpectatorResponse;
import response.JoinUnstartedGameResponse;
import response.KickPlayerResponse;
import response.LoginResponse;
import response.MakeMoveResponse;
import response.NextTurnResponse;
import response.RegisterResponse;
import response.Response;
import response.SendChatResponse;
import response.SetReadyResponse;
import response.TestGameResponse;
import response.UserJoinedUnstartedGameResponse;
import response.UserLeftUnstartedGameResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientNetwork.
 */
public class ClientNetwork {
	
	/** The server connection. */
	private static Socket serverConnection;
	
	/** The Constant PORT. */
	private static final int PORT = 5555;

	//Push with localhost turned on

/** The Constant HOST. */
	private static final String HOST = "proj-309-rb-c-3.cs.iastate.edu";
	//private static final String HOST = "localhost";



	/** The received response queue. */
	private static ConcurrentLinkedQueue<Response> receivedResponseQueue = new ConcurrentLinkedQueue<Response>();
	
	/** The lobby. */
	public static MatchLobby lobby;
	
	/** The game. */
	public static GameUI game;
	
	/** The connected. */
	public static boolean connected = false;
	
	
	/**
	 * Connects to server and starts listening for data.
	 */
	public static void connect(){
		try{
			if(!connected) {
				serverConnection = new Socket(HOST, PORT);
				System.out.println("Connected to " + HOST);
				connected = true;
				listenToServer(serverConnection);
			}
		}
		catch(IOException e){
			System.out.println(e);
			System.out.println("Cannot connect to server...");
		}
	}
	
	/**
	 * Creates thread to listen for incoming data.
	 *
	 * @param serverConnection the server connection
	 */
	private static void listenToServer(Socket serverConnection) {
		Runnable messageListener = new Runnable(){
			public void run(){
				while(true) {
					int status = receive(serverConnection);
					if(status == -1){
						try {
						//	System.out.println("Disconnected from Server.");
						//	serverConnection.close();
						}
						catch(Exception e){
							e.printStackTrace();
						}
						return;
					}
				}
			}
		};
		
		new Thread(messageListener).start();
	}
	
	/**
	 * Process received data from server.
	 *
	 * @param serverConnection the server connection
	 * @return the int
	 */
	private static int receive(Socket serverConnection){


				ObjectInputStream oos;
				try{
					oos = new ObjectInputStream(serverConnection.getInputStream());
					Response response = (Response) oos.readObject();
					System.out.println("R: " + response.getId());

					if(response.getId().equals("userJoinedUnstartedGame")) {
						userJoinedUnstartedGame(response);
					}
					else if(response.getId().equals("userLeftUnstartedGame")) {
						userLeftUnstartedGame(response);
					}
					else if(response.getId().equals("sendChat")) {
						receiveSentChat(response);
					}
					else if(response.getId().equals("setReady")) {
						receiveSetReady(response);
					}
					else if(response.getId().equals("beginGame")) {
						Runnable receiveThread = new Runnable() {public void run() {beginGame();}};
						new Thread(receiveThread).start();
					}
					else if(response.getId().equals("nextTurn")) {
						receiveEndTurn(response);
					}
					else if(response.getId().equals("makeMove")) {
						receiveMakeMove(response);
					}
					else if(response.getId().equals("kickPlayer")) {
						kickFromLobby(response);
					}
					else {
						receivedResponseQueue.add(response);	
					}
				}
				catch(Exception e){

						
						e.printStackTrace();
						return -1;

				}		
		//inputScanner.close();
		return 1;
	}

	/**
	 * Kicks the user from their current game.
	 *
	 * @param response a KickPlayerResponse
	 */
	private static void kickFromLobby(Response response) {
		// TODO Auto-generated method stub
		KickPlayerResponse r = (KickPlayerResponse) response;
		lobby.leaveGame();
		
	}
	
	/**
	 * Receive make move.
	 *
	 * @param response the response
	 */
	private static void receiveMakeMove(Response response) {
		// TODO Auto-generated method stub
		MakeMoveResponse r = (MakeMoveResponse) response;
		//PlayerProfile p = game.getGame().getPlayerById(r.getUserId());
		if(!r.getUserId().equals(game.getPlayer().getUserID()))
			game.getGame().getMover().makeMove( game.getGame().getPlayerById(r.getUserId()), r.getMoveInfo());
		
		game.repaint();

		game.CheckForEnd();

		
	}

	/**
	 * Receive end turn.
	 *
	 * @param response the response
	 */
	private static void receiveEndTurn(Response response) {
		// TODO Auto-generated method stub
		NextTurnResponse r = (NextTurnResponse) response;
		game.ChangeTurn();
		
		
	}

	/**
	 * Begin game.
	 */
	private static void beginGame() {
		lobby.beginGame();
		
	}

	/**
	 * User left unstarted game.
	 *
	 * @param response the response
	 */
	private static void userLeftUnstartedGame(Response response) {
		UserLeftUnstartedGameResponse r = (UserLeftUnstartedGameResponse) response;
		if(!(r.getUserId().equals(TitleScreen.UserID))) {
			lobby.getGame().removePlayer(r.getUserId());
			lobby.checkOtherPlayerReadyness();
		}
		
	}

	/**
	 * Receive set ready.
	 *
	 * @param response the response
	 */
	private static void receiveSetReady(Response response) {
		SetReadyResponse r = (SetReadyResponse) response;
		//if(!(r.getUserId().equals(TitleScreen.UserID))) {
			lobby.getGame().setReady(r.getUserId());
			lobby.checkOtherPlayerReadyness();
		//}
	}

	/**
	 * Receive sent chat.
	 *
	 * @param response the response
	 */
	private static void receiveSentChat(Response response) {
		SendChatResponse r = (SendChatResponse) response;
		if(r.getLocation().equals("lobby"))
			lobby.chat.receiveMSG(r.getMessage());
		if(r.getLocation().equals("ingame") || r.getLocation().equals("spectator"))
			game.getChat().receiveMSG(r.getMessage());
		
	}

	/**
	 * User joined unstarted game.
	 *
	 * @param response the response
	 */
	private static void userJoinedUnstartedGame(Response response) {
		UserJoinedUnstartedGameResponse r = (UserJoinedUnstartedGameResponse) response;
		if(!(r.getP().getUserID().equals(TitleScreen.UserID))){
			lobby.getGame().addPlayer(r.getP());
			lobby.getGame().getReady().add(false);
			lobby.checkOtherPlayerReadyness();
		}
		
	}
	
	/**
	 * Sends data to server.
	 *
	 * @param r the r
	 */
	public static void send(Request r){
		try{
			OutputStream out = serverConnection.getOutputStream();
			ObjectOutputStream  oos= new ObjectOutputStream(out);
			System.out.println("S: " + r.getId());
			oos.writeObject(r);
			oos.flush();
		}
		catch(IOException e){
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println("Not Connected");
		}
	}
	
	/**
	 * Requests list of all online users from server.
	 *
	 * @return List of all online users usernames
	 */
	public static ArrayList<String> getUsernames(){
		send(new GetUsernamesRequest());
		GetUsernamesResponse r = (GetUsernamesResponse) waitForResponse("getUsernames");
		return r.getUsernames();

	}
	
	/**
	 * Wait for response.
	 *
	 * @param id the id
	 * @return the response
	 */
	private static Response waitForResponse(String id) {
		while(true) {
			if(receivedResponseQueue.peek() != null && receivedResponseQueue.peek().getId().equals(id)){
				return receivedResponseQueue.poll();
			}
		}
	}

	/**
	 * Attempts to create a new account on the server.
	 *
	 * @param username the username
	 * @param password the password
	 * @return "usernameFail" if username is not in the correct format
	 * "passwordFail" if password is not in correct format
	 * "takenFail" if username is already taken
	 * "success" if account was created successfully
	 */
	public static String createAccountOnServer(String username, char[] password) {
		
		send(new RegisterRequest(username, password));
		
		RegisterResponse r = (RegisterResponse) waitForResponse("register");
		return r.getResult();
		
	}
	
	/**
	 * Attempts to login to server.
	 *
	 * @param username the username
	 * @param password the password
	 * @return "nullError" if a bad error happens
	 * "invalidUsername" if username is invalid
	 * "invalidPassOrUsername" if either password or username is invalid
	 * 	If none of the above happen:
	 *  The user's unique ID
	 */
	public static LoginResponse loginToServer(String username, char[] password) {
		send(new LoginRequest(username, password));
		LoginResponse r = (LoginResponse) waitForResponse("login");
		return r;	
	}
	
	/**
	 * Gets the player profile.
	 *
	 * @param userID the user ID
	 * @return the player profile
	 */
	public static PlayerProfile getPlayerProfile(String userID) {
		PlayerProfile p = new PlayerProfile();
		
		send(new GetPlayerProfileRequest(userID));
		GetPlayerProfileResponse r = (GetPlayerProfileResponse) waitForResponse("getPlayerProfile");
		return r.getP();
	}
	

	/**
	 * Creates the unstarted game.
	 *
	 * @param userID the user ID
	 * @return the string
	 */
	public static String createUnstartedGame(String userID) {
		
		send(new CreateUnstartedGameRequest(userID));
		
		CreateUnstartedGameResponse r = (CreateUnstartedGameResponse) waitForResponse("createUnstartedGame");
		return r.getGameID();

	}
	
	/**
	 * Join unstarted game.
	 *
	 * @param gameID the game ID
	 * @param userID the user ID
	 * @return the join unstarted game response
	 */
	public static JoinUnstartedGameResponse joinUnstartedGame(String gameID, String userID) {
		
		send(new JoinUnstartedGameRequest(userID, gameID));
		
		JoinUnstartedGameResponse r = (JoinUnstartedGameResponse) waitForResponse("joinUnstartedGame");
		
		return r;
	}
	
	/**
	 * Leave unstarted game.
	 *
	 * @param gameID the game ID
	 * @param userID the user ID
	 */
	public static void leaveUnstartedGame(String gameID, String userID) {
		
		send(new LeaveUnstartedGameRequest(userID, gameID));
		
		//LeaveUnstartedGameResponse r = (LeaveUnstartedGameResponse) waitForResponse("joinUnstartedGame");
		
		//return r;
	}
	
	/**
	 * Gets the card library.
	 *
	 * @return the card library
	 */
	public static ArrayList<Card> getCardLibrary() {
		ArrayList<Card> cardLibrary = new ArrayList<Card>();
		
		send(new GetCardLibraryRequest());
		
		GetCardLibraryResponse r = (GetCardLibraryResponse) waitForResponse("getCardLibrary");
		return new ArrayList<Card>(r.getCardLibrary().values());
		
		
	}
	
	/**
	 * Gets the card library map.
	 *
	 * @author Jeremiah
	 * Returns hashmap of all cards in the game 
	 * @return Returns hashmap of all cards in the game with the key
	 * being the name of the card
	 */
    public static HashMap<String, Card> getCardLibraryMap(){
        HashMap<String, Card> cardLib = new HashMap<>();
        ArrayList<Card> cards = getCardLibrary();

        for(int i = 0;i<cards.size();i++) {
            cardLib.put(cards.get(i).getName(), cards.get(i));
        }

        return cardLib;

    }

	/**
	 * Parses the card library serialization.
	 *
	 * @param message the message
	 * @return the array list
	 */
	private static ArrayList<Card> parseCardLibrarySerialization(ArrayList<String> message) {
		ArrayList<Card> cardLibrary = new ArrayList<Card>();
		
		String cost;
		String imgFileName;
		String health;
		String description;
		String type;
		String speed;
		String dateCreated;
		String attack;
		String name;
		String modified;
		String id;
		String ability;
		String value;
		String rarity;
		
		for(int i = 0; i < message.size(); i += 14) {
			cost = message.get(i);
			imgFileName = message.get(i+1);
			health = message.get(i+2);
			description = message.get(i+3);
			type = message.get(i+4);
			speed = message.get(i+5);
			dateCreated = message.get(i+6);
			attack = message.get(i+7);
			name = message.get(i+8);
			modified = message.get(i+9);
			id = message.get(i+10);
			ability = message.get(i+11);
			value = message.get(i+12);
			rarity = message.get(i+13);
			
			cardLibrary.add(new Card(name, id, imgFileName, description, rarity, dateCreated,
					modified, ability, value, type, 0, 0, 0, 0, 0, null));
			
		}
		return cardLibrary;
	}
	
	/**
	 * Gets the player currency.
	 *
	 * @param userID the user ID
	 * @return the player currency
	 */
	public static int getPlayerCurrency(String userID) {
		
		int playerCurrency;
		
		send(new GetPlayerCurrencyRequest(userID));
		
		GetPlayerCurrencyResponse r = (GetPlayerCurrencyResponse) waitForResponse("getPlayerCurrency");
		return r.getCurrency();
		
	}
	
	/**
	 * Sets the player currency.
	 *
	 * @param userID the user ID
	 * @param amount the amount
	 */
	public static void setPlayerCurrency(String userID, int amount) {
		send(new SetPlayerCurrencyRequest(userID, amount));
	}
	
	/**
	 * Gets the card ID by rarity add to user.
	 *
	 * @param userID the user ID
	 * @param type the type
	 * @return the card ID by rarity add to user
	 */
	public static String getCardIDByRarityAddToUser(String userID, String type) {
		send(new GetCardIdByRarityAddToUserRequest(userID, type));
		
		GetCardIdByRarityAddToUserResponse r = (GetCardIdByRarityAddToUserResponse) waitForResponse("getCardIdByRarityAddToUser");
		return r.getCardId();
		
	}
	
	/**
	 * Sets the profile picture.
	 *
	 * @param userID the user ID
	 * @param file the file
	 */
	public static void setProfilePicture(String userID, byte[] file) {
		send(new SetProfilePictureRequest(userID, file));
	}
	
	/**
	 * Gets the profile picture.
	 *
	 * @param userID the user ID
	 * @return the profile picture
	 */
	public static byte[] getProfilePicture(String userID) {
		send(new GetProfilePictureRequest(userID));
		GetProfilePictureResponse r = (GetProfilePictureResponse) waitForResponse("getProfilePicture");
		return r.getImage();
	}
	
	/**
	 * Gets the count of players cards.
	 *
	 * @param UserID the user ID
	 * @return the count of players cards
	 */
	/*
	 * @author Harry Mitchell
	 * returns the number of cards owned by a user
	 */
	public static int getCountOfPlayersCards(String UserID) {
		send(new GetCountOfPlayersCardsRequest(UserID));
		
		GetCountOfPlayersCardsResponse r = (GetCountOfPlayersCardsResponse) waitForResponse("getCountOfPlayersCards");
		return r.getCount();
	}
	
	/**
	 * Gets the player cards and amounts.
	 *
	 * @param UserID the user ID
	 * @return the player cards and amounts
	 */
	/*
	 * @author Harry Mitchell
	 * returns all of the cards owned by a user
	 */
	public static ArrayList<String> getPlayerCardsAndAmounts(String UserID) {
		send(new GetPlayerCardsByIdRequest(UserID));
		GetPlayerCardsByIdResponse r = (GetPlayerCardsByIdResponse) waitForResponse("getPlayerCardsById");
		
		return r.getCardAmounts();
	}
	
	/**
	 * Gets the players deck names.
	 *
	 * @param UserID the user ID
	 * @return the players deck names
	 */
	/*
	 * @author Harry Mitchell
	 * returns the names of the decks owned by a user
	 */
	public ArrayList<String> getPlayersDeckNames(String UserID){
		//send("getPlayersDeckNames"+"\0"+UserID);
		
		return null;
	}
	
	/**
	 * Send chat message.
	 *
	 * @param gameId the game id
	 * @param location the location
	 * @param username the username
	 * @param message the message
	 */
	public static void sendChatMessage(String gameId, String location, String username, String message) {
		send(new SendChatRequest(gameId, location,  username, message));
		
	}
	
	/**
	 * Sets the ready.
	 *
	 * @param gameId the game id
	 * @param userId the user id
	 */
	public static void setReady(String gameId, String userId) {
		send(new SetReadyRequest(gameId, userId));
	}
	
	/**
	 * Adds the deck to player.
	 *
	 * @author Jeremiah
	 * @param deck to be added to player's id in database
	 * Adds deck to database for player
	 */
	public static void addDeckToPlayer(Deck deck) {
		send(new AddPlayerDeckRequest(deck));
	}
	
	/**
	 * End turn.
	 *
	 * @param userId the user id
	 * @param gameId the game id
	 */
	public static void endTurn(String userId, String gameId) {
		send(new EndTurnRequest(userId, gameId));
	}
	
	/**
	 * Make move.
	 *
	 * @param userID the user ID
	 * @param gameID the game ID
	 * @param m the m
	 */
	public static void makeMove(String userID, String gameID, MoveInfo m) {
		send(new MakeMoveRequest(userID, gameID, m));
	}

	/**
	 * Gets the test game.
	 *
	 * @param testGameRequest the test game request
	 * @return the test game
	 */
	public static TestGameResponse getTestGame(TestGameRequest testGameRequest) {
		// TODO Auto-generated method stub
		send(testGameRequest);
		TestGameResponse r = (TestGameResponse) waitForResponse("testGame");
		return r;
	}
	
	/**
	 * Sets the account type.
	 *
	 * @param userID the user ID
	 * @param adminStatus the admin status
	 */
	public static void setAccountType(String userID,int adminStatus) {
		send(new AccountTypeSetRequest(userID, adminStatus));
	}
	
	/**
	 * Gets the account type.
	 *
	 * @param userID the user ID
	 * @return the account type
	 */
	public static int getAccountType(String userID) {
		send(new AccountTypeGetRequest(userID));
		AccountTypeGetResponse r = (AccountTypeGetResponse) waitForResponse("accountTypeGetResponse");
		return r.getAccountType();
	}
	
	/**
	 * Join game as spectator.
	 *
	 * @param userId the user id
	 * @param gameId the game id
	 * @return the game
	 */
	public static Game joinGameAsSpectator(String userId, String gameId){
		
		send(new JoinGameAsSpectatorRequest(userId, gameId));
		JoinGameAsSpectatorResponse r = (JoinGameAsSpectatorResponse) waitForResponse("joinGameAsSpectator");
		
		return r.getGame();
		
	}
	
	/**
	 * Kick player from lobby.
	 *
	 * @param userID the user ID
	 * @param gameID the game ID
	 */
	public static void KickPlayerFromLobby(String userID, String gameID) {
		send(new KickPlayerRequest(userID, gameID));
	}
	
	/**
	 * Eliminate player.
	 *
	 * @param userID the user ID
	 * @param gameID the game ID
	 */
	public static void EliminatePlayer(String userID, String gameID) {
		send(new EliminatePlayerRequest(userID, gameID));
	}
}