package gt.component;

import java.awt.image.BufferedImage;

import gt.util.EMath;

public class JavaGameImage implements IJavaGameImage {
    private final int imageType;

    private BufferedImage image;
    private JavaGraphics graphics;

    public JavaGameImage(int width, int height, int imageType) {
        this.imageType = imageType;
        resizeImage(width, height);
    }

    public JavaGameImage(int width, int height) {
        this(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private JavaGameImage(BufferedImage image) {
        this.image = image;
        imageType = image.getType();
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
    public void setSize(double width, double height) {
        if (width <= 0 || height <= 0) {
            return;
        }
        if (image.getWidth() != width || image.getHeight() != height) {
            resizeImage(EMath.round(width), EMath.round(height));
        }
    }

    private void resizeImage(int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, imageType);
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
