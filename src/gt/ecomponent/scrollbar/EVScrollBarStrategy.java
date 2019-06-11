package gt.ecomponent.scrollbar;

import gt.ecomponent.EComponentLocation;
import gt.ecomponent.button.EArrowButtonDrawer.ArrowDirection;
import gt.ecomponent.button.EButton;
import gt.ecomponent.location.EGluedLocation;
import gt.ecomponent.location.EPaddedLocation;
import gt.ecomponent.location.GlueSide;

public class EVScrollBarStrategy implements EScrollBarStrategy {
    private final EPaddedLocation cl;
    private final EPaddedLocation trackLocation;
    private final EVScrollBarThumbLocation thumbLocation;

    private final EViewport view;

    private final EButton button1;
    private final EButton button2;

    private double mouseOverY = 0;

    public EVScrollBarStrategy(EComponentLocation parentLocation, EViewport view) {
        this.view = view;
        cl = new EPaddedLocation(new EGluedLocation(parentLocation, GlueSide.RIGHT, EScrollBar.BAR_WIDTH), 0, EScrollBar.BAR_WIDTH, 0, 0);
        trackLocation = new EPaddedLocation(cl, EScrollBar.BAR_WIDTH, EScrollBar.BAR_WIDTH, 0, 0);
        button1 = EButton.createArrowButton(new EGluedLocation(cl, GlueSide.TOP, EScrollBar.BAR_WIDTH), ArrowDirection.UP,
                () -> view.move(0, -view.getYIncrement()));
        button2 = EButton.createArrowButton(new EGluedLocation(cl, GlueSide.BOTTOM, EScrollBar.BAR_WIDTH), ArrowDirection.DOWN,
                () -> view.move(0, view.getYIncrement()));
        thumbLocation = new EVScrollBarThumbLocation(trackLocation, view);
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
        cl.setPadding(0, visible ? EScrollBar.BAR_WIDTH : 0, 0, 0);
    }

    @Override
    public void checkMousePressed() {
        if (view.getHeight() > view.getViewHeight()) {
            double y = mouseOverY - trackLocation.getY0();
            double percent = y / (trackLocation.getHeight() - 1);
            view.setPosition(view.getViewX(), (view.getHeight() - view.getViewHeight()) * percent);
        }
    }

    @Override
    public void setMouseOverImpl(int screenX, int screenY) {
        mouseOverY = Math.min(Math.max(trackLocation.getY0(), screenY), trackLocation.getY1());
    }

    @Override
    public void setMouseScrolledImpl(double wheelDelta) {
        view.move(0, wheelDelta * view.getYIncrement());
    }
}
