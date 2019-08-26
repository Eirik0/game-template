package gt.ecomponent.scrollbar;

import gt.ecomponent.button.EArrowButtonDrawer.ArrowDirection;
import gt.ecomponent.list.EComponentLocation;
import gt.ecomponent.button.EButton;
import gt.ecomponent.location.EGluedLocation;
import gt.ecomponent.location.GlueSide;

public class EHScrollBarStrategy implements EScrollBarStrategy {
    private final EGluedLocation cl;
    private final EComponentLocation trackLocation;
    private final EHScrollBarThumbLocation thumbLocation;

    private final EViewport view;

    private final EButton button1;
    private final EButton button2;

    private double mouseOverX = 0;

    public EHScrollBarStrategy(EComponentLocation parentLocation, EViewport view) {
        this.view = view;
        cl = parentLocation.createGluedLocation(GlueSide.BOTTOM, 0, -EScrollBar.BAR_WIDTH + 1, 0, 0);
        button1 = EButton.createArrowButton(cl.createGluedLocation(GlueSide.LEFT, 0, 0, EScrollBar.BAR_WIDTH - 1, 0), ArrowDirection.LEFT,
                () -> view.move(-view.getXIncrement(), 0));
        button2 = EButton.createArrowButton(cl.createGluedLocation(GlueSide.RIGHT, -EScrollBar.BAR_WIDTH + 1, 0, 0, 0), ArrowDirection.RIGHT,
                () -> view.move(view.getXIncrement(), 0));
        trackLocation = cl.createPaddedLocation(EScrollBar.BAR_WIDTH, 0, EScrollBar.BAR_WIDTH, 0);
        thumbLocation = new EHScrollBarThumbLocation(trackLocation, view);
    }

    @Override
    public EComponentLocation getComponentLocation() {
        return cl;
    }

    @Override
    public EButton getButton1() {
        return button1;
    }

    @Override
    public EButton getButton2() {
        return button2;
    }

    @Override
    public EComponentLocation getTrackLocation() {
        return trackLocation;
    }

    @Override
    public EComponentLocation getThumbLocation() {
        return thumbLocation;
    }

    @Override
    public void setOtherBarVisible(boolean visible) {
        cl.setDeltas(0, -EScrollBar.BAR_WIDTH + 1, visible ? -EScrollBar.BAR_WIDTH : 0, 0);
    }

    @Override
    public void checkMousePressed() {
        if (view.getWidth() > view.getViewWidth()) {
            double x = mouseOverX - trackLocation.getX0();
            double percent = x / (trackLocation.getWidth() - 1);
            view.setPosition((view.getWidth() - view.getViewWidth()) * percent, view.getViewY());
        }
    }

    @Override
    public void setMouseOverImpl(int screenX, int screenY) {
        mouseOverX = Math.min(Math.max(trackLocation.getX0(), screenX), trackLocation.getX1());
    }

    @Override
    public void setMouseScrolledImpl(double wheelDelta) {
        view.move(wheelDelta * view.getXIncrement(), 0);
    }
}
