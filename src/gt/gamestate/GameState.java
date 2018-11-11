package gt.gamestate;

import gt.gameentity.GameEntity;
import gt.gameentity.Sizable;

public interface GameState extends GameEntity, Sizable {
    void handleUserInput(UserInput input);
}
