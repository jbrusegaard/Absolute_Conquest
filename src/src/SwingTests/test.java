package src.SwingTests;

	import javax.swing.JFrame;
	import javax.swing.SwingUtilities;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.JButton;
	import javax.swing.*;
	

	// TODO: Auto-generated Javadoc
/**
	 * The Class test.
	 */
	public class test extends JFrame {

	    //public test() {
	      // setTitle("Simple example");
	       //setSize(300, 200);
	       //setLocationRelativeTo(null);
	       //setDefaultCloseOperation(EXIT_ON_CLOSE);
	    /**
    	 * Instantiates a new test.
    	 */
    	//}
	public test() {
		initUI();
	}
	
	/**
	 * Inits the UI.
	 */
	public final void initUI()
	{
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		JButton quitB = new JButton("Quit");
		quitB.setBounds(100,100,100,100);
		  quitB.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent event) {
	               System.exit(0);
	          }
	       });
		  panel.add(quitB);
		  
		  JButton add = new JButton("Add");
			add.setBounds(200,60,100,100);
			  add.addActionListener(new ActionListener() {
		           public void actionPerformed(ActionEvent event) {
		               System.out.println("adding");
		          }
		       });
			  panel.add(add);
		  
		 setTitle("Simple example");
	       setSize(1000,2000);
	       setLocationRelativeTo(null);
	       setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	    
    	/**
    	 * The main method.
    	 *
    	 * @param args the arguments
    	 */
    	public static void main(String[] args) {
	       test ex = new test();
	       ex.setVisible(true);
	    }
	}

