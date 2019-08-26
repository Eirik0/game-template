package gt.ecomponent.button;

import java.awt.Color;

import gt.ecomponent.EBackground;
import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.ETextLabel;
import gt.ecomponent.button.EArrowButtonDrawer.ArrowDirection;
import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.Drawable;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;

public class EButton implements EComponent, EComponentSettings {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(BUTTON_BACKGROUND_COLOR, BUTTON_BACKGROUND_COLOR_DEFAULT);
    private static final Color BORDER_COLOR = GameSettings.getValue(BUTTON_BORDER_COLOR, BUTTON_BORDER_COLOR_DEFAULT);
    private static final Color BORDER_HIGHLIGHT_COLOR = GameSettings.getValue(BUTTON_BORDER_HIGHLIGHT_COLOR, BUTTON_BORDER_HIGHLIGHT_COLOR_DEFAULT);
    private static final Color PRESSED_COLOR = GameSettings.getValue(BUTTON_PRESSED_COLOR, BUTTON_PRESSED_COLOR_DEFAULT);

    public static final double PRESSED_GAP = GameSettings.getDouble(BUTTON_PRESSED_GAP, BUTTON_PRESSED_GAP_DEFAULT);

    private final EComponentLocation cl;
    private final Drawable drawer;

    private final EBackground background;
    private final EBorder border;

    private final Runnable action;

    private boolean mousePressed = false;
    private boolean selected = false;

    private EButton(EComponentLocation cl, Drawable drawer, Runnable action) {
        this.cl = cl;
        this.drawer = drawer;
        this.action = action;
        background = new EBackground(cl, BACKGROUND_COLOR);
        border = new EBorder(cl, BORDER_COLOR, BORDER_HIGHLIGHT_COLOR, false);
    }

    public static EButton createTextButton(EComponentLocation cl, String text, Runnable action) {
        return new EButton(cl, new ETextLabel(cl, text, false), action);
    }

    public static EButton createBlockButton(EComponentLocation cl, Color color, Runnable action) {
        return new EButton(cl, new EBlockButtonDrawer(cl, color), action);
    }

    public static EButton createArrowButton(EComponentLocation cl, ArrowDirection direction, Runnable action) {
        return new EButton(cl, new EArrowButtonDrawer(cl, direction), action);
    }

    @Override
    public void update(double dt) {
        border.update(dt);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public void drawOn(IGraphics g) {
        background.drawOn(g);
        border.drawOn(g);
        drawer.drawOn(g);
        if (mousePressed || selected) {
            double x = cl.getX0() + PRESSED_GAP;
            double y = cl.getY0() + PRESSED_GAP;
            double width = cl.getWidth() - 2 * PRESSED_GAP;
            double height = cl.getHeight() - 2 * PRESSED_GAP;
            g.drawRect(x, y, width, height, PRESSED_COLOR);
        }
    }

    @Override
    public boolean setMouseOver(int screenX, int screenY) {
        boolean containsPoint = cl.containsPoint(screenX, screenY);
        border.setSelected(containsPoint);
        return containsPoint;
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        mousePressed = cl.containsPoint(screenX, screenY);
        return mousePressed;
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
        if (mousePressed && cl.containsPoint(screenX, screenY)) {
            action.run();
        }
        mousePressed = false;
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
        mousePressed = false;
        border.setSelected(false);
    }
}
