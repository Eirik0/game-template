package gt.ecomponent.list;

import java.awt.Color;
import java.util.function.IntConsumer;

import gt.component.ComponentCreator;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.scrollbar.EScrollBar;
import gt.ecomponent.scrollbar.EViewport;
import gt.ecomponent.scrollbar.ViewportWindow;
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
    private final ViewportWindow window;

    private final String[] items;
    private final IntConsumer action;
    private int selectedIndex;

    private boolean mousePressed = false;
    private boolean mouseOver = false;
    private double mouseOverY = 0;

    public EListViewport(EComponentLocation cl, String[] items, int selectedIndex, IntConsumer action) {
        this.cl = cl;
        window = new ViewportWindow(this, 0, 0, cl.getWidth(), cl.getHeight(), 0, ITEM_HEIGHT);
        this.items = items;
        this.selectedIndex = selectedIndex;
        this.action = action;
    }

    @Override
    public ViewportWindow getWindow() {
        return window;
    }

    public boolean setSelected(double screenX, double screenY) {
        boolean containsPoint = window.containsPoint(screenX, screenY);
        if (containsPoint) {
            mousePressed = true;
        }
        return containsPoint;
    }

    private int getMouseOverIndex() {
        double indexY = mouseOverY + EMath.round(window.getTruncatedY0(ITEM_HEIGHT));
        double minY = window.getTruncatedY0(ITEM_HEIGHT);
        double maxY = Math.min(window.getHeight() + window.getTruncatedY0(ITEM_HEIGHT) - 1, (items.length - 1) * ITEM_HEIGHT);
        return EMath.round(Math.min(Math.max(minY, indexY), maxY)) / ITEM_HEIGHT;
    }

    @Override
    public void update(double dt) {
        if (mousePressed) {
            if (mouseOverY < 0) {
                window.move(0, mouseOverY / 15.0);
            } else if (mouseOverY > window.getHeight() - 1) {
                window.move(0, (mouseOverY - (window.getHeight() - 1)) / 15.0);
            }
        }
    }

    @Override
    public void drawOn(IGraphics g) {
        g.fillRect(0, 0, window.getWidth(), window.getHeight(), BACKGROUND_COLOR);
        double itemY = -window.getTruncatedY0(ITEM_HEIGHT);
        g.setColor(TEXT_COLOR);
        g.setFont(ComponentCreator.DEFAULT_FONT_SMALL);
        for (int i = 0; i < items.length; ++i) {
            if (itemY >= 0 && itemY <= window.getHeight()) {
                g.drawCenteredYString(items[i], ITEM_PADDING - window.getX0(), itemY + ITEM_HEIGHT / 2);
            }
            itemY += ITEM_HEIGHT;
        }
        if (mouseOver || mousePressed) {
            double mouseOverY = getMouseOverIndex() * ITEM_HEIGHT - window.getTruncatedY0(ITEM_HEIGHT);
            g.drawRect(1, mouseOverY + 1, window.getWidth() - 2, ITEM_HEIGHT - 2, PRESSED_COLOR);
        }
        double selectedY = selectedIndex * ITEM_HEIGHT - window.getTruncatedY0(ITEM_HEIGHT);
        g.drawRect(0, selectedY, window.getWidth(), ITEM_HEIGHT, SELECTED_COLOR);
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
    public boolean setMouseOver(double screenX, double screenY) {
        mouseOver = window.containsPoint(screenX, screenY);
        mouseOverY = screenY;
        return mouseOver;
    }

    @Override
    public boolean setMousePressed(double screenX, double screenY) {
        mousePressed = window.containsPoint(screenX, screenY);
        return mousePressed;
    }

    @Override
    public void setMouseReleased(double screenX, double screenY) {
        if (mousePressed && window.containsPoint(screenX, screenY)) {
            int selected = getMouseOverIndex();
            selectedIndex = selected;
            action.accept(selected);
        }
        mousePressed = false;
    }

    @Override
    public void focusLost(boolean fromClick) {
        mouseOver = false;
        if (fromClick) {
            mousePressed = false;
        }
    }
}
