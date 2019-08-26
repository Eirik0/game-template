package gt.component;

import java.awt.image.BufferedImage;

public class JavaGameImage implements IJavaGameImage {
    private BufferedImage image;
    private JavaGraphics graphics;

    public JavaGameImage() {
        resizeImage(ComponentCreator.DEFAULT_WIDTH, ComponentCreator.DEFAULT_HEIGHT);
    }

    public JavaGameImage(int width, int height) {
        resizeImage(width, height);
    }

    private JavaGameImage(BufferedImage image) {
        this.image = image;
        graphics = new JavaGraphics(image.createGraphics());
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public JavaGraphics getGraphics() {
        return graphics;
    }

    @Override
    public JavaGameImage getSubimage(int x0, int y0, int width, int height) {
        return new JavaGameImage(image.getSubimage(x0, y0, width, height));
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
        JavaGraphics newGraphics = new JavaGraphics(newImage.createGraphics());
        newGraphics.fillRect(0, 0, width, height, ComponentCreator.backgroundColor());
        if (image != null) {
            newGraphics.getGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        }
        image = newImage;
        graphics = newGraphics;
    }

    @Override
    public double getWidth() {
        return image.getWidth();
    }

    @Override
    public double getHeight() {
        return image.getHeight();
    }
}
