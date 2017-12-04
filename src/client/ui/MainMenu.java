package client.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class MainMenu.
 *
 * @author Harry Mitchell
 */

public class MainMenu extends JPanel {
	
	/** The clip. */
	public static Clip clip;
	
	/**
	 * Instantiates a new main menu.
	 */
	public MainMenu() {
		MainWindow.displayPanel(this,700,596,"Main Menu");
		setBackground(Color.decode("#3498db"));
		this.setLayout(null);
		setSize(700, 596);
		playMusic();
		JLabel title = new JLabel("Total Conquest:Noobs, Big Daddies, and Dragons!");
		title.setBounds(215, 10, 100000, 100);
		JButton FG = (JButton)CreateUI.MakeUIObject("Find Game",250, 100, 200, 75,"button");
		JButton quit = (JButton)CreateUI.MakeUIObject("Exit Game",250, 400, 200, 75,"button");
		this.add(FG);
		String[] args = new String[0];
		if(TitleScreen.UserProfile.getAccountType() != 2) {
			JButton VP = (JButton)CreateUI.MakeUIObject("View Profile",250, 200, 200, 75,"button");
			JButton CD = (JButton)CreateUI.MakeUIObject("Card Collection",250, 300, 200, 75,"button");
			this.add(VP);
			this.add(CD);
			VP.addActionListener((ActionEvent event) -> {
				User.main(args);
				this.setVisible(false);
				removeAll();
	        });
			CD.addActionListener((ActionEvent event) -> {
				CardCollection.main(args);
				this.setVisible(false);
				removeAll();
	        });
		}
		this.add(quit);
		this.add(title);
		//add(panel);
		quit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
		
		FG.addActionListener((ActionEvent event) -> {
			FindGame.main(args);
			this.setVisible(false);
			removeAll();
        });
	
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            MainMenu mm = new MainMenu();
            mm.setVisible(true);
        });
	}

/**
 * Play music.
 *
 * @author Jeremiah Brusegaard
 * Plays background music of the game
 */
	public static void playMusic() {
		try {
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./bgm.wav"));
			clip.open(inputStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

	}
}
