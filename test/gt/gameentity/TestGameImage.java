package gt.gameentity;

import java.util.ArrayList;
import java.util.List;

import gt.component.ComponentCreator;

public class TestGameImage implements IGameImage {
    private final List<TestGraphicsObject> graphicsObjects = new ArrayList<>();

    private int width;
    private int height;

    private TestGraphics graphics;

    public TestGameImage() {
        this(ComponentCreator.DEFAULT_WIDTH, ComponentCreator.DEFAULT_HEIGHT);
    }

    public TestGameImage(int width, int height) {
        setSize(width, height);
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        graphics = new TestGraphics(this);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
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
