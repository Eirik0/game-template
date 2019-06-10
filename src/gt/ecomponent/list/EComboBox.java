package gt.ecomponent.list;

import java.awt.Graphics2D;
import java.util.function.IntConsumer;

import gt.component.ComponentCreator;
import gt.ecomponent.EBorder;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.button.EArrowButtonDrawer;
import gt.ecomponent.button.EArrowButtonDrawer.ArrowDirection;
import gt.ecomponent.location.EGluedLocation;
import gt.ecomponent.location.EStruttedLocation;
import gt.ecomponent.location.GlueSide;

public class EComboBox implements EComponent {
    private final EComponentLocation cl;
    private final String[] items;
    private int selectedIndex;
    private final IntConsumer action;

    private final EBorder border;
    private final EArrowButtonDrawer arrowDrawer;
    private final EComponentLocation listLocation;
    private final EList list;

    private boolean mousePressed = false;
    private boolean listVisible = false;

    public EComboBox(EComponentLocation cl, String[] items, double numItemsToShow, int selectedIndex, IntConsumer action) {
        this.cl = cl;
        this.items = items;
        this.selectedIndex = selectedIndex;
        this.action = action;
        border = new EBorder(cl, false);
        arrowDrawer = new EArrowButtonDrawer(new EGluedLocation(cl, GlueSide.RIGHT, cl.getHeight()), ArrowDirection.DOWN);
        listLocation = new EStruttedLocation(cl, GlueSide.TOP, Math.min(items.length, numItemsToShow) * EListViewport.LIST_ITEM_HEIGHT + 2);
        list = new EList(listLocation, items, selectedIndex, i -> setSelectedIndex(i));
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
    public void drawOn(Graphics2D graphics) {
        border.drawOn(graphics);
        graphics.setColor(ComponentCreator.foregroundColor());
        drawCenteredYString(graphics, items[selectedIndex], EListViewport.X_PADDING + cl.getX0(), cl.getCenterY());
        arrowDrawer.drawOn(graphics);
        if (listVisible) {
            list.drawOn(graphics);
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
