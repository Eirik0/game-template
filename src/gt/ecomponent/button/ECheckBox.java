package gt.ecomponent.button;

import java.awt.Color;

import gt.ecomponent.EBackground;
import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.ETextLabel;
import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.Drawable;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;
import gt.util.BooleanConsumer;

public class ECheckBox implements EComponent, EComponentSettings {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(CHECK_BOX_BACKGROUND_COLOR, CHECK_BOX_BACKGROUND_COLOR_DEFAULT);
    private static final Color BORDER_COLOR = GameSettings.getValue(CHECK_BOX_BORDER_COLOR, CHECK_BOX_BORDER_COLOR_DEFAULT);
    private static final Color BORDER_HIGHLIGHT_COLOR = GameSettings.getValue(CHECK_BOX_BORDER_HIGHLIGHT_COLOR, CHECK_BOX_BORDER_HIGHLIGHT_COLOR_DEFAULT);
    private static final Color PRESSED_COLOR = GameSettings.getValue(CHECK_BOX_PRESSED_COLOR, CHECK_BOX_PRESSED_COLOR_DEFAULT);

    private final EComponentLocation cl;
    private final Drawable selectedDrawable;
    private final Drawable deselectedDrawable;

    private final EBackground background;
    private final EBorder border;

    private final BooleanConsumer action;

    private boolean mousePressed = false;
    private boolean selected;

    public ECheckBox(EComponentLocation cl, boolean selected, BooleanConsumer action) {
        selectedDrawable = new ECheckDrawer(cl);
        deselectedDrawable = g -> {
        };
        this.cl = cl;
        this.selected = selected;
        this.action = action;

        background = new EBackground(cl, BACKGROUND_COLOR);
        border = new EBorder(cl, BORDER_COLOR, BORDER_HIGHLIGHT_COLOR, false);
    }

    public ECheckBox(EComponentLocation cl, String selectedText, String deslectedText, boolean selected, BooleanConsumer action) {
        selectedDrawable = new ETextLabel(cl, selectedText, false);
        deselectedDrawable = new ETextLabel(cl, deslectedText, false);
        this.cl = cl;
        this.selected = selected;
        this.action = action;

        background = new EBackground(cl, BACKGROUND_COLOR);
        border = new EBorder(cl, BORDER_COLOR, BORDER_HIGHLIGHT_COLOR, false);
    }

    @Override
    public void update(double dt) {
        border.update(dt);
    }

    @Override
    public void drawOn(IGraphics g) {
        background.drawOn(g);
        border.drawOn(g);
        if (selected) {
            selectedDrawable.drawOn(g);
        } else {
            deselectedDrawable.drawOn(g);
        }
        if (mousePressed) {
            double x = cl.getX0() + EButton.PRESSED_GAP;
            double y = cl.getY0() + EButton.PRESSED_GAP;
            double width = cl.getWidth() - 2 * EButton.PRESSED_GAP;
            double height = cl.getHeight() - 2 * EButton.PRESSED_GAP;
            g.drawRect(x, y, width, height, PRESSED_COLOR);
        }
    }

    @Override
    public boolean setMouseOver(double screenX, double screenY) {
        boolean containsPoint = cl.containsPoint(screenX, screenY);
        border.setSelected(containsPoint);
        return containsPoint;
    }

    @Override
    public boolean setMousePressed(double screenX, double screenY) {
        mousePressed = cl.containsPoint(screenX, screenY);
        return mousePressed;
    }

    @Override
    public void setMouseReleased(double screenX, double screenY) {
        if (mousePressed && cl.containsPoint(screenX, screenY)) {
            selected = !selected;
            action.accept(selected);
        }
        mousePressed = false;
    }

    @Override
    public boolean setMouseScrolled(double screenX, double screenY, double wheelDelta) {
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
        mousePressed = false;
        border.setSelected(false);
    }
}
