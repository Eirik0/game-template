package gt.component;

public interface IMouseTracker {
    boolean isMouseEntered();

    int mouseX();

    int mouseY();

    void addWheelRotation(int rotation);

    int wheelRotationDelta();
}
