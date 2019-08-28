package gt.ecomponent.scrollbar;

import gt.ecomponent.EComponent;
import gt.gameentity.Sized;

public interface EViewport extends EComponent, Sized {
    ViewportWindow getWindow();

    @Override
    default boolean setMouseScrolled(double screenX, double screenY, double wheelDelta) {
        ViewportWindow window = getWindow();
        boolean containsPoint = window.containsPoint(screenX, screenY);
        if (containsPoint) {
            window.move(0, wheelDelta * window.getYIncrement());
        }
        return containsPoint;
    }
}
