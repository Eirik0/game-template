package gt.ecomponent;

import gt.gameentity.GameEntity;

public interface EComponent extends GameEntity {
    boolean setMouseOver(double screenX, double screenY);

    boolean setMousePressed(double screenX, double screenY);

    void setMouseReleased(double screenX, double screenY);

    boolean setMouseScrolled(double screenX, double screenY, double wheelDelta);

    void focusLost(boolean fromClick);
}
