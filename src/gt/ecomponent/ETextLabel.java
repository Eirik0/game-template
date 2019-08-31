package gt.ecomponent;

import java.awt.Color;

import gt.component.ComponentCreator;
import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.IGraphics;
import gt.settings.GameSettings;

public class ETextLabel implements EComponent, EComponentSettings {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(TEXT_LABEL_BACKGROUND_COLOR, TEXT_LABEL_BACKGROUND_COLOR_DEFAULT);
    private static final Color TEXT_COLOR = GameSettings.getValue(TEXT_LABEL_TEXT_COLOR, TEXT_LABEL_TEXT_COLOR_DEFAULT);

    private final EComponentLocation cl;

    private final EBackground background;

    private final boolean drawBackground;
    private final boolean center;
    private String text;

    public ETextLabel(EComponentLocation cl, String text, boolean drawBackground, boolean center) {
        this.cl = cl;
        this.text = text;
        this.drawBackground = drawBackground;
        this.center = center;
        background = new EBackground(cl, BACKGROUND_COLOR);
    }

    public ETextLabel(EComponentLocation cl, String text, boolean drawBackground) {
        this(cl, text, drawBackground, true);
    }

    public void setText(String text) {
        this.text = text;
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
        if (center) {
            g.drawCenteredString(text, cl.getCenterX(), cl.getCenterY());
        } else {
            g.drawCenteredYString(text, cl.getX0() + 2, cl.getCenterY());
        }
    }

    @Override
    public boolean setMouseOver(double screenX, double screenY) {
        return false;
    }

    @Override
    public boolean setMousePressed(double screenX, double screenY) {
        return false;
    }

    @Override
    public void setMouseReleased(double screenX, double screenY) {
    }

    @Override
    public boolean setMouseScrolled(double screenX, double screenY, double wheelDelta) {
        return false;
    }

    @Override
    public void focusLost(boolean fromClick) {
    }
}
