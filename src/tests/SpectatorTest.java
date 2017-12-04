package tests;

import client.ClientNetwork;
import client.ui.GameUI;
import client.ui.MainWindow;
import client.ui.SpecInGame;
import client.ui.TitleScreen;
import game.Game;
import game.client.UnstartedGame;
import player.PlayerProfile;
import request.AccountTypeSetRequest;
import request.TestGameRequest;
import response.LoginResponse;
import response.TestGameResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class SpectatorTest.
 */
public class SpectatorTest {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		ClientNetwork.connect();
		new MainWindow();
		LoginResponse r = ClientNetwork.loginToServer("spec", "pass".toCharArray());

		PlayerProfile p = r.getP();
		

		TitleScreen.UserProfile = p;
		TitleScreen.UserID = p.getUserID();
		
		Game g = ClientNetwork.joinGameAsSpectator(p.getUserID(), "0000");
		new SpecInGame(p,g.getPlayers(),g);
		
		//InGame.main(args,p, g.getPlayers(), g, p.getDecks().get(0));
	}

}
