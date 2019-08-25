package gt.component;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import gt.gameloop.FixedDurationGameLoop;
import gt.gamestate.GameState;
import gt.gamestate.GameStateManager;
import gt.gamestate.NullGameState;
import gt.gamestate.UserInput;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
    private final GamePanelController controller;
    private final GameStateManager gameStateManager;

    public GamePanel(String name) {
        super(null, false);

        JavaGameImageDrawer imageDrawer = new JavaGameImageDrawer();
        controller = new GamePanelController(name, this, imageDrawer, NullGameState.getInstance());
        MouseTracker mouseTracker = new MouseTracker(input -> FixedDurationGameLoop.enqueueUserInput(controller, input));
        gameStateManager = new GameStateManager(this, mouseTracker, imageDrawer);

        setBackground(ComponentCreator.backgroundColor());
        setIgnoreRepaint(true);

        GameMouseAdapter mouseAdapter = new GameMouseAdapter(mouseTracker);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);

        addKeyListener(new GameKeyListener(input -> FixedDurationGameLoop.enqueueUserInput(controller, input)));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                FixedDurationGameLoop.enqueueUserInput(controller, UserInput.RESIZE);
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

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        controller.drawImageOn(g);
    }
}
