package gt.ecomponent.scrollbar;

import gt.ecomponent.list.EComponentLocation;

public class EVScrollBarThumbLocation implements EComponentLocation {
    private final EComponentLocation trackLocation;
    private final EViewport view;

    public EVScrollBarThumbLocation(EComponentLocation trackLocation, EViewport view) {
        this.trackLocation = trackLocation;
        this.view = view;
    }

    @Override
    public double getX0() {
        return trackLocation.getX0() + 1;
    }

    @Override
    public double getY0() {
        double y0Percent = view.getWindow().getY0() / (view.getHeight() - 1);
        return trackLocation.getY0() + 1 + (trackLocation.getHeight() - 1) * y0Percent;
    }

    @Override
    public double getX1() {
        return trackLocation.getX1() - 1;
    }

    @Override
    public double getY1() {
        double y1Percent = Math.min(1, (view.getWindow().getY0() + view.getWindow().getHeight() - 1) / (view.getHeight() - 1));
        return trackLocation.getY0() + (trackLocation.getHeight() - 1) * y1Percent - 1;
    }
}
