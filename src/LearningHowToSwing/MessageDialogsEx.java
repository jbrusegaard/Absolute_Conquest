package LearningHowToSwing;


import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageDialogsEx.
 */
public class MessageDialogsEx extends JFrame {
    
    /** The pnl. */
    private JPanel pnl;

    /**
     * Instantiates a new message dialogs ex.
     */
    public MessageDialogsEx() {

        initUI();
    }

    /**
     * Inits the UI.
     */
    private void initUI() {
        
        pnl = (JPanel) getContentPane();

        JButton warBtn = new JButton("Warning");
        JButton errBtn = new JButton("Error");
        JButton queBtn = new JButton("Question");
        JButton infBtn = new JButton("Information");
        
        warBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(pnl, "A deprecated call!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });        

        errBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(pnl, "Could not open file!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        queBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(pnl, "Are you sure to quit?",
                        "Question", JOptionPane.QUESTION_MESSAGE);
            }
        });

        infBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(pnl, "Download completed.",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        createLayout(warBtn, errBtn , queBtn, infBtn);

        setTitle("Message dialogs");
        setSize(300, 200);
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

        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[2]))
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[1])
                        .addComponent(arg[3]))
                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1]))
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[2])
                        .addComponent(arg[3]))
                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gl.linkSize(arg[0], arg[1], arg[2], arg[3]);

        pack();
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MessageDialogsEx md = new MessageDialogsEx();
                md.setVisible(true);
            }
        });
    }
}