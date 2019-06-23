package gt.ecomponent;

import gt.component.MouseTracker;
import gt.gameentity.Drawable;
import gt.gameentity.IGraphics;
import gt.gameentity.Updatable;
import gt.gameentity.UserInputHandler;
import gt.gamestate.UserInput;

public class EComponentPanel implements Updatable, Drawable, UserInputHandler {
    private final MouseTracker mouseTracker;
    private double mouseWheelRotation;

    private final EComponent[][] components;
    private EComponent selectedComponent = null;

    public EComponentPanel(MouseTracker mouseTracker, EComponent[][] components) {
        this.mouseTracker = mouseTracker;
        mouseWheelRotation = mouseTracker.wheelRotation;
        this.components = components;
    }

    @Override
    public void update(double dt) {
        for (int i = 0; i < components.length; ++i) {
            EComponent[] layer = components[i];
            for (int j = 0; j < layer.length; ++j) {
                layer[j].update(dt);
            }
        }
    }

    @Override
    public void drawOn(IGraphics g) {
        for (int i = 0; i < components.length; ++i) {
            EComponent[] layer = components[i];
            for (int j = 0; j < layer.length; ++j) {
                layer[j].drawOn(g);
            }
        }
    }

    private void mouseMoved(int mouseX, int mouseY) {
        boolean mouseOver = false;
        for (int i = components.length - 1; i >= 0; --i) {
            EComponent[] layer = components[i];
            for (int j = 0; j < layer.length; ++j) {
                if (mouseOver && layer[j].setMouseOver(mouseX, mouseY)) {
                    layer[j].focusLost(false);
                } else if (layer[j].setMouseOver(mouseX, mouseY)) {
                    mouseOver = true;
                }
            }
        }
    }

    @Override
    public void handleUserInput(UserInput input) {
        int mouseX = mouseTracker.mouseX;
        int mouseY = mouseTracker.mouseY;
        switch (input) {
        case MOUSE_MOVED:
            if (selectedComponent != null) {
                selectedComponent.setMouseOver(mouseX, mouseY);
            } else {
                mouseMoved(mouseX, mouseY);
            }
            break;
        case LEFT_BUTTON_PRESSED:
            boolean pressed = false;
            for (int i = components.length - 1; i >= 0; --i) {
                EComponent[] layer = components[i];
                for (int j = 0; j < layer.length; ++j) {
                    if (pressed) {
                        layer[j].focusLost(true);
                    } else {
                        if (layer[j].setMousePressed(mouseX, mouseY)) {
                            selectedComponent = layer[j];
                            pressed = true;
                            break;
                        }
                    }
                }
            }
            if (!pressed) {
                selectedComponent = NullEComponent.getInstance();
            }
            break;
        case LEFT_BUTTON_RELEASED:
            if (selectedComponent != null) {
                selectedComponent.setMouseReleased(mouseX, mouseY);
                selectedComponent = null;
            }
            mouseMoved(mouseX, mouseY);
            break;
        case MOUSE_WHEEL_MOVED:
            double wheelDelta = mouseTracker.wheelRotation - mouseWheelRotation;
            mouseWheelRotation = mouseTracker.wheelRotation;
            for (int i = components.length - 1; i >= 0; --i) {
                EComponent[] layer = components[i];
                for (int j = 0; j < layer.length; ++j) {
                    if (layer[j].setMouseScrolled(mouseX, mouseY, wheelDelta)) {
                        return;
                    }
                }
            }
            break;
        default:
            break;
        }
    }
}
