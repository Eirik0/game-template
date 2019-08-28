package gt.ecomponent.scrollbar;

import gt.gameentity.IGraphics;

public class TestViewport implements EViewport {
    private final double width;
    private final double height;

    private double viewX = 0;
    private double viewY = 0;

    private double viewWidth;
    private double viewHeight;

    public TestViewport(double width, double height) {
        this.width = width;
        this.height = height;
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
    public double getViewX() {
        return viewX;
    }

    @Override
    public double getViewY() {
        return viewY;
    }

    @Override
    public double getViewWidth() {
        return viewWidth;
    }

    @Override
    public double getViewHeight() {
        return viewHeight;
    }

    @Override
    public double getXIncrement() {
        return 0;
    }

    @Override
    public double getYIncrement() {
        return 0;
    }

    @Override
    public void setPosition(double viewX, double viewY) {
        this.viewX = viewX;
        this.viewY = viewY;
    }

    @Override
    public void setViewSize(double viewWidth, double viewHeight) {
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
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
