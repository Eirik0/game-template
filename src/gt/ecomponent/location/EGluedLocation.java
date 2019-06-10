package gt.ecomponent.location;

import gt.ecomponent.EComponentLocation;

public class EGluedLocation implements EComponentLocation {
    private final EComponentLocation pl;
    private final GlueSide glueSide;
    private final double delta;

    public EGluedLocation(EComponentLocation pl, GlueSide side, double width) {
        this.pl = pl;
        glueSide = side;
        delta = width - 1;
    }

    @Override
    public double getX0() {
        switch (glueSide) {
        case RIGHT:
            return pl.getX1() - delta;
        default:
            return pl.getX0();
        }
    }

    @Override
    public double getY0() {
        switch (glueSide) {
        case BOTTOM:
            return pl.getY1() - delta;
        default:
            return pl.getY0();
        }
    }

    @Override
    public double getX1() {
        switch (glueSide) {
        case LEFT:
            return pl.getX0() + delta;
        default:
            return pl.getX1();
        }
    }

    @Override
    public double getY1() {
        switch (glueSide) {
        case TOP:
            return pl.getY0() + delta;
        default:
            return pl.getY1();
        }
    }
}
