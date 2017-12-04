package LearningHowToSwing;

import java.awt.EventQueue;
import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleEx.
 */
public class SimpleEx extends JFrame {

    /**
     * Instantiates a new simple ex.
     */
    public SimpleEx() {

        initUI();
    }

    /**
     * Inits the UI.
     */
    private void initUI() {
        
        setTitle("Simple example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SimpleEx ex = new SimpleEx();
            ex.setVisible(true);
        });
    }
}
