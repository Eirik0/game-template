package gt.ecomponent.button;

import java.awt.Color;

import gt.ecomponent.EComponentSettings;
import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.Drawable;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;

public class EArrowButtonDrawer implements Drawable, EComponentSettings {
    private static final Color ARROW_COLOW = GameSettings.getValue(BUTTON_ARROW_COLOR, BUTTON_ARROW_COLOR_DEFAULT);

    public enum ArrowDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private final EComponentLocation cl;
    private final ArrowDirection direction;

    public EArrowButtonDrawer(EComponentLocation buttonLocation, ArrowDirection direction) {
        double widthPadding = buttonLocation.getWidth() * 0.3;
        double heightPadding = buttonLocation.getHeight() * 0.3;
        cl = buttonLocation.createPaddedLocation(widthPadding, heightPadding, widthPadding, heightPadding);
        this.direction = direction;
    }

    @Override
    public void drawOn(IGraphics g) {
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
        g.setColor(ARROW_COLOW);
        g.drawLine(centerX, centerY, t1X, t1Y);
        g.drawLine(centerX, centerY, t2X, t2Y);
    }
}
