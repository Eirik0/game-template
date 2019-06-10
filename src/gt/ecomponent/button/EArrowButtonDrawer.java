package gt.ecomponent.button;

import java.awt.Graphics2D;

import gt.component.ComponentCreator;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.location.EScaledLocation;
import gt.gameentity.Drawable;

public class EArrowButtonDrawer implements Drawable {
    public enum ArrowDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private EComponentLocation cl;
    private final ArrowDirection direction;

    public EArrowButtonDrawer(EComponentLocation buttonLocation, ArrowDirection direction) {
        cl = new EScaledLocation(buttonLocation, .33);
        this.direction = direction;
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        double centerX = 0;
        double centerY = 0;
        double t1X = 0;
        double t1Y = 0;
        double t2X = 0;
        double t2Y = 0;
        switch (direction) {
        case UP: // ^
            centerX = cl.getCenterX();
            centerY = cl.getY0();
            t1X = cl.getX0();
            t1Y = cl.getY1();
            t2X = cl.getX1();
            t2Y = cl.getY1();
            break;
        case DOWN: // v
            centerX = cl.getCenterX();
            centerY = cl.getY1();
            t1X = cl.getX0();
            t1Y = cl.getY0();
            t2X = cl.getX1();
            t2Y = cl.getY0();
            break;
        case LEFT: // <
            centerX = cl.getX0();
            centerY = cl.getCenterY();
            t1X = cl.getX1();
            t1Y = cl.getY0();
            t2X = cl.getX1();
            t2Y = cl.getY1();
            break;
        case RIGHT: // >
            centerX = cl.getX1();
            centerY = cl.getCenterY();
            t1X = cl.getX0();
            t1Y = cl.getY0();
            t2X = cl.getX0();
            t2Y = cl.getY1();
            break;
        default:
            throw new IllegalStateException("Unknown direction: " + direction.name());
        }
        graphics.setColor(ComponentCreator.foregroundColor());
        drawLine(graphics, centerX, centerY, t1X, t1Y);
        drawLine(graphics, centerX, centerY, t2X, t2Y);
    }
}
