package gt.gameloop;

import gt.gameentity.Updatable;

public interface GameLoopItem extends Updatable {
    String getName();

    void draw();
}
