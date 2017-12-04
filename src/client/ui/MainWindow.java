package client.ui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// TODO: Auto-generated Javadoc
/**
 * The Class MainWindow.
 */
public class MainWindow extends JFrame{
	
	/** The displayed panel. */
	private static JPanel displayedPanel = new JPanel();
	
	/** The this window. */
	private static MainWindow thisWindow;
	
	/**
	 * Instantiates a new main window.
	 */
	public MainWindow() {
		if(thisWindow == null) {
			thisWindow = this;
			
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			
			ImageIcon TheFrame = (ImageIcon)CreateUI.MakeUIObject("pics/The Title picture.png", 0, 0, 0, 0, "Icon");
			setIconImage(TheFrame.getImage());
			setBackground(Color.decode("#3498db"));
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			displayPanel(new TitleScreen(), 700, 596, "Total Conquest:Noobs, Big Daddies, and Dragons!");
			this.setVisible(true);
		}
		
	}
	
	
	/**
	 * Display panel.
	 *
	 * @param p the p
	 * @param xsize the xsize
	 * @param ysize the ysize
	 * @param title the title
	 */
	public static void displayPanel(JPanel p, int xsize, int ysize, String title) {
		thisWindow.remove(displayedPanel);
		displayedPanel = p;
		thisWindow.add(p);
		//displayedPanel.setVisible(true);
		thisWindow.setTitle(title);
		thisWindow.setSize(xsize, ysize);
		thisWindow.getContentPane().revalidate();
		thisWindow.getContentPane().repaint();
		
	}
}
