package client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

/**
 * @author Harry Mitchell
 * @author Josh Brenneman
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import client.ClientNetwork;
import player.Card;
import player.Deck;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class DeckEditor.
 */
public class DeckEditor extends JPanel {
	
	/** The current deck list. */
	private JList currentDeckList = new JList();
	
	/** The list. */
	private JList list = new JList();
	
	/** The decks. */
	private JComboBox<String> decks;
	
	/** The totdecks. */
	private int totdecks; //the total number of decks the user has created.
	
	/** The cur deck names. */
	private ArrayList<String> curDeckNames = new ArrayList<>(); //the names of the cards in the deck being currently built.
	
	/** The User deck names. */
	private ArrayList<String> UserDeckNames = new ArrayList<>(); //The names of the decks the user has created.
	
	/** The User CC names. */
	private ArrayList<String> UserCCNames = new ArrayList<>(); //Contains the names of all the cards the user owns.
	
	/** The cc. */
	private ArrayList<Card> CC = new ArrayList<>(); //The user's card collection
	
	/** The cc2. */
	private ArrayList<Card> CC2 = new ArrayList<>(); //A copy of the user's card collection that will be edited a lot.
	
	/** The cards in deck. */
	private ArrayList<Card> cardsInDeck = new ArrayList<>();//the cards in the current deck the user is editing.
	
	/** The Decksowned. */
	private ArrayList<Deck> Decksowned; //The decks owned by the current user.
	
	/** The num cards in deck. */
	private int numCardsInDeck; //The number of cards in the current deck
	
	/** The edited deck. */
	private Deck editedDeck; //The deck that is being built or edited
	
	/** The selected index. */
	private int selectedIndex; // The index of the currently edited deck in the arraylist of decks
	
	/**
	 * Instantiates a new deck editor.
	 */
	public DeckEditor() {
		initUI();
	}
	
