package gt.component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gt.gameentity.DrawingMethods;
import gt.gameentity.Sizable;
import gt.gameentity.Sized;

public class BufferedGameImage implements DrawingMethods, Sizable, Sized {
    private GameImage buffer;
    private GameImage current;

    public BufferedGameImage(int width, int height) {
        buffer = new GameImage(width, height);
        current = new GameImage(width, height);
    }

    public BufferedImage getImage() {
        return current.getImage();
    }

    public Graphics2D getGraphics() {
        return buffer.getGraphics();
    }

    public synchronized void commitBuffer() {
        GameImage temp = current;
        current = buffer;
        buffer = temp;
    }

    @Override
    public synchronized void setSize(int width, int height) {
        current.setSize(width, height);
        buffer.setSize(width, height);
    }

    @Override
    public int getWidth() {
        return current.getWidth();
    }

    @Override
    public int getHeight() {
        return current.getHeight();
    }
}
