package gt.component;

import java.awt.image.BufferedImage;

import gt.gameentity.IGameImage;

public interface IJavaGameImage extends IGameImage {
    BufferedImage getImage();

    JavaGraphics getGraphics();
}
