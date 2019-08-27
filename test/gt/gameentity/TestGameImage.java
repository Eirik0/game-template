package gt.gameentity;

import java.util.ArrayList;
import java.util.List;

public class TestGameImage implements IGameImage {
    private final List<TestGraphicsObject> graphicsObjects = new ArrayList<>();

    private double width;
    private double height;

    private TestGraphics graphics;

    public TestGameImage(int width, int height) {
        setSize(width, height);
    }

    @Override
    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
        graphics = new TestGraphics(this);
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public TestGraphics getGraphics() {
        return graphics;
    }

    @Override
    public IGameImage getSubimage(int x0, int y0, int width, int height) {
        throw new UnsupportedOperationException("Implement this");
    }

    public void clear() {
        graphicsObjects.clear();
    }

    public void add(TestGraphicsObject o) {
        graphicsObjects.add(o);
    }

    public List<TestGraphicsObject> getGraphicsObjects() {
        return new ArrayList<>(graphicsObjects);
    }
}
