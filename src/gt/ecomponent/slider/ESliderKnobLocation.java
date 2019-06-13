package gt.ecomponent.slider;

import gt.ecomponent.EComponentLocation;

public class ESliderKnobLocation implements EComponentLocation {
    private final EComponentLocation pl;
    private final ESlider parent;

    private final double width;

    public ESliderKnobLocation(ESlider parent, EComponentLocation pl, double width) {
        this.pl = pl;
        this.parent = parent;
        this.width = width;
    }

    @Override
    public double getX0() {
        return parent.getValueX() - width / 2;
    }

    @Override
    public double getY0() {
        return pl.getY0();
    }

    @Override
    public double getX1() {
        return parent.getValueX() + width / 2;
    }

    @Override
    public double getY1() {
        return pl.getY1();
    }
}
