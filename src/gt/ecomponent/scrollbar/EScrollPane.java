package gt.ecomponent.scrollbar;

import java.awt.Color;
import java.awt.Graphics2D;

import gt.component.GameImage;
import gt.ecomponent.EBackground;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentColors;
import gt.ecomponent.EComponentLocation;
import gt.gameentity.Sizable;
import gt.settings.GameSettings;

public class EScrollPane implements EComponent, EComponentColors, Sizable {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(SCROLL_PANE_BACKGROUND_COLOR, SCROLL_PANE_BACKGROUND_COLOR_DEFAULT);

    private final EViewport view;
    private final GameImage viewImage = new GameImage();

    private final EBackground background;
    private final EScrollBar hBar;
    private final EScrollBar vBar;

    public EScrollPane(EComponentLocation cl, EViewport view) {
        this.view = view;
        background = new EBackground(cl, BACKGROUND_COLOR);
        hBar = new EScrollBar(new EHScrollBarStrategy(cl, view), view);
        vBar = new EScrollBar(new EVScrollBarStrategy(cl, view), view);
        setSize(round(cl.getWidth()), round(cl.getHeight()));
    }

    @Override
    public void setSize(int width, int height) {
        boolean hBarVisible = view.getWidth() > width || (view.getHeight() > height && view.getWidth() > width - EScrollBar.BAR_WIDTH);
        boolean vBarVisible = view.getHeight() > height || (view.getWidth() > width && view.getHeight() > height - EScrollBar.BAR_WIDTH);
        double newImageWidth = vBarVisible ? width - EScrollBar.BAR_WIDTH : width;
        double newImageHeight = hBarVisible ? height - EScrollBar.BAR_WIDTH : height;
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
        hBar.setOtherBarVisible(vBarVisible);
        vBar.setOtherBarVisible(hBarVisible);

        view.setViewSize(vBarVisible ? width - EScrollBar.BAR_WIDTH : width, hBarVisible ? height - EScrollBar.BAR_WIDTH : height);
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
        background.drawOn(graphics);
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
