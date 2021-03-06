package gt.ecomponent.scrollbar;

import gt.ecomponent.list.EComponentLocation;

public class EHScrollBarThumbLocation implements EComponentLocation {
    private final EComponentLocation trackLocation;
    private final EViewport view;

    public EHScrollBarThumbLocation(EComponentLocation trackLocation, EViewport view) {
        this.trackLocation = trackLocation;
        this.view = view;
    }

    @Override
    public double getX0() {
        double x0Percent = view.getWindow().getX0() / (view.getWidth() - 1);
        return trackLocation.getX0() + (trackLocation.getWidth() - 1) * x0Percent + 1;
    }

    @Override
    public double getY0() {
        return trackLocation.getY0() + 1;
    }

    @Override
    public double getX1() {
        double x1Percent = Math.min(1, (view.getWindow().getX0() + view.getWindow().getWidth() - 1) / (view.getWidth() - 1));
        return trackLocation.getX0() + (trackLocation.getWidth() - 1) * x1Percent - 1;
    }

    @Override
    public double getY1() {
        return trackLocation.getY1() - 1;
    }
}
