package gt.ecomponent.list;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.IntConsumer;

import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.location.EPaddedLocation;
import gt.ecomponent.scrollbar.EScrollPane;
import gt.settings.GameSettings;

public class EList implements EComponent, EComponentSettings {
    private static final Color BORDER_COLOR = GameSettings.getValue(LIST_BORDER_COLOR, LIST_BORDER_COLOR_DEFAULT);
    private static final Color BORDER_HIGHLIGHT_COLOR = GameSettings.getValue(LIST_BORDER_HIGHLIGHT_COLOR, LIST_BORDER_HIGHLIGHT_COLOR_DEFAULT);

    private final EComponentLocation cl;

    private final EBorder border;
    private final EListViewport view;
    private final EScrollPane scrollPane;

    public EList(EComponentLocation cl, String[] items, int selectedIndex, IntConsumer action) {
        this.cl = cl;
        border = new EBorder(cl, BORDER_COLOR, BORDER_HIGHLIGHT_COLOR, false);
        EPaddedLocation sPLoc = new EPaddedLocation(cl, 1, 1, 1, 1);
        view = new EListViewport(sPLoc, items, selectedIndex, action);
        scrollPane = new EScrollPane(sPLoc, view);
    }

    public boolean setViewSelected(int screenX, int screenY) {
        return view.setSelected(screenX, screenY);
    }

    @Override
    public void update(double dt) {
        border.update(dt);
        scrollPane.update(dt);
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        border.drawOn(graphics);
        scrollPane.drawOn(graphics);
    }

    @Override
    public boolean setMouseOver(int screenX, int screenY) {
        boolean containsPoint = cl.containsPoint(screenX, screenY);
        border.setSelected(containsPoint);
        scrollPane.setMouseOver(screenX, screenY);
        return containsPoint;
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        return scrollPane.setMousePressed(screenX, screenY);
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
        scrollPane.setMouseReleased(screenX, screenY);
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        return scrollPane.setMouseScrolled(screenX, screenY, wheelDelta);
    }

    @Override
    public void focusLost(boolean fromClick) {
        scrollPane.focusLost(fromClick);
        border.setSelected(false);
    }
}
