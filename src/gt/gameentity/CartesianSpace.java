package gt.gameentity;

public class CartesianSpace implements Sizable {
    private double x0;
    private double y0;

    private double width;
    private double height;

    private double imageWidth;
    private double imageHeight;

    private double scale;
    private double scaleInv;

    public CartesianSpace(double imageWidth, double imageHeight, double x0, double y0, double width, double height) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;

        setBounds(x0, y0, width, height);
    }

    @Override
    public void setSize(int imageWidth, int imageHeight) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        setBounds(x0, y0, width, height);
    }

    public double getX(double imageX) {
        return x0 + imageX * scaleInv;
    }

    public double getY(double imageY) {
        return y0 + imageY * scaleInv;
    }

    public double getWidth(double imageWidth) {
        return imageWidth * scaleInv;
    }

    public double getImageX(double x) {
        return scale * (x - x0);
    }

    public double getImageY(double y) {
        return scale * (y - y0);
    }

    public double getImageWidth(double width) {
        return scale * width;
    }

    public void move(double imageX, double imageY) {
        x0 += imageX / scale;
        y0 += imageY / scale;
    }

    public void zoom(double percent) {
        double dx = width * percent;
        double dy = height * percent;

        setBounds(x0 + dx, y0 + dy, width - 2 * dx, height - 2 * dy);
    }

    public void zoom(double percent, double x, double y) {
        double dx2 = 2 * width * percent;
        double dy2 = 2 * height * percent;

        setBounds(x0 + dx2 * (x / imageWidth), y0 + dy2 * (y / imageHeight), width - dx2, height - dy2);
    }

    public void setBounds(double x0, double y0, double width, double height) {
        this.x0 = x0;
        this.y0 = y0;

        this.width = width;
        this.height = height;

        double imageAspectRatio = imageWidth / imageHeight;
        double aspectRatio = width / height;

        if (imageAspectRatio > aspectRatio) {
            double newWidth = height * imageAspectRatio;
            this.x0 -= (newWidth - width) / 2;
            this.width = newWidth;
        } else {
            double newHeight = width / imageAspectRatio;
            this.y0 -= (newHeight - height) / 2;
            this.height = newHeight;
        }

        scale = imageWidth / this.width;
        scaleInv = this.width / imageWidth;
    }
}
