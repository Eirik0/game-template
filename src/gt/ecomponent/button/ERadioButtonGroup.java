package gt.ecomponent.button;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.IntConsumer;

import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.location.EFixedLocation;
import gt.ecomponent.location.ERelativeLocation;

public class ERadioButtonGroup implements EComponent {
    private static final int BUTTON_SPACING = 4;

    private final EButton[] buttons;

    private final boolean multiSelect;

    private final IntConsumer action;

    public ERadioButtonGroup(EComponentLocation cl, int numWide, int numHigh, boolean multiSelect, Color[] colors, IntConsumer action) {
        this.action = action;
        buttons = new EButton[numWide * numHigh];
        this.multiSelect = multiSelect;
        double buttonWidth = (cl.getWidth() - (numWide - 1) * BUTTON_SPACING) / numWide;
        double buttonHeight = (cl.getHeight() - (numHigh - 1) * BUTTON_SPACING) / numHigh;
        double x = 0;
        double y = 0;
        int i = 0;
        for (int i_y = 0; i_y < numHigh; ++i_y) {
            for (int i_x = 0; i_x < numWide; ++i_x) {
                int value = i;
                EFixedLocation buttonLoc = new EFixedLocation(x, y, x + buttonWidth - 1, y + buttonHeight - 1);
                buttons[i] = EButton.createBlockButton(new ERelativeLocation(cl, buttonLoc), colors[i], () -> selectButton(value));
                ++i;
                x += buttonWidth + BUTTON_SPACING;
            }
            x = 0;
            y += buttonHeight + BUTTON_SPACING;
        }
        buttons[0].setSelected(true);
    }

    private void selectButton(int n) {
        if (multiSelect) {
            buttons[n].setSelected(!buttons[n].isSelected());
            action.accept(n);
        } else {
            for (int i = 0; i < buttons.length; ++i) {
                if (n == i) {
                    action.accept(n);
                    buttons[i].setSelected(true);
                } else {
                    buttons[i].setSelected(false);
                }
            }
        }
    }

    @Override
    public void update(double dt) {
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i].update(dt);
        }
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i].drawOn(graphics);
        }
    }

    @Override
    public boolean setMouseOver(int screenX, int screenY) {
        boolean containsPoint = false;
        for (int i = 0; i < buttons.length; ++i) {
            if (buttons[i].setMouseOver(screenX, screenY)) {
                containsPoint = true;
            }
        }
        return containsPoint;
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        for (int i = 0; i < buttons.length; ++i) {
            if (buttons[i].setMousePressed(screenX, screenY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i].setMouseReleased(screenX, screenY);
        }
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i].focusLost(fromClick);
        }
    }
}
