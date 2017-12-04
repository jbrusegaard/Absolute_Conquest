package tests;

import client.ClientNetwork;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterTestUsers.
 */
public class RegisterTestUsers {
		
		/**
		 * The main method.
		 *
		 * @param args the arguments
		 */
		public static void main(String args[]) {
			ClientNetwork.connect();
			String s = ClientNetwork.createAccountOnServer("P1", "pass".toCharArray());
			ClientNetwork.setAccountType(s,0);
			s = ClientNetwork.createAccountOnServer("P2", "pass".toCharArray());
			ClientNetwork.setAccountType(s,0);
			s = ClientNetwork.createAccountOnServer("P3", "pass".toCharArray());
			ClientNetwork.setAccountType(s,0);
			s = ClientNetwork.createAccountOnServer("P4", "pass".toCharArray());
			ClientNetwork.setAccountType(s,0);
			s = ClientNetwork.createAccountOnServer("spec", "pass".toCharArray());
			ClientNetwork.setAccountType(s,0);
		}
}
