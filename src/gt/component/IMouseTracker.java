package gt.component;

public interface IMouseTracker {
    boolean isMouseEntered();

    int mouseX();

    int mouseY();

    int wheelRotationDelta();
}
