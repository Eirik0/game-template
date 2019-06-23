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
    public boolean setMouseOver(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        return false;
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
    }
}
