package gt.gamestate;

import gt.gameentity.IGraphics;

public class NullGameState implements GameState {
    private static final NullGameState instance = new NullGameState();

    private NullGameState() {
    }

    public static NullGameState getInstance() {
        return instance;
    }

    @Override
    public void update(double dt) {
        // Do nothing
    }

    @Override
    public void drawOn(IGraphics g) {
        // Do nothing
    }

    @Override
    public void setSize(int width, int height) {
        // Do nothing
    }

    @Override
    public void handleUserInput(UserInput input) {
        // Do nothing
    }
}
