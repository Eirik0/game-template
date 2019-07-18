package gt.gameentity;

import java.util.List;

public class TestGameImageDrawer implements IGameImageDrawer {
    @Override
    public TestGameImage newGameImage() {
        return new TestGameImage();
    }

    @Override
    public TestGameImage newGameImage(int width, int height) {
        return new TestGameImage(width, height);
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
