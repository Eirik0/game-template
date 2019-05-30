package gt.component;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import gt.gameloop.FixedDurationGameLoop;
import gt.gamestate.GameState;
import gt.gamestate.NullGameState;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
    private final GamePanelController controller;

    public GamePanel(String name) {
        super(null, false);

        controller = new GamePanelController(name, this, NullGameState.getInstance());

        setBackground(ComponentCreator.backgroundColor());
        setIgnoreRepaint(true);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                controller.setSize(getWidth(), getHeight());
            }
        });
    }

    public void addToGameLoop() {
        FixedDurationGameLoop.addItem(controller);
    }

    public void removeFromGameLoop() {
        FixedDurationGameLoop.removeItem(controller);
    }

    public void setGameState(GameState gameState) {
        controller.setGameState(gameState);
        if (getWidth() > 0 && getHeight() > 0) {
            controller.setSize(getWidth(), getHeight());
        }
    }

    public MouseTracker getMouseTracker() {
        return controller.getMouseTracker();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        controller.drawImageOn(g);
    }
}
