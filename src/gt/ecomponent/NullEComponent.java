package gt.ecomponent;

import gt.gameentity.IGraphics;

public class NullEComponent implements EComponent {
    private static final NullEComponent instance = new NullEComponent();

    public static NullEComponent getInstance() {
        return instance;
    }

    private NullEComponent() {
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void drawOn(IGraphics g) {
    }

    @Override
    public boolean setMouseOver(double screenX, double screenY) {
        return false;
    }

    @Override
    public boolean setMousePressed(double screenX, double screenY) {
        return false;
    }

    @Override
    public void setMouseReleased(double screenX, double screenY) {
    }

    @Override
    public boolean setMouseScrolled(double screenX, double screenY, double wheelDelta) {
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
    }
}
