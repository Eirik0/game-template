package gt.component;

import java.awt.Color;
import java.awt.Font;
import java.util.function.IntConsumer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ComponentCreator {
    private static final boolean DARK_THEME_DEFAULT = true;

    public static final int DEFAULT_WIDTH = 1280;
    public static final int DEFAULT_HEIGHT = 720;

    public static final Font DEFAULT_FONT = new Font("consolas", Font.PLAIN, 24);
    public static final Font DEFAULT_FONT_SMALL = new Font(Font.DIALOG, Font.PLAIN, 12);

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

    public static <T extends JComponent> T initComponent(T component) {
        component.setBackground(backgroundColor);
        component.setForeground(foregroundColor);
        component.setFocusable(false);
        if (component instanceof JTextField) {
            component.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createDashedBorder(null), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
        } else if (!(component instanceof JButton)) {
            component.setBorder(BorderFactory.createEmptyBorder());
        }
        return component;
    }

    public static JButton createButton(String text, Runnable action) {
        JButton button = initComponent(new JButton(text));
        button.addActionListener(e -> action.run());
        return button;
    }

    public static JLabel createLabel(String title, Color foregroundColor) {
        JLabel label = initComponent(new JLabel(title));
        label.setForeground(foregroundColor);
        return label;
    }

    public static JSlider createSlider(int min, int max, int defaultVlue, IntConsumer valueConsumer) {
        JSlider slider = initComponent(new JSlider(JSlider.HORIZONTAL));
        slider.setMinimum(min);
        slider.setMaximum(max);
        slider.setMinorTickSpacing(min);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setValue(defaultVlue);
        slider.addChangeListener(e -> valueConsumer.accept(slider.getValue()));
        return slider;
    }
}
