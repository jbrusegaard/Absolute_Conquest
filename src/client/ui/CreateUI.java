package client.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateUI.
 *
 * @author Harry Mitchell
 * @author Jeremiah Brusegaard
 */
public class CreateUI {
	
	/**
	 * Make UI object.
	 *
	 * @param text the text
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param type the type
	 * @return the object
	 */
	public static Object MakeUIObject(String text, int x, int y, int width, int height, String type) {
		Object jb = null;
		switch(type) {
		case("button"):
			jb = new JButton(text);
			((Component) jb).setBounds(x, y, width, height);
			break;
		case("label"):
			jb = new JLabel(text);
			((Component) jb).setBounds(x, y, width, height);
			break;
		case("text"):
			jb = new JTextField();
			((Component) jb).setBounds(x, y, width, height);
			break;
		case("pass"):
			jb = new JPasswordField();
			((Component) jb).setBounds(x, y, width, height);
			break;
		case("radio"):
			jb = new JRadioButton(text);
			((Component) jb).setBounds(x, y, width, height);
			break;
		case("Icon"):
			jb = new ImageIcon(text);
			break;
		}
		return jb;
	}
	
	/**
	 * Make UI object.
	 *
	 * @param i the i
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param type the type
	 * @return the object
	 */
	public static Object MakeUIObject(ImageIcon i, int x, int y, int width, int height, String type) {
		Object jb = null;
		if(type.equals("label"))
			jb = new JLabel(i);
		else if(type.equals("button"))
			jb = new JButton(i);
		((Component) jb).setBounds(x, y, width, height);
		return jb;
	}
	
	/**
	 * Make UI object.
	 *
	 * @param text the text
	 * @param type the type
	 * @return the object
	 */
	public static Object MakeUIObject(String text, String type) {
		Object jb = null;
		switch(type) {
		case("button"):
			jb = new JButton(text);
			break;
		case("label"):
			jb = new JLabel(text);
			break;
		case("text"):
			jb = new JTextField();
			break;
		case("pass"):
			jb = new JPasswordField();
			break;
		case("radio"):
			jb = new JRadioButton(text);
			break;
		case("Icon"):
			jb = new ImageIcon(text);
			break;
		}
		return jb;
	}
	
	/**
	 * Make UI object.
	 *
	 * @param i the i
	 * @param type the type
	 * @return the object
	 */
	public static Object MakeUIObject(ImageIcon i, String type) {
		Object jb = null;
		if(type.equals("label"))
			jb = new JLabel(i);
		else if(type.equals("button"))
			jb = new JButton(i);
		return jb;
	}
	
	/**
	 * Make 2 line button.
	 *
	 * @param j1 the j 1
	 * @param j2 the j 2
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param tooltip the tooltip
	 * @return the j button
	 */
	public static JButton Make2LineButton(JLabel j1, JLabel j2, int x, int y, int width, int height, String tooltip) {
		JButton jb = new JButton();
		jb.setLayout(new BorderLayout());
		jb.add(j1);
		jb.add(j2);
		jb.add(BorderLayout.NORTH, j1);
		jb.add(BorderLayout.SOUTH, j2);
		jb.setToolTipText(tooltip);
		jb.setBounds(x, y, width, height);
		return jb;
	}

}
