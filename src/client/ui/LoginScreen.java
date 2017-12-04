package client.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.ClientNetwork;
import response.LoginResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginScreen.
 *
 * @author Harry Mitchell
 * This creates the screen for logging into an account
 */

public class LoginScreen extends JPanel {
	
	/**
	 * Instantiates a new login screen.
	 */
	public LoginScreen() {	
		MainWindow.displayPanel(this,700,596,"Login");
		String[] args = new String[0];
		ClientNetwork.connect();
		setBackground(Color.decode("#3498db"));
		this.setLayout(null);
		JLabel TT = (JLabel)CreateUI.MakeUIObject("LOGIN",300, 10, 1000, 20,"label");
		//TT.setFont();
		JLabel EU = (JLabel)CreateUI.MakeUIObject("Please enter a username",100, 150, 150, 50, "label");
		JLabel EP = (JLabel)CreateUI.MakeUIObject("Please enter a password",100, 200, 150, 50,"label");
		EP.setToolTipText("Passwords may only contain numbers and letters");
		EU.setToolTipText("Usernames may only contain numbers and letters");
		JButton submit = (JButton)CreateUI.MakeUIObject("Submit",200, 400, 100, 100,"button");
		JButton reg = (JButton)CreateUI.MakeUIObject("Register",325, 400, 100, 100, "button");
		 
		reg.setToolTipText("Register a new account");
		setSize(700, 596);
		JTextField Us = (JTextField)CreateUI.MakeUIObject("", 300, 150, 300, 50, "text");
		JPasswordField p1 = (JPasswordField)CreateUI.MakeUIObject("",300, 200, 300, 50, "pass");
		p1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String res = "";
				if (p1.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "Could not login becuase of the following problem: Empty password field");
				}
				else {
					LoginResponse lr = ClientNetwork.loginToServer(Us.getText(), p1.getPassword());
					res = lr.getResult();
					
					if (res.equals("invalidUsername") || res.equals("nullError") || res.equals("invalidPassOrUsername")) {
						submit.setVisible(true);
						JOptionPane.showMessageDialog(null, "Could not login becuase of the following problem "+res);
					}
					else {
						TitleScreen.UserProfile = lr.getP();
						TitleScreen.UserID = TitleScreen.UserProfile.getUserID();
						TitleScreen.UserProfile.setAccountType(ClientNetwork.getAccountType(TitleScreen.UserID));
						System.out.println(ClientNetwork.getAccountType(TitleScreen.UserID));
						MainMenu.main(args);
						removeAll();
						setVisible(false);
					}
				}
		    }
		});
		this.add(TT);
		this.add(EU);
		this.add(EP);
		this.add(Us);
		this.add(p1);
		this.add(submit);
		this.add(reg);
		JButton clear = (JButton)CreateUI.MakeUIObject("Clear",450, 400, 100, 100,"button");
		clear.setToolTipText("Clear all textboxes");
		this.add(clear);
		this.add(TT);
		

		reg.addActionListener((ActionEvent event) -> {
			RegisterScreen.main(args);
			this.setVisible(false);
			removeAll();
        });
		submit.addActionListener((ActionEvent event) -> {
			submit.setVisible(false);
			String res = "";
			if (p1.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "Could not login because of the following problem: Empty password field");
				submit.setVisible(true);
			}
			else {
				LoginResponse lr = ClientNetwork.loginToServer(Us.getText(), p1.getPassword());
				res = lr.getResult();
				
				if (res.equals("invalidUsername") || res.equals("nullError") || res.equals("invalidPassOrUsername")) {
					submit.setVisible(true);
					JOptionPane.showMessageDialog(null, "Could not login because of the following problem "+res);
					submit.setVisible(true);
				}
				else {
					TitleScreen.UserProfile = lr.getP();
					TitleScreen.UserID = res;
					MainMenu.main(args);
					this.setVisible(false);
					removeAll();
				}
			}		
        });
		clear.addActionListener((ActionEvent event) -> {
			Us.setText("");
			p1.setText("");
        });
		//add(panel);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			new MainWindow();
            LoginScreen ls = new LoginScreen();
            ls.setVisible(true);
        });
	}

}