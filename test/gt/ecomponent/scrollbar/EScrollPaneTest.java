package gt.ecomponent.scrollbar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gt.ecomponent.location.EFixedLocation;
import gt.gameentity.TestGameImageDrawer;

public class EScrollPaneTest {
    private static EScrollPane createAndCheckViewLocation(EFixedLocation cl, TestViewport view, int x0, int y0, int width, int height) {
        EScrollPane scrollPane = new EScrollPane(cl, view, new TestGameImageDrawer());
        ViewportWindow window = view.getWindow();
        assertEquals(x0, window.getX0());
        assertEquals(y0, window.getY0());
        assertEquals(width, window.getWidth());
        assertEquals(height, window.getHeight());
        return scrollPane;
    }

    @Test
    public void testNoScrollBars() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 100, 100);
        createAndCheckViewLocation(cl, view, 0, 0, 100, 100);
    }

    @Test
    public void testVerticalBarOnly() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 80, 200);
        createAndCheckViewLocation(cl, view, 0, 0, 80, 100);
    }

    @Test
    public void testHorizontalBarOnly() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 200, 80);
        createAndCheckViewLocation(cl, view, 0, 0, 100, 80);
    }

    @Test
    public void testHorizontalCausesVertical() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 101, 100);
        createAndCheckViewLocation(cl, view, 0, 0, 80, 80);
    }

    @Test
    public void testVerticalCausesHorizontal() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 100, 101);
        createAndCheckViewLocation(cl, view, 0, 0, 80, 80);
    }
}
