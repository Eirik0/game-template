package gt.ecomponent.slider;

import java.awt.Color;
import java.util.function.IntConsumer;

import gt.ecomponent.EBackground;
import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.location.EPaddedLocation;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;
import gt.util.EMath;

public class ESlider implements EComponent, EComponentSettings {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(SLIDER_BACKGROUND_COLOR, SLIDER_BACKGROUND_COLOR_DEFAULT);
    private static final Color BAR_COLOR = GameSettings.getValue(SLIDER_BAR_COLOR, SLIDER_BAR_COLOR_DEFAULT);
    private static final Color KNOB_COLOR = GameSettings.getValue(SLIDER_KNOB_COLOR, SLIDER_KNOB_COLOR_DEFAULT);
    private static final Color KNOB_HIGHLIGHT_COLOR = GameSettings.getValue(SLIDER_KNOB_HIGHLIGHT_COLOR, SLIDER_KNOB_HIGHLIGHT_COLOR_DEFAULT);
    private static final Color TICK_COLOR = GameSettings.getValue(SLIDER_TICK_COLOR, SLIDER_TICK_COLOR_DEFAULT);

    private static final double KNOB_WIDTH = GameSettings.getDouble(SLIDER_KNOB_WIDTH, SLIDER_KNOB_WIDTH_DEFAULT);
    private static final double TICK_HEIGHT = GameSettings.getDouble(SLIDER_TICK_HEIGHT, SLIDER_TICK_HEIGHT_DEFAULT);

    private final EComponentLocation cl;
    private final ESliderKnobLocation kcl;

    private final EBackground background;
    private final EBorder sliderKnob;

    private final int min;
    private final int max;
    private final double pixelsPerValue;
    private double currentValue;

    private final IntConsumer action;

    private boolean mousePressed = false;
    private boolean mouseOver = false;
    private double mouseOverX = 0;

    public ESlider(EComponentLocation pl, int min, int max, int defaultValue, IntConsumer action) {
        this.min = min;
        this.max = max;
        this.action = action;
        cl = new EPaddedLocation(pl, 0, 0, KNOB_WIDTH / 2, KNOB_WIDTH / 2);
        background = new EBackground(pl, BACKGROUND_COLOR);
        pixelsPerValue = (cl.getWidth() - 1) / (max - min);
        currentValue = defaultValue;
        kcl = new ESliderKnobLocation(this, cl, KNOB_WIDTH);
        sliderKnob = new EBorder(kcl, KNOB_COLOR, KNOB_HIGHLIGHT_COLOR, false);
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = Math.min(Math.max(min, currentValue), max);
    }

    public double getValueX() {
        return cl.getX0() + (currentValue - min) * pixelsPerValue;
    }

    @Override
    public void update(double dt) {
        sliderKnob.update(dt);
    }

    @Override
    public void drawOn(IGraphics g) {
        background.drawOn(g);
        double centerY = cl.getCenterY();
        g.setColor(BAR_COLOR);
        g.drawLine(cl.getX0(), centerY, cl.getX1(), centerY);
        sliderKnob.drawOn(g);
        double tickX = mouseOver || mousePressed ? mouseOverX : getValueX();
        g.setColor(TICK_COLOR);
        g.drawLine(tickX, centerY - TICK_HEIGHT / 2, tickX, centerY + TICK_HEIGHT / 2);
    }

    private void checkMousePressed() {
        if (mousePressed) {
            double x = mouseOverX - cl.getX0();
            currentValue = x / pixelsPerValue + min;
            action.accept(EMath.round(currentValue));
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
            action.accept(EMath.round(currentValue));
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
