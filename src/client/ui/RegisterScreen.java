package client.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.ClientNetwork;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterScreen.
 *
 * @author Harry Mitchell
 * 
 * This creates the screen for registering an account
 */

public class RegisterScreen extends JPanel {
	
	/**
	 * Instantiates a new register screen.
	 */
	public RegisterScreen(){
		MainWindow.displayPanel(this,700,596,"Register");
		String[] args = new String[0];
		ClientNetwork.connect();
		this.setLayout(null);
		setBackground(Color.decode("#3498db"));
		JLabel EU = (JLabel)CreateUI.MakeUIObject("Please enter a username",100, 150, 150, 50, "label");
		JLabel EP = (JLabel)CreateUI.MakeUIObject("Please enter a password",100, 200, 150, 50,"label");
		JLabel RP = (JLabel)CreateUI.MakeUIObject("Please reenter your password",100, 250, 250, 50,"label");
		JLabel TT = (JLabel)CreateUI.MakeUIObject("Usernames and Passwords may only",200, 10, 1000, 20,"label");
		JLabel TT2 = (JLabel)CreateUI.MakeUIObject("consist of numbers and letters.",200, 41, 1000, 10,"label");
		EP.setToolTipText("Passwords may only contain numbers and letters");
		EU.setToolTipText("Usernames may only contain numbers and letters");
		JButton submit = (JButton)CreateUI.MakeUIObject("Submit",200, 400, 100, 100,"button");
		JButton Login = (JButton)CreateUI.MakeUIObject("Login",325, 400, 100, 100, "button");
		Login.setToolTipText("Login to an already existing account");
		setSize(700, 596);
		JLabel TOU = (JLabel)CreateUI.MakeUIObject("Please select an acount type.",100, 85, 175, 15,"label");
		String[] TypesOfUsers = new String[] {"Player", "Admin", "Spectator"};
		JComboBox<String> UserTypeSelect = new JComboBox<>(TypesOfUsers);
		UserTypeSelect.setBounds(300, 75, 125, 50);
		JTextField Us = (JTextField)CreateUI.MakeUIObject("", 300, 150, 300, 50, "text");
		JPasswordField p1 = (JPasswordField)CreateUI.MakeUIObject("",300, 200, 300, 50, "pass");
		JPasswordField p2 = (JPasswordField)CreateUI.MakeUIObject("",300, 250, 300, 50, "pass");
		p2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(Us.getText() != null && passmatch(p1, p2)) {
		    		ClientNetwork.connect();
		    		int accountType = UserTypeSelect.getSelectedIndex();
		    		System.out.println(accountType);
		    		boolean flag = false;
		    		if(accountType == 1) {
		    			String attempt = JOptionPane.showInputDialog("Please enter the admin password:");
		    			if(attempt.equals("Large_Father")) {accountType = 1;}
		    			else {
		    				flag = true;
		    			}
		    		}
		    		if(flag) {
						JOptionPane.showMessageDialog(null, "That is not the correct password!\nAccounts starting with Admin_ can only be used by admins!");
					}
		    		else {
		    			String res = ClientNetwork.createAccountOnServer(Us.getText().toString(), p1.getPassword());		
						if (res.equals("usernameFail") || res.equals("passwordFail") || res.equals("takenFail") || flag) {
								JOptionPane.showMessageDialog(null, "Account Registration failed because of the following error: "+res);
						}
						else {
							ClientNetwork.setAccountType(res, accountType);
							TitleScreen.UserProfile = ClientNetwork.loginToServer(Us.getText().toString(), p1.getPassword()).getP();
							TitleScreen.UserID = TitleScreen.UserProfile.getUserID();
							TitleScreen.UserProfile.setAccountType(ClientNetwork.getAccountType(TitleScreen.UserID));
							ClientNetwork.setPlayerCurrency(TitleScreen.UserID, 500);
							setVisible(false);
							removeAll();
							MainMenu.main(args);
						}
		    		}
		    	}
		    }
		});
		
		JButton clear = (JButton)CreateUI.MakeUIObject("Clear",450, 400, 100, 100,"button");
		clear.setToolTipText("Clear all textboxes");
		this.add(clear);
		this.add(TT);
		this.add(TT2);
		this.add(EU);
		this.add(EP);
		this.add(RP);
		this.add(Us);
		this.add(p1);
		this.add(p2);
		this.add(submit);
		this.add(Login);
		this.add(UserTypeSelect);
		this.add(TOU);
		//add(panel);	
		submit.addActionListener((ActionEvent event) -> {
			submit.setVisible(false);
			if(Us.getText() != null && passmatch(p1, p2)) {
				ClientNetwork.connect();
	    		int accountType = UserTypeSelect.getSelectedIndex();
	    		System.out.println(accountType);
	    		boolean flag = false;
	    		if(accountType == 1) {
	    			String attempt = JOptionPane.showInputDialog("Please enter the admin password:");
	    			if(attempt.equals("Large_Father")) {accountType = 1;}
	    			else {
	    				flag = true;
	    			}
	    		}
	    		if(flag) {
					JOptionPane.showMessageDialog(null, "That is not the correct password!\nAccounts starting with Admin_ can only be used by admins!");
				}
	    		else {
	    			String res = ClientNetwork.createAccountOnServer(Us.getText().toString(), p1.getPassword());		
					if (res.equals("usernameFail") || res.equals("passwordFail") || res.equals("takenFail") || flag) {
							JOptionPane.showMessageDialog(null, "Account Registration failed because of the following error: "+res);
					}
					else {
						ClientNetwork.setAccountType(res, accountType);
						TitleScreen.UserProfile = ClientNetwork.loginToServer(Us.getText().toString(), p1.getPassword()).getP();
						//TitleScreen.UserProfile.setAccountType(accountType);
						TitleScreen.UserID = TitleScreen.UserProfile.getUserID();
						ClientNetwork.setPlayerCurrency(TitleScreen.UserID, 500);
						setVisible(false);
						removeAll();
						MainMenu.main(args);
					}
	    		}
			}
			submit.setVisible(true);
        });		
		Login.addActionListener((ActionEvent event) -> {
			LoginScreen.main(args);
			removeAll();
			this.setVisible(false);
        });
		clear.addActionListener((ActionEvent event) -> {
			Us.setText("");
			p1.setText("");
			p2.setText("");
        });
	}
	
	/**
	 * Passmatch.
	 *
	 * @param T the t
	 * @param B the b
	 * @return true, if successful
	 */
	public boolean passmatch(JPasswordField T, JPasswordField B) {
		char[] pass1 = T.getPassword();
		char[] pass2 = B.getPassword();
		if(pass1.length != pass2.length) return false;
		for(int i = 0; i<pass1.length;i++) {
			if(pass1[i] != pass2[i]) return false;
		}
		return true;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			new MainWindow();
            RegisterScreen rs = new RegisterScreen();
            rs.setVisible(true);
        });
	}

}
