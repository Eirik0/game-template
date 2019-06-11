package gt.ecomponent;

import java.awt.Color;
import java.awt.Graphics2D;

import gt.gameentity.DurationTimer;
import gt.gameentity.GameEntity;
import gt.gameloop.TimeConstants;

public class EBorder implements GameEntity {
    private final EComponentLocation cl;
    private final Color baseColor;
    private final Color highlightColor;
    private final boolean fill;

    private final DurationTimer mouseOverTimer = new DurationTimer(TimeConstants.NANOS_PER_SECOND / 5, false);

    public EBorder(EComponentLocation cl, Color baseColor, Color highlightColor, boolean fill) {
        this.cl = cl;
        this.fill = fill;
        this.baseColor = baseColor;
        this.highlightColor = highlightColor;
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
        Color fadeColor = fadeToColor(baseColor, highlightColor, mouseOverTimer.getPercentComplete());
        if (fill) {
            fillRect(graphics, cl.getX0(), cl.getY0(), cl.getWidth(), cl.getHeight(), fadeColor);
        } else {
            drawRect(graphics, cl.getX0(), cl.getY0(), cl.getWidth() - 1, cl.getHeight() - 1, fadeColor);
        }
    }
}
