package gt.ecomponent;

import gt.ecomponent.location.EScaledLocation;
import gt.gameentity.Sized;

public interface EComponentLocation extends Sized {
    double getX0();

    double getY0();

    double getX1();

    double getY1();

    default boolean containsPoint(int screenX, int screenY) {
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
}
