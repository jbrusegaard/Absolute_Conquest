package LearningHowToSwing;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

// TODO: Auto-generated Javadoc
/**
 * The Class FrameIconEx.
 */
public class FrameIconEx extends JFrame {

    /**
     * Instantiates a new frame icon ex.
     */
    public FrameIconEx() {
        
        initUI();
    }
    
    /**
     * Inits the UI.
     */
    private void initUI() {
        
        ImageIcon webIcon = new ImageIcon("BD.png");

        setIconImage(webIcon.getImage());

        setTitle("Noobs, Big Daddies, and Dragons");
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
            FrameIconEx ex = new FrameIconEx();
            ex.setVisible(true);
        });
    }
}
