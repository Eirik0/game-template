package gt.gameloop;

import gt.gameentity.Updatable;
import gt.gameentity.UserInputHandler;

public interface GameLoopItem extends Updatable, UserInputHandler {
    String getName();

    void draw();
}
