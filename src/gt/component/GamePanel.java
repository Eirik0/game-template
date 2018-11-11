package gt.component;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import gt.gamestate.GameState;
import gt.gamestate.NullGameState;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
    private final GamePanelController controller;

    public GamePanel(String name) {
        controller = new GamePanelController(name, this, NullGameState.getInstance());

        setBackground(ComponentCreator.backgroundColor());

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                controller.setSize(getWidth(), getHeight());
            }
        });
    }

    public void addToGameLoop() {
        controller.addToGameLoop();
    }

    public void removeFromGameLoop() {
        controller.removeFromGameLoop();
    }

    public void setGameState(GameState gameState) {
        controller.setGameState(gameState);
        if (getWidth() > 0 && getHeight() > 0) {
            controller.setSize(getWidth(), getHeight());
        }
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        controller.drawImageOn(g);
    }
}
