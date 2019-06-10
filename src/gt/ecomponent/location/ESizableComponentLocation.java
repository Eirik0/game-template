package gt.ecomponent.location;

import gt.ecomponent.EComponentLocation;
import gt.gameentity.Sizable;

public class ESizableComponentLocation implements Sizable, EComponentLocation {
    private final double x0;
    private final double y0;
    private double width;
    private double height;

    public ESizableComponentLocation(double x0, double y0, double width, double height) {
        this.x0 = x0;
        this.y0 = y0;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getX0() {
        return x0;
    }

    @Override
    public double getY0() {
        return y0;
    }

    @Override
    public double getX1() {
        return x0 + width - 1;
    }

    @Override
    public double getY1() {
        return y0 + height - 1;
    }
}