	/**
	 * Inits the UI.
	 */
	private void initUI() {
		Decksowned = TitleScreen.UserProfile.getDecks();
		editedDeck = Decksowned.get(0);
		selectedIndex  = 0;
		for(int i=0; i<TitleScreen.UserProfile.getDecks().size(); i++) {
			UserDeckNames.add(Decksowned.get(i).getDeckName());
		}
		ArrayList<Card> Kard = TitleScreen.UserProfile.getCardCollection();
		for(int i=0;i<Kard.size()-1;i++) {
			UserCCNames.add(Kard.get(i).getName());
		}		
		setBackground(Color.decode("#3498db"));
		totdecks = TitleScreen.UserProfile.getDecks().size();
		for(int i=0; i<TitleScreen.UserProfile.getCardCollection().size()-1;i++) {
			CC.add(TitleScreen.UserProfile.getCardCollection().get(i));
			CC2.add(TitleScreen.UserProfile.getCardCollection().get(i));
		}
		numCardsInDeck = 0;
		
		String[] args = new String[0];
		
		MainWindow.displayPanel(this, 650, 700, "Deck Editor");
		
		this.setLayout(null);
	    setSize(650,700);
	    
	    String[] tt = UserDeckNames.toArray(new String[UserDeckNames.size()]);
	    decks = new JComboBox<>(tt);
		decks.setBounds(500, 75, 100, 50);
		this.add(decks);
		
	    JLabel c1 = (JLabel)CreateUI.MakeUIObject("Create", "label");
	    JLabel c2 = (JLabel)CreateUI.MakeUIObject("Deck", "label");
	    JButton cnd = CreateUI.Make2LineButton(c1, c2,255,75,75,50,"Create a new deck to play with.");
	    this.add(cnd);
	    
	    JLabel e1 = (JLabel)CreateUI.MakeUIObject("Edit", "label");
	    JLabel e2 = (JLabel)CreateUI.MakeUIObject("Deck", "label");
	    JButton ed = CreateUI.Make2LineButton(e1, e2, 340, 75, 75, 50, "Edit an already exising deck.");
	    this.add(ed);
	    
	    JLabel l1 = (JLabel)CreateUI.MakeUIObject("Delete", "label");
	    JLabel l2 = (JLabel)CreateUI.MakeUIObject("Deck", "label");
	    JButton del = CreateUI.Make2LineButton(l1, l2, 420, 75, 75, 50, "Delete selected deck");
	    
	    this.add(del);
	    
		JButton btnBack = (JButton)CreateUI.MakeUIObject("Back", 10,10, 75, 50, "button");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CardCollection.main(args);
				setVisible(false);
				removeAll();
		    }	
		});
		this.add(btnBack);
		
		ArrayList<Card> tempc = Decksowned.get(0).getCards();
		for (int i=0; i<tempc.size(); i++) {
			cardsInDeck.add(tempc.get(i));
		}
		numCardsInDeck = cardsInDeck.size();
		
		JLabel top = (JLabel)CreateUI.MakeUIObject("Click the Create new deck button to create a deck",150, 25, 100000, 10,"label");
		JLabel top2 = (JLabel)CreateUI.MakeUIObject("You can select your deck from the list on the right to change your active deck.",150, 35, 100000, 15,"label");
		JLabel deckbeingedited = (JLabel)CreateUI.MakeUIObject(Decksowned.get(0).getDeckName(),450, 175, 10000, 20,"label");
		JLabel cicd = (JLabel)CreateUI.MakeUIObject("You have "+numCardsInDeck+"/50 cards in your deck",400, 510, 10000, 15,"label");
		JLabel CardCol = (JLabel)CreateUI.MakeUIObject("Card Collection",150, 175, 100, 20,"label");

		this.add(top);
		this.add(top2);
		this.add(deckbeingedited);
		this.add(cicd);
		this.add(CardCol);
		
		JScrollPane spane = new JScrollPane();
		for(int i=0;i<numCardsInDeck;i++) {
			CC2.remove(cardsInDeck.get(i));
		}
		updateUCCN();
		list = new JList(UserCCNames.toArray(new String[UserCCNames.size()]));
		spane.setBounds(100, 200, 200, 300);
		spane.getViewport().add(list);
		this.add(spane);
		
		for(int i=0;i<numCardsInDeck;i++) {
			curDeckNames.add(cardsInDeck.get(i).getName());
		}
		currentDeckList = new JList(curDeckNames.toArray(new String[curDeckNames.size()]));
		JScrollPane spane2 = new JScrollPane();
		spane2.setBounds(400, 200, 200, 300);
		spane2.getViewport().add(currentDeckList);
		this.add(spane2);
		
		ImageIcon n = (ImageIcon)CreateUI.MakeUIObject("pics/NextArrow.png",0,0,0,0,"Icon");
		ImageIcon p = (ImageIcon)CreateUI.MakeUIObject("pics/PrevArrow.png",0,0,0,0,"Icon");
		JButton add = (JButton)CreateUI.MakeUIObject(n,315, 300, 75, 75,"button");
		JButton rev = (JButton)CreateUI.MakeUIObject(p,315, 400, 75, 75,"button");
		JButton save = (JButton)CreateUI.MakeUIObject("Save",165, 75, 75, 50,"button");
		this.add(save);
		this.add(add);
		this.add(rev);
		
		add.addActionListener((ActionEvent event) -> { //adds the selected card from the user's card collection to the currently editing deck
			if(numCardsInDeck >= 50) {
				JOptionPane.showMessageDialog(null, "You have the max amount of cards in your deck!\nPlease remove a card before trying to add another card!");
			}
			else {
				String cardname = list.getSelectedValue().toString();
				int cardin = list.getSelectedIndex();
				Card switcher = CC2.get(cardin);
				if(checkCap(switcher)) {
					CC2.remove(switcher);
					curDeckNames.add(cardname);
					cardsInDeck.add(switcher);
					numCardsInDeck++;
					cicd.setText("You have "+numCardsInDeck+"/50 cards in your deck");
					updateUCCN();
					list = new JList(UserCCNames.toArray(new String[UserCCNames.size()]));
					spane.getViewport().add(list);
					currentDeckList = new JList(curDeckNames.toArray(new String[curDeckNames.size()]));
					spane2.getViewport().add(currentDeckList);
					editedDeck.addCard(switcher);
					if(switcher.getType().equals("Terrain")) {
						editedDeck.increaseTerrianAmount();
					}
				}
			}
		});
		rev.addActionListener((ActionEvent event) -> {//removes the selected card from the currently editing deck
			String cardname = currentDeckList.getSelectedValue().toString();
			int cardindex = currentDeckList.getSelectedIndex();
			Card switcher = cardsInDeck.get(cardindex);
			if(checkCap(switcher)) {
				cardsInDeck.remove(switcher);
				CC2.add(switcher);
				curDeckNames.remove(switcher.getName());
				updateUCCN();
				numCardsInDeck--;
				cicd.setText("You have "+numCardsInDeck+"/50 cards in your deck");
				list = new JList(UserCCNames.toArray(new String[UserCCNames.size()]));
				spane.getViewport().add(list);
				currentDeckList = new JList(curDeckNames.toArray(new String[curDeckNames.size()]));
				spane2.getViewport().add(currentDeckList);
				editedDeck.removeCard(switcher);
				if(switcher.getType().equals("Terrain")) {
					editedDeck.decreaseTerrianAmount();
				}
			}
		});
		cnd.addActionListener((ActionEvent event) -> {//creates a new deck that is the new editable deck
			TitleScreen.UserProfile.setDecks(Decksowned);
			String deckname = JOptionPane.showInputDialog("Please enter a name for your deck");
			clearRight();
			UserDeckNames.add(deckname);
			totdecks++;
			spane2.getViewport().add(currentDeckList);
			deckbeingedited.setText(deckname);
			decks.removeAllItems();
			for(int i=0;i<totdecks;i++) {
				decks.addItem(UserDeckNames.get(i));
			}
			numCardsInDeck = 1;
			cicd.setText("You have "+numCardsInDeck+"/50 cards in your deck");
			Deck deck = new Deck(deckname, "", TitleScreen.UserID);
			Card c = ClientNetwork.getCardLibraryMap().get("Capital");
			deck.addCard((Card)c.clone());
			removeAll(cardsInDeck);
			cardsInDeck.add(c);
			curDeckNames.add(cardsInDeck.get(0).getName());
			currentDeckList = new JList(curDeckNames.toArray(new String[curDeckNames.size()]));
			spane2.getViewport().add(currentDeckList);
			Decksowned.add(deck);
			restoreCC2();
			CC2.remove(c);
			updateUCCN();
			list = new JList(UserCCNames.toArray(new String[UserCCNames.size()]));
			spane.getViewport().add(list);
			editedDeck = new Deck();
			editedDeck.setUserID(TitleScreen.UserID);
			editedDeck.setDeckName(deckname);
			editedDeck.setDeckID(null);
			Card cap = new Card("Capital", "gtoZ4s9xf9PVvna6", "Capital", " ", " ", " ",
					" ", " ", "", "Terrain", 0, 0, 0, 0, 0,
					TitleScreen.UserID);
			editedDeck.addCard(cap);
			selectedIndex = UserDeckNames.size()-1;
		});
		ed.addActionListener((ActionEvent event) -> { //makes the selected deck the editable deck.
			TitleScreen.UserProfile.setDecks(Decksowned);
			clearRight();
			cardsInDeck = (ArrayList<Card>) Decksowned.get(decks.getSelectedIndex()).getCards().clone();
			numCardsInDeck = cardsInDeck.size();
			cicd.setText("You have "+numCardsInDeck+"/50 cards in your deck");
			restoreCC2();
			deckbeingedited.setText(UserDeckNames.get(decks.getSelectedIndex()));
			for(int i=0; i<cardsInDeck.size(); i++) {
				curDeckNames.add(cardsInDeck.get(i).getName());
				CC2.remove(cardsInDeck.get(i));
			}
			currentDeckList = new JList(curDeckNames.toArray(new String[curDeckNames.size()]));
			spane2.getViewport().add(currentDeckList);
			updateUCCN();
			list = new JList(UserCCNames.toArray(new String[UserCCNames.size()]));
			spane.getViewport().add(list);
			editedDeck = Decksowned.get(decks.getSelectedIndex());
			editedDeck.setUserID(TitleScreen.UserID);
			selectedIndex = decks.getSelectedIndex();
		});
		del.addActionListener(new ActionListener() { //deletes the selected deck
			public void actionPerformed(ActionEvent event) {
				String[] da2 = new String[] {"Yes", "NO"};
				int res = JOptionPane.showOptionDialog(null, "Are you sure you would like to delete this deck?", "Delete?",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				        null, da2, da2[1]);
				if (res == 0) {						
					clearRight();
					UserDeckNames.remove(decks.getSelectedIndex());
					decks.removeAllItems();
					totdecks--;
					deckbeingedited.setText("No selected deck.");
					for(int i=0;i<totdecks;i++) {
						decks.addItem(UserDeckNames.get(i));
					}
				}
		    }
		});
		save.addActionListener((ActionEvent event) -> { //allows the user to store the edits they made to their currently selected deck on the database.
			Decksowned.get(selectedIndex).setCards(cardsInDeck);
			TitleScreen.UserProfile.setDecks(Decksowned);
			ClientNetwork.addDeckToPlayer(editedDeck);
		});
	}
	
	/**
	 * Clear right.
	 */
	private void clearRight() { //clears all of the elements in the right list
		String[] currentDeckLists = new String[50];
		removeAll(curDeckNames);
		currentDeckList = new JList(curDeckNames.toArray(new String[curDeckNames.size()]));	
		restoreCC2();
		for(int i=0;i<50;i++) {currentDeckLists[i] = "";}
	}
	
	/**
	 * Restore CC 2.
	 */
	private void restoreCC2() { //restores CC2 to contain all of the elements CC has.
		removeAll(CC2);
		for(int i=0; i<CC.size();i++) {CC2.add(CC.get(i));}
	}
	
	/**
	 * Removes the all.
	 *
	 * @param a the a
	 */
	private void removeAll(ArrayList<?> a) { //removes all of the elements in an ArrayList because Java is hot trash for not having this built in.
		while(a.size()>0) {
			a.remove(0);
		}
	}
	
	/**
	 * Check cap.
	 *
	 * @param c the c
	 * @return the boolean
	 */
	private Boolean checkCap(Card c) { //checks to make sure the current card is not the Capital card.
		if(c.getName().equals("Capital")) {JOptionPane.showMessageDialog(null, "You cannot add or remove the Capitol cards from your deck!");
			return false;
		}
		return true;
	}
	
	/**
	 * Update UCCN.
	 */
	private void updateUCCN() { //updates the ArrayList of User Card Collection Names to only have cards not in the currently edited deck.
		removeAll(UserCCNames);
		for(int i=0;i<CC2.size();i++) {
			UserCCNames.add(CC2.get(i).getName());
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		DeckEditor menu = new DeckEditor();
		menu.setVisible(true);
	}
	 
}