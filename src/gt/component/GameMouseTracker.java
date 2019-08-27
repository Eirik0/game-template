package gt.component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import gt.gamestate.UserInput;

public class GameMouseTracker implements IMouseTracker {
    private volatile boolean isMouseEntered = false;
    private volatile int mouseX;
    private volatile int mouseY;
    private final AtomicInteger wheelRotation = new AtomicInteger(0);

    private final Consumer<UserInput> userInputConsumer;

    public GameMouseTracker(Consumer<UserInput> userInputConsumer) {
        this.userInputConsumer = userInputConsumer;
    }

    public void handleUserInput(UserInput input) {
        userInputConsumer.accept(input);
    }

    public void setMouseEntered(boolean mouseEntered) {
        isMouseEntered = mouseEntered;
    }

    @Override
    public boolean isMouseEntered() {
        return isMouseEntered;
    }

    public void setMouseXY(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    @Override
    public int mouseX() {
        return mouseX;
    }

    @Override
    public int mouseY() {
        return mouseY;
    }

    public void addWheelRotation(int rotation) {
        wheelRotation.getAndAdd(rotation);
    }

    @Override
    public int wheelRotationDelta() {
        return wheelRotation.getAndSet(0);
    }
}
