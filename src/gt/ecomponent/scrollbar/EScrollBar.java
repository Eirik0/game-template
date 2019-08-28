package gt.ecomponent.scrollbar;

import java.awt.Color;

import gt.ecomponent.EBackground;
import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.button.EButton;
import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;

public class EScrollBar implements EComponent, EComponentSettings {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(SCROLL_BAR_BACKGROUND_COLOR, SCROLL_BAR_BACKGROUND_COLOR_DEFAULT);
    private static final Color BORDER_COLOR = GameSettings.getValue(SCROLL_BAR_BORDER_COLOR, SCROLL_BAR_BORDER_COLOR_DEFAULT);
    private static final Color THUMB_COLOR = GameSettings.getValue(SCROLL_BAR_THUMB_COLOR, SCROLL_BAR_THUMB_COLOR_DEFAULT);
    private static final Color THUMB_HIGHLIGHT_COLOR = GameSettings.getValue(SCROLL_BAR_THUMB_HIGHLIGHT_COLOR, SCROLL_BAR_THUMB_HIGHLIGHT_COLOR_DEFAULT);

    public static final double BAR_WIDTH = GameSettings.getDouble(SCROLL_BAR_WIDTH, SCROLL_BAR_WIDTH_DEFAULT);

    private final EScrollBarStrategy strategy;

    private final EComponentLocation trackLocation;
    private final EComponentLocation thumbLocation;

    private final EViewport view;
    private final EBackground background;
    private final EButton button1;
    private final EButton button2;
    private final EBorder barThumb;

    private boolean visible = true;
    private boolean mousePressed = false;

    public EScrollBar(EScrollBarStrategy strategy, EViewport view) {
        this.strategy = strategy;
        this.view = view;
        button1 = strategy.getButton1();
        button2 = strategy.getButton2();
        trackLocation = strategy.getTrackLocation();
        thumbLocation = strategy.getThumbLocation();
        background = new EBackground(trackLocation, BACKGROUND_COLOR);
        barThumb = new EBorder(thumbLocation, THUMB_COLOR, THUMB_HIGHLIGHT_COLOR, true);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setOtherBarVisible(boolean visible) {
        strategy.setOtherBarVisible(visible);
    }

    @Override
    public void update(double dt) {
        if (!visible) {
            return;
        }
        button1.update(dt);
        button2.update(dt);
        barThumb.update(dt);
    }

    @Override
    public void drawOn(IGraphics g) {
        if (!visible) {
            return;
        }
        button1.drawOn(g);
        button2.drawOn(g);
        background.drawOn(g);
        g.drawRect(trackLocation.getX0(), trackLocation.getY0(), trackLocation.getWidth(), trackLocation.getHeight(), BORDER_COLOR);
        barThumb.drawOn(g);
    }

    @Override
    public boolean setMouseOver(double screenX, double screenY) {
        if (!visible) {
            return false;
        }
        button1.setMouseOver(screenX, screenY);
        button2.setMouseOver(screenX, screenY);
        strategy.setMouseOverImpl(screenX, screenY);
        barThumb.setSelected(thumbLocation.containsPoint(screenX, screenY) || mousePressed);
        if (mousePressed) {
            strategy.checkMousePressed();
        }
        return strategy.getComponentLocation().containsPoint(screenX, screenY);
    }

    @Override
    public boolean setMousePressed(double screenX, double screenY) {
        if (!visible) {
            return false;
        }
        if (button1.setMousePressed(screenX, screenY) || button2.setMousePressed(screenX, screenY)) {
            return true;
        }
        mousePressed = trackLocation.containsPoint(screenX, screenY);
        if (mousePressed) {
            barThumb.setSelected(true);
            strategy.checkMousePressed();
        }
        return mousePressed;
    }

    @Override
    public void setMouseReleased(double screenX, double screenY) {
        if (!visible) {
            return;
        }
        button1.setMouseReleased(screenX, screenY);
        button2.setMouseReleased(screenX, screenY);
        mousePressed = false;
        barThumb.setSelected(thumbLocation.containsPoint(screenX, screenY));
    }

    @Override
    public boolean setMouseScrolled(double screenX, double screenY, double wheelDelta) {
        if (visible && strategy.getComponentLocation().containsPoint(screenX, screenY)) {
            strategy.setMouseScrolledImpl(wheelDelta);
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
        button1.setSelected(false);
        button2.setSelected(false);
        view.focusLost(fromClick);
    }
}
