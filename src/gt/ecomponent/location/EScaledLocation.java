package gt.ecomponent.location;

import gt.ecomponent.list.EComponentLocation;
import gt.util.EMath;

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
        return EMath.round(pl.getX0() * scaleX);
    }

    @Override
    public double getY0() {
        return EMath.round(pl.getY0() * scaleY);
    }

    @Override
    public double getX1() {
        return EMath.round(pl.getX1() * scaleX);
    }

    @Override
    public double getY1() {
        return EMath.round(pl.getY1() * scaleY);
    }
}
