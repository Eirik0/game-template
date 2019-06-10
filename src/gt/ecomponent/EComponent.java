package gt.ecomponent;

import gt.gameentity.GameEntity;

public interface EComponent extends GameEntity {
    boolean setMouseOver(int screenX, int screenY);

    boolean setMousePressed(int screenX, int screenY);

    void setMouseReleased(int screenX, int screenY);

    boolean setMouseScrolled(int screenX, int screenY, double wheelDelta);

    void focusLost(boolean fromClick);
}
