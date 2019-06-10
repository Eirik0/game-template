package gt.ecomponent.location;

import gt.ecomponent.EComponentLocation;

public class EStruttedLocation implements EComponentLocation {
    private final EComponentLocation pl;
    private final GlueSide glueSide;
    private final double width;

    public EStruttedLocation(EComponentLocation pl, GlueSide side, double width) {
        this.pl = pl;
        glueSide = side;
        this.width = width;
    }

    @Override
    public double getX0() {
        switch (glueSide) {
        case LEFT:
            return pl.getX1() + 1;
        case RIGHT:
            return pl.getX0() - width;
        default:
            return pl.getX0();
        }
    }

    @Override
    public double getY0() {
        switch (glueSide) {
        case TOP:
            return pl.getY1() + 1;
        case BOTTOM:
            return pl.getY0() - width;
        default:
            return pl.getY0();
        }
    }

    @Override
    public double getX1() {
        switch (glueSide) {
        case LEFT:
            return pl.getX1() + width;
        case RIGHT:
            return pl.getX0() - 1;
        default:
            return pl.getX1();
        }
    }

    @Override
    public double getY1() {
        switch (glueSide) {
        case TOP:
            return pl.getY1() + width;
        case BOTTOM:
            return pl.getY0() - 1;
        default:
            return pl.getY1();
        }
    }
}
