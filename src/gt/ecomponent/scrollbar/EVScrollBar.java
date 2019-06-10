package gt.ecomponent.scrollbar;

import java.awt.Color;
import java.awt.Graphics2D;

import gt.component.ComponentCreator;
import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.button.EButton;
import gt.ecomponent.button.EArrowButtonDrawer.ArrowDirection;
import gt.ecomponent.location.EGluedLocation;
import gt.ecomponent.location.EPaddedLocation;
import gt.ecomponent.location.GlueSide;

public class EVScrollBar implements EComponent {
    private final EPaddedLocation cl;
    private final EComponentLocation trackLocation;
    private final EComponentLocation thumbLocation;

    private final EViewport view;

    private final EButton upButton;
    private final EButton downButton;
    private final EBorder barThumb;

    private double mouseOverY = 0;
    private boolean mousePressed = false;
    private boolean visible = true;

    public EVScrollBar(EComponentLocation parentLocation, EViewport view, double barWidth) {
        this.view = view;
        cl = new EPaddedLocation(new EGluedLocation(parentLocation, GlueSide.RIGHT, barWidth), 0, barWidth, 0, 0);
        trackLocation = new EPaddedLocation(cl, barWidth, barWidth, 0, 0);
        upButton = EButton.createArrowButton(new EGluedLocation(cl, GlueSide.TOP, barWidth), ArrowDirection.UP,
                () -> view.move(0, -view.getYIncrement()));
        downButton = EButton.createArrowButton(new EGluedLocation(cl, GlueSide.BOTTOM, barWidth), ArrowDirection.DOWN,
                () -> view.move(0, view.getYIncrement()));
        thumbLocation = new EVScrollBarThumbLocation(trackLocation, view);
        barThumb = new EBorder(thumbLocation, true);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setHBarVisible(boolean visible, double barWidth) {
        cl.setPadding(0, visible ? barWidth : 0, 0, 0);
    }

    @Override
    public void update(double dt) {
        if (!visible) {
            return;
        }
        upButton.update(dt);
        downButton.update(dt);
        barThumb.update(dt);
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        if (!visible) {
            return;
        }
        upButton.drawOn(graphics);
        downButton.drawOn(graphics);
        fillRect(graphics, trackLocation.getX0(), trackLocation.getY0(), trackLocation.getWidth(), trackLocation.getHeight(),
                ComponentCreator.backgroundColor());
        drawRect(graphics, trackLocation.getX0(), trackLocation.getY0(), trackLocation.getWidth() - 1, trackLocation.getHeight() - 1, Color.CYAN);
        barThumb.drawOn(graphics);
    }

    private void checkMousePressed() {
        if (mousePressed && view.getHeight() > view.getViewHeight()) {
            double y = mouseOverY - trackLocation.getY0();
            double percent = y / (trackLocation.getHeight() - 1);
            view.setPosition(view.getViewX(), (view.getHeight() - view.getViewHeight()) * percent);
        }
    }

    @Override
    public boolean setMouseOver(int screenX, int screenY) {
        if (!visible) {
            return false;
        }
        upButton.setMouseOver(screenX, screenY);
        downButton.setMouseOver(screenX, screenY);
        mouseOverY = Math.min(Math.max(trackLocation.getY0(), screenY), trackLocation.getY1());
        barThumb.setSelected(thumbLocation.containsPoint(screenX, screenY) || mousePressed);
        checkMousePressed();
        return cl.containsPoint(screenX, screenY);
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        if (!visible) {
            return false;
        }
        if (upButton.setMousePressed(screenX, screenY) || downButton.setMousePressed(screenX, screenY)) {
            return true;
        }
        mousePressed = trackLocation.containsPoint(screenX, screenY);
        if (mousePressed) {
            barThumb.setSelected(true);
            checkMousePressed();
        }
        return mousePressed;
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
        if (!visible) {
            return;
        }
        upButton.setMouseReleased(screenX, screenY);
        downButton.setMouseReleased(screenX, screenY);
        mousePressed = false;
        barThumb.setSelected(thumbLocation.containsPoint(screenX, screenY));
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        if (visible && cl.containsPoint(screenX, screenY)) {
            view.move(0, wheelDelta * view.getYIncrement());
            barThumb.setSelected(thumbLocation.containsPoint(screenX, screenY));
            return true;
        }
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
        if (fromClick) {
            mousePressed = false;
            barThumb.setSelected(false);
        }
        upButton.setSelected(false);
        downButton.setSelected(false);
        view.focusLost(fromClick);
    }
}
