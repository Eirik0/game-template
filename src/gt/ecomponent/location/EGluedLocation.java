package gt.ecomponent.location;

import gt.ecomponent.list.EComponentLocation;

public class EGluedLocation implements EComponentLocation {
    private final EComponentLocation pl;

    private final boolean x0left;
    private final boolean y0top;
    private final boolean x1left;
    private final boolean y1top;

    private double dx0;
    private double dy0;
    private double dx1;
    private double dy1;

    public EGluedLocation(EComponentLocation pl, boolean x0left, boolean y0top, boolean x1left, boolean y1top, double dx0, double dy0, double dx1,
            double dy1) {
        this.pl = pl;
        this.x0left = x0left;
        this.y0top = y0top;
        this.x1left = x1left;
        this.y1top = y1top;
        this.dx0 = dx0;
        this.dy0 = dy0;
        this.dx1 = dx1;
        this.dy1 = dy1;
    }

    @Override
    public double getX0() {
        return (x0left ? pl.getX0() : pl.getX1()) + dx0;
    }

    @Override
    public double getY0() {
        return (y0top ? pl.getY0() : pl.getY1()) + dy0;
    }

    @Override
    public double getX1() {
        return (x1left ? pl.getX0() : pl.getX1()) + dx1;
    }

    @Override
    public double getY1() {
        return (y1top ? pl.getY0() : pl.getY1()) + dy1;
    }

    public void setDeltas(double dx0, double dy0, double dx1, double dy1) {
        this.dx0 = dx0;
        this.dy0 = dy0;
        this.dx1 = dx1;
        this.dy1 = dy1;
    }
}
