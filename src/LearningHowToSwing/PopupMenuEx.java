package LearningHowToSwing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

// TODO: Auto-generated Javadoc
/**
 * The Class PopupMenuEx.
 */
public class PopupMenuEx extends JFrame {

    /** The pmenu. */
    private JPopupMenu pmenu;

    /**
     * Instantiates a new popup menu ex.
     */
    public PopupMenuEx() {

        initUI();
    }

    /**
     * Inits the UI.
     */
    private void initUI() {

        createPopupMenu();

        setTitle("JPopupMenu");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates the popup menu.
     */
    private void createPopupMenu() {

        pmenu = new JPopupMenu();
        
        JMenuItem maxMi = new JMenuItem("Maximize");
        maxMi.addActionListener((ActionEvent e) -> {
            if (getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        pmenu.add(maxMi);

        JMenuItem quitMi = new JMenuItem("Quit");
        quitMi.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        pmenu.add(quitMi);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON3) {
                    pmenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            PopupMenuEx pm = new PopupMenuEx();
            pm.setVisible(true);
        });
    }
}