package gt.ecomponent.scrollbar;

import gt.ecomponent.list.EComponentLocation;

public class EViewportBackgroundLocation implements EComponentLocation {
    private final EViewport view;

    public EViewportBackgroundLocation(EViewport view) {
        this.view = view;
    }

    @Override
    public double getX0() {
        return 0;
    }

    @Override
    public double getY0() {
        return 0;
    }

    @Override
    public double getX1() {
        return view.getViewWidth() - 1;
    }

    @Override
    public double getY1() {
        return view.getHeight() - 1;
    }
}
