package gt.ecomponent;

import java.awt.Graphics2D;

import gt.component.ComponentCreator;

public class ETextLabel implements EComponent {
    private final EComponentLocation cl;
    private final String text;

    public ETextLabel(EComponentLocation cl, String text) {
        this.cl = cl;
        this.text = text;
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        graphics.setColor(ComponentCreator.foregroundColor());
        graphics.setFont(ComponentCreator.DEFAULT_FONT_SMALL);
        drawCenteredString(graphics, text, cl.getCenterX(), cl.getCenterY());
    }

    @Override
    public boolean setMouseOver(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        return false;
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
    }
}
