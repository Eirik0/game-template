package gt.ecomponent.list;

import java.awt.Color;
import java.util.function.IntConsumer;

import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.scrollbar.EScrollPane;
import gt.gameentity.IGameImageDrawer;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;

public class EList implements EComponent, EComponentSettings {
    private static final Color BORDER_COLOR = GameSettings.getValue(LIST_BORDER_COLOR, LIST_BORDER_COLOR_DEFAULT);
    private static final Color BORDER_HIGHLIGHT_COLOR = GameSettings.getValue(LIST_BORDER_HIGHLIGHT_COLOR, LIST_BORDER_HIGHLIGHT_COLOR_DEFAULT);

    private final EComponentLocation cl;

    private final EBorder border;
    private final EListViewport view;
    private final EScrollPane scrollPane;

    public EList(EComponentLocation cl, IGameImageDrawer imageDrawer, String[] items, int selectedIndex, IntConsumer action) {
        this.cl = cl;
        border = new EBorder(cl, BORDER_COLOR, BORDER_HIGHLIGHT_COLOR, false);
        EComponentLocation sPLoc = cl.createPaddedLocation(1, 1, 1, 1);
        view = new EListViewport(sPLoc, items, selectedIndex, action);
        scrollPane = new EScrollPane(sPLoc, view, imageDrawer);
    }

    public boolean setViewSelected(double screenX, double screenY) {
        return view.setSelected(screenX - (cl.getX0() + 1), screenY - (cl.getY0() + 1));
    }

    @Override
    public void update(double dt) {
        border.update(dt);
        scrollPane.update(dt);
    }

    @Override
    public void drawOn(IGraphics g) {
        border.drawOn(g);
        scrollPane.drawOn(g);
    }

    @Override
    public boolean setMouseOver(double screenX, double screenY) {
        boolean containsPoint = cl.containsPoint(screenX, screenY);
        border.setSelected(containsPoint);
        scrollPane.setMouseOver(screenX, screenY);
        return containsPoint;
    }

    @Override
    public boolean setMousePressed(double screenX, double screenY) {
        return scrollPane.setMousePressed(screenX, screenY);
    }

    @Override
    public void setMouseReleased(double screenX, double screenY) {
        scrollPane.setMouseReleased(screenX, screenY);
    }

    @Override
    public boolean setMouseScrolled(double screenX, double screenY, double wheelDelta) {
        return scrollPane.setMouseScrolled(screenX, screenY, wheelDelta);
    }

    @Override
    public void focusLost(boolean fromClick) {
        scrollPane.focusLost(fromClick);
        border.setSelected(false);
    }
}
