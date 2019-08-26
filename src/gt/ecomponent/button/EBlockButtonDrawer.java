package gt.ecomponent.button;

import java.awt.Color;

import gt.ecomponent.EComponentSettings;
import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.Drawable;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;

public class EBlockButtonDrawer implements Drawable, EComponentSettings {
    private static final double BLOCK_GAP = GameSettings.getDouble(BUTTON_BLOCK_GAP, BUTTON_BLOCK_GAP_DEFAULT);

    private final EComponentLocation buttonLocation;
    private final Color color;

    public EBlockButtonDrawer(EComponentLocation buttonLocation, Color color) {
        this.buttonLocation = buttonLocation;
        this.color = color;
    }

    @Override
    public void drawOn(IGraphics g) {
        double x = buttonLocation.getX0() + BLOCK_GAP;
        double y = buttonLocation.getY0() + BLOCK_GAP;
        double width = buttonLocation.getWidth() - 2 * BLOCK_GAP;
        double height = buttonLocation.getHeight() - 2 * BLOCK_GAP;
        g.fillRect(x, y, width, height, color);
    }
}
