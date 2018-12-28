package gt.gamestate.menu;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import gt.component.ComponentCreator;
import gt.component.MouseTracker;
import gt.gamestate.GameState;
import gt.gamestate.UserInput;
import gt.util.Pair;

public class ScaledMenuState implements GameState {
    private final MouseTracker mouseTracker;
    private final Font menuFont;

    private final List<ScaledMenuItem> menuItems = new ArrayList<>();

    private int width;
    private int height;

    public ScaledMenuState(MouseTracker mouseTracker, Font menuFont, List<Pair<String, Runnable>> namedActions) {
        this.mouseTracker = mouseTracker;
        this.menuFont = menuFont;

        double widthPercentStart = 0.25;
        double widthPercentEnd = 0.75;
        double gap = 0.05;
        double height = 1.0 / namedActions.size() - gap * (namedActions.size() + 1) / namedActions.size();

        double currentHeight = gap;
        for (Pair<String, Runnable> namedRunnable : namedActions) {
            String name = namedRunnable.getFirst();
            Runnable action = namedRunnable.getSecond();
            menuItems.add(new ScaledMenuItem(this, name, action, widthPercentStart, currentHeight, widthPercentEnd, currentHeight + height));
            currentHeight += height + gap;
        }
    }

    @Override
    public void update(double dt) {
        // Do nothing
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        fillRect(graphics, 0, 0, width, height, ComponentCreator.backgroundColor());
        graphics.setFont(menuFont);
        for (ScaledMenuItem menuItem : menuItems) {
            menuItem.drawOn(graphics);
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
