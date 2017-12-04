package client.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import client.ClientNetwork;

// TODO: Auto-generated Javadoc
/**
 * The Class PackBuyer.
 */
public class PackBuyer extends JPanel {
	
	/** The money. */
	private int money=0;
	
	/**
	 * Instantiates a new pack buyer.
	 */
	public PackBuyer() {
		String[] curp = new String[16]; //This keeps the names of the cards in the most recent pack
		money = ClientNetwork.getPlayerCurrency(TitleScreen.UserID);
		ButtonGroup group = new ButtonGroup();
		MainWindow.displayPanel(this, 700, 596, "Absolute Conquest:Noobs, Big Daddies, and Dragons!");
		setSize(700, 596);
		this.setLayout(null);
		ImageIcon i3 = (ImageIcon)CreateUI.MakeUIObject("pics/3cards.png", 0, 0, 0, 0, "Icon");
		ImageIcon i7 = (ImageIcon)CreateUI.MakeUIObject("pics/7 cards.png", 0, 0, 0,0, "Icon");
		ImageIcon i16 = (ImageIcon)CreateUI.MakeUIObject("pics/16 cards.png", 0,0,0,0, "Icon");
		JLabel title =(JLabel)CreateUI.MakeUIObject("Select the type of pack you would like to buy and press confirm", 100, 25, 10000, 15, "label");
		JLabel title2 = (JLabel)CreateUI.MakeUIObject("You have "+money+" coins in your account.", 100, 50, 10000, 15, "label");
		JLabel p3 = (JLabel)CreateUI.MakeUIObject(i3,10, 200, 200, 200,"label");
		JLabel p7 = (JLabel)CreateUI.MakeUIObject(i7,220, 200, 200, 200,"label");
		JLabel p16 = (JLabel)CreateUI.MakeUIObject(i16,430, 200, 200, 200,"label");
		JRadioButton m3 = (JRadioButton)CreateUI.MakeUIObject("100 coins for 3 cards",10, 180, 200, 15,"radio");
		JRadioButton m7 = (JRadioButton)CreateUI.MakeUIObject("200 coins for 7 cards",220, 180, 200, 15,"radio");
		JRadioButton m16 = (JRadioButton)CreateUI.MakeUIObject("400 coins for 16 cards", 430, 180, 200, 15, "radio");
		JButton back = (JButton)CreateUI.MakeUIObject("Back",250, 420, 100, 100,"button");
		JButton sub = (JButton)CreateUI.MakeUIObject("Purchase",360, 420, 100, 100,"button");
		if(TitleScreen.UserProfile.getAccountType() == 1) {
			JButton deleteThisLater = new JButton(":)");
			deleteThisLater.setBounds(10, 150, 50, 50);
			this.add(deleteThisLater);
			deleteThisLater.addActionListener((ActionEvent event) -> {
				money += 500;
				ClientNetwork.setPlayerCurrency(TitleScreen.UserID, money);		
				title2.setText("You have "+money+" coins in your account.");
				playNeed();
	        });
			
		}
		setBackground(Color.decode("#3498db"));
		group.add(m3);
		group.add(m7);
		group.add(m16);
		
		this.add(title);
		this.add(title2);
		this.add(p3);
		this.add(p7);
		this.add(p16);
		this.add(m3);
		this.add(m7);
		this.add(m16);
		this.add(back);
		this.add(sub);
		
		//add(panel);
		
		String[] args = new String[0];
		
		back.addActionListener((ActionEvent event) -> {
			CardCollection.main(args);
			removeAll();
			this.setVisible(false);
        });
		sub.addActionListener((ActionEvent event) -> {
			playNeed();
			int n=0;
			if(m3.isSelected()) {
				if(money>=100) {
					money -= 100;
					ClientNetwork.setPlayerCurrency(TitleScreen.UserID, money);		
					n=3;
				}
				else
					JOptionPane.showMessageDialog(null, "You are "+(100 - money)+" gold short of buying that pack.");
			}
			else if(m7.isSelected()) {
				if(money>=200) {
					money -= 200;
					ClientNetwork.setPlayerCurrency(TitleScreen.UserID, money);
					n=7;
				}
				else
					JOptionPane.showMessageDialog(null, "You are "+(200 - money)+" gold short of buying that pack.");
			}
			else {
				if(money>=400) {
					money -= 400;
					ClientNetwork.setPlayerCurrency(TitleScreen.UserID, money);
					n=16;
				}
				else
					JOptionPane.showMessageDialog(null, "You are "+(400 - money)+" gold short of buying that pack.");
			}
			getNewCards(n);
			title2.setText("You have "+money+" coins in your account.");
//			int Admin = TitleScreen.UserProfile.getAccountType();
			TitleScreen.UserProfile = ClientNetwork.getPlayerProfile(TitleScreen.UserID);
//			TitleScreen.UserProfile.setAccountType(Admin);
        });
	}
	
	/**
	 * Gets the new cards.
	 *
	 * @param n the n
	 */
	public void getNewCards(int n) {	
		if (n==0)
			return;
		String cards = "";
		for(int i=0; i<n; i++) {
			int val = (int)(Math.random() * 100 + 1);
			int nc = 0;
			String type = "Common";
			if (val <= 5) {
				nc++;
				type = "Legendary";
			}
			else if(val<=20) {
				nc++;
				type = "Epic";
			}
			else if(val<=50) {
				nc++;
				type = "Rare";
			}
			if(i==n-1 && nc<1) {type = "Rare";}
			cards += ClientNetwork.getCardIDByRarityAddToUser(TitleScreen.UserID, type)+"\n";
		}
		JOptionPane.showMessageDialog(null, "You recieved the following cards from your pack:\n"+cards);
	}
	
	/**
	 * Play need.
	 */
	private void playNeed() {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./need.wav"));
			clip.open(inputStream);
			clip.start();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            PackBuyer mm = new PackBuyer();
            mm.setVisible(true);
        });
	}

}
