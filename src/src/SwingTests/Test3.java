package src.SwingTests;



import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;

// TODO: Auto-generated Javadoc
/**
 * The Class Test3.
 */
public class Test3 extends JFrame {

	/** The frame. */
	private JFrame frame;

	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Test3 screen = new Test3();
		screen.setVisible(true);
		
	}


	/**
	 * Instantiates a new test 3.
	 */
	public Test3() {
		initialize();
	}


	/**
	 * Initialize.
	 */
	private void initialize() {
		JPanel panel = new JPanel();
		setSize(500, 500);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblYo = new JLabel("yo");
		lblYo.setBounds(0,0,15,20);
		panel.add(lblYo);
		
		JTextField text = new JTextField();
		text.setBounds(50, 50, 300, 20);
		panel.add(text);
		
		JButton btnButton = new JButton("button");
		btnButton.setBounds(100, 100, 200, 75);
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("hello");
			}
		});
		panel.add(btnButton);
	}

}
