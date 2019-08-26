package gt.ecomponent.location;

import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.Sized;

public class SizedComponentLocationAdapter implements EComponentLocation {
    private final Sized parent;
    private final double x0;
    private final double y0;

    public SizedComponentLocationAdapter(Sized parent, double x0, double y0) {
        this.parent = parent;
        this.x0 = x0;
        this.y0 = y0;
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
        return parent.getWidth() - 1;
    }

    @Override
    public double getY1() {
        return parent.getHeight() - 1;
    }
}
