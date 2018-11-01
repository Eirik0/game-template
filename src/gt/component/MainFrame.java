package gt.component;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gt.gameloop.FixedDurationGameLoop;

public class MainFrame {
    private final JFrame mainFrame;
    private final JComponent contentPane;

    public MainFrame(String title, JComponent contentPane) {
        this.contentPane = contentPane;
        mainFrame = new JFrame(title);
        mainFrame.setBackground(ComponentCreator.backgroundColor());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setFocusable(false);
        mainFrame.setContentPane(contentPane);
    }

    public void show(Runnable startRunnable) {
        SwingUtilities.invokeLater(() -> {
            mainFrame.pack();

            startRunnable.run();
            FixedDurationGameLoop.startLoop();

            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);

            contentPane.requestFocus();
        });
    }
}
