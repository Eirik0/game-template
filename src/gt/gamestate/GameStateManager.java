package gt.gamestate;

import gt.component.GamePanel;
import gt.component.MouseTracker;
import gt.gameentity.GameImageDrawer;

public class GameStateManager {
    private final GamePanel mainPanel;
    private final MouseTracker mouseTracker;
    private final GameImageDrawer imageDrawer;

    public GameStateManager(GamePanel mainPanel, MouseTracker mouseTracker, GameImageDrawer imageDrawer) {
        this.mainPanel = mainPanel;
        this.mouseTracker = mouseTracker;
        this.imageDrawer = imageDrawer;
    }

    public void setGameState(GameState gameState) {
        mainPanel.setGameState(gameState);
    }

    public MouseTracker getMouseTracker() {
        return mouseTracker;
    }

    public GameImageDrawer getImageDrawer() {
        return imageDrawer;
    }

    public void requestFocus() {
        mainPanel.requestFocus();
    }
}
