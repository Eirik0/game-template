package gt.ecomponent.button;

import java.awt.Color;
import java.awt.Graphics2D;

import gt.ecomponent.EComponentLocation;
import gt.gameentity.Drawable;

public class EBlockButtonDrawer implements Drawable {
    private static final int BLOCK_GAP = 4;

    private final EComponentLocation buttonLocation;
    private final Color color;

    public EBlockButtonDrawer(EComponentLocation buttonLocation, Color color) {
        this.buttonLocation = buttonLocation;
        this.color = color;
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        double x = buttonLocation.getX0() + BLOCK_GAP;
        double y = buttonLocation.getY0() + BLOCK_GAP;
        double width = buttonLocation.getWidth() - 2 * BLOCK_GAP;
        double height = buttonLocation.getHeight() - 2 * BLOCK_GAP;
        fillRect(graphics, x, y, width, height, color);
    }
}
