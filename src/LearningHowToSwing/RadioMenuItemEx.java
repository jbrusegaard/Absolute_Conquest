package LearningHowToSwing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

// TODO: Auto-generated Javadoc
/**
 * The Class RadioMenuItemEx.
 */
public class RadioMenuItemEx extends JFrame {

    /** The statusbar. */
    private JLabel statusbar;

    /**
     * Instantiates a new radio menu item ex.
     */
    public RadioMenuItemEx() {

        initUI();
    }

    /**
     * Inits the UI.
     */
    private void initUI() {

        createMenuBar();

        statusbar = new JLabel("Easy");
        statusbar.setBorder(BorderFactory.createEtchedBorder());
        add(statusbar, BorderLayout.SOUTH);

        setTitle("JRadioButtonMenuItem");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Creates the menu bar.
     */
    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        JMenu difMenu = new JMenu("Difficulty");
        difMenu.setMnemonic(KeyEvent.VK_F);

        ButtonGroup difGroup = new ButtonGroup();

        JRadioButtonMenuItem eaRMi = new JRadioButtonMenuItem("Easy");
        eaRMi.setSelected(true);
        difMenu.add(eaRMi);

        eaRMi.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusbar.setText("Easy");
            }
        });

        JRadioButtonMenuItem meRMi = new JRadioButtonMenuItem("Medium");
        difMenu.add(meRMi);

        meRMi.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusbar.setText("Medium");
            }
        });

        JRadioButtonMenuItem haRMi = new JRadioButtonMenuItem("Hard");
        difMenu.add(haRMi);

        haRMi.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusbar.setText("Hard");
            }
        });
        
        //Harry Made this
        JRadioButtonMenuItem caRMi = new JRadioButtonMenuItem("Casual");
        difMenu.add(caRMi);

        caRMi.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusbar.setText("Casual");
            }
        });

        difGroup.add(caRMi);
        difGroup.add(eaRMi);
        difGroup.add(meRMi);
        difGroup.add(haRMi);

        menubar.add(difMenu);

        setJMenuBar(menubar);
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            RadioMenuItemEx ex = new RadioMenuItemEx();
            ex.setVisible(true);
        });
    }
}