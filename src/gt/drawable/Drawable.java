package gt.drawable;

import java.awt.Graphics2D;

public interface Drawable extends DrawingMethods {
    void drawOn(Graphics2D graphics);
}
