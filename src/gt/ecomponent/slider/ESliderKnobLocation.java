package gt.ecomponent.slider;

import gt.ecomponent.EComponentLocation;

public class ESliderKnobLocation implements EComponentLocation {
    private final ESlider parent;
    private double centerY;
    private final double width;
    private final double height;

    public ESliderKnobLocation(ESlider parent, double centerY, double width, double height) {
        this.parent = parent;
        this.centerY = centerY;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getX0() {
        return parent.getValueX() - width / 2;
    }

    @Override
    public double getY0() {
        return centerY - height / 2;
    }

    @Override
    public double getX1() {
        return parent.getValueX() + width / 2;
    }

    @Override
    public double getY1() {
        return centerY + height / 2;
    }
}
