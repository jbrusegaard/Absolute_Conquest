package LearningHowToSwing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// TODO: Auto-generated Javadoc
/**
 * The Class Demo.
 */
public class Demo{

    /**
     * Inits the GUI.
     */
    private void initGUI(){
        AbstractAction buttonPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        };

        JButton submit = new JButton("Submit");
        submit.addActionListener(buttonPressed);

        /*
         * Get the InputMap related to JComponent.WHEN_IN_FOCUSED_WINDOW condition
         * to put an event when A key is pressed
         */
        submit.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,0), "A_pressed");
        /*
         * Add an action when the event key is "A_pressed"
         */
        submit.getActionMap().put("A_pressed", buttonPressed);

        /*
         * Same as above when you press B key
         */
        submit.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B,0), "B_pressed");
        submit.getActionMap().put("B_pressed", buttonPressed);

        JPanel content = new JPanel(new FlowLayout());
        content.add(new JLabel("Test:"));
        content.add(submit);

        JFrame frame = new JFrame("Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(content);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Demo().initGUI();
            }
        });

    }
}