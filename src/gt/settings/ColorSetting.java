package gt.settings;

import java.awt.Color;

public class ColorSetting implements GameSetting<Color> {
    private final Color color;

    public ColorSetting(Color color) {
        this.color = color;
    }

    public ColorSetting(String s) {
        String[] colors = s.substring(6, s.length() - 1).split(",");
        color = new Color(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]));
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
