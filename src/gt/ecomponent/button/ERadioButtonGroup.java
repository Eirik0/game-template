package gt.ecomponent.button;

import java.awt.Color;
import java.util.function.IntConsumer;

import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.list.EComponentLocation;
import gt.ecomponent.location.EGluedLocation;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;

public class ERadioButtonGroup implements EComponent, EComponentSettings {
    private static final double BUTTON_SPACING = GameSettings.getDouble(BUTTON_RADIO_GAP, BUTTON_RADIO_GAP_DEFAULT);

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
                EGluedLocation bl = cl.createRelativeLocation(x, y, x + buttonWidth - 1, y + buttonHeight - 1);
                buttons[i] = EButton.createBlockButton(bl, colors[i], () -> selectButton(value));
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
    public void drawOn(IGraphics g) {
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i].drawOn(g);
        }
    }

    @Override
    public boolean setMouseOver(double screenX, double screenY) {
        boolean containsPoint = false;
        for (int i = 0; i < buttons.length; ++i) {
            if (buttons[i].setMouseOver(screenX, screenY)) {
                containsPoint = true;
            }
        }
        return containsPoint;
    }

    @Override
    public boolean setMousePressed(double screenX, double screenY) {
        for (int i = 0; i < buttons.length; ++i) {
            if (buttons[i].setMousePressed(screenX, screenY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setMouseReleased(double screenX, double screenY) {
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i].setMouseReleased(screenX, screenY);
        }
    }

    @Override
    public boolean setMouseScrolled(double screenX, double screenY, double wheelDelta) {
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i].focusLost(fromClick);
        }
    }
}
