package gt.component;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import gt.drawable.GameState;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
    private final String name;
    private final GamePanelController controller;

    public GamePanel(String name, GameState initialState) {
        this.name = name;

        controller = new GamePanelController(this, initialState);

        setBackground(ComponentCreator.backgroundColor());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                controller.checkResized(getWidth(), getHeight());
            }
        });
    }

    public void addToGameLoop() {
        controller.addToGameLoop(name, this::repaint);
    }

    public void removeFromGameLoop() {
        controller.removeFromGameLoop(name);
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        controller.drawOn(g);
    }
}
