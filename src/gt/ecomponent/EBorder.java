package gt.ecomponent;

import java.awt.Color;

import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.DrawingMethods;
import gt.gameentity.DurationTimer;
import gt.gameentity.GameEntity;
import gt.gameentity.IGraphics;
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
    public void drawOn(IGraphics g) {
        Color fadeColor = DrawingMethods.fadeToColor(baseColor, highlightColor, mouseOverTimer.getPercentComplete());
        if (fill) {
            g.fillRect(cl.getX0(), cl.getY0(), cl.getWidth(), cl.getHeight(), fadeColor);
        } else {
            g.drawRect(cl.getX0(), cl.getY0(), cl.getWidth(), cl.getHeight(), fadeColor);
        }
    }
}
