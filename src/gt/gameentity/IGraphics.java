package gt.gameentity;

import java.awt.Color;
import java.awt.Font;

import gt.util.DoublePair;

public interface IGraphics {
    void setColor(Color color);

    void setFont(Font font);

    void drawLine(double x0, double y0, double x1, double y1);

    default void drawPixel(double x0, double y0) {
        drawLine(x0, y0, x0, y0);
    }

    void drawRect(double x0, double y0, double width, double height);

    default void drawRect(double x0, double y0, double width, double height, Color color) {
        setColor(color);
        drawRect(x0, y0, width, height);
    }

    void fillRect(double x0, double y0, double width, double height);

    default void fillRect(double x0, double y0, double width, double height, Color color) {
        setColor(color);
        fillRect(x0, y0, width, height);
    }

    void drawCircle(double x0, double y0, double radius);

    default void drawCircle(double x0, double y0, double radius, Color color) {
        setColor(color);
        drawCircle(x0, y0, radius);
    }

    void fillCircle(double x0, double y0, double radius);

    default void fillCircle(double x0, double y0, double radius, Color color) {
        setColor(color);
        fillCircle(x0, y0, radius);
    }

    void drawString(String text, double x0, double y0);

    DoublePair getStringDimensions(String text);

    default void drawCenteredString(String text, double centerX, double centerY) {
        DoublePair stringDimensions = getStringDimensions(text);
        drawString(text, centerX - stringDimensions.getFirst() / 2, centerY + stringDimensions.getSecond() / 2);
    }

    default void drawCenteredXString(String text, double centerX, double y0) {
        DoublePair stringDimensions = getStringDimensions(text);
        drawString(text, centerX - stringDimensions.getFirst() / 2, y0);
    }

    default void drawCenteredYString(String text, double x0, double centerY) {
        DoublePair stringDimensions = getStringDimensions(text);
        drawString(text, x0, centerY + stringDimensions.getSecond() / 2);
    }
}
