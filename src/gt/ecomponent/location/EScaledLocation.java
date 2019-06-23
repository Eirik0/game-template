package gt.ecomponent.location;

import gt.ecomponent.EComponentLocation;

public class EScaledLocation implements EComponentLocation {
    private final EComponentLocation pl;
    private final double scaleX;
    private final double scaleY;

    public EScaledLocation(EComponentLocation pl, double scaleX, double scaleY) {
        this.pl = pl;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public double getX0() {
        return pl.getX0() * scaleX;
    }

    @Override
    public double getY0() {
        return pl.getY0() * scaleY;
    }

    @Override
    public double getX1() {
        return pl.getX1() * scaleX;
    }

    @Override
    public double getY1() {
        return pl.getY1() * scaleY;
    }
}
