package gt.ecomponent;

import gt.component.IMouseTracker;
import gt.ecomponent.list.EComponentLocation;
import gt.util.EMath;

public class ComponentMouseTracker implements IMouseTracker {
    private final IMouseTracker parent;
    private final EComponentLocation cl;

    public ComponentMouseTracker(IMouseTracker parent, EComponentLocation cl) {
        this.parent = parent;
        this.cl = cl;
    }

    @Override
    public boolean isMouseEntered() {
        return cl.containsPoint(parent.mouseX(), parent.mouseY());
    }

    @Override
    public int mouseX() {
        return EMath.round(parent.mouseX() - cl.getX0());
    }

    @Override
    public int mouseY() {
        return EMath.round(parent.mouseY() - cl.getY0());
    }

    @Override
    public int wheelRotationDelta() {
        return parent.wheelRotationDelta();
    }
}
