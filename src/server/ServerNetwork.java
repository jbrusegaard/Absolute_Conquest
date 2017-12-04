package server;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import db.Database_Utils;
import game.Game;
import game.server.ServerGame;
import game.server.ServerUnstartedGame;
import player.Card;
import player.Deck;
import player.PlayerProfile;
import request.*;
import response.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerNetwork.
 */
public class ServerNetwork {
	
	/** The temp connections. */
	private static ArrayList<Socket> tempConnections = new ArrayList<Socket>(); //Holds Socket before user logs in
	
	/** The online players. */
	public static HashMap<String, PlayerProfile> onlinePlayers = new HashMap<String, PlayerProfile>();	//Holds logged in player profiles
	
	/** The current games. */
	public static HashMap<String, ServerGame> currentGames = new HashMap<String, ServerGame>(); //Holds games currently being played
	
	/** The unstarted games. */
	public static HashMap<String, ServerUnstartedGame> unstartedGames = new HashMap<String, ServerUnstartedGame>(); //Holds unstarted games
	
	/** The card map. */
	public static HashMap<String, Card> cardMap = new HashMap<String, Card>();
	
	/** The database. */
	private static Database_Utils database;
	
	/** The server. */
	private static ServerSocket server;
	
	/** The port. */
	private final int PORT = 5555;
	
	/** The test profiles. */
	private static ArrayList<PlayerProfile> testProfiles = new ArrayList<PlayerProfile>(4);
	
	/**
	 * Instantiates a new server network.
	 */
	public ServerNetwork(){
		
	}
	
