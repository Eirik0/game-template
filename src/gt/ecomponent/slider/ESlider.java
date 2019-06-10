package gt.ecomponent.slider;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.IntConsumer;

import gt.component.ComponentCreator;
import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.location.EPaddedLocation;

public class ESlider implements EComponent {
    private static final double DIAL_WIDTH = 20;
    private static final double TICK_HEIGHT = 10;

    private final EComponentLocation cl;
    private final ESliderKnobLocation kcl;

    private final int min;
    private final int max;
    private final double pixelsPerValue;
    private double currentValue;

    private final EBorder sliderKnob;

    private final IntConsumer action;

    private boolean mouseOver = false;
    private double mouseOverX = 0;
    private boolean mousePressed = false;

    public ESlider(EComponentLocation pl, int min, int max, int defaultValue, IntConsumer action) {
        cl = new EPaddedLocation(pl, 0, 0, DIAL_WIDTH / 2, DIAL_WIDTH / 2);
        this.min = min;
        this.max = max;
        this.action = action;
        pixelsPerValue = (cl.getWidth() - 1) / (max - min);
        currentValue = defaultValue;
        kcl = new ESliderKnobLocation(this, cl.getCenterY(), DIAL_WIDTH, cl.getHeight());
        sliderKnob = new EBorder(kcl, false);
    }

    public double getValueX() {
        return cl.getX0() + (currentValue - min) * pixelsPerValue;
    }

    @Override
    public void update(double dt) {
        sliderKnob.update(dt);
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        double centerY = cl.getCenterY();
        graphics.setColor(Color.CYAN);
        drawLine(graphics, cl.getX0(), centerY, cl.getX1(), centerY);
        sliderKnob.drawOn(graphics);
        double tickX = mouseOver || mousePressed ? mouseOverX : getValueX();
        graphics.setColor(ComponentCreator.foregroundColor());
        drawLine(graphics, tickX, centerY - TICK_HEIGHT / 2, tickX, centerY + TICK_HEIGHT / 2);
    }

    private void checkMousePressed() {
        if (mousePressed) {
            double x = mouseOverX - cl.getX0();
            currentValue = x / pixelsPerValue + min;
            action.accept(round(currentValue));
        }
    }

    @Override
    public boolean setMouseOver(int screenX, int screenY) {
        mouseOver = cl.containsPoint(screenX, screenY);
        mouseOverX = Math.min(Math.max(cl.getX0(), screenX), cl.getX1());
        sliderKnob.setSelected(kcl.containsPoint(screenX, screenY) || mousePressed);
        checkMousePressed();
        return mouseOver;
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        mousePressed = cl.containsPoint(screenX, screenY) || kcl.containsPoint(screenX, screenY);
        if (mousePressed) {
            sliderKnob.setSelected(true);
            checkMousePressed();
        }
        return mousePressed;
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
        mousePressed = false;
        sliderKnob.setSelected(kcl.containsPoint(screenX, screenY));
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        if (cl.containsPoint(screenX, screenY)) {
            currentValue = Math.min(Math.max(min, currentValue + wheelDelta * 10), max);
            sliderKnob.setSelected(kcl.containsPoint(screenX, screenY));
            action.accept(round(currentValue));
            return true;
        }
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
        if (fromClick) {
            mousePressed = false;
            sliderKnob.setSelected(false);
        }
        mouseOver = false;
    }
}
