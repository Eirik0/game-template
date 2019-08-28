package gt.ecomponent.scrollbar;

import gt.ecomponent.EComponent;

public interface EViewport extends EComponent {
    double getWidth();

    double getHeight();

    double getViewX();

    double getViewY();

    double getViewWidth();

    double getViewHeight();

    double getXIncrement();

    double getYIncrement();

    void setPosition(double viewX, double viewY);

    void setViewSize(double viewWidth, double viewHeight);

    default void move(double dx, double dy) {
        double x = Math.min(Math.max(0, getViewX() + dx), Math.max(0, getWidth() - getViewWidth()));
        double y = Math.min(Math.max(0, getViewY() + dy), Math.max(0, getHeight() - getViewHeight()));
        setPosition(x, y);
    }
}
