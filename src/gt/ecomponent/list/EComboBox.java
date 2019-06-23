package gt.ecomponent.list;

import java.awt.Color;
import java.util.function.IntConsumer;

import gt.component.ComponentCreator;
import gt.ecomponent.EBackground;
import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.button.EArrowButtonDrawer;
import gt.ecomponent.button.EArrowButtonDrawer.ArrowDirection;
import gt.ecomponent.location.EGluedLocation;
import gt.ecomponent.location.EStruttedLocation;
import gt.ecomponent.location.GlueSide;
import gt.gameentity.GameImageDrawer;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;

public class EComboBox implements EComponent, EComponentSettings {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(COMBO_BOX_BACKGROUND_COLOR, COMBO_BOX_BACKGROUND_COLOR_DEFAULT);
    private static final Color BORDER_COLOR = GameSettings.getValue(COMBO_BOX_BORDER_COLOR, COMBO_BOX_BORDER_COLOR_DEFAULT);
    private static final Color BORDER_HIGHLIGHT_COLOR = GameSettings.getValue(COMBO_BOX_BORDER_HIGHLIGHT_COLOR, COMBO_BOX_BORDER_HIGHLIGHT_COLOR_DEFAULT);
    private static final Color TEXT_COLOR = GameSettings.getValue(COMBO_BOX_TEXT_COLOR, COMBO_BOX_TEXT_COLOR_DEFAULT);

    private final EComponentLocation cl;
    private final String[] items;

    private final EBackground background;
    private final EBorder border;
    private final EArrowButtonDrawer arrowDrawer;
    private final EComponentLocation listLocation;
    private final EList list;

    private final IntConsumer action;
    private int selectedIndex;

    private boolean mousePressed = false;
    private boolean listVisible = false;

    public EComboBox(EComponentLocation cl, GameImageDrawer imageDrawer, String[] items, double numItemsToShow, int selectedIndex, IntConsumer action) {
        this.cl = cl;
        this.items = items;
        this.selectedIndex = selectedIndex;
        this.action = action;
        background = new EBackground(cl, BACKGROUND_COLOR);
        border = new EBorder(cl, BORDER_COLOR, BORDER_HIGHLIGHT_COLOR, false);
        arrowDrawer = new EArrowButtonDrawer(new EGluedLocation(cl, GlueSide.RIGHT, cl.getHeight()), ArrowDirection.DOWN);
        listLocation = new EStruttedLocation(cl, GlueSide.TOP, Math.min(items.length, numItemsToShow) * EListViewport.ITEM_HEIGHT + 2);
        list = new EList(listLocation, imageDrawer, items, selectedIndex, i -> setSelectedIndex(i));
    }

    private void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        action.accept(selectedIndex);
        listVisible = false;
    }

    @Override
    public void update(double dt) {
        border.update(dt);
        list.update(dt);
    }

    @Override
    public void drawOn(IGraphics g) {
        background.drawOn(g);
        border.drawOn(g);
        g.setColor(TEXT_COLOR);
        g.setFont(ComponentCreator.DEFAULT_FONT_SMALL);
        g.drawCenteredYString(items[selectedIndex], EListViewport.ITEM_PADDING + cl.getX0(), cl.getCenterY());
        arrowDrawer.drawOn(g);
        if (listVisible) {
            list.drawOn(g);
        }
    }

    @Override
    public boolean setMouseOver(int screenX, int screenY) {
        boolean containsPoint = cl.containsPoint(screenX, screenY);
        border.setSelected(containsPoint);
        boolean listContains = listVisible && list.setMouseOver(screenX, screenY);
        if (mousePressed && list.setViewSelected(screenX, screenY)) {
            mousePressed = false;
        }
        return containsPoint || listContains;
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        mousePressed = cl.containsPoint(screenX, screenY);
        if (mousePressed) {
            listVisible = !listVisible;
            if (!listVisible) {
                mousePressed = false;
            }
            return true;
        } else if (listVisible) {
            boolean listPressed = list.setMousePressed(screenX, screenY);
            if (!listPressed) {
                listVisible = false;
            }
            return listPressed;
        }
        return false;
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
        if (listVisible) {
            if (mousePressed) {
                if (cl.containsPoint(screenX, screenY)) {
                    mousePressed = false;
                    return;
                }
            }
            if (!listLocation.containsPoint(screenX, screenY)) {
                mousePressed = false;
                listVisible = false;
                return;
            }
            list.setMouseReleased(screenX, screenY);
            list.focusLost(true);
        }
        mousePressed = false;
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        return listVisible && list.setMouseScrolled(screenX, screenY, wheelDelta);
    }

    @Override
    public void focusLost(boolean fromClick) {
        if (fromClick) {
            listVisible = false;
        }
        list.focusLost(fromClick);
        border.setSelected(false);
    }
}
