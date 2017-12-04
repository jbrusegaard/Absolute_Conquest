package client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import client.ClientNetwork;
//import javafx.scene.layout.Border;
import response.LoginResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class TextChat.
 *
 * @author Jeremiah Brusegaard
 * 
 * Chat panel for ingame and lobby screens
 */

public class TextChat extends JPanel{

/** The panel. */
private JPanel panel;

/** The chat box. */
private JTextArea chatBox;

/** The prev msg. */
private String prevMsg;

/** The location. */
private String location;

/** The msg time limit ms. */
private long msgTimeLimitMs = 500;

/** The msg start time. */
private long msgStartTime;

/** The msg end time. */
private long msgEndTime;
	
	/**
	 * Instantiates a new text chat.
	 *
	 * @param x the x
	 * @param y the y
	 * @param location the location
	 */
	public TextChat(int x,int y, String location ) {
		panel = new JPanel();
		panel.setLayout(null);
		this.location = location;
		setSize(700, 596);
		JButton send = new JButton("Send");
		//send.setBounds(400, 450, 65, 50);
		send.setBounds(x+300, y+305,65, 50 );
		JTextField message = new JTextField();
		//message.setBounds(100, 450, 300, 50);
		message.setBounds(x, y+305, 300, 50);
		DefaultCaret caret = (DefaultCaret)message.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 
		chatBox = new JTextArea(5,30);
		chatBox.setEditable(false);
		chatBox.setFont(new Font("Calbri", Font.PLAIN, 14));
        chatBox.setLineWrap(true);
        JScrollPane scroll = new JScrollPane();
        //scroll.setBounds(100, 45, 365, 400);
        scroll.setBounds(x, y,365, 300 );
        scroll.getViewport().add(chatBox);
        panel.add(scroll,BorderLayout.CENTER);
		panel.add(send);
		panel.add(message);
		this.prevMsg = "";
		this.msgStartTime = System.currentTimeMillis();
		send.addActionListener((ActionEvent event) -> {
			msgEndTime = System.currentTimeMillis();
			String msg = message.getText();
			String userName = TitleScreen.UserProfile.getUsername();
			if((msgEndTime - msgStartTime) < msgTimeLimitMs) {
				JOptionPane.showMessageDialog(null, "Too many messages within the " + msgTimeLimitMs + "ms limit");
			}else if(msg.equals("")){
				JOptionPane.showMessageDialog(null, "Message can't be empty");
			}else if(msg.equals(prevMsg)) {
				JOptionPane.showMessageDialog(null, "Message already entered please enter a different message");
			}else {
//				System.out.println("MSG: " + msg);
//				System.out.println("Prev MSG: " + prevMsg);
				String sentMsg = "[" + userName + "]: " +  message.getText() + "\n";
				if(location.equals("lobby")) {
					ClientNetwork.sendChatMessage(ClientNetwork.lobby.getGame().getGameID(),location,userName, sentMsg);	
				} else if(location.equals("ingame") || location.equals("spectator")) {
					ClientNetwork.sendChatMessage(ClientNetwork.game.getGame().getGameID(),location,userName, sentMsg);
				}
				prevMsg = new String(msg);
				
			}
			message.setText("");
			msgStartTime = msgEndTime;
        });
		message.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	msgEndTime = System.currentTimeMillis();
				String msg = message.getText();
				String userName = TitleScreen.UserProfile.getUsername();
				if((msgEndTime - msgStartTime) < msgTimeLimitMs) {
					JOptionPane.showMessageDialog(null, "Too many messages within the " + msgTimeLimitMs + "ms limit");
				}else if(msg.equals("")){
					JOptionPane.showMessageDialog(null, "Message can't be empty");
				}else if(msg.equals(prevMsg)) {
					JOptionPane.showMessageDialog(null, "Message already entered please enter a different message");
				}else {
					System.out.println("MSG: " + msg);
					System.out.println("Prev MSG: " + prevMsg);
					String sentMsg = "[" + userName + "]: " +  message.getText() + "\n";
					if(location.equals("lobby")) {
						ClientNetwork.sendChatMessage(ClientNetwork.lobby.getGame().getGameID(),location,userName, sentMsg);	
					} else if(location.equals("ingame") || location.equals("spectator")) {
						ClientNetwork.sendChatMessage(ClientNetwork.game.getGame().getGameID(),location,userName, sentMsg);
					}
					prevMsg = new String(msg);
				}
				message.setText("");
				msgStartTime = msgEndTime;
		    }
		});
	}
	
	/**
	 * Gets the panel.
	 *
	 * @return the panel
	 */
	public JPanel getPanel() {
		return this.panel;
	}
	
	/**
	 * Receive MSG.
	 *
	 * @param msg the msg
	 */
	public void receiveMSG(String msg) {
		chatBox.append(msg);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		//JPanel frame = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(750, 750);
		frame.setDefaultCloseOperation(3);
		TextChat chat = new TextChat(50, 100, "lobby");
		frame.add(chat.getPanel());
		//getContentPane.add(frame)
		EventQueue.invokeLater(() -> {
            frame.setVisible(true);
        });
	}
}
