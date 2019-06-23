package gt.gameentity;

import java.awt.Color;
import java.awt.Font;

import gt.util.DoublePair;
import gt.util.EMath;

public class TestGraphics implements IGraphics {
    public static final int CHAR_WIDTH = 8;
    public static final int CHAR_HEIGHT = 16;

    private final TestGameImage gameImage;

    private Color color;
    private Font font;

    public TestGraphics(TestGameImage gameImage) {
        super();
        this.gameImage = gameImage;
    }

    public void add(TestGraphicsObject o) {
        gameImage.add(o);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void drawLine(double x0, double y0, double x1, double y1) {
        gameImage.add(new TestGraphicsLine(color, EMath.round(x0), EMath.round(y0), EMath.round(x1), EMath.round(y1)));
    }

    @Override
    public void drawRect(double x0, double y0, double width, double height) {
        gameImage.add(new TestGraphicsRectangle(color, EMath.round(x0), EMath.round(y0), EMath.round(width), EMath.round(height), false));
    }

    @Override
    public void fillRect(double x0, double y0, double width, double height) {
        gameImage.add(new TestGraphicsRectangle(color, EMath.round(x0), EMath.round(y0), EMath.round(width), EMath.round(height), true));
    }

    @Override
    public void drawCircle(double x0, double y0, double radius) {
        gameImage.add(new TestGraphicsCircle(color, EMath.round(x0), EMath.round(y0), EMath.round(radius), false));
    }

    @Override
    public void fillCircle(double x0, double y0, double radius) {
        gameImage.add(new TestGraphicsCircle(color, EMath.round(x0), EMath.round(y0), EMath.round(radius), true));
    }

    @Override
    public void drawString(String text, double x0, double y0) {
        gameImage.add(new TestGraphicsString(color, font, EMath.round(x0), EMath.round(y0), text));

    }

    @Override
    public DoublePair getStringDimensions(String text) {
        return new DoublePair(text.length() * CHAR_WIDTH, CHAR_HEIGHT);
    }
}
