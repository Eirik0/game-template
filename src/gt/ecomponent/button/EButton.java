package gt.ecomponent.button;

import java.awt.Color;
import java.awt.Graphics2D;

import gt.component.ComponentCreator;
import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.ETextLabel;
import gt.ecomponent.button.EArrowButtonDrawer.ArrowDirection;
import gt.gameentity.Drawable;

public class EButton implements EComponent {
    public static final int PRESSED_GAP = 2;

    private final EComponentLocation cl;
    private final Drawable drawer;
    private final Runnable action;

    private final EBorder border;

    private boolean mousePressed = false;
    private boolean selected = false;

    private EButton(EComponentLocation cl, Drawable drawer, Runnable action) {
        this.cl = cl;
        this.drawer = drawer;
        this.action = action;
        border = new EBorder(cl, false);
    }

    public static EButton createTextButton(EComponentLocation cl, String text, Runnable action) {
        return new EButton(cl, new ETextLabel(cl, text), action);
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
    public void drawOn(Graphics2D graphics) {
        fillRect(graphics, cl.getX0(), cl.getY0(), cl.getWidth(), cl.getHeight(), ComponentCreator.backgroundColor());
        border.drawOn(graphics);
        if (mousePressed || selected) {
            graphics.setColor(Color.CYAN);
            double x = cl.getX0() + PRESSED_GAP;
            double y = cl.getY0() + PRESSED_GAP;
            double width = cl.getWidth() - 2 * PRESSED_GAP - 1;
            double height = cl.getHeight() - 2 * PRESSED_GAP - 1;
            drawRect(graphics, x, y, width, height);
        }
        drawer.drawOn(graphics);
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
