package gt.ecomponent.list;

import gt.ecomponent.location.EGluedLocation;
import gt.ecomponent.location.EScaledLocation;
import gt.ecomponent.location.GlueSide;
import gt.gameentity.Sized;

public interface EComponentLocation extends Sized {
    double getX0();

    double getY0();

    double getX1();

    double getY1();

    default boolean containsPoint(double screenX, double screenY) {
        return screenX >= getX0() && screenX <= getX1() && screenY >= getY0() && screenY <= getY1();
    }

    @Override
    default double getWidth() {
        return getX1() - getX0() + 1;
    }

    @Override
    default double getHeight() {
        return getY1() - getY0() + 1;
    }

    default double getCenterX() {
        return (getX0() + getX1()) / 2;
    }

    default double getCenterY() {
        return (getY0() + getY1()) / 2;
    }

    default EScaledLocation scale(double scaleX, double scaleY) {
        return new EScaledLocation(this, scaleX, scaleY);
    }

    default EGluedLocation createGluedLocation(GlueSide glueSide, double dx0, double dy0, double dx1, double dy1) {
        switch (glueSide) {
        case TOP:
            return new EGluedLocation(this, true, true, false, true, dx0, dy0, dx1, dy1);
        case BOTTOM:
            return new EGluedLocation(this, true, false, false, false, dx0, dy0, dx1, dy1);
        case LEFT:
            return new EGluedLocation(this, true, true, true, false, dx0, dy0, dx1, dy1);
        case RIGHT:
            return new EGluedLocation(this, false, true, false, false, dx0, dy0, dx1, dy1);
        default:
            throw new IllegalStateException("Unknown side: " + glueSide);
        }
    }

    default EGluedLocation createPaddedLocation(double left, double top, double right, double bottom) {
        return new EGluedLocation(this, true, true, false, false, left, top, -right, -bottom);
    }

    default EGluedLocation createRelativeLocation(double x0, double y0, double x1, double y1) {
        return new EGluedLocation(this, true, true, true, true, x0, y0, x1, y1);
    }
}
