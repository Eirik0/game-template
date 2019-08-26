package gt.gameentity;

import java.util.List;

import gt.component.ComponentCreator;

public class TestGameImageDrawer implements IGameImageDrawer {
    @Override
    public TestGameImage newGameImage() {
        return new TestGameImage(ComponentCreator.DEFAULT_WIDTH, ComponentCreator.DEFAULT_HEIGHT);
    }

    @Override
    public TestGameImage newGameImage(int width, int height) {
        return new TestGameImage(Math.max(1, width), Math.max(1, height));
    }

    @Override
    public void drawImage(IGraphics g, IGameImage image, double x, double y) {
        List<TestGraphicsObject> graphicsObjects = ((TestGameImage) image).getGraphicsObjects();
        for (TestGraphicsObject graphicsObject : graphicsObjects) {
            ((TestGraphics) g).add(graphicsObject.newAt(x, y));
        }
    }

    @Override
    public void drawImage(IGraphics g, IGameImage image, double x, double y, double width, double height) {
        throw new UnsupportedOperationException("Implement this");
    }
}
