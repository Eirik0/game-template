package gt.ecomponent.scrollbar;

import gt.gameentity.Sized;
import gt.gameentity.SizedSizable;

public class ViewportWindow implements SizedSizable {
    private final Sized view;

    private double x0;
    private double y0;

    private double width;
    private double height;

    private final double xIncrement;
    private final double yIncrement;

    public ViewportWindow(Sized view, double x0, double y0, double width, double height, double xIncrement, double yIncrement) {
        this.view = view;
        this.x0 = x0;
        this.y0 = y0;
        this.width = width;
        this.height = height;
        this.xIncrement = xIncrement;
        this.yIncrement = yIncrement;
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double getTruncatedY0(int itemHeight) {
        double rounded = (int) y0 / itemHeight * itemHeight;
        return y0 - rounded < itemHeight / 2 ? rounded : rounded + itemHeight;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public boolean containsPoint(double screenX, double screenY) {
        return screenX >= 0 && screenX < width && screenY >= 0 && screenY < height;
    }

    public double getXIncrement() {
        return xIncrement;
    }

    public double getYIncrement() {
        return yIncrement;
    }

    public void setPosition(double x0, double y0) {
        this.x0 = x0;
        this.y0 = y0;
    }

    @Override
    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void move(double dx, double dy) {
        double x = Math.min(Math.max(0, x0 + dx), Math.max(0, view.getWidth() - width));
        double y = Math.min(Math.max(0, y0 + dy), Math.max(0, view.getHeight() - height));
        setPosition(x, y);
    }
}
