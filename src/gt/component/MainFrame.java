package gt.component;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gt.gameloop.FixedDurationGameLoop;
import gt.settings.GameSettings;

public class MainFrame {
    private final JFrame mainFrame;
    private final GamePanel gamePanel;

    public MainFrame(String frameTitle) {
        this(null, frameTitle, false);
    }

    public MainFrame(String projectName, String frameTitle) {
        this(projectName, frameTitle, true);
    }

    private MainFrame(String projectName, String frameTitle, boolean useGameSettings) {
        gamePanel = new GamePanel(frameTitle);
        gamePanel.setPreferredSize(new Dimension(ComponentCreator.DEFAULT_WIDTH, ComponentCreator.DEFAULT_HEIGHT));
        ComponentCreator.setCrossPlatformLookAndFeel();
        if (useGameSettings) {
            GameSettings.loadSettings(projectName);
        }
        mainFrame = new JFrame(frameTitle);
        mainFrame.setIgnoreRepaint(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setFocusable(false);
        mainFrame.setContentPane(gamePanel);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (useGameSettings) {
                    GameSettings.saveSettings(projectName);
                }
            }
        });
    }

    public GamePanel getGamePanel() {
        return gamePanel;
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
