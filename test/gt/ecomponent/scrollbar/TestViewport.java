package gt.ecomponent.scrollbar;

import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.IGraphics;

public class TestViewport implements EViewport {
    private final ViewportWindow window;

    private final double width;
    private final double height;

    public TestViewport(EComponentLocation cl, double width, double height) {
        window = new ViewportWindow(this, 0, 0, cl.getWidth(), cl.getHeight(), 0, 0);
        this.width = width;
        this.height = height;
    }

    @Override
    public ViewportWindow getWindow() {
        return window;
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void drawOn(IGraphics g) {
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
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
    public void focusLost(boolean fromClick) {
    }
}
