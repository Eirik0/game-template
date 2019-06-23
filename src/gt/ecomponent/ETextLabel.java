package gt.ecomponent;

import java.awt.Color;

import gt.component.ComponentCreator;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;

public class ETextLabel implements EComponent, EComponentSettings {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(TEXT_LABEL_BACKGROUND_COLOR, TEXT_LABEL_BACKGROUND_COLOR_DEFAULT);
    private static final Color TEXT_COLOR = GameSettings.getValue(TEXT_LABEL_TEXT_COLOR, TEXT_LABEL_TEXT_COLOR_DEFAULT);

    private final EComponentLocation cl;

    private final EBackground background;

    private final boolean drawBackground;
    private final String text;

    public ETextLabel(EComponentLocation cl, String text, boolean drawBackground) {
        this.cl = cl;
        this.text = text;
        this.drawBackground = drawBackground;
        background = new EBackground(cl, BACKGROUND_COLOR);
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void drawOn(IGraphics g) {
        if (drawBackground) {
            background.drawOn(g);
        }
        g.setColor(TEXT_COLOR);
        g.setFont(ComponentCreator.DEFAULT_FONT_SMALL);
        g.drawCenteredString(text, cl.getCenterX(), cl.getCenterY());
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
