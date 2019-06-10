package gt.ecomponent.location;

import gt.ecomponent.EComponentLocation;

public class EScaledLocation implements EComponentLocation {
    private final EComponentLocation cl;
    private final double scale;

    public EScaledLocation(EComponentLocation cl, double scale) {
        this.cl = cl;
        this.scale = scale;
    }

    @Override
    public double getX0() {
        return cl.getX0() + scale * (cl.getWidth() - 1);
    }

    @Override
    public double getY0() {
        return cl.getY0() + scale * (cl.getHeight() - 1);
    }

    @Override
    public double getX1() {
        return cl.getX0() + (1 - scale) * (cl.getWidth() - 1);
    }

    @Override
    public double getY1() {
        return cl.getY0() + (1 - scale) * (cl.getHeight() - 1);
    }
}
