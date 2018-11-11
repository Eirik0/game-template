package gt.gamestate;

import gt.component.GamePanel;

public class GameStateManager {
    private static final GameStateManager instance = new GameStateManager();

    private GamePanel mainPanel;

    private GameStateManager() {
    }

    public static void setMainPanel(GamePanel mainPanel) {
        instance.mainPanel = mainPanel;
    }

    public static void setGameState(GameState gameState) {
        instance.mainPanel.setGameState(gameState);
    }
}
