package gt.component;

import gt.gameentity.IGameImage;
import gt.gameentity.IGameImageDrawer;
import gt.gameentity.IGraphics;
import gt.util.EMath;

public class JavaGameImageDrawer implements IGameImageDrawer {
    @Override
    public JavaGameImage newGameImage() {
        return new JavaGameImage(ComponentCreator.DEFAULT_WIDTH, ComponentCreator.DEFAULT_HEIGHT);
    }

    @Override
    public JavaGameImage newGameImage(int width, int height) {
        return new JavaGameImage(Math.max(1, width), Math.max(1, height));
    }

    @Override
    public void drawImage(IGraphics g, IGameImage image, double x, double y) {
        ((JavaGraphics) g).getGraphics().drawImage(((JavaGameImage) image).getImage(), EMath.round(x), EMath.round(y), null);
    }

    @Override
    public void drawImage(IGraphics g, IGameImage image, double x, double y, double width, double height) {
        ((JavaGraphics) g).getGraphics().drawImage(((JavaGameImage) image).getImage(),
                EMath.round(x), EMath.round(y), EMath.round(width), EMath.round(height), null);
    }
}