	/**
	 * Starts server and creates thread for new connections,
	 * each connection will create another thread that listens for incoming messages.
	 */
	public void open(){
		
		try{
			server = new ServerSocket(PORT);
			System.out.println("Starting Server...");
			database = new Database_Utils();
			cardMap = getCardMap();
			listenForConnections();
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	/**
	 * Create thread for listening for new connections.
	 */
	private void listenForConnections(){
		Runnable connectionListener = new Runnable(){
			public void run(){
				while(true){
					try{
						Socket newConnection = server.accept();
						tempConnections.add(newConnection);
						listenForData(newConnection);
						writeLog(newConnection.toString() + " connected");
					}
					catch(IOException e){
						writeLog("Cannot connect to client.");
					}
				}
			}
		};
		
		new Thread(connectionListener).start();
	}
	
	/**
	 * Create thread to listen for data coming from a connection.
	 *
	 * @param newConnection the new connection
	 */
	private void listenForData(Socket newConnection){
		//Create thread to listen for incoming messages, remove connection if disconnected
		Runnable messageListener = new Runnable(){
			public void run(){
				while(true){
					int status = receive(newConnection);
					if(status == -1){
						try {
							writeLog(newConnection.toString() + " disconnected");
							newConnection.close();
							remove(newConnection);
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
	 * Removes player based on their Socket.
	 *
	 * @param connection the connection
	 */
	private void remove(Socket connection) {
		Iterator i = onlinePlayers.values().iterator();
		String userId = "";
		while(i.hasNext()) {
			PlayerProfile p = (PlayerProfile) i.next();
			if(p.getClientConnection().equals(connection)) {
				userId = p.getUserID();
				break;
			}
		}
		onlinePlayers.remove(userId);
		return;
		
	}
	
	/**
	 * Process data coming from clients.
	 *
	 * @param connection the connection
	 * @return -1 if client is disconnected, 1 if connected
	 */
	public int receive(Socket connection){
		ObjectInputStream ois; 
		try{
			ois = new ObjectInputStream(connection.getInputStream());
			Request input =(Request) ois.readObject();
			writeLog("R: " + input.getId());
			proccessRecievedData(connection, input);
	
		}catch (SocketException e) {
			try {
				connection.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return -1;
		}
		catch(Exception e){
			try {
				connection.close();
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return -1;
		}
		//inputScanner.close();
		return 1;
	}
	
	/**
	 * Process file data.
	 *
	 * @param connection the connection
	 * @param input the input
	 */
	private void processFileData(Socket connection, String input) {
		//writeLog(input);
		//System.out.println(input.substring(0,21));
		
		byte[] data;
		if(input.substring(0,21).equals("filesetProfilePicture")){
			try {
				InputStream in = connection.getInputStream();
				DataInputStream dis = new DataInputStream(in);
			    int len = dis.readInt();
			    data = new byte[len];
			    if (len > 0) {
			        dis.readFully(data);
			        database.storePlayerAvatar(data, input.substring(21, 37));
			        
			    }
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			//System.out.println(input.substring(21, 37));

		}
		
	}

	/**
	 * Takes in received data and sends it to the appropriate function.
	 *
	 * @param connection the connection
	 * @param input the input
	 */
	private synchronized void proccessRecievedData(Socket connection, Object input) {
		//String messageID = inputTokens.get(0);
		//inputTokens.remove(0);
		
		Request request = (Request)input;
		String requestId = request.getId();
		
		if(requestId.equals("register")) {
			createNewAccount(connection, request);
		}
		if(requestId.equals("login")) {
			login(connection, request);
		}
		if(requestId.equals("getPlayerProfile")) {
			getPlayerProfile(connection, request);
		}
		if(requestId.equals("getUsernames")) {
			sendClientUsernames(connection, request);
		}
		if(requestId.equals("getCardLibrary")) {
			sendCardLibrary(connection);
		}
		if(requestId.equals("getPlayerCurrency")) {
			sendPlayerCurrency(connection, request);
		}
		if(requestId.equals("setPlayerCurrency")) {
			setPlayerCurrency(connection, request);
		}
		if(requestId.equals("createUnstartedGame")) {
			createUnstartedGame(connection, request);
		}
		if(requestId.equals("joinUnstartedGame")) {
			joinUnstartedGame(connection, request);
		}
		if(requestId.equals("leaveUnstartedGame")) {
			leaveUnstartedGame(connection, request);
		}
		if(requestId.equals("getCardIdByRarityAddToUser")) {
			getCardIdByRarityAddToUser(connection, request);
		}
		if(requestId.equals("getProfilePicture")){
			getProfilePicture(connection, request);
		}
		if(requestId.equals("getCountOfPlayersCards")){
			getCountOfPlayersCards(connection, request);
		}
		if(requestId.equals("setProfilePicture")){
			setProfilePicture(connection, request);
		}
		if(requestId.equals("getPlayerCardsById")){
			getPlayerCardsById(connection, request);
		}
		if(requestId.equals("sendChat")){
			sendChatRequest(connection, request);
		}
		if(requestId.equals("setReady")){
			setReady(connection, request);
		}
		if(requestId.equals("addPlayerDeck")) {
			addPlayerDeck(connection, request);
		}
		if(requestId.equals("endTurn")) {
			endTurn(connection, request);
		}
		if(requestId.equals("makeMove")) {
			makeMove(connection, request);
		}
		if(requestId.equals("testGame")) {
			testGame(connection, request);
		}
		if(requestId.equals("setAdminStatus")) {
			setAdminStatus(connection,request);
		}
		if(requestId.equals("getAccountType")) {
			getAccountType(connection,request);
		}
		if(requestId.equals("joinGameAsSpectator")) {
			addSpectator(connection,request);
		}
		if(requestId.equals("kickPlayer")) {
			kickPlayerFromLobby(connection,request);
		}
		if(requestId.equals("eliminatePlayer")) {
			eliminatePlayer(connection,request);
		}
	}

	/**
	 * Adds the spectator.
	 *
	 * @param connection the connection
	 * @param request the request
	 */
	private void addSpectator(Socket connection, Request request) {
		JoinGameAsSpectatorRequest rq = (JoinGameAsSpectatorRequest) request;
		ServerGame game = currentGames.get(rq.getGameID());
		if(game==null) {
			send(connection, new JoinGameAsSpectatorResponse(null));
		}else {
			game.addSpectator(rq.getUserId());
			send(connection, new JoinGameAsSpectatorResponse(game.getGame()));
		}
	}

	/**
	 * Test game.
	 *
	 * @param connection the connection
	 * @param request the request
	 */
	private void testGame(Socket connection, Request request) {
		// TODO Auto-generated method stub
		TestGameRequest rq = (TestGameRequest) request;
		TestGameResponse r = new TestGameResponse();
		PlayerProfile lastPlayer;
		
		if(testProfiles.size() ==  0) {
			PlayerProfile p1 =  fetchPlayerProfile(connection, "P1");
			testProfiles.add(p1);
			r.getPlayers().add(p1);
			PlayerProfile p2 =  fetchPlayerProfile(connection, "P2");
			p2.setClientConnection(null);
			testProfiles.add(p2);
			r.getPlayers().add(p2);
			PlayerProfile p3 =  fetchPlayerProfile(connection, "P3");
			p3.setClientConnection(null);
			testProfiles.add(p3);
			r.getPlayers().add(p3);	 
			PlayerProfile p4 =  fetchPlayerProfile(connection, "P4");
			p4.setClientConnection(null);
			testProfiles.add(p4);
			r.getPlayers().add(p4);
		}
		else {
			PlayerProfile p1 =  testProfiles.get(0);
			r.getPlayers().add(p1);
			PlayerProfile p2 =  testProfiles.get(1);
			r.getPlayers().add(p2);
			PlayerProfile p3 =  testProfiles.get(2);
			r.getPlayers().add(p3);	 
			PlayerProfile p4 =  testProfiles.get(3);
			r.getPlayers().add(p4);
		}
		
		if(rq.getPlayer().equals("p1")) {
			testProfiles.get(0).setClientConnection(connection);
			testProfiles.get(1).setClientConnection(null);
			testProfiles.get(2).setClientConnection(null);
			testProfiles.get(3).setClientConnection(null);
		}
		if(rq.getPlayer().equals("p2")) {
			testProfiles.get(1).setClientConnection(connection);
		}
		if(rq.getPlayer().equals("p3")) {
			testProfiles.get(2).setClientConnection(connection);
		}
		if(rq.getPlayer().equals("p4")) {
			testProfiles.get(3).setClientConnection(connection);
		}
		
		r.setGameId("0000");
		
		if(!currentGames.containsKey("0000")) {
			currentGames.put(r.getGameId(), new ServerGame(r.getPlayers(), r.getGameId()));
		}
		
		send(connection, r);

	}

	/**
	 * Make move.
	 *
	 * @param connection the connection
	 * @param request the request
	 */
	private void makeMove(Socket connection, Request request) {
		// TODO Auto-generated method stub
		MakeMoveRequest r = (MakeMoveRequest) request;
		ServerGame game = currentGames.get(r.getGameId());
		game.makeMove(r.getMoveInfo(), game.getPlayerById(r.getUserId()));
		game.broadcast(new MakeMoveResponse(r.getUserId(), r.getMoveInfo()));
		game.broadcastSpectators(new MakeMoveResponse(r.getUserId(), r.getMoveInfo()));
		
	}

	/**
	 * End turn.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	private void endTurn(Socket connection, Request r) {
		EndTurnRequest request = (EndTurnRequest) r;
		ServerGame game = currentGames.get(request.getGameId());
		game.getGame().resetAllUnits();
		game.broadcast(new NextTurnResponse(request.getUserId()));
		game.broadcastSpectators(new NextTurnResponse(request.getUserId()));
		
	}

	/**
	 * Leave unstarted game.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	private void leaveUnstartedGame(Socket connection, Request r) {
		LeaveUnstartedGameRequest request = (LeaveUnstartedGameRequest) r;
		ServerUnstartedGame game = unstartedGames.get(request.getGameID());
		if(game == null) {
			return;
		}
		game.removePlayer(request.getUserId());
		game.broadcast(new UserLeftUnstartedGameResponse(request.getUserId()));
	}

	/**
	 * Sets the ready.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	private void setReady(Socket connection, Request r) {
		SetReadyRequest request = (SetReadyRequest) r;
		ServerUnstartedGame game = unstartedGames.get(request.getGameId());
		game.setReady(request.getUserId());
		game.broadcast(new SetReadyResponse(request.getGameId(), request.getUserId()));
	}

	/**
	 * Send chat request.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	private void sendChatRequest(Socket connection, Request r) {
		SendChatRequest request = (SendChatRequest) r;
		if(unstartedGames.containsKey(request.getGameId()))
			unstartedGames.get(request.getGameId()).broadcast(new SendChatResponse(request.getLocation(), request.getUsername(), request.getMessage()));
		else if(currentGames.containsKey(request.getGameId())) {
			if(request.getLocation().equals("ingame"))
				currentGames.get(request.getGameId()).broadcast(new SendChatResponse(request.getLocation(), request.getUsername(), request.getMessage()));
			else if(request.getLocation().equals("spectator"))
					currentGames.get(request.getGameId()).broadcastSpectators(new SendChatResponse(request.getLocation(), request.getUsername(), request.getMessage()));
					
		}
	}

	/**
	 * Gets the player cards by id.
	 *
	 * @param connection the connection
	 * @param r the r
	 * @return the player cards by id
	 */
	private void getPlayerCardsById(Socket connection, Request r) {
		GetPlayerCardsByIdRequest request = (GetPlayerCardsByIdRequest) r;
		send(connection, new GetPlayerCardsByIdResponse(database.getCardNumbersForUser(request.getUserId())));
		
	}

	/**
	 * Join unstarted game.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	private void joinUnstartedGame(Socket connection, Request r) {
		JoinUnstartedGameRequest request = (JoinUnstartedGameRequest) r;
		ServerUnstartedGame game = unstartedGames.get(request.getGameID());
		if(game == null) {
			send(connection, new JoinUnstartedGameResponse("noSuchGame"));
			return;
		}
		int result = game.addPlayer(getProfileFromOnlineUsers(request.getUserId()));
		if(result == -1) {
			send(connection, new JoinUnstartedGameResponse("gameFull"));
		}else {
			send(connection, new JoinUnstartedGameResponse(game.getGameID(), game.getPlayers(), game.getReadyList()));
			game.broadcast(new UserJoinedUnstartedGameResponse(getProfileFromOnlineUsers(request.getUserId())));
		}
	}

	/**
	 * Sets the profile picture.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	private void setProfilePicture(Socket connection, Request r) {
		SetProfilePictureRequest request = (SetProfilePictureRequest) r;
		database.storePlayerAvatar(request.getAvatar(), request.getUserId());
		
	}

	/**
	 * Sets the player currency.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	private void setPlayerCurrency(Socket connection, Request r) {
		SetPlayerCurrencyRequest request = (SetPlayerCurrencyRequest) r;
		database.updatePlayerCurrency(request.getUserId(),Integer.toString(request.getCurrency()));
	}

	/**
	 * Gets the count of players cards.
	 *
	 * @param connection the connection
	 * @param r the r
	 * @return the count of players cards
	 */
	private void getCountOfPlayersCards(Socket connection, Request r) {
		GetCountOfPlayersCardsRequest request = (GetCountOfPlayersCardsRequest) r;
		String count = database.getCountOfPlayersCards(request.getUserId());
		
		send(connection, new GetCountOfPlayersCardsResponse(Integer.parseInt(count)));
	}

	/**
	 * Gets the player profile.
	 *
	 * @param connection the connection
	 * @param r the r
	 * @return the player profile
	 */
	private void getPlayerProfile(Socket connection, Request r) {
		GetPlayerProfileRequest request = (GetPlayerProfileRequest) r;
		
		
		PlayerProfile p;
/*		if(onlinePlayers.containsKey(request.getUserId())) {
			p = onlinePlayers.get(request.getUserId());
		}
		else {*/
			String username = database.getUnameByID(request.getUserId());
			 p =  fetchPlayerProfile(connection, username);
	//	}

		p.setClientConnection(null);
		send(connection, new GetPlayerProfileResponse(p));
		p.setClientConnection(connection);
		//System.out.print(new String(file));
/*		p.setClientConnection(null);
		try {
		    OutputStream out = connection.getOutputStream(); 
		    ObjectOutputStream oos = new ObjectOutputStream(out);

		    oos.writeObject(p);

		}
		catch(IOException e) {
				e.printStackTrace();
		}
		finally {
			p.setClientConnection(connection);
		}*/
		
	}
	
	/**
	 * Adds the player deck.
	 *
	 * @author Jeremiah
	 * @param connection the connection
	 * @param r Method adds deck to database that player created
	 */
	private void addPlayerDeck(Socket connection, Request r) {
		AddPlayerDeckRequest request = (AddPlayerDeckRequest) r;
		database.addDeckToDB(request.getDeck());
	}

	/**
	 * Send data to a client.
	 *
	 * @param clientConnection Connection to send to
	 * @param r the r
	 */
	public static void  send(Socket clientConnection, Response r){
		try{
			OutputStream out = clientConnection.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			writeLog("S: " + r.getId());
			oos.writeObject(r);
			out.flush();
		}
		catch(IOException e){
			e.printStackTrace();;
		}
	}
	
	/**
	 * Send data to all connected clients.
	 *
	 * @param r the r
	 */
	public void broadcast(Response r){
		
		for(PlayerProfile p : onlinePlayers.values()){
			send(p.getClientConnection(), r);
		}
	}
	
	/**
	 * Contancts sql server to create new user account.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	private void createNewAccount(Socket connection, Request r){
		RegisterRequest request = (RegisterRequest) r;
		String username = request.getUsername();
		String password = new String(request.getPassword());
		//System.out.print(username);
		//System.out.print(request.getId());
		String accountCreationResult = "";
		
		//Check if username is ok
		for(int i = 0; i < username.length(); i++) {
			if(Character.isLetterOrDigit(username.charAt(i))|| username.charAt(i) == '_') {
				continue;
			}
			else {
				accountCreationResult = "usernameFail";
				send(connection, new RegisterResponse("usernameFail"));
				return;
			}
		}
		//Check if password is ok
		for(int i = 0; i < password.length(); i++) {
			if(Character.isLetterOrDigit(password.charAt(i))) {
				continue;
			}
			else {
				accountCreationResult = "passwordFail";
				send(connection, new RegisterResponse("passwordFail"));
				return;
			}
		}

		accountCreationResult = database.register(username, password);
		 if(accountCreationResult.equals("USERNAME ALREADY TAKEN")) {
			send(connection, new RegisterResponse("takenFail"));
			
		}else if(accountCreationResult != null) {
			send(connection, new RegisterResponse(accountCreationResult));
		}

	}
	
	/**
	 * Checks with database to make sure user is valid.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	private void login(Socket connection, Request r) {
		LoginRequest request = (LoginRequest) r;
		String username = request.getUsername();
		char[] password = request.getPassword();
		//System.out.print(username);
		//System.out.print(request.getId());
		
		String loginResult;
		loginResult = database.login(username, new String(password));
		//System.out.print(loginResult);
		if(loginResult == null) {
			send(connection, new LoginResponse(null, "nullError"));
		}
		else if(loginResult.equals("INVALID USERNAME")) {
			send(connection, new LoginResponse(null, "invalidUsername"));
		}
		else if(loginResult.equals("INVALID PASSWORD OR USERNAME")) {
			send(connection, new LoginResponse(null, "invalidPassOrUsername"));
		}
		else {
			PlayerProfile p = fetchPlayerProfile(null, request.getUsername());
			send(connection, new LoginResponse(p, loginResult));
			p.setClientConnection(connection);
			onlinePlayers.put(p.getUserID(), p);
		}
	}
	
	/**
	 * Sends an error message to client.
	 *
	 * @param connection the connection
	 * @param message the message
	 */
	public void sendError(Socket connection, String message){
		//send(connection, "error" + '\0' + "\"" + message + "\"");
	}
	
	/**
	 * Fetch player profile.
	 *
	 * @param connection the connection
	 * @param username the username
	 * @return the player profile
	 */
	private static PlayerProfile fetchPlayerProfile(Socket connection, String username) {
		PlayerProfile p = new PlayerProfile();
		p.setUsername(username);
		p.setUserID(database.getIDByUname(username));
		p.setClientConnection(connection);
		p.setCurrency(Integer.parseInt(database.getPlayerCurrency(p.getUserID())));
		p.setAccountType(database.accountType(p.getUserID()));
		HashMap<String, Card> cards = getCardMap();
		ArrayList<String> deckIDs = database.getPlayersDeckIDs(p.getUserID());
		ArrayList<Deck> decks = new ArrayList<Deck>();
		for(int i = 0; i < deckIDs.size(); i++){
			Deck d = new Deck();
			d.setDeckID(deckIDs.get(i));
			d.setDeckName(database.getDeckName(deckIDs.get(i)));
			ArrayList<String> cardNames = database.getCardsInDeck(d.getDeckID());
			
			for(int j = 0; j < cardNames.size(); j++) {
				d.addCard(cards.get(cardNames.get(j)));
			}
			decks.add(d);
			
		}
		p.setDecks(decks);
		ArrayList<String> cardsAndNumbers = database.getCardNumbersForUser(p.getUserID());
		
		ArrayList<Card> cardCollection = new ArrayList<Card>();
		for(int i = 0; i < cardsAndNumbers.size(); i += 2) {
			String cardName = cardsAndNumbers.get(i);
			int cardAmount = Integer.parseInt(cardsAndNumbers.get(i + 1));
			Card cardToAdd = cards.get(cardName);
			for(int j = 0; j < cardAmount; j++) {
				cardCollection.add(cardToAdd);
			}
		}
		p.setCardCollection(cardCollection);
		p.setAccountType(database.accountType(p.getUserID()));	
		return p;		
	}
	
	/**
	 * Gets the card map.
	 *
	 * @return the card map
	 */
	public static HashMap<String, Card> getCardMap(){
		if(!cardMap.isEmpty()) {
			return cardMap;
		}
		HashMap<String, Card> map = new HashMap<String, Card>();
		
		ArrayList<HashMap<String, String>> cards = database.getCards();
		for(HashMap<String, String> cardMap : cards) {
			Card c = new Card(
					cardMap.get("name"),
					cardMap.get("id"),
					cardMap.get("imgfilename"),
					cardMap.get("description"),
					cardMap.get("rarity"),
					cardMap.get("dateCreated"),
					cardMap.get("modified"),
					cardMap.get("ability"),
					cardMap.get("value"),
					cardMap.get("type"),
					Integer.parseInt(cardMap.get("attack")),
					Integer.parseInt(cardMap.get("speed")),
					Integer.parseInt(cardMap.get("health")),
					Integer.parseInt(cardMap.get("cost")),
					Integer.parseInt(cardMap.get("rnge")),
					null
					);
			map.put(c.getName(), c);
		}
		return map;
	}
	
	
	/**
	 * Send client usernames.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	private void sendClientUsernames(Socket connection, Request r) {
		ArrayList<String> usernames = new ArrayList<String>();
		
		for(int i = 0; i < onlinePlayers.size(); i++) {
			PlayerProfile p = onlinePlayers.get(i);
			usernames.add(p.getUsername());
			
		}
		
		send(connection,new GetUsernamesResponse(usernames));
		
	}
	
	/**
	 * Write log.
	 *
	 * @param log the log
	 */
	public static void writeLog(String log) {
		
		String fileName = "log.txt";
/*		DateFormat dateFormat = new SimpleDateFormat("[MM/dd/yyyy HH:mm:ss] ");
		Date date = new Date();
		//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
*/		
		try {
			FileWriter f = new FileWriter(fileName, true);
			BufferedWriter b = new BufferedWriter(f);
			b.write("[" + database.getSQLDate() + "] " + log + System.lineSeparator());
			System.out.println("[" + database.getSQLDate() + "] " + log);
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Send card library.
	 *
	 * @param connection the connection
	 */
	public void  sendCardLibrary(Socket connection) {
		
		send(connection, new GetCardLibraryResponse(getCardMap()));
	}
	
	/**
	 * Send player currency.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	public void sendPlayerCurrency(Socket connection, Request r) {
		GetPlayerCurrencyRequest request = (GetPlayerCurrencyRequest) r;
		send(connection, new GetPlayerCurrencyResponse(Integer.parseInt(database.getPlayerCurrency(request.getUserId()))));
	}
	
	/**
	 * Creates the unstarted game.
	 *
	 * @param connection the connection
	 * @param r the r
	 */
	public void createUnstartedGame( Socket connection, Request r) {
		CreateUnstartedGameRequest request = (CreateUnstartedGameRequest) r;
		ServerUnstartedGame newGame = new ServerUnstartedGame(getProfileFromOnlineUsers(request.getUserID()));
		unstartedGames.put(newGame.getGameID(), newGame);
		send(connection, new CreateUnstartedGameResponse(newGame.getGameID()));
	}
	
	/**
	 * Gets the card id by rarity add to user.
	 *
	 * @param connection the connection
	 * @param r the r
	 * @return the card id by rarity add to user
	 */
	private void getCardIdByRarityAddToUser(Socket connection, Request r) {
		GetCardIdByRarityAddToUserRequest request = (GetCardIdByRarityAddToUserRequest) r;
		String cardName = database.getCardIDByRarityAddToUser(request.getUserId(), request.getType());
		if(cardName.equals("")) {
			cardName = "<InsertCardNameHere>";
		}
		send(connection, new GetCardIdByRarityAddToUserResponse(cardName));
		
	}
	
	/**
	 * Gets the profile picture.
	 *
	 * @param connection the connection
	 * @param r the r
	 * @return the profile picture
	 */
	private void getProfilePicture(Socket connection, Request r) {
		GetProfilePictureRequest request = (GetProfilePictureRequest) r;
		byte[] file =  database.getAvatar(request.getUserId());
		send(connection, new GetProfilePictureResponse(file));

		//System.out.print(new String(file));
/*		try {
		    OutputStream out = connection.getOutputStream(); 
		    DataOutputStream dos = new DataOutputStream(out);

		    dos.writeInt(file.length);
		    if (file.length > 0) {
		        dos.write(file, 0, file.length);
		        dos.flush();
		    }
		}
		catch(IOException e) {
				e.printStackTrace();
		}
		
		System.out.println("Done");*/
	}
	
	/**
	 * Gets the profile from online users.
	 *
	 * @param userID the user ID
	 * @return the profile from online users
	 */
	public static PlayerProfile getProfileFromOnlineUsers(String userID) {
		return onlinePlayers.get(userID);
	}
	
	/**
	 * Sets the admin status.
	 *
	 * @param connection the connection
	 * @param request the request
	 */
	public void setAdminStatus(Socket connection, Request request) {
		AccountTypeSetRequest r = (AccountTypeSetRequest) request;
		database.setAccountType(r.getUserID(), r.getAccountType());
	}
	
	/**
	 * Gets the account type.
	 *
	 * @param connection the connection
	 * @param request the request
	 */
	public void getAccountType(Socket connection, Request request) {
		AccountTypeGetRequest r = (AccountTypeGetRequest) request;
		int accountType = database.accountType(r.getUserID());
		send(connection,new AccountTypeGetResponse(r.getUserID(), accountType));
	}
	
	/**
	 * Kick player from lobby.
	 *
	 * @param connection the connection
	 * @param request the request
	 */
	public void kickPlayerFromLobby(Socket connection, Request request) {
		KickPlayerRequest rq = (KickPlayerRequest) request;
		ServerUnstartedGame game = unstartedGames.get(rq.getGameID());
		//game.broadcast(new UserLeftUnstartedGameResponse(rq.getUserID()));
		PlayerProfile kickedPlayer = game.getPlayerById(rq.getUserID());
		send(kickedPlayer.getClientConnection(), new KickPlayerResponse(rq.getUserID()));
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ServerNetwork s = new ServerNetwork();
		s.open();
		System.out.println(fetchPlayerProfile(null,"dog"));
	}
	
	/**
	 * Eliminate player.
	 *
	 * @param connection the connection
	 * @param request the request
	 */
	public void eliminatePlayer(Socket connection, Request request) {
		EliminatePlayerRequest r = (EliminatePlayerRequest) request;
		ServerGame g = currentGames.get(r.getGameID());
		g.getGame().eliminate(r.getUserID());
		g.broadcast(new EliminatePlayerResponse(r.getUserID()));
		g.broadcastSpectators(new EliminatePlayerResponse(r.getUserID()));
	}
	
}
