package LearningHowToSwing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleMenuEx.
 */
public class SimpleMenuEx extends JFrame {

    /**
     * Instantiates a new simple menu ex.
     */
    public SimpleMenuEx() {

        initUI();
    }

    /**
     * Inits the UI.
     */
    private void initUI() {
        
        createMenuBar();

        setTitle("Simple menu");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Creates the menu bar.
     */
    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("BD.png");

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenu Options = new JMenu("Options");
        file.setMnemonic(KeyEvent.VK_O);

        JMenuItem eMenuItem = new JMenuItem("Exit");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        
        JMenuItem save = new JMenuItem("Save");
        save.setToolTipText("This does nothing");
        
        JMenu BD = new JMenu("Big Ol' Daddy");
        BD.setToolTipText("Don't Click This");
        
        JMenuItem breaker = new JMenuItem("Opps!", icon);
        breaker.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        file.add(eMenuItem);
        Options.add(save);
        BD.add(breaker);

        menubar.add(file);
        menubar.add(Options);
        menubar.add(BD);

        setJMenuBar(menubar);
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SimpleMenuEx ex = new SimpleMenuEx();
            ex.setVisible(true);
        });
    }
}
