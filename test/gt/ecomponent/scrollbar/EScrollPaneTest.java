package gt.ecomponent.scrollbar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gt.ecomponent.EComponentLocation;
import gt.ecomponent.location.EFixedLocation;
import gt.ecomponent.scrollbar.EScrollPane;

public class EScrollPaneTest {
    private static EScrollPane createAndCheckViewLocation(EFixedLocation cl, TestViewport view, int x0, int y0, int x1, int y1) {
        EScrollPane scrollPane = new EScrollPane(cl, view);
        EComponentLocation viewLocation = view.getViewLocation();
        assertEquals(x0, viewLocation.getX0());
        assertEquals(y0, viewLocation.getY0());
        assertEquals(x1, viewLocation.getX1());
        assertEquals(y1, viewLocation.getY1());
        return scrollPane;
    }

    @Test
    public void testNoScrollBars() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 100, 100);
        createAndCheckViewLocation(cl, view, 0, 0, 99, 99);
    }

    @Test
    public void testNoScrollBars_Centered() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 80, 80);
        createAndCheckViewLocation(cl, view, 10, 10, 89, 89);
    }

    @Test
    public void testVerticalBarOnly() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 80, 200);
        createAndCheckViewLocation(cl, view, 0, 0, 79, 99);
    }

    @Test
    public void testVerticalBarOnly_Centered() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 60, 200);
        createAndCheckViewLocation(cl, view, 10, 0, 69, 99);
    }

    @Test
    public void testHorizontalBarOnly() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 200, 80);
        createAndCheckViewLocation(cl, view, 0, 0, 99, 79);
    }

    @Test
    public void testHorizontalBarOnly_Centered() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 200, 60);
        createAndCheckViewLocation(cl, view, 0, 10, 99, 69);
    }

    @Test
    public void testHorizontalCausesVertical() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 101, 100);
        createAndCheckViewLocation(cl, view, 0, 0, 79, 79);
    }

    @Test
    public void testVerticalCausesHorizontal() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestViewport view = new TestViewport(cl, 100, 101);
        createAndCheckViewLocation(cl, view, 0, 0, 79, 79);
    }
}
