package gt.gameentity;

import java.awt.Color;
import java.util.Objects;

import gt.util.EMath;

public class TestGraphicsLine implements TestGraphicsObject {
    private final Color color;
    private final int x0;
    private final int y0;
    private final int x1;
    private final int y1;

    public TestGraphicsLine(Color color, int x0, int y0, int x1, int y1) {
        this.color = color;
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override
    public TestGraphicsObject newAt(double x, double y) {
        return new TestGraphicsLine(color, EMath.round(x0 + x), EMath.round(y0 + y), EMath.round(x1 + x), EMath.round(y1 + y));
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, Double.valueOf(x0), Double.valueOf(y0), Double.valueOf(x1), Double.valueOf(y1));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TestGraphicsLine other = (TestGraphicsLine) obj;
        return Objects.equals(color, other.color) && x0 == other.x0 && y0 == other.y0 && x1 == other.x1 && y1 == other.y1;
    }

    @Override
    public String toString() {
        return "TestGraphicsLine [color=" + color + ", x0=" + x0 + ", y0=" + y0 + ", x1=" + x1 + ", y1=" + y1 + "]";
    }
}