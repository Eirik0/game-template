package gt.ecomponent.scrollbar;

import gt.ecomponent.EComponentLocation;
import gt.gameentity.IGraphics;

public class TestViewport implements EViewport {
    private final double width;
    private final double height;

    private double viewX = 0;
    private double viewY = 0;

    private double viewWidth;
    private double viewHeight;

    private final EScrollPaneViewLocation vl;

    public TestViewport(EComponentLocation cl, double width, double height) {
        this.width = width;
        this.height = height;
        vl = new EScrollPaneViewLocation(cl, this);
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void drawOn(IGraphics g) {
    }

    @Override
    public EComponentLocation getViewLocation() {
        return vl;
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
