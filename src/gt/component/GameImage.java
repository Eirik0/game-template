package gt.component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gt.gameentity.DrawingMethods;
import gt.gameentity.Sizable;
import gt.gameentity.Sized;

public class GameImage implements DrawingMethods, Sizable, Sized {
    private BufferedImage image;
    private Graphics2D graphics;

    public GameImage() {
        resizeImage(ComponentCreator.DEFAULT_WIDTH, ComponentCreator.DEFAULT_HEIGHT);
    }

    public GameImage(int width, int height) {
        resizeImage(width, height);
    }

    public BufferedImage getImage() {
        return image;
    }

    public Graphics2D getGraphics() {
        return graphics;
    }

    @Override
    public void setSize(int width, int height) {
        if (width <= 0 || height <= 0) {
            return;
        }
        if (image.getWidth() != width || image.getHeight() != height) {
            resizeImage(width, height);
        }
    }

    private void resizeImage(int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D newGraphics = newImage.createGraphics();
        fillRect(newGraphics, 0, 0, width, height, ComponentCreator.backgroundColor());
        if (image != null) {
            newGraphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        }
        image = newImage;
        graphics = newGraphics;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }
}
