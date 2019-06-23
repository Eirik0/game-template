package gt.gamestate.menu;

import java.awt.Color;

import gt.gameentity.Drawable;
import gt.gameentity.IGraphics;

public class ScaledMenuItem implements Drawable {
    private final ScaledMenuState parentState;

    private final String name;
    private final Runnable action;

    private final double widthPercentStart;
    private final double heightPercentStart;
    private final double widthPercentEnd;
    private final double heightPercentEnd;

    public ScaledMenuItem(ScaledMenuState parentState, String name, Runnable action,
            double widthPercentStart, double heightPercentStart, double widthPercentEnd, double heightPercentEnt) {
        this.parentState = parentState;

        this.name = name;
        this.action = action;

        this.widthPercentStart = widthPercentStart;
        this.heightPercentStart = heightPercentStart;
        this.widthPercentEnd = widthPercentEnd;
        heightPercentEnd = heightPercentEnt;
    }

    @Override
    public void drawOn(IGraphics g) {
        g.setColor(containsCursor() ? Color.GREEN : Color.RED);
        g.drawRect(getX0(), getY0(), getX1() - getX0() + 1, getY1() - getY0() + 1);
        g.drawCenteredString(name, (getX0() + getX1()) / 2, (getY0() + getY1()) / 2);
    }

    public boolean containsCursor() {
        int mouseX = parentState.getMouseX();
        int mouseY = parentState.getMouseY();
        return parentState.isMouseEntered() && mouseY >= getY0() && mouseY <= getY1() && mouseX >= getX0() && mouseX <= getX1();
    }

    private double getX0() {
        return parentState.getWidth() * widthPercentStart;
    }

    private double getY0() {
        return parentState.getHeight() * heightPercentStart;
    }

    private double getX1() {
        return parentState.getWidth() * widthPercentEnd;
    }

    private double getY1() {
        return parentState.getHeight() * heightPercentEnd;
    }

    public void performAction() {
        action.run();
    }
}
