package gt.ecomponent.list;

import java.awt.Color;
import java.util.function.IntConsumer;

import gt.component.ComponentCreator;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.scrollbar.EScrollBar;
import gt.ecomponent.scrollbar.EViewport;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;
import gt.util.EMath;

public class EListViewport implements EViewport, EComponentSettings {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(LIST_VIEWPORT_BACKGROUND_COLOR, LIST_VIEWPORT_BACKGROUND_COLOR_DEFAULT);
    private static final Color PRESSED_COLOR = GameSettings.getValue(LIST_VIEWPORT_PRESSED_COLOR, LIST_VIEWPORT_PRESSED_COLOR_DEFAULT);
    private static final Color SELECTED_COLOR = GameSettings.getValue(LIST_VIEWPORT_SELECTED_COLOR, LIST_VIEWPORT_SELECTED_COLOR_DEFAULT);
    private static final Color TEXT_COLOR = GameSettings.getValue(LIST_VIEWPORT_TEXT_COLOR, LIST_VIEWPORT_TEXT_COLOR_DEFAULT);

    public static final double ITEM_PADDING = GameSettings.getDouble(LIST_VIEWPORT_ITEM_PADDING, LIST_VIEWPORT_ITEM_PADDING_DEFAULT);
    public static final int ITEM_HEIGHT = GameSettings.getInt(LIST_VIEWPORT_ITEM_HEIGHT, LIST_VIEWPORT_ITEM_HEIGHT_DEFAULT);

    private final EComponentLocation cl;
    private final String[] items;

    private double x0 = 0;
    private double y0 = 0;
    private double viewWidth;
    private double viewHeight;

    private final IntConsumer action;
    private int selectedIndex;

    private boolean mousePressed = false;
    private boolean mouseOver = false;
    private double mouseOverY = 0;

    public EListViewport(EComponentLocation cl, String[] items, int selectedIndex, IntConsumer action) {
        this.cl = cl;
        this.items = items;
        this.selectedIndex = selectedIndex;
        this.action = action;
        viewWidth = cl.getWidth();
        viewHeight = cl.getHeight();
    }

    private boolean containsPoint(double screenX, double screenY) {
        return screenX >= 0 && screenX <= viewWidth && screenY >= 0 && screenY <= viewHeight;
    }

    public boolean setSelected(double screenX, double screenY) {
        boolean containsPoint = containsPoint(screenX, screenY);
        if (containsPoint) {
            mousePressed = true;
        }
        return containsPoint;
    }

    private double getTruncatedY0() {
        double rounded = (int) y0 / ITEM_HEIGHT * ITEM_HEIGHT;
        return y0 - rounded < ITEM_HEIGHT / 2 ? rounded : rounded + ITEM_HEIGHT;
    }

    private int getMouseOverIndex() {
        double indexY = mouseOverY + EMath.round(getTruncatedY0());
        double minY = getTruncatedY0();
        double maxY = Math.min(getViewHeight() + getTruncatedY0() - 1, (items.length - 1) * ITEM_HEIGHT);
        return EMath.round(Math.min(Math.max(minY, indexY), maxY)) / ITEM_HEIGHT;
    }

    @Override
    public void update(double dt) {
        if (mousePressed) {
            if (mouseOverY < 0) {
                move(0, mouseOverY / 15.0);
            } else if (mouseOverY > getViewHeight() - 1) {
                move(0, (mouseOverY - (getViewHeight() - 1)) / 15.0);
            }
        }
    }

    @Override
    public void drawOn(IGraphics g) {
        g.fillRect(0, 0, getViewWidth(), getViewHeight(), BACKGROUND_COLOR);
        double itemY = -getTruncatedY0();
        g.setColor(TEXT_COLOR);
        g.setFont(ComponentCreator.DEFAULT_FONT_SMALL);
        for (int i = 0; i < items.length; ++i) {
            if (itemY >= 0 && itemY <= viewHeight) {
                g.drawCenteredYString(items[i], ITEM_PADDING - getViewX(), itemY + ITEM_HEIGHT / 2);
            }
            itemY += ITEM_HEIGHT;
        }
        if (mouseOver || mousePressed) {
            double mouseOverY = getMouseOverIndex() * ITEM_HEIGHT - getTruncatedY0();
            g.drawRect(1, mouseOverY + 1, viewWidth - 2, ITEM_HEIGHT - 2, PRESSED_COLOR);
        }
        double selectedY = selectedIndex * ITEM_HEIGHT - getTruncatedY0();
        g.drawRect(0, selectedY, viewWidth, ITEM_HEIGHT, SELECTED_COLOR);
    }

    @Override
    public double getWidth() {
        boolean scrollBar = (items.length * ITEM_HEIGHT) > cl.getHeight();
        return scrollBar ? cl.getWidth() - EScrollBar.BAR_WIDTH : cl.getWidth();
    }

    @Override
    public double getHeight() {
        return Math.max(cl.getHeight(), items.length * ITEM_HEIGHT - 1);
    }

    @Override
    public double getViewX() {
        return x0;
    }

    @Override
    public double getViewY() {
        return y0;
    }

    @Override
    public double getViewWidth() {
        return viewWidth;
    }

    @Override
    public double getViewHeight() {
        return viewHeight;
    }

    @Override
    public double getXIncrement() {
        return 10;
    }

    @Override
    public double getYIncrement() {
        return ITEM_HEIGHT;
    }

    @Override
    public void setPosition(double x, double y) {
        x0 = x;
        y0 = y;
    }

    @Override
    public void setViewSize(double width, double height) {
        viewWidth = width;
        viewHeight = height;
    }

    @Override
    public boolean setMouseOver(double screenX, double screenY) {
        mouseOver = containsPoint(screenX, screenY);
        mouseOverY = screenY;
        return mouseOver;
    }

    @Override
    public boolean setMousePressed(double screenX, double screenY) {
        mousePressed = containsPoint(screenX, screenY);
        return mousePressed;
    }

    @Override
    public void setMouseReleased(double screenX, double screenY) {
        boolean containsPoint = containsPoint(screenX, screenY);
        if (mousePressed && containsPoint) {
            int selected = getMouseOverIndex();
            selectedIndex = selected;
            action.accept(selected);
        }
        mousePressed = false;
    }

    @Override
    public boolean setMouseScrolled(double screenX, double screenY, double wheelDelta) {
        if (containsPoint(screenX, screenY)) {
            move(0, wheelDelta * getYIncrement());
            return true;
        }
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
        mouseOver = false;
        if (fromClick) {
            mousePressed = false;
        }
    }
}
