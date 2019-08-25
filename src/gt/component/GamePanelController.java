package gt.component;

import java.awt.Graphics;

import gt.gameentity.FpsTracker;
import gt.gameentity.Sizable;
import gt.gameloop.GameLoopItem;
import gt.gamestate.GameState;
import gt.gamestate.UserInput;

public class GamePanelController implements GameLoopItem, Sizable {
    private final String name;

    private final GamePanel gamePanel;
    private final BufferedJavaGameImage gameImage;

    private final JavaGameImageDrawer imageDrawer;

    private GameState currentState;
    private boolean showFpsToggled = false;

    public GamePanelController(String name, GamePanel gamePanel, GameState initialState) {
        this.name = name;
        this.gamePanel = gamePanel;
        imageDrawer = new JavaGameImageDrawer();
        gameImage = new BufferedJavaGameImage(imageDrawer.newGameImage(), imageDrawer.newGameImage());

        currentState = initialState;
    }

    @Override
    public void update(double dt) {
        currentState.update(dt);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void draw() {
        currentState.drawOn(gameImage.getGraphics());
        if (showFpsToggled) {
            FpsTracker.getInstance().drawOn(gameImage.getGraphics());
        }
        gameImage.commitBuffer();
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

    @Override
    public void handleUserInput(UserInput input) {
        switch (input) {
        case RESIZE:
            setSize(gamePanel.getWidth(), gamePanel.getHeight());
            return;
        case F3_KEY_PRESSED:
            showFpsToggled = !showFpsToggled;
            break;
        }
        currentState.handleUserInput(input);
    }

    public JavaGameImageDrawer getGameImageDrawer() {
        return imageDrawer;
    }
}
