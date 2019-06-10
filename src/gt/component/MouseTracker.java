package gt.component;

import java.util.function.Consumer;

import gt.gamestate.UserInput;

public class MouseTracker {
    public volatile boolean isMouseEntered = false;
    public volatile int mouseX;
    public volatile int mouseY;
    public volatile double wheelRotation = 0;

    private final Consumer<UserInput> userInputConsumer;

    public MouseTracker(Consumer<UserInput> userInputConsumer) {
        this.userInputConsumer = userInputConsumer;
    }

    public void handleUserInput(UserInput input) {
        userInputConsumer.accept(input);
    }

    public void setMouseEntered(boolean mouseEntered) {
        isMouseEntered = mouseEntered;
    }

    public void setMouseXY(int x, int y) {
        mouseX = x;
        mouseY = y;
    }
}
