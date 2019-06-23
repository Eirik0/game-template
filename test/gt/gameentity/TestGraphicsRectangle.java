package gt.gameentity;

import java.awt.Color;
import java.util.Objects;

import gt.util.EMath;

public class TestGraphicsRectangle implements TestGraphicsObject {
    private final Color color;
    private final int x0;
    private final int y0;
    private final int width;
    private final int height;
    private final boolean filled;

    public TestGraphicsRectangle(Color color, int x0, int y0, int width, int height, boolean filled) {
        this.color = color;
        this.x0 = x0;
        this.y0 = y0;
        this.width = width;
        this.height = height;
        this.filled = filled;
    }

    @Override
    public TestGraphicsObject newAt(double x, double y) {
        return new TestGraphicsRectangle(color, EMath.round(x0 + x), EMath.round(y0 + y), width, height, filled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, Integer.valueOf(x0), Integer.valueOf(y0), Integer.valueOf(width), Integer.valueOf(height), Boolean.valueOf(filled));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TestGraphicsRectangle other = (TestGraphicsRectangle) obj;
        return Objects.equals(color, other.color) && x0 == other.x0 && y0 == other.y0 && width == other.width && height == other.height
                && filled == other.filled;
    }

    @Override
    public String toString() {
        return "TestGraphicsRectangle [color=" + color + ", x0=" + x0 + ", y0=" + y0 + ", width=" + width + ", height=" + height + ", filled=" + filled + "]";
    }
}
