package gt.ecomponent.scrollbar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gt.ecomponent.location.EFixedLocation;
import gt.gameentity.TestGameImageDrawer;

public class EScrollPaneTest {
    private static EScrollPane createAndCheckViewLocation(EFixedLocation cl, TestViewport view, int x0, int y0, int x1, int y1) {
        EScrollPane scrollPane = new EScrollPane(cl, view, new TestGameImageDrawer());
        assertEquals(x0, cl.getX0());
        assertEquals(y0, cl.getY0());
        assertEquals(x1, cl.getX0() + view.getViewWidth() - 1);
        assertEquals(y1, cl.getY0() + view.getViewHeight() - 1);
        return scrollPane;
    }

    @Test
    public void testNoScrollBars() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(100, 100);
        createAndCheckViewLocation(cl, view, 0, 0, 99, 99);
    }

    @Test
    public void testVerticalBarOnly() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(80, 200);
        createAndCheckViewLocation(cl, view, 0, 0, 79, 99);
    }

    @Test
    public void testHorizontalBarOnly() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(200, 80);
        createAndCheckViewLocation(cl, view, 0, 0, 99, 79);
    }

    @Test
    public void testHorizontalCausesVertical() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(101, 100);
        createAndCheckViewLocation(cl, view, 0, 0, 79, 79);
    }

    @Test
    public void testVerticalCausesHorizontal() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(100, 101);
        createAndCheckViewLocation(cl, view, 0, 0, 79, 79);
    }
}
