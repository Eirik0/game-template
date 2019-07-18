package gt.ecomponent.scrollbar;

import java.awt.Color;

import gt.ecomponent.EBackground;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentLocation;
import gt.ecomponent.EComponentSettings;
import gt.gameentity.IGameImageDrawer;
import gt.gameentity.IGameImage;
import gt.gameentity.IGraphics;
import gt.gameentity.Sizable;
import gt.settings.GameSettings;
import gt.util.EMath;

public class EScrollPane implements EComponent, EComponentSettings, Sizable {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(SCROLL_PANE_BACKGROUND_COLOR, SCROLL_PANE_BACKGROUND_COLOR_DEFAULT);

    private final EViewport view;
    private final IGameImageDrawer imageDrawer;
    private final IGameImage viewImage;

    private final EBackground background;
    private final EScrollBar hBar;
    private final EScrollBar vBar;

    public EScrollPane(EComponentLocation cl, EViewport view, IGameImageDrawer imageDrawer) {
        this.view = view;
        this.imageDrawer = imageDrawer;
        int width = EMath.round(cl.getWidth());
        int height = EMath.round(cl.getHeight());
        viewImage = imageDrawer.newGameImage(width, height);
        background = new EBackground(cl, BACKGROUND_COLOR);
        hBar = new EScrollBar(new EHScrollBarStrategy(cl, view), view);
        vBar = new EScrollBar(new EVScrollBarStrategy(cl, view), view);
        setSize(width, height);
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
        viewImage.setSize(EMath.round(newImageWidth), EMath.round(newImageHeight));
    }

    @Override
    public void update(double dt) {
        hBar.update(dt);
        vBar.update(dt);
        view.update(dt);
    }

    @Override
    public void drawOn(IGraphics g) {
        background.drawOn(g);
        hBar.drawOn(g);
        vBar.drawOn(g);
        view.drawOn(viewImage.getGraphics());
        imageDrawer.drawImage(g, viewImage, EMath.round(view.getViewLocation().getX0()), EMath.round(view.getViewLocation().getY0()));
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
        boolean pressedHBar = hBar.setMousePressed(screenX, screenY);
        boolean pressedVBar = vBar.setMousePressed(screenX, screenY);
        boolean pressedView = view.setMousePressed(screenX, screenY);
        return pressedHBar || pressedVBar || pressedView;
    }

    @Override
    public void setMouseReleased(int screenX, int screenY) {
        hBar.setMouseReleased(screenX, screenY);
        vBar.setMouseReleased(screenX, screenY);
        view.setMouseReleased(screenX, screenY);
    }

    @Override
    public boolean setMouseScrolled(int screenX, int screenY, double wheelDelta) {
        boolean scrolledHBar = hBar.setMouseScrolled(screenX, screenY, wheelDelta);
        boolean scrolledVBar = vBar.setMouseScrolled(screenX, screenY, wheelDelta);
        boolean scrolledView = view.setMouseScrolled(screenX, screenY, wheelDelta);
        return scrolledHBar || scrolledVBar || scrolledView;
    }

    @Override
    public void focusLost(boolean fromClick) {
        hBar.focusLost(fromClick);
        vBar.focusLost(fromClick);
        view.focusLost(fromClick);
    }
}
