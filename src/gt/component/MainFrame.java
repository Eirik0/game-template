package gt.component;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gt.gameloop.FixedDurationGameLoop;

public class MainFrame {
    private final JFrame mainFrame;
    private final GamePanel gamePanel;

    public MainFrame(String title, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        mainFrame = new JFrame(title);
        mainFrame.setIgnoreRepaint(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setFocusable(false);
        mainFrame.setContentPane(gamePanel);
    }

    public JFrame getFrame() {
        return mainFrame;
    }

    public void show() {
        SwingUtilities.invokeLater(() -> {
            mainFrame.pack();

            gamePanel.addToGameLoop();
            FixedDurationGameLoop.startLoop();

            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);

            gamePanel.requestFocus();
        });
    }
}
