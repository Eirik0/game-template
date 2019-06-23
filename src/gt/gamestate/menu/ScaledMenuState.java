package gt.gamestate.menu;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import gt.component.ComponentCreator;
import gt.component.MouseTracker;
import gt.gameentity.IGraphics;
import gt.gamestate.GameState;
import gt.gamestate.UserInput;
import gt.util.Pair;

public class ScaledMenuState implements GameState {
    private static final double VERTICAL_GAP = 0.05;
    private static final double HORIZONTAL_GAP = 0.05;

    private final MouseTracker mouseTracker;
    private final Font menuFont;

    private final List<ScaledMenuItem> menuItems = new ArrayList<>();

    private int width;
    private int height;

    public ScaledMenuState(MouseTracker mouseTracker, Font menuFont, double horizontalPadding, List<List<Pair<String, Runnable>>> namedActionsList) {
        this.mouseTracker = mouseTracker;
        this.menuFont = menuFont;

        int numColumns = namedActionsList.size();
        int maxActions = namedActionsList.get(0).size();
        for (int i = 1; i < numColumns; ++i) {
            maxActions = Math.max(maxActions, namedActionsList.get(i).size());
        }

        double widthPercent = (1.0 - (numColumns + 1) * HORIZONTAL_GAP - 2 * horizontalPadding) / numColumns;
        double heightPercent = (1.0 - (maxActions + 1) * VERTICAL_GAP) / maxActions;

        double currentWidth = HORIZONTAL_GAP + horizontalPadding;
        for (List<Pair<String, Runnable>> namedActions : namedActionsList) {
            double currentHeight = VERTICAL_GAP;
            for (Pair<String, Runnable> namedAction : namedActions) {
                String name = namedAction.getFirst();
                Runnable action = namedAction.getSecond();
                menuItems.add(new ScaledMenuItem(this, name, action, currentWidth, currentHeight, currentWidth + widthPercent, currentHeight + heightPercent));
                currentHeight += heightPercent + VERTICAL_GAP;
            }
            currentWidth += widthPercent + HORIZONTAL_GAP;
        }
    }

    @Override
    public void update(double dt) {
        // Do nothing
    }

    @Override
    public void drawOn(IGraphics g) {
        g.fillRect(0, 0, width, height, ComponentCreator.backgroundColor());
        g.setFont(menuFont);
        for (ScaledMenuItem menuItem : menuItems) {
            menuItem.drawOn(g);
        }
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void handleUserInput(UserInput input) {
        ScaledMenuItem selectedItem = null;
        if (input == UserInput.LEFT_BUTTON_RELEASED) {
            for (ScaledMenuItem menuItem : menuItems) {
                if (menuItem.containsCursor()) {
                    selectedItem = menuItem;
                    break;
                }
            }
        }
        if (selectedItem != null) {
            selectedItem.performAction();
        }
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    boolean isMouseEntered() {
        return mouseTracker.isMouseEntered;
    }

    int getMouseX() {
        return mouseTracker.mouseX;
    }

    int getMouseY() {
        return mouseTracker.mouseY;
    }
}
