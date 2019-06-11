package gt.ecomponent.list;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.IntConsumer;

import gt.ecomponent.EBackground;
import gt.ecomponent.EComponentColors;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.scrollbar.EScrollBar;
import gt.ecomponent.scrollbar.EScrollPaneViewLocation;
import gt.ecomponent.scrollbar.EViewport;
import gt.ecomponent.scrollbar.EViewportBackgroundLocation;
import gt.settings.GameSettings;

public class EListViewport implements EViewport, EComponentColors {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(LIST_VIEWPORT_BACKGROUND_COLOR, LIST_VIEWPORT_BACKGROUND_COLOR_DEFAULT);
    private static final Color PRESSED_COLOR = GameSettings.getValue(LIST_VIEWPORT_PRESSED_COLOR, LIST_VIEWPORT_PRESSED_COLOR_DEFAULT);
    private static final Color SELECTED_COLOR = GameSettings.getValue(LIST_VIEWPORT_SELECTED_COLOR, LIST_VIEWPORT_SELECTED_COLOR_DEFAULT);
    private static final Color TEXT_COLOR = GameSettings.getValue(LIST_VIEWPORT_TEXT_COLOR, LIST_VIEWPORT_TEXT_COLOR_DEFAULT);

    public static final int X_PADDING = 5;
    public static final int LIST_ITEM_HEIGHT = 20;

    private final EComponentLocation cl;
    private final EScrollPaneViewLocation vl;
    private final String[] items;

    private final EBackground background;

    private double x0 = 0;
    private double y0 = 0;
    private double viewWidth;
    private double viewHeight;

    private final IntConsumer action;
    private int selectedIndex;

    private boolean mousePressed = false;
    private boolean mouseOver = false;
    private int mouseOverY = 0;

    public EListViewport(EComponentLocation cl, String[] items, int selectedIndex, IntConsumer action) {
        this.cl = cl;
        this.items = items;
        this.selectedIndex = selectedIndex;
        this.action = action;
        vl = new EScrollPaneViewLocation(cl, this);
        background = new EBackground(new EViewportBackgroundLocation(this), BACKGROUND_COLOR);
        viewWidth = cl.getWidth();
        viewHeight = cl.getHeight();
    }

    public boolean setSelected(int screenX, int screenY) {
        boolean containsPoint = vl.containsPoint(screenX, screenY);
        if (containsPoint) {
            mousePressed = true;
        }
        return containsPoint;
    }

    @Override
    public EComponentLocation getViewLocation() {
        return vl;
    }

    private double getTruncatedY0() {
        double rounded = (int) y0 / LIST_ITEM_HEIGHT * LIST_ITEM_HEIGHT;
        return y0 - rounded < LIST_ITEM_HEIGHT / 2 ? rounded : rounded + LIST_ITEM_HEIGHT;
    }

    private int getMouseOverIndex() {
        double indexY = mouseOverY + round(getTruncatedY0());
        double minY = getTruncatedY0();
        double maxY = Math.min(getViewHeight() + getTruncatedY0() - 1, (items.length - 1) * LIST_ITEM_HEIGHT);
        return round(Math.min(Math.max(minY, indexY), maxY)) / LIST_ITEM_HEIGHT;
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
    public void drawOn(Graphics2D graphics) {
        background.drawOn(graphics);
        double itemY = -getTruncatedY0();
        graphics.setColor(TEXT_COLOR);
        for (int i = 0; i < items.length; ++i) {
            if (itemY >= 0 && itemY <= viewHeight) {
                drawCenteredYString(graphics, items[i], X_PADDING - getViewX(), itemY + LIST_ITEM_HEIGHT / 2);
            }
            itemY += LIST_ITEM_HEIGHT;
        }
        if (mouseOver || mousePressed) {
            graphics.setColor(PRESSED_COLOR);
            double mouseOverY = getMouseOverIndex() * LIST_ITEM_HEIGHT - getTruncatedY0();
            drawRect(graphics, 1, mouseOverY + 1, vl.getWidth() - 3, LIST_ITEM_HEIGHT - 3);
        }
        graphics.setColor(SELECTED_COLOR);
        double selectedY = selectedIndex * LIST_ITEM_HEIGHT - getTruncatedY0();
        drawRect(graphics, 0, selectedY, vl.getWidth() - 1, LIST_ITEM_HEIGHT - 1);
    }

    @Override
    public double getWidth() {
        boolean scrollBar = (items.length * LIST_ITEM_HEIGHT) > cl.getHeight();
        return scrollBar ? cl.getWidth() - EScrollBar.BAR_WIDTH : cl.getWidth();
    }

    @Override
    public double getHeight() {
        return Math.max(cl.getHeight(), items.length * LIST_ITEM_HEIGHT - 1);
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
        return LIST_ITEM_HEIGHT;
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
    public boolean setMouseOver(int screenX, int screenY) {
        mouseOver = vl.containsPoint(screenX, screenY);
        mouseOverY = screenY - round(vl.getY0());
        return mouseOver;
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        mousePressed = vl.containsPoint(screenX, screenY);
        return mousePressed;
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
        boolean containsPoint = vl.containsPoint(screenX, screenY);
        if (mousePressed && containsPoint) {
            int selected = getMouseOverIndex();
            selectedIndex = selected;
            action.accept(selected);
        }
        mousePressed = false;
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        if (vl.containsPoint(screenX, screenY)) {
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
