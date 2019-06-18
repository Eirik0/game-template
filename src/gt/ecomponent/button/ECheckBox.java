package gt.ecomponent.button;

import java.awt.Color;
import java.awt.Graphics2D;

import gt.ecomponent.EBackground;
import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.location.EScaledLocation;
import gt.settings.GameSettings;
import gt.util.BooleanConsumer;

public class ECheckBox implements EComponent, EComponentSettings {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(CHECK_BOX_BACKGROUND_COLOR, CHECK_BOX_BACKGROUND_COLOR_DEFAULT);
    private static final Color BORDER_COLOR = GameSettings.getValue(CHECK_BOX_BORDER_COLOR, CHECK_BOX_BORDER_COLOR_DEFAULT);
    private static final Color BORDER_HIGHLIGHT_COLOR = GameSettings.getValue(CHECK_BOX_BORDER_HIGHLIGHT_COLOR, CHECK_BOX_BORDER_HIGHLIGHT_COLOR_DEFAULT);
    private static final Color PRESSED_COLOR = GameSettings.getValue(CHECK_BOX_PRESSED_COLOR, CHECK_BOX_PRESSED_COLOR_DEFAULT);
    private static final Color SELECTED_COLOR = GameSettings.getValue(CHECK_BOX_SELECTED_COLOR, CHECK_BOX_SELECTED_COLOR_DEFAULT);

    private final EComponentLocation cl;
    private final EComponentLocation checkLocation;

    private final EBackground background;
    private final EBorder border;

    private final BooleanConsumer action;

    private boolean mousePressed = false;
    private boolean selected;

    public ECheckBox(EComponentLocation cl, boolean selected, BooleanConsumer action) {
        this.cl = cl;
        this.selected = selected;
        this.action = action;
        checkLocation = new EScaledLocation(cl, .3);
        background = new EBackground(cl, BACKGROUND_COLOR);
        border = new EBorder(cl, BORDER_COLOR, BORDER_HIGHLIGHT_COLOR, false);
    }

    @Override
    public void update(double dt) {
        border.update(dt);
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        background.drawOn(graphics);
        border.drawOn(graphics);
        if (selected) {
            graphics.setColor(SELECTED_COLOR);
            drawLine(graphics, checkLocation.getX0(), checkLocation.getY0(), checkLocation.getX1(), checkLocation.getY1());
            drawLine(graphics, checkLocation.getX1(), checkLocation.getY0(), checkLocation.getX0(), checkLocation.getY1());
        }
        if (mousePressed) {
            graphics.setColor(PRESSED_COLOR);
            double x = cl.getX0() + EButton.PRESSED_GAP;
            double y = cl.getY0() + EButton.PRESSED_GAP;
            double width = cl.getWidth() - 2 * EButton.PRESSED_GAP - 1;
            double height = cl.getHeight() - 2 * EButton.PRESSED_GAP - 1;
            drawRect(graphics, x, y, width, height);
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
            selected = !selected;
            action.accept(selected);
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