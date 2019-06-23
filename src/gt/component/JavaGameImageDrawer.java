package gt.component;

import gt.gameentity.GameImageDrawer;
import gt.gameentity.IGameImage;
import gt.gameentity.IGraphics;
import gt.util.EMath;

public class JavaGameImageDrawer implements GameImageDrawer {
    @Override
    public JavaGameImage newGameImage() {
        return new JavaGameImage();
    }

    @Override
    public JavaGameImage newGameImage(int width, int height) {
        return new JavaGameImage(width, height);
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
