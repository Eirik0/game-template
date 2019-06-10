package gt.ecomponent;

import java.awt.Color;
import java.awt.Graphics2D;

import gt.gameentity.DurationTimer;
import gt.gameentity.GameEntity;
import gt.gameloop.TimeConstants;

public class EBorder implements GameEntity {
    private final EComponentLocation cl;
    private final boolean fill;
    private final DurationTimer mouseOverTimer = new DurationTimer(TimeConstants.NANOS_PER_SECOND / 5, false);

    public EBorder(EComponentLocation cl, boolean fill) {
        this.cl = cl;
        this.fill = fill;
    }

    public void setSelected(boolean selected) {
        mouseOverTimer.setCountUp(selected);
    }

    @Override
    public void update(double dt) {
        mouseOverTimer.update(dt);
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        Color color = fadeToColor(Color.RED, Color.GREEN, mouseOverTimer.getPercentComplete());
        if (fill) {
            fillRect(graphics, cl.getX0(), cl.getY0(), cl.getWidth(), cl.getHeight(), color);
        } else {
            drawRect(graphics, cl.getX0(), cl.getY0(), cl.getWidth() - 1, cl.getHeight() - 1, color);
        }
    }
}
