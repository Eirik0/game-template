package gt.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ComponentCreator {
    private static final boolean DARK_THEME_DEFAULT = true;

    public static final int DEFAULT_WIDTH = 1280;
    public static final int DEFAULT_HEIGHT = 720;

    public static final Font DEFAULT_FONT = new Font("consolas", Font.PLAIN, 24);

    private static Color backgroundColor;
    private static Color foregroundColor;

    static {
        setDarkTheme(DARK_THEME_DEFAULT);
    }

    public static void setDarkTheme(boolean darkTheme) {
        backgroundColor = darkTheme ? Color.BLACK : Color.WHITE;
        foregroundColor = darkTheme ? Color.WHITE : Color.BLACK;
    }

    public static Color backgroundColor() {
        return backgroundColor;
    }

    public static Color foregroundColor() {
        return foregroundColor;
    }

    public static void setCrossPlatformLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
    }
}
