package gt.gameentity;

import java.awt.Color;
import java.awt.Font;
import java.util.Objects;

import gt.util.EMath;

public class TestGraphicsString implements TestGraphicsObject {
    private final Color color;
    private final Font font;
    private final int x0;
    private final int y0;
    private final String text;

    public TestGraphicsString(Color color, Font font, int x0, int y0, String text) {
        this.color = color;
        this.font = font;
        this.x0 = x0;
        this.y0 = y0;
        this.text = text;
    }

    @Override
    public TestGraphicsObject newAt(double x, double y) {
        return new TestGraphicsString(color, font, EMath.round(x0 + x), EMath.round(y0 + y), text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, font, Double.valueOf(x0), Double.valueOf(y0), text);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TestGraphicsString other = (TestGraphicsString) obj;
        return Objects.equals(color, other.color) && Objects.equals(font, other.font) && x0 == other.x0 && y0 == other.y0 && Objects.equals(text, other.text);
    }

    @Override
    public String toString() {
        return "TestGraphicsString [color=" + color + ", font=" + font + ", x0=" + x0 + ", y0=" + y0 + ", text=" + text + "]";
    }
}
