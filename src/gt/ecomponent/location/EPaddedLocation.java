package gt.ecomponent.location;

import gt.ecomponent.EComponentLocation;

public class EPaddedLocation implements EComponentLocation {
    private final EComponentLocation pl;
    private double top;
    private double bottom;
    private double left;
    private double right;

    public EPaddedLocation(EComponentLocation pl, double top, double bottom, double left, double right) {
        this.pl = pl;
        setPadding(top, bottom, left, right);
    }

    @Override
    public double getX0() {
        return pl.getX0() + left;
    }

    @Override
    public double getY0() {
        return pl.getY0() + top;
    }

    @Override
    public double getX1() {
        return pl.getX1() - right;
    }

    @Override
    public double getY1() {
        return pl.getY1() - bottom;
    }

    public void setPadding(double top, double bottom, double left, double right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
}
