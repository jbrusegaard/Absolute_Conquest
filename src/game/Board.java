package game;


import java.awt.Container;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
public class Board extends JPanel {

	/** The game board. */
	//holds the tiles that make up the board
	private Tile[][] gameBoard;

/*	//draws the board on the screen
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    int k = 6;
	    int j = 0;
	    int c = 0;
	    boolean hit = false;
	    while( k>= 6)
	    {
	    	if(k <= 12 && !hit) {
	    		for(int i = 0; i < k; i++)
	    	    	{
	    	    		new Tile(36+(j*54), 445+(i*65)-(j*36)).draw(g);
	    	    	}	    		
	    		k++;
	    		j++;
	    	}
	    	else {
	    		hit = true;
	    		k--;
	    		c++;
	    		if( c <= 6) {
	    		for(int i = 0; i < k-1; i++)
    	    	{
    	    		new Tile(36+(j*54), 376+(i*65)+(j*36)-367).draw(g);
    	    	}
	    		}
	    		j++;
	    	}
	    }
	}*/
	
	/**
 * The main method.
 *
 * @param args the arguments
 */
public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		JPanel pan = new JPanel();
		frame.setTitle("test");
		frame.setSize(3000, 2000);
		
		JPanel panel = new Board();
		JScrollPane pane = new JScrollPane(panel);
		frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		 	}
		});
		//setDefaultCloseOperation(EXIT_ON_CLOSE);;
		frame.add(pane);
		frame.setVisible(true);
	}
}
