package src.SwingTests;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class FileSelectTest.
 */
public class FileSelectTest extends JFrame {

    /** The panel. */
    private JPanel panel;
    
    /** The area. */
    private JTextArea area;

    /**
     * Instantiates a new file select test.
     */
    public FileSelectTest() {

        initUI();
    }

    /**
     * Inits the UI.
     */
    private void initUI() {

        panel = (JPanel) getContentPane();

        area = new JTextArea();

        JScrollPane spane = new JScrollPane();
        spane.getViewport().add(area);

        JToolBar toolbar = createToolBar();

        createLayout(toolbar, spane);

        setTitle("JFileChooser");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Creates the tool bar.
     *
     * @return the j tool bar
     */
    private JToolBar createToolBar() {

        ImageIcon open = new ImageIcon("document-open.png");

        JToolBar toolbar = new JToolBar();
        JButton openb = new JButton(open);

        openb.addActionListener(new OpenFileAction());

        toolbar.add(openb);

        return toolbar;
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

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0], DEFAULT_SIZE, DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[1]))
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(4)
                .addComponent(arg[1])
        );

        pack();
    }

    /**
     * Read file.
     *
     * @param file the file
     * @return the string
     */
    public String readFile(File file) {

        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(
                    file.getAbsolutePath())));
        } catch (IOException ex) {
            Logger.getLogger(FileSelectTest.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return content;
    }

    /**
     * The Class OpenFileAction.
     */
    private class OpenFileAction extends AbstractAction {

        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            JFileChooser fdia = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("Java files", 
                    "java");
            fdia.addChoosableFileFilter(filter);

            int ret = fdia.showDialog(panel, "Open file");

            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fdia.getSelectedFile();
                String text = readFile(file);
                area.setText(text);
            }
        }
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
                FileSelectTest fcd = new FileSelectTest();
                fcd.setVisible(true);
            }
        });
    }
}