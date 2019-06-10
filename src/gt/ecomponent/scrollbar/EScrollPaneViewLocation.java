package gt.ecomponent.scrollbar;

import gt.ecomponent.EComponentLocation;

public class EScrollPaneViewLocation implements EComponentLocation {
    private final EComponentLocation cl;
    private final EViewport view;

    public EScrollPaneViewLocation(EComponentLocation cl, EViewport view) {
        this.cl = cl;
        this.view = view;
    }

    @Override
    public double getX0() {
        return Math.max((view.getViewWidth() - view.getWidth()) / 2, 0) + cl.getX0();
    }

    @Override
    public double getY0() {
        return Math.max((view.getViewHeight() - view.getHeight()) / 2, 0) + cl.getY0();
    }

    @Override
    public double getX1() {
        return getX0() + Math.min(view.getWidth(), view.getViewWidth()) - 1;
    }

    @Override
    public double getY1() {
        return getY0() + Math.min(view.getHeight(), view.getViewHeight()) - 1;
    }
}
