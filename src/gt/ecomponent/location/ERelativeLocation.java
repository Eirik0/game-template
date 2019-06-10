package gt.ecomponent.location;

import gt.ecomponent.EComponentLocation;

public class ERelativeLocation implements EComponentLocation {
    private final EComponentLocation pl;
    private final EComponentLocation cl;

    public ERelativeLocation(EComponentLocation pl, EComponentLocation cl) {
        this.pl = pl;
        this.cl = cl;
    }

    @Override
    public double getX0() {
        return pl.getX0() + cl.getX0();
    }

    @Override
    public double getY0() {
        return pl.getY0() + cl.getY0();
    }

    @Override
    public double getX1() {
        return pl.getX0() + cl.getX1();
    }

    @Override
    public double getY1() {
        return pl.getY0() + cl.getY1();
    }
}
