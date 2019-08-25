package gt.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import gt.gameentity.IGraphics;
import gt.util.DoublePair;
import gt.util.EMath;

public class JavaGraphics implements IGraphics {
    private final Graphics2D g;

    public JavaGraphics(Graphics2D g) {
        this.g = g;
    }

    public Graphics2D getGraphics() {
        return g;
    }

    @Override
    public void setColor(Color color) {
        g.setColor(color);
    }

    @Override
    public void setFont(Font font) {
        g.setFont(font);
    }

    @Override
    public void drawLine(double x0, double y0, double x1, double y1) {
        g.drawLine(EMath.round(x0), EMath.round(y0), EMath.round(x1), EMath.round(y1));
    }

    @Override
    public void drawThickLine(double x0, double y0, double x1, double y1, float thickness, boolean round) {
        Stroke oldStroke = g.getStroke();
        g.setStroke(new BasicStroke(thickness, round ? BasicStroke.CAP_ROUND : BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 0));
        g.drawLine(EMath.round(x0), EMath.round(y0), EMath.round(x1), EMath.round(y1));
        g.setStroke(oldStroke);
    }

    @Override
    public void drawRect(double x0, double y0, double width, double height) {
        // The left and right edges of the rectangle are at x and x + width. The top and bottom edges are at y and y + height.
        g.drawRect(EMath.round(x0), EMath.round(y0), EMath.round(width - 1), EMath.round(height - 1));
    }

    @Override
    public void fillRect(double x0, double y0, double width, double height) {
        // The left and right edges of the rectangle are at x and x + width - 1. The top and bottom edges are at y and y + height - 1.
        g.fillRect(EMath.round(x0), EMath.round(y0), EMath.round(width), EMath.round(height));
    }

    @Override
    public void drawCircle(double x0, double y0, double radius) {
        // The oval covers an area that is width + 1 pixels wide and height + 1 pixels tall.
        double height = 2 * radius - 1;
        g.drawOval(EMath.round(x0 - radius), EMath.round(y0 - radius), EMath.round(height), EMath.round(height));
    }

    @Override
    public void fillCircle(double x0, double y0, double radius) {
        // Fills an oval bounded by the specified rectangle.
        double height = 2 * radius;
        g.fillOval(EMath.round(x0 - radius), EMath.round(y0 - radius), EMath.round(height), EMath.round(height));
    }

    @Override
    public void drawString(String text, double x0, double y0) {
        g.drawString(text, EMath.round(x0), EMath.round(y0));
    }

    @Override
    public DoublePair getStringDimensions(String text) {
        Rectangle glyphVector = g.getFont().createGlyphVector(g.getFontRenderContext(), text).getPixelBounds(null, 0, 0);
        return new DoublePair(g.getFontMetrics().stringWidth(text), glyphVector.getHeight() - 1);
    }
}
