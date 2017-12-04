package client.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import client.ClientNetwork;
import game.Game;
import game.client.UnstartedGame;
import response.JoinUnstartedGameResponse;


// TODO: Auto-generated Javadoc
/**
 * The Class FindGame.
 *
 * @author Josh Brenneman
 * @author Harry Mitchell
 * 
 * this creates the menu for Finding a game
 */
public class FindGame extends JPanel {

	/** The game code. */
	static String gameCode;
	
	/**
	 * Instantiates a new find game.
	 */
	public FindGame()
	{
		initUI();
	}
	
	/**
	 * Inits the UI.
	 */
	public final void initUI()
	{
		String[] args= new String[0];
		
		MainWindow.displayPanel(this,650,700,"Find a Match");
		this.setLayout(null);
	    setSize(650,700);
	    setBackground(Color.decode("#3498db"));
	    JTextField code = (JTextField)CreateUI.MakeUIObject("",200, 250, 250, 20,"text");
	    code.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	gameCode = code.getText();
				JoinUnstartedGameResponse r = ClientNetwork.joinUnstartedGame(gameCode, TitleScreen.UserID);
				if(r.getResult().equals("gameFull")){
					JOptionPane.showMessageDialog(null, "This game is full.");
				}
				else if(r.getResult().equals("noSuchGame")){
					JOptionPane.showMessageDialog(null, "No game found.");
				}else {
				UnstartedGame game = new UnstartedGame(r.getGameId());
				game.setReady(r.getReadyList());
				game.setPlayers(r.getPlayerList());
				MatchLobby.main(args, game);
				setVisible(false);
				removeAll();
				}
		    }
		});
		this.add(code);
	    
	    JButton btnJoin = (JButton)CreateUI.MakeUIObject("Join Match",100,450,200,100,"button");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					gameCode = code.getText();
					if(TitleScreen.UserProfile.getAccountType() != 2) {
						JoinUnstartedGameResponse r = ClientNetwork.joinUnstartedGame(gameCode, TitleScreen.UserID);
						if(r.getResult().equals("gameFull")){
							String[] da2 = new String[] {"yes", "no"};
							int res = JOptionPane.showOptionDialog(null, "This game is full. Join as a spectator?", "Spectate?",
							        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
							        null, da2, da2[0]);
							if(res == 0) {
								removeAll();
								setVisible(false);
								Game g = ClientNetwork.joinGameAsSpectator(TitleScreen.UserID, gameCode);
								new SpecInGame(TitleScreen.UserProfile,g.getPlayers(),g);
							}
						}
						else if(r.getResult().equals("noSuchGame")){
							JOptionPane.showMessageDialog(null, "No game found.");
						}else {
						UnstartedGame game = new UnstartedGame(r.getGameId());
						game.setReady(r.getReadyList());
						game.setPlayers(r.getPlayerList());
						MatchLobby.main(args, game);
						setVisible(false);
						removeAll();
						}
					} else {
						System.out.println(TitleScreen.UserID);
						Game g = ClientNetwork.joinGameAsSpectator(TitleScreen.UserID, gameCode);
						if(g.equals(null)) {
							JOptionPane.showMessageDialog(null, "The game has not started yet.");
						} else {
							removeAll();
							setVisible(false);
							new SpecInGame(TitleScreen.UserProfile,g.getPlayers(),g);
						}
					}
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, "No game found. The game may not be started yet.");
				}
			}	
	       });	
		this.add(btnJoin);
		
		JRadioButton spectator = (JRadioButton)CreateUI.MakeUIObject("Spectator",100, 565, 100, 15,"radio");
		ButtonGroup group = new ButtonGroup();
		group.add(spectator);
		if(TitleScreen.UserProfile.getAccountType() == 0 || TitleScreen.UserProfile.getAccountType() == 1) {
			JRadioButton player = (JRadioButton)CreateUI.MakeUIObject("Player",100, 550, 100, 15,"radio");
			//group.add(player);
			//panel.add(player);
		}
//		if(TitleScreen.UserProfile.getAccountType() == 1) {
//			JRadioButton admin = (JRadioButton)CreateUI.MakeUIObject("Admin",100, 580, 100, 15,"radio");
//			group.add(admin);
//			panel.add(admin);
//		}
		//panel.add(spectator);
		
		if(TitleScreen.UserProfile.getAccountType() != 2) {
			JButton btnCreate = (JButton)CreateUI.MakeUIObject("Create Match",350, 450, 200, 100,"button");
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					gameCode = ClientNetwork.createUnstartedGame(TitleScreen.UserID);
					UnstartedGame game = new UnstartedGame(gameCode, TitleScreen.UserProfile);
					MatchLobby.main(args, game);
					setVisible(false);
					removeAll();
		          }	
		       });
			this.add(btnCreate);
			
			JButton btnProfile = (JButton)CreateUI.MakeUIObject("Profile",525, 0, 100, 50,"button");
			btnProfile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					User.main(args);
					setVisible(false);
					removeAll();
		          }	
		       });
			this.add(btnProfile);
		}
		
//		JLabel gamesRunning = (JLabel)CreateUI.MakeUIObject("Amount of Games Running:",350, 530, 200,50,"label");
//		this.add(gamesRunning);
		
		JLabel prompt = (JLabel)CreateUI.MakeUIObject("Enter Room Code or Select Create Match",210, 200, 1000, 20,"label");
		this.add(prompt);
		
		JButton btnBack = (JButton)CreateUI.MakeUIObject("Back",0, 0, 100, 50,"button");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MainMenu.main(args);
				setVisible(false);
				removeAll();
	          }	
	       });
		this.add(btnBack);
		this.revalidate();
		this.repaint();
	}	
	
	 /**
 	 * The main method.
 	 *
 	 * @param args the arguments
 	 */
 	public static void main(String[] args) {
	       FindGame menu = new FindGame();
	       menu.setVisible(true);
	    }
}
