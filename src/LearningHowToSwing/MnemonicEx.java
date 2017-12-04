package LearningHowToSwing;

import java.awt.Container;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

// TODO: Auto-generated Javadoc
/**
 * The Class MnemonicEx.
 */
public class MnemonicEx extends JFrame {

    /**
     * Instantiates a new mnemonic ex.
     */
    public MnemonicEx() {

        initUI();
    }

    /**
     * Inits the UI.
     */
    private void initUI() {

        JButton btn = new JButton("Button");
        btn.addActionListener((ActionEvent e) -> {
            System.out.println("Button pressed");
        });


        btn.setMnemonic(KeyEvent.VK_B);
        //btn.setMnemonic(KeyEvent.VK_H); //this overrides the line above

        createLayout(btn);

        setTitle("Mnemonics");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Creates the layout.
     *
     * @param arg the arg
     */
    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(200)
        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addGap(200)
        );

        pack();
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MnemonicEx ex = new MnemonicEx();
            ex.setVisible(true);
        });
    }
}
