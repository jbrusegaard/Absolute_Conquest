package client.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.ClientNetwork;
import player.Card;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class CardCollection.
 *
 * @author Harry Mitchell
 * @author Josh Brenneman
 */

public class CardCollection extends JPanel{
	
	/** The total. */
	private int topleft, total;
	
	/** The cards. */
	private ArrayList<Card> cards = new ArrayList();
	
	/** The User profile. */
	private static PlayerProfile UserProfile;
	
	/** The counts. */
	private JLabel[] counts;
	
	/** The buttons. */
	private JButton[] buttons;
	
	/** The maxcount. */
	private final int maxcount = 8;
	
	/**
	 * Instantiates a new card collection.
	 */
	public CardCollection() {
		counts = new JLabel[maxcount];
		buttons = new JButton[maxcount];
		UserProfile = TitleScreen.UserProfile;
		ArrayList<Card> cccc = UserProfile.getCardCollection();
		for (int i=0; i<cccc.size(); i++) {
			cards.add(cccc.get(i));
		} 
		removeDupes(cards);
		topleft = 0;
		setBackground(Color.decode("#3498db"));
		total = cards.size();
		int overflow = total%8;
		if(overflow!=0)
			overflow = 8-overflow;
		total += overflow;
		for(int i=0;i<overflow; i++) {
			Card tcard = new Card("Missing", "0", "Missing", null, null, null,
					null, null, null, "Missing", 0, 0, 0, 0, 0,
					"empty card");
			cards.add(tcard);
		}
		
		ArrayList<String> t = ClientNetwork.getPlayerCardsAndAmounts(TitleScreen.UserID);
		String[] count = new String[total];
		int j=0;
		while(t.size()>1) {
			t.remove(0);
			if(!t.get(0).equals("0")) {
				count[j] = t.get(0);
				j++;
			}
			t.remove(0);
		}
		for(int i=total-overflow;i<total;i++) {
			count[i] = "0";
		}
		
		String[] args = new String[0];
		
		MainWindow.displayPanel(this, 650, 700, "Card Collection");
		this.setLayout(null);
	    setSize(650,700);
	    ImageIcon TheFrame = new ImageIcon("pics/The Title picture.png");
	    
	    JLabel top = new JLabel("Click a card to open a new tab with its information.");
	    top.setBounds(190, 125, 100000, 20);
	    this.add(top);    
	    
	    for(int i=0; i<maxcount/2; i++) {
	    	counts[i] = (JLabel) CreateUI.MakeUIObject("X"+count[i],145+(i*110),270, 40, 10, "label");
	    	this.add(counts[i]);
	    	ImageIcon gone = new ImageIcon("pics/"+cards.get(i).getFilename()+".png");
	    	buttons[i] = (JButton) CreateUI.MakeUIObject(gone,100+(i*110), 150, 100, 100, "button");
	    	this.add(buttons[i]);
	    }
	    for(int i=(maxcount/2);i<maxcount;i++) {
	    	counts[i] = (JLabel) CreateUI.MakeUIObject("X"+count[i],145+((i-(maxcount/2))*110),520, 40, 10, "label");
	    	this.add(counts[i]);
	    	ImageIcon gone = new ImageIcon("pics/"+cards.get(i).getFilename()+".png");
	    	buttons[i] = (JButton) CreateUI.MakeUIObject(gone,100+((i-(maxcount/2))*110), 400, 100, 100, "button");
	    	this.add(buttons[i]);
	    }
	    
	    ImageIcon PrevI = new ImageIcon("Pics/NextArrow.png");//I messed up the naming convention on these pics. They are in the correct spot.
	    JButton prev = new JButton(PrevI);
	    prev.setBounds(550, 300, 50, 50);
	    prev.addActionListener((ActionEvent event) -> {
			if(topleft+8>=total) {
				JOptionPane.showMessageDialog(null, "There exists no next page.");
			}
			else {
				topleft += 8;
				for(int i=0; i<maxcount; i++) {
			    	counts[i].setText("X"+count[topleft+i]);
					ImageIcon iii = new ImageIcon("pics/"+cards.get(topleft+i).getFilename()+".png");
			    	buttons[i].setIcon(iii);
			    }
			}
        });
	    this.add(prev);
	    
	    ImageIcon NextI = new ImageIcon("Pics/PrevArrow.png");
	    JButton next = new JButton(NextI);
	    next.setBounds(50, 300, 50, 50);
	    next.addActionListener((ActionEvent event) -> {
			if(topleft == 0) {
				JOptionPane.showMessageDialog(null, "There exists no previous page.");
			}
			else {
				topleft -= 8;
				for(int i=0; i<maxcount; i++) {
					counts[i].setText("X"+count[topleft+i]);
					ImageIcon iii = new ImageIcon("pics/"+cards.get(topleft+i).getFilename()+".png");
			    	buttons[i].setIcon(iii);
			    }
			}
        });
	    this.add(next);
	    
	    JButton dcke = new JButton("Deck Editor");
	    dcke.setBounds(10, 600, 150, 50);
	    dcke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				DeckEditor.main(args);
				setVisible(false);
				removeAll();
		    }	
		});
		this.add(dcke);
	    
	    JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 10, 75, 50);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MainMenu.main(args);
				setVisible(false);
				removeAll();
		    }	
		});
		this.add(btnBack);
	    
	    JButton btnPacks= new JButton("Get Packs");
	    btnPacks.setBounds(160, 600, 150, 50);
	    btnPacks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PackBuyer.main(args);
				setVisible(false);
				removeAll();
		    }	
		});
		this.add(btnPacks);
		
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayCard(topleft);
		    }	
		});
		
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayCard(topleft+1);
		    }	
		});
		
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayCard(topleft+2);
		    }	
		});
		
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayCard(topleft+3);
		    }	
		});
		
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayCard(topleft+4);
		    }	
		});
		
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayCard(topleft+5);
		    }	
		});
		
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayCard(topleft+6);
		    }	
		});
		
		buttons[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayCard(topleft+7);
		    }	
		});
		
		//add(panel);
	}
	
	/**
	 * Removes the dupes.
	 *
	 * @param c the c
	 */
	private void removeDupes(ArrayList<Card> c) {
		int i=0;
		while(i<c.size()-2) {
			if(c.get(i).equals(c.get(i+1))){
				c.remove(i+1);
			}
			else
				i++;
		}
		if(c.get(c.size()-2).equals(c.get(c.size()-1))) {
			c.remove(c.size()-1);
		}
	}
	
	/**
	 * Display card.
	 *
	 * @param index the index
	 */
	private void displayCard(int index) {
		Card temp = cards.get(index);
		if(temp.getType().equals("Land") || temp.getType().equals("Air") || temp.getType().equals("Aquatic")) {
			JOptionPane.showMessageDialog(null, temp.getName()+"\n"+temp.getDescription()+"\nAttack:"+temp.getAttack()
					+"\nHealth:"+temp.getHealth()+"\nMovement:"+temp.getSpeed());
		}
		else if(temp.getType().equals("Missing")) {
			JOptionPane.showMessageDialog(null, "Not a valid card!");
		}
		else {
			JOptionPane.showMessageDialog(null, temp.getName()+"\n"+temp.getDescription()+"\n\n"+temp.getAbility());
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
	       CardCollection menu = new CardCollection();
	       menu.setVisible(true);
	    }
}
