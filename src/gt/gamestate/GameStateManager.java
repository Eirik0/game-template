package gt.gamestate;

import gt.component.GamePanel;
import gt.component.IMouseTracker;
import gt.gameentity.IGameImageDrawer;

public class GameStateManager {
    private final GamePanel mainPanel;
    private final IMouseTracker mouseTracker;
    private final IGameImageDrawer imageDrawer;

    public GameStateManager(GamePanel mainPanel, IMouseTracker mouseTracker, IGameImageDrawer imageDrawer) {
        this.mainPanel = mainPanel;
        this.mouseTracker = mouseTracker;
        this.imageDrawer = imageDrawer;
    }

    public void setGameState(GameState gameState) {
        mainPanel.setGameState(gameState);
    }

    public IMouseTracker getMouseTracker() {
        return mouseTracker;
    }

    public IGameImageDrawer getImageDrawer() {
        return imageDrawer;
    }

    public void requestFocus() {
        mainPanel.requestFocus();
    }
}
