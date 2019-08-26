package gt.ecomponent.button;

import java.awt.Color;

import gt.ecomponent.EComponentSettings;
import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.Drawable;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;

public class ECheckDrawer implements Drawable, EComponentSettings {
    private static final Color SELECTED_COLOR = GameSettings.getValue(CHECK_BOX_SELECTED_COLOR, CHECK_BOX_SELECTED_COLOR_DEFAULT);

    private final EComponentLocation cl;

    public ECheckDrawer(EComponentLocation buttonLocation) {
        double widthPadding = buttonLocation.getWidth() * 0.3;
        double heightPadding = buttonLocation.getHeight() * 0.3;
        cl = buttonLocation.createPaddedLocation(widthPadding, heightPadding, widthPadding, heightPadding);
    }

    @Override
    public void drawOn(IGraphics g) {
        g.setColor(SELECTED_COLOR);
        g.drawLine(cl.getX0(), cl.getY0(), cl.getX1(), cl.getY1());
        g.drawLine(cl.getX1(), cl.getY0(), cl.getX0(), cl.getY1());
    }
}
