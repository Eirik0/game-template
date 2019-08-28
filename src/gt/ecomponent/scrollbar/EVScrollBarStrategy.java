package gt.ecomponent.scrollbar;

import gt.ecomponent.button.EArrowButtonDrawer.ArrowDirection;
import gt.ecomponent.button.EButton;
import gt.ecomponent.list.EComponentLocation;
import gt.ecomponent.location.EGluedLocation;
import gt.ecomponent.location.GlueSide;

public class EVScrollBarStrategy implements EScrollBarStrategy {
    private final EGluedLocation cl;
    private final EComponentLocation trackLocation;
    private final EVScrollBarThumbLocation thumbLocation;

    private final EViewport view;

    private final EButton button1;
    private final EButton button2;

    private double mouseOverY = 0;

    public EVScrollBarStrategy(EComponentLocation parentLocation, EViewport view) {
        this.view = view;
        cl = parentLocation.createGluedLocation(GlueSide.RIGHT, -EScrollBar.BAR_WIDTH + 1, 0, 0, 0);
        trackLocation = cl.createPaddedLocation(0, EScrollBar.BAR_WIDTH, 0, EScrollBar.BAR_WIDTH);
        button1 = EButton.createArrowButton(cl.createGluedLocation(GlueSide.TOP, 0, 0, 0, EScrollBar.BAR_WIDTH - 1), ArrowDirection.UP,
                () -> view.getWindow().move(0, -view.getWindow().getYIncrement()));
        button2 = EButton.createArrowButton(cl.createGluedLocation(GlueSide.BOTTOM, 0, -EScrollBar.BAR_WIDTH + 1, 0, 0), ArrowDirection.DOWN,
                () -> view.getWindow().move(0, view.getWindow().getYIncrement()));
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
        cl.setDeltas(-EScrollBar.BAR_WIDTH + 1, 0, 0, visible ? -EScrollBar.BAR_WIDTH : 0);
    }

    @Override
    public void checkMousePressed() {
        if (view.getHeight() > view.getWindow().getHeight()) {
            double y = mouseOverY - trackLocation.getY0();
            double percent = y / (trackLocation.getHeight() - 1);
            view.getWindow().setPosition(view.getWindow().getX0(), (view.getHeight() - view.getWindow().getHeight()) * percent);
        }
    }

    @Override
    public void setMouseOverImpl(double screenX, double screenY) {
        mouseOverY = Math.min(Math.max(trackLocation.getY0(), screenY), trackLocation.getY1());
    }

    @Override
    public void setMouseScrolledImpl(double wheelDelta) {
        view.getWindow().move(0, wheelDelta * view.getWindow().getYIncrement());
    }
}
