package gt.ecomponent.scrollbar;

import java.awt.Graphics2D;

import gt.component.GameImage;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentLocation;
import gt.gameentity.Sizable;

public class EScrollPane implements EComponent, Sizable {
    public static final int BAR_WIDTH = 20;

    private final EViewport view;
    private final GameImage viewImage = new GameImage();

    private final EHScrollBar hBar;
    private final EVScrollBar vBar;

    public EScrollPane(EViewport view, EComponentLocation cl) {
        this.view = view;
        hBar = new EHScrollBar(cl, view, BAR_WIDTH);
        vBar = new EVScrollBar(cl, view, BAR_WIDTH);
        setSize(round(cl.getWidth()), round(cl.getHeight()));
    }

    @Override
    public void setSize(int width, int height) {
        boolean hBarVisible = view.getWidth() > width || (view.getHeight() > height && view.getWidth() > width - BAR_WIDTH);
        boolean vBarVisible = view.getHeight() > height || (view.getWidth() > width && view.getHeight() > height - BAR_WIDTH);
        double newImageWidth = vBarVisible ? width - BAR_WIDTH : width;
        double newImageHeight = hBarVisible ? height - BAR_WIDTH : height;
        double x = view.getWidth() - newImageWidth;
        double y = view.getHeight() - newImageHeight;

        if (x < view.getViewX() || y < view.getViewY()) {
            if (x < 0) {
                x = 0;
                newImageWidth = view.getWidth();
            }
            if (y < 0) {
                y = 0;
                newImageHeight = view.getHeight();
            }
            view.setPosition(Math.min(x, view.getViewX()), Math.min(y, view.getViewY()));
        }

        hBar.setVisible(hBarVisible);
        vBar.setVisible(vBarVisible);
        hBar.setVBarVisible(vBarVisible, BAR_WIDTH);
        vBar.setHBarVisible(hBarVisible, BAR_WIDTH);

        view.setViewSize(vBarVisible ? width - BAR_WIDTH : width, hBarVisible ? height - BAR_WIDTH : height);
        viewImage.setSize(round(newImageWidth), round(newImageHeight));
    }

    @Override
    public void update(double dt) {
        hBar.update(dt);
        vBar.update(dt);
        view.update(dt);
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        hBar.drawOn(graphics);
        vBar.drawOn(graphics);
        view.drawOn(viewImage.getGraphics());
        graphics.drawImage(viewImage.getImage(), round(view.getViewLocation().getX0()), round(view.getViewLocation().getY0()), null);
    }

    @Override
    public boolean setMouseOver(int screenX, int screenY) {
        boolean overVBar = hBar.setMouseOver(screenX, screenY);
        boolean overHBar = vBar.setMouseOver(screenX, screenY);
        boolean overView = view.setMouseOver(screenX, screenY);
        return overVBar || overHBar || overView;
    }

    @Override
    public boolean setMousePressed(int screenX, int screenY) {
        return view.setMousePressed(screenX, screenY) ||
                hBar.setMousePressed(screenX, screenY) ||
                vBar.setMousePressed(screenX, screenY);
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
        hBar.setMouseReleased(screenX, screenY);
        vBar.setMouseReleased(screenX, screenY);
        view.setMouseReleased(screenX, screenY);
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        return view.setMouseScrolled(screenX, screenY, wheelDelta) ||
                hBar.setMouseScrolled(screenX, screenY, wheelDelta) ||
                vBar.setMouseScrolled(screenX, screenY, wheelDelta);
    }

    @Override
    public void focusLost(boolean fromClick) {
        hBar.focusLost(fromClick);
        vBar.focusLost(fromClick);
        view.focusLost(fromClick);
    }
}
