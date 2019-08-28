package gt.ecomponent.scrollbar;

import java.awt.Color;

import gt.ecomponent.EBackground;
import gt.ecomponent.EComponent;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.list.EComponentLocation;
import gt.gameentity.IGameImage;
import gt.gameentity.IGameImageDrawer;
import gt.gameentity.IGraphics;
import gt.gameentity.Sizable;
import gt.settings.GameSettings;
import gt.util.EMath;

public class EScrollPane implements EComponent, EComponentSettings, Sizable {
    private static final Color BACKGROUND_COLOR = GameSettings.getValue(SCROLL_PANE_BACKGROUND_COLOR, SCROLL_PANE_BACKGROUND_COLOR_DEFAULT);

    private final EComponentLocation cl;
    private final EViewport view;
    private final IGameImageDrawer imageDrawer;
    private final IGameImage viewImage;

    private final EBackground background;
    private final EScrollBar hBar;
    private final EScrollBar vBar;

    public EScrollPane(EComponentLocation cl, EViewport view, IGameImageDrawer imageDrawer) {
        this.cl = cl;
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
    public void setSize(double width, double height) {
        boolean hBarVisible = view.getWidth() > width || (view.getHeight() > height && view.getWidth() > width - EScrollBar.BAR_WIDTH);
        boolean vBarVisible = view.getHeight() > height || (view.getWidth() > width && view.getHeight() > height - EScrollBar.BAR_WIDTH);
        double newImageWidth = vBarVisible ? width - EScrollBar.BAR_WIDTH : width;
        double newImageHeight = hBarVisible ? height - EScrollBar.BAR_WIDTH : height;
        double x = view.getWidth() - newImageWidth;
        double y = view.getHeight() - newImageHeight;

        ViewportWindow window = view.getWindow();

        if (x < window.getX0() || y < window.getY0()) {
            if (x < 0) {
                x = 0;
                newImageWidth = view.getWidth();
            }
            if (y < 0) {
                y = 0;
                newImageHeight = view.getHeight();
            }
            window.setPosition(Math.min(x, window.getX0()), Math.min(y, window.getY0()));
        }

        hBar.setVisible(hBarVisible);
        vBar.setVisible(vBarVisible);
        hBar.setOtherBarVisible(vBarVisible);
        vBar.setOtherBarVisible(hBarVisible);

        window.setSize(vBarVisible ? width - EScrollBar.BAR_WIDTH : width, hBarVisible ? height - EScrollBar.BAR_WIDTH : height);
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
        imageDrawer.drawImage(g, viewImage, cl.getX0(), cl.getY0());
    }

    @Override
    public boolean setMouseOver(double screenX, double screenY) {
        boolean overVBar = hBar.setMouseOver(screenX, screenY);
        boolean overHBar = vBar.setMouseOver(screenX, screenY);
        boolean overView = view.setMouseOver(screenX - cl.getX0(), screenY - cl.getY0());
        return overVBar || overHBar || overView;
    }

    @Override
    public boolean setMousePressed(double screenX, double screenY) {
        boolean pressedHBar = hBar.setMousePressed(screenX, screenY);
        boolean pressedVBar = vBar.setMousePressed(screenX, screenY);
        boolean pressedView = view.setMousePressed(screenX - cl.getX0(), screenY - cl.getY0());
        return pressedHBar || pressedVBar || pressedView;
    }

    @Override
    public void setMouseReleased(double screenX, double screenY) {
        hBar.setMouseReleased(screenX, screenY);
        vBar.setMouseReleased(screenX, screenY);
        view.setMouseReleased(screenX - cl.getX0(), screenY - cl.getY0());
    }

    @Override
    public boolean setMouseScrolled(double screenX, double screenY, double wheelDelta) {
        boolean scrolledHBar = hBar.setMouseScrolled(screenX, screenY, wheelDelta);
        boolean scrolledVBar = vBar.setMouseScrolled(screenX, screenY, wheelDelta);
        boolean scrolledView = view.setMouseScrolled(screenX - cl.getX0(), screenY - cl.getY0(), wheelDelta);
        return scrolledHBar || scrolledVBar || scrolledView;
    }

    @Override
    public void focusLost(boolean fromClick) {
        hBar.focusLost(fromClick);
        vBar.focusLost(fromClick);
        view.focusLost(fromClick);
    }
}
