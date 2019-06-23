package gt.gameentity;

import java.awt.Color;

public interface DrawingMethods {
    static Color fadeToColor(Color from, Color to, double percent) {
        double red = Math.min(percent * to.getRed() + (1 - percent) * from.getRed(), 255);
        double green = Math.min(percent * to.getGreen() + (1 - percent) * from.getGreen(), 255);
        double blue = Math.min(percent * to.getBlue() + (1 - percent) * from.getBlue(), 255);
        return new Color((int) red, (int) green, (int) blue);
    }
}
