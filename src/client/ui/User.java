package client.ui;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import client.ClientNetwork;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Iterator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;


// TODO: Auto-generated Javadoc
/**
 * The Class User.
 *
 * @author Josh Brenneman
 * 
 * creates the menu for the user profile
 * user can enter the deck editor, change their password
 * or chnage profile picture
 */


public class User extends JPanel{
	
	/** The panel. */
	//holds the panel for the screen
	private JPanel panel;
	
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
		panel = this;
		initUI();
	}
	
	/**
	 * Display pic.
	 *
	 * @param imgData the img data
	 */
	//displays the users profile picture
	public void displayPic(byte [] imgData)
	{ 
		ImageIcon ppic = new ImageIcon(imgData);
		JLabel profilePic = new JLabel(ppic);
		System.out.print("test");
		profilePic.setBounds(70, 50, 500, 500);
		this.add(profilePic);
	}	
	
	/**
	 * Inits the UI.
	 */
	//initulizes the UI
	public final void initUI()
	{
		String[] args = new String[0];
		
		MainWindow.displayPanel(this, 650, 700, TitleScreen.UserProfile.getUsername()+"'s: Profile");
		//panel = new JPanel();
		//getContentPane().add(panel);
		this.setLayout(null);
		setBackground(Color.decode("#3498db"));
		ImageIcon TheFrame = new ImageIcon("pics/The Title picture.png");
		//setIconImage(TheFrame.getImage());
		//setTitle( TitleScreen.UserProfile.getUsername()+"'s: Profile");
	    setSize(650,700);
	   // setLocationRelativeTo(null);
	    //setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
//	    JButton btnDeck = new JButton("Enter Deck Editor");
//	    btnDeck.setBounds(25, 450, 150, 100);
	    JButton btnDeck = (JButton) CreateUI.MakeUIObject("Enter Deck Editor", 25, 450, 150, 100, "button");
	    btnDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				DeckEditor.main(args);
				setVisible(false);
	          }	
	       });
		this.add(btnDeck);
		
		displayPic(ClientNetwork.getProfilePicture(TitleScreen.UserID));
		
		
//		JButton btnEdit = new JButton("Edit Profile Picture");
//		btnEdit.setBounds(225, 450, 150, 100);
		JButton btnEdit = (JButton)CreateUI.MakeUIObject("Edit Profile Picture", 225, 450, 150, 100, "button");
		btnEdit.addActionListener(new OpenFileAction());
		this.add(btnEdit);
		
//		JButton btnCPass = new JButton("Change Password");
//		btnCPass.setBounds(425, 450, 150, 100);
		JButton btnCPass = (JButton) CreateUI.MakeUIObject("Change Password", 425, 450, 150, 100, "button");
		btnCPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String newpass = JOptionPane.showInputDialog("Enter a new password:");
				String newpass2 = JOptionPane.showInputDialog("Renter a new password:");
				if(newpass.equals(newpass2))
				{
					//change the password word on the profile to the new password.
					JOptionPane.showMessageDialog(null,"Password Updated");
				}
				JOptionPane.showMessageDialog(null,"Passwords Did Not Match");
		    }	
		});
		this.add(btnCPass);
		
//		JButton btnBack = new JButton("Back");
//		btnBack.setBounds(10, 10, 75, 50);
		JButton btnBack = (JButton) CreateUI.MakeUIObject("Back", 10, 10, 75, 50, "button");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MainMenu.main(args);
				setVisible(false);
		    }	
		});
		this.add(btnBack);
		
//		JLabel prompt = new JLabel("Select Option or Go Back");
//		prompt.setBounds(250, 200, 1000, 20);
		JLabel prompt = (JLabel) CreateUI.MakeUIObject("Select Option or Go Back", 250, 200, 1000, 20, "label");
		this.add(prompt);
		
		JLabel Name = (JLabel) CreateUI.MakeUIObject(TitleScreen.UserProfile.getUsername()+"'s Profile", 250, 150, 10000, 20, "label");
		this.add(Name);
	}
	
	/**
	 * The Class OpenFileAction.
	 */
	//gets the img file and sends it to the file handler
    private class OpenFileAction extends AbstractAction {

    	/** The img. */
	    byte[] img;
    	
	    /** The arg. */
	    String[] arg = new String[1];   	
    	
	    /* (non-Javadoc)
	     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	     */
	    @Override
        public void actionPerformed(ActionEvent e) {
            
            int update = -1;
        	String[] args;
        	JFileChooser fdia = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
            fdia.addChoosableFileFilter(filter);
            
            int ret = fdia.showDialog(panel, "Open file");

            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fdia.getSelectedFile();
                try {
					update = fileHandler(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            if(update ==0)
            {
            	if(!(img == null)) {
            		//displayPic(img);
            		setVisible(false);
            		//System.out.print("test");
            		main(arg);
            	}
            		
            }
        }
    	
    	/**
	     * File handler.
	     *
	     * @param file the file
	     * @return the int
	     * @throws IOException Signals that an I/O exception has occurred.
	     */
	    //ensure that the file is an img and checks the size, and sends img to server
        public int fileHandler(File file) throws IOException
        {
        	   if(((file.getName().contains(".jpg")) || (file.getName().contains(".jpeg")||(file.getName().contains(".png"))))&& file.exists())
               {
               		if((Files.readAllBytes(file.toPath()).length>43750))
               		{
               			JOptionPane.showMessageDialog(null, "File Size needs to be smaller than 350 KB");
               			return -1;
               		}
               		
        		   	ClientNetwork.setProfilePicture(TitleScreen.UserID, Files.readAllBytes(file.toPath()));
        		   	img = Files.readAllBytes(file.toPath());
        		   	return 0;
               }
               return -1;
        }
    }



	 /**
 	 * The main method.
 	 *
 	 * @param args the arguments
 	 */
 	public static void main(String[] args) {
	       User menu = new User();
	       menu.setVisible(true);
	    }
}

