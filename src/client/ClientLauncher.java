package client;

import java.util.ArrayList;

import client.ui.MainWindow;
import client.ui.TitleScreen;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientLauncher.
 */
public class ClientLauncher {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		ClientNetwork.connect();
		new MainWindow();
		//new TitleScreen();
/*		System.out.println(ClientNetwork.createAccountOnServer("Jaboby2", "password".toCharArray()));
		System.out.println(ClientNetwork.loginToServer("Jaboby2", "password".toCharArray()));
		System.out.println(ClientNetwork.createAccountOnServer("Jaboby", "password".toCharArray()));
		System.out.println(ClientNetwork.loginToServer("Jaboby", "password".toCharArray()));
		System.out.println(ClientNetwork.createAccountOnServer("Jabob", "password".toCharArray()));
		System.out.println(ClientNetwork.loginToServer("Jabob", "password".toCharArray()));
		ArrayList<String> users = ClientNetwork.getUsernames();
		System.out.println("Users: ");
		for(String s : users) {
			System.out.print(s + ", ");
		}
		System.out.println();*/
//		System.out.println(ClientNetwork.createAccountOnServer("Jabob", "password".toCharArray()));
		
//		String id = ClientNetwork.loginToServer("jabob", "password".toCharArray());
//		System.out.println(id);
//		System.out.println(ClientNetwork.getPlayerProfile(id));
		
	}

}
