package client.ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class TitleScreen.
 *
 * @author Harry Mitchell
 * 
 * This is the first screen the user will see when they
 * start our game.
 */

public class TitleScreen extends JPanel {
	
	/** The User ID. */
	public static String UserID;
	
	/** The User profile. */
	public static PlayerProfile UserProfile;
	
	/**
	 * Instantiates a new title screen.
	 */
	public TitleScreen() {

		MainWindow.displayPanel(this, 700, 596, "Absolute Conquest: Noobs, Big Daddies, and Dragons!");
		ImageIcon TheFrame = (ImageIcon)CreateUI.MakeUIObject("pics/The Title picture.png", 0, 0, 0, 0, "Icon");
		setSize(700, 596);
		
		JLabel label1 = (JLabel)CreateUI.MakeUIObject(TheFrame, 20, 260, 500, 496, "label");
		JLabel title = (JLabel) CreateUI.MakeUIObject("Absolute Conquest: Noobs, Big Daddies, and Dragons!", 10, 10, 100, 100, "label");
		JButton Reg = (JButton) CreateUI.MakeUIObject("Register", 200, 200, 50, 50, "button");
		JButton Login = (JButton) CreateUI.MakeUIObject("Login", 300, 200, 50, 50, "button");
		this.add(title);
		this.add(Reg);
		this.add(Login);
		this.add(label1);
		//add(panel);
		String[] args = new String[0];
		Reg.addActionListener((ActionEvent event) -> {
			RegisterScreen.main(args);
			this.removeAll();
			this.setVisible(false);
        });
		Login.addActionListener((ActionEvent event) -> {
			LoginScreen.main(args);
			this.removeAll();
			this.setVisible(false);
        });
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			new MainWindow();
            TitleScreen mm = new TitleScreen();
            mm.setVisible(true);
        });
	}

}
