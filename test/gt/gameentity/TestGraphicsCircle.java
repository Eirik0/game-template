package gt.gameentity;

import java.awt.Color;
import java.util.Objects;

import gt.util.EMath;

public class TestGraphicsCircle implements TestGraphicsObject {
    private final Color color;
    private final int x0;
    private final int y0;
    private final int radius;
    private final boolean filled;

    public TestGraphicsCircle(Color color, int x0, int y0, int radius, boolean filled) {
        this.color = color;
        this.x0 = x0;
        this.y0 = y0;
        this.radius = radius;
        this.filled = filled;
    }

    @Override
    public TestGraphicsObject newAt(double x, double y) {
        return new TestGraphicsCircle(color, EMath.round(x0 + x), EMath.round(y0 + y), radius, filled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, Double.valueOf(x0), Double.valueOf(y0), Double.valueOf(radius), Boolean.valueOf(filled));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TestGraphicsCircle other = (TestGraphicsCircle) obj;
        return Objects.equals(color, other.color) && x0 == other.x0 && y0 == other.y0 && radius == other.radius && filled == other.filled;
    }

    @Override
    public String toString() {
        return "TestGraphicsCircle [color=" + color + ", x0=" + x0 + ", y0=" + y0 + ", radius=" + radius + ", filled=" + filled + "]";
    }
}
