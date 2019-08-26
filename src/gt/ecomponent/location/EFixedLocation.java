package gt.ecomponent.location;

import gt.ecomponent.list.EComponentLocation;

public class EFixedLocation implements EComponentLocation {
    private final double x0;
    private final double y0;
    private final double x1;
    private final double y1;

    public EFixedLocation(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public static EFixedLocation fromRect(double x0, double y0, double width, double height) {
        return new EFixedLocation(x0, y0, x0 + width - 1, y0 + height - 1);
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
        return x1;
    }

    @Override
    public double getY1() {
        return y1;
    }
}
