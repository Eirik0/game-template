package gt.settings;

import java.awt.Color;

public class ColorSetting implements GameSetting<Color> {
    private final Color color;

    public ColorSetting(Color color) {
        this.color = color;
    }

    public ColorSetting(int r, int g, int b) {
        color = new Color(r, g, b);
    }

    @Override
    public Color getValue() {
        return color;
    }

    @Override
    public String toFileString() {
        return "color(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    }
}
