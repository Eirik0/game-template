package gt.ecomponent;

import gt.component.IMouseTracker;
import gt.gameentity.Drawable;
import gt.gameentity.IGraphics;
import gt.gameentity.Updatable;
import gt.gameentity.UserInputHandler;
import gt.gamestate.UserInput;

public class EComponentPanel implements Updatable, Drawable, UserInputHandler {
    private final IMouseTracker mouseTracker;

    private final EComponent[][] components;
    private EComponent selectedComponent = null;

    public EComponentPanel(IMouseTracker mouseTracker, EComponent[][] components) {
        this.mouseTracker = mouseTracker;
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

    private void deselectComponents(int exceptLayer, int exceptIndex) {
        for (int i = 0; i < components.length; ++i) {
            EComponent[] layer = components[i];
            for (int j = 0; j < layer.length; ++j) {
                if (i == exceptLayer && j == exceptIndex) {
                    continue;
                }
                layer[j].focusLost(true);
            }
        }
    }

    @Override
    public void handleUserInput(UserInput input) {
        int mouseX = mouseTracker.mouseX();
        int mouseY = mouseTracker.mouseY();
        switch (input) {
        case MOUSE_MOVED:
            if (selectedComponent != null) {
                selectedComponent.setMouseOver(mouseX, mouseY);
            } else {
                mouseMoved(mouseX, mouseY);
            }
            break;
        case LEFT_BUTTON_PRESSED:
            for (int i = components.length - 1; i >= 0; --i) {
                EComponent[] layer = components[i];
                for (int j = 0; j < layer.length; ++j) {
                    if (layer[j].setMousePressed(mouseX, mouseY)) {
                        selectedComponent = layer[j];
                        deselectComponents(i, j);
                        return;
                    }
                }
            }
            selectedComponent = NullEComponent.getInstance();
            break;
        case LEFT_BUTTON_RELEASED:
            if (selectedComponent != null) {
                selectedComponent.setMouseReleased(mouseX, mouseY);
                selectedComponent = null;
            }
            mouseMoved(mouseX, mouseY);
            break;
        case MOUSE_WHEEL_MOVED:
            int wheelDelta = mouseTracker.wheelRotationDelta();
            if (wheelDelta == 0) {
                return;
            }
            for (int i = components.length - 1; i >= 0; --i) {
                EComponent[] layer = components[i];
                for (int j = 0; j < layer.length; ++j) {
                    if (layer[j].setMouseScrolled(mouseX, mouseY, wheelDelta)) {
                        return;
                    }
                }
            }
            mouseTracker.addWheelRotation(wheelDelta);
            break;
        default:
            break;
        }
    }
}
