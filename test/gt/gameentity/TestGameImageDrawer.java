package gt.gameentity;

import java.util.List;

import gt.component.ComponentCreator;
import gt.util.EMath;

public class TestGameImageDrawer implements IGameImageDrawer {
    @Override
    public TestGameImage newGameImage() {
        return new TestGameImage(ComponentCreator.DEFAULT_WIDTH, ComponentCreator.DEFAULT_HEIGHT);
    }

    @Override
    public TestGameImage newGameImage(double width, double height) {
        return new TestGameImage(Math.max(1, EMath.round(width)), Math.max(1, EMath.round(height)));
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
