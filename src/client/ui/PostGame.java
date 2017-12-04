package client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.ClientNetwork;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class PostGame.
 */
public class PostGame extends JPanel {
	
	/** The Pros. */
	private static ArrayList<PlayerProfile> Pros;
	
	/**
	 * Main.
	 *
	 * @param args the args
	 * @param pp the pp
	 */
	public static void main(String args[], ArrayList<PlayerProfile> pp) {
		Pros = pp;
		orderPlayers();
		payPlayers();
		EventQueue.invokeLater(() -> {
            PostGame pg = new PostGame();
            pg.setVisible(true);
        });
	}
	
	/**
	 * Instantiates a new post game.
	 */
	public PostGame() {
		String[] args = new String[0];
		MainWindow.displayPanel(this, 650, 700, "Post Match");
		this.setLayout(null);
		setSize(650, 700);
		
		ImageIcon one = (ImageIcon)CreateUI.MakeUIObject("pics/First.png", 0, 0, 0, 0, "Icon");
		ImageIcon two = (ImageIcon)CreateUI.MakeUIObject("pics/Second.png", 0, 0, 0, 0, "Icon");
		ImageIcon three = (ImageIcon)CreateUI.MakeUIObject("pics/Third.png", 0, 0, 0, 0, "Icon");
		ImageIcon four = (ImageIcon)CreateUI.MakeUIObject("pics/Last.png", 0, 0, 0, 0, "Icon");
		JLabel fir = (JLabel)CreateUI.MakeUIObject(one, 50, 100, 100, 100, "label");
		JLabel sec = (JLabel)CreateUI.MakeUIObject(two, 50, 210, 100, 100, "label");
		JLabel thir = (JLabel)CreateUI.MakeUIObject(three, 50, 320, 100, 100, "label");
		JLabel forth = (JLabel)CreateUI.MakeUIObject(four, 50, 430, 100, 100, "label");
		JLabel finals = (JLabel)CreateUI.MakeUIObject("Final", 275, 10, 100, 25, "label");
		JLabel scores = (JLabel)CreateUI.MakeUIObject("Scores", 275, 35, 100, 25, "label");
		this.add(fir);
		this.add(sec);
		this.add(thir);
		this.add(forth);
		this.add(finals);
		this.add(scores);
		
		JLabel p1 = (JLabel)CreateUI.MakeUIObject(Pros.get(0).getUsername(),160, 120, 90, 60,"label");
		JLabel p2 = (JLabel)CreateUI.MakeUIObject(Pros.get(1).getUsername(),160, 235, 90, 45,"label");
		JLabel p3 = (JLabel)CreateUI.MakeUIObject(Pros.get(2).getUsername(),160, 355, 90, 30,"label");
		JLabel p4 = (JLabel)CreateUI.MakeUIObject(Pros.get(3).getUsername(),160, 475, 90, 20,"label");
		p1.setFont(new Font("Serif", Font.PLAIN, 50));
		p2.setFont(new Font("Serif", Font.PLAIN, 35));
		p3.setFont(new Font("Serif", Font.PLAIN, 20));
		p4.setFont(new Font("Serif", Font.PLAIN, 10));
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		
		
		JLabel s1 = (JLabel)CreateUI.MakeUIObject(Pros.get(0).getPoints()+"",275, 120, 100000, 60,"label");
		JLabel s2 = (JLabel)CreateUI.MakeUIObject(Pros.get(1).getPoints()+"",275, 235, 100000, 45,"label");
		JLabel s3 = (JLabel)CreateUI.MakeUIObject(Pros.get(2).getPoints()+"",275, 355, 100000, 30,"label");
		JLabel s4 = (JLabel)CreateUI.MakeUIObject(Pros.get(3).getPoints()+"",275, 475, 100000, 20,"label");
		this.add(s1);
		this.add(s2);
		this.add(s3);
		this.add(s4);
		
		JLabel mm1 = (JLabel)CreateUI.MakeUIObject("Main", "label");
		JLabel mm2 = (JLabel)CreateUI.MakeUIObject("Menu", "label");
		JButton mm = CreateUI.Make2LineButton(mm1, mm2, 10, 10, 70, 50, "Return to the main menu");
		mm.addActionListener((ActionEvent event) -> { //returns the user to the main menu
			this.setVisible(false);
			removeAll();
			MainMenu.main(args);
		});
		this.add(mm);
		setBackground(Color.decode("#3498db"));
		//add(panel);
	}


	/**
	 * Pay players.
	 */
	private static void payPlayers() {
		Pros.get(0).setCurrency(Pros.get(0).getCurrency()+50);
		Pros.get(1).setCurrency(Pros.get(1).getCurrency()+100);
		Pros.get(2).setCurrency(Pros.get(2).getCurrency()+200);
		Pros.get(3).setCurrency(Pros.get(0).getCurrency()+500);
	}
	
	/**
	 * Order players.
	 */
	private static void orderPlayers() {
		int max = 0;
		int best = 0;
		ArrayList<PlayerProfile> places = new ArrayList<>();
		int tot = Pros.size();
		for(int j=0;j<tot;j++) {
			for(int i=0;i<Pros.size();i++) {
				if(Pros.get(i).getPoints()>max) {
					max = Pros.get(i).getPoints();
					best = i;
				}
			}
			max = -1;
			places.add(Pros.get(best));
			Pros.remove(best);
		}
		Pros = places;
	}
}
