package gt.component;

import java.awt.Graphics;

import gt.gameentity.FpsTracker;
import gt.gameentity.Sizable;
import gt.gameloop.FixedDurationGameLoop;
import gt.gameloop.GameLoopItem;
import gt.gamestate.GameState;
import gt.gamestate.UserInput;

public class GamePanelController implements GameLoopItem, Sizable {
    private final String name;

    private final GamePanel gamePanel;
    private final GameImage gameImage = new GameImage();

    private final MouseTracker mouseTracker;

    private GameState currentState;
    private boolean showFpsToggled = false;

    public GamePanelController(String name, GamePanel gamePanel, GameState initialState) {
        this.name = name;
        this.gamePanel = gamePanel;

        currentState = initialState;

        mouseTracker = new MouseTracker(this::handleUserInput);
        GameMouseAdapter mouseAdapter = new GameMouseAdapter(mouseTracker);
        gamePanel.addMouseListener(mouseAdapter);
        gamePanel.addMouseMotionListener(mouseAdapter);
        gamePanel.addMouseWheelListener(mouseAdapter);

        gamePanel.addKeyListener(new GameKeyListener(this::handleUserInput));
    }

    public void addToGameLoop() {
        FixedDurationGameLoop.addItem(name, this);
    }

    public void removeFromGameLoop() {
        FixedDurationGameLoop.removeItem(name);
    }

    @Override
    public void update(double dt) {
        currentState.update(dt);
    }

    @Override
    public void draw() {
        currentState.drawOn(gameImage.getGraphics());
        if (showFpsToggled) {
            FpsTracker.getInstance().drawOn(gameImage.getGraphics());
        }
        gamePanel.repaint();
    }

    public void setGameState(GameState state) {
        currentState = state;
    }

    public void drawImageOn(Graphics g) {
        g.drawImage(gameImage.getImage(), 0, 0, null);
    }

    @Override
    public void setSize(int width, int height) {
        gameImage.setSize(width, height);
        currentState.setSize(width, height);
    }

    private void handleUserInput(UserInput input) {
        if (input == UserInput.F3_KEY_PRESSED) {
            showFpsToggled = !showFpsToggled;
        }
        currentState.handleUserInput(input);
    }

    public MouseTracker getMouseTracker() {
        return mouseTracker;
    }
}
