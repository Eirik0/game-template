package gt.ecomponent;

import java.awt.Color;

import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.IGraphics;

public class EBackground implements EComponent {
    private final EComponentLocation cl;
    private final Color color;

    public EBackground(EComponentLocation cl, Color color) {
        this.cl = cl;
        this.color = color;
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void drawOn(IGraphics g) {
        g.fillRect(cl.getX0(), cl.getY0(), cl.getWidth(), cl.getHeight(), color);
    }

    @Override
    public boolean setMouseOver(double screenX, double screenY) {
        return false;
    }

    @Override
    public boolean setMousePressed(double screenX, double screenY) {
        return cl.containsPoint(screenX, screenY);
    }

    @Override
    public void setMouseReleased(double screenX, double screenY) {
    }

    @Override
    public boolean setMouseScrolled(double screenX, double screenY, double wheelDelta) {
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
    }
}
