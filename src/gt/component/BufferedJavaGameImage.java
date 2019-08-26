package gt.component;

import java.awt.image.BufferedImage;

import gt.gameentity.IGameImage;

public class BufferedJavaGameImage implements IJavaGameImage {
    private JavaGameImage buffer;
    private JavaGameImage current;

    public BufferedJavaGameImage(JavaGameImage buffer, JavaGameImage current) {
        this.buffer = buffer;
        this.current = current;
    }

    @Override
    public BufferedImage getImage() {
        return current.getImage();
    }

    @Override
    public JavaGraphics getGraphics() {
        return buffer.getGraphics();
    }

    @Override
    public IGameImage getSubimage(int x0, int y0, int width, int height) {
        return new BufferedJavaGameImage(buffer.getSubimage(x0, y0, width, height), current.getSubimage(x0, y0, width, height));
    }

    public synchronized void commitBuffer() {
        JavaGameImage temp = current;
        current = buffer;
        buffer = temp;
    }

    @Override
    public synchronized void setSize(int width, int height) {
        current.setSize(width, height);
        buffer.setSize(width, height);
    }

    @Override
    public double getWidth() {
        return current.getWidth();
    }

    @Override
    public double getHeight() {
        return current.getHeight();
    }
}
