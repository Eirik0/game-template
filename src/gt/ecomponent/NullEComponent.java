package gt.ecomponent;

import java.awt.Graphics2D;

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
    public void drawOn(Graphics2D graphics) {
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
