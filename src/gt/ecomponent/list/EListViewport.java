package gt.ecomponent.list;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.IntConsumer;

import gt.component.ComponentCreator;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.scrollbar.EScrollPane;
import gt.ecomponent.scrollbar.EScrollPaneViewLocation;
import gt.ecomponent.scrollbar.EViewport;

public class EListViewport implements EViewport {
    public static final int X_PADDING = 5;
    public static final int LIST_ITEM_HEIGHT = 20;

    private final EComponentLocation cl;
    private final String[] items;
    private int selectedIndex;
    private final IntConsumer action;

    private final EScrollPaneViewLocation vl;
    private double x0 = 0;
    private double y0 = 0;
    private double viewWidth;
    private double viewHeight;

    private boolean mouseOver = false;
    private boolean mousePressed = false;
    private int mouseOverY;

    public EListViewport(EComponentLocation cl, String[] items, int selectedIndex, IntConsumer action) {
        this.cl = cl;
        this.items = items;
        this.selectedIndex = selectedIndex;
        this.action = action;
        vl = new EScrollPaneViewLocation(cl, this);
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
        fillRect(graphics, 0, 0, vl.getWidth(), vl.getHeight(), ComponentCreator.backgroundColor());
        double itemY = -getTruncatedY0();
        graphics.setColor(ComponentCreator.foregroundColor());
        for (int i = 0; i < items.length; ++i) {
            if (itemY >= 0 && itemY <= viewHeight) {
                drawCenteredYString(graphics, items[i], X_PADDING - getViewX(), itemY + LIST_ITEM_HEIGHT / 2);
            }
            itemY += LIST_ITEM_HEIGHT;
        }

        if (mouseOver || mousePressed) {
            graphics.setColor(Color.GREEN);
            double mouseOverY = getMouseOverIndex() * LIST_ITEM_HEIGHT - getTruncatedY0();
            drawRect(graphics, 1, mouseOverY + 1, vl.getWidth() - 3, LIST_ITEM_HEIGHT - 3);
        }
        graphics.setColor(Color.CYAN);
        double selectedY = selectedIndex * LIST_ITEM_HEIGHT - getTruncatedY0();
        drawRect(graphics, 0, selectedY, vl.getWidth() - 1, LIST_ITEM_HEIGHT - 1);
    }

    @Override
    public double getWidth() {
        boolean scrollBar = (items.length * LIST_ITEM_HEIGHT) > cl.getHeight();
        return scrollBar ? cl.getWidth() - EScrollPane.BAR_WIDTH : cl.getWidth();
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
