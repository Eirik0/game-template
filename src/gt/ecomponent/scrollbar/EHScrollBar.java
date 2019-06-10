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

public class EHScrollBar implements EComponent {
    private final EPaddedLocation cl;
    private final EComponentLocation trackLocation;
    private final EComponentLocation thumbLocation;

    private final EViewport view;

    private final EButton leftButton;
    private final EButton rightButton;
    private final EBorder barThumb;

    private double mouseOverX = 0;
    private boolean mousePressed = false;
    private boolean visible = true;

    public EHScrollBar(EComponentLocation parentLocation, EViewport view, double barWidth) {
        this.view = view;
        cl = new EPaddedLocation(new EGluedLocation(parentLocation, GlueSide.BOTTOM, barWidth), 0, 0, 0, barWidth);
        leftButton = EButton.createArrowButton(new EGluedLocation(cl, GlueSide.LEFT, barWidth), ArrowDirection.LEFT,
                () -> view.move(-view.getXIncrement(), 0));
        rightButton = EButton.createArrowButton(new EGluedLocation(cl, GlueSide.RIGHT, barWidth), ArrowDirection.RIGHT,
                () -> view.move(view.getXIncrement(), 0));
        trackLocation = new EPaddedLocation(cl, 0, 0, barWidth, barWidth);
        thumbLocation = new EHScrollBarThumbLocation(trackLocation, view);
        barThumb = new EBorder(thumbLocation, true);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setVBarVisible(boolean visible, double barWidth) {
        cl.setPadding(0, 0, 0, visible ? barWidth : 0);
    }

    @Override
    public void update(double dt) {
        if (!visible) {
            return;
        }
        leftButton.update(dt);
        rightButton.update(dt);
        barThumb.update(dt);
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        if (!visible) {
            return;
        }
        leftButton.drawOn(graphics);
        rightButton.drawOn(graphics);
        fillRect(graphics, trackLocation.getX0(), trackLocation.getY0(), trackLocation.getWidth(), trackLocation.getHeight(),
                ComponentCreator.backgroundColor());
        drawRect(graphics, trackLocation.getX0(), trackLocation.getY0(), trackLocation.getWidth() - 1, trackLocation.getHeight() - 1, Color.CYAN);
        barThumb.drawOn(graphics);
    }

    private void checkMousePressed() {
        if (mousePressed && view.getWidth() > view.getViewWidth()) {
            double x = mouseOverX - trackLocation.getX0();
            double percent = x / (trackLocation.getWidth() - 1);
            view.setPosition((view.getWidth() - view.getViewWidth()) * percent, view.getViewY());
        }
    }

    @Override
    public boolean setMouseOver(int screenX, int screenY) {
        if (!visible) {
            return false;
        }
        leftButton.setMouseOver(screenX, screenY);
        rightButton.setMouseOver(screenX, screenY);
        mouseOverX = Math.min(Math.max(trackLocation.getX0(), screenX), trackLocation.getX1());
        barThumb.setSelected(thumbLocation.containsPoint(screenX, screenY) || mousePressed);
        checkMousePressed();
        return cl.containsPoint(screenX, screenY);
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        if (!visible) {
            return false;
        }
        if (leftButton.setMousePressed(screenX, screenY) || rightButton.setMousePressed(screenX, screenY)) {
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
        leftButton.setMouseReleased(screenX, screenY);
        rightButton.setMouseReleased(screenX, screenY);
        mousePressed = false;
        barThumb.setSelected(thumbLocation.containsPoint(screenX, screenY));
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        if (visible && cl.containsPoint(screenX, screenY)) {
            view.move(wheelDelta * view.getXIncrement(), 0);
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
        leftButton.setSelected(false);
        rightButton.setSelected(false);
        view.focusLost(fromClick);
    }
}
