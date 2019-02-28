package gt.gameentity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CartesianSpaceTest {
    @Test
    public void testOneToOne() {
        CartesianSpace cs = new CartesianSpace(500, 500, 0, 0, 500, 500);
        assertEquals(100, cs.getImageX(100));
        assertEquals(100, cs.getImageY(100));
    }

    @Test
    public void testTwoToOne() {
        CartesianSpace cs = new CartesianSpace(1000, 1000, 0, 0, 500, 500);
        assertEquals(200, cs.getImageX(100));
        assertEquals(200, cs.getImageY(100));
    }

    @Test
    public void testOneToTwo() {
        CartesianSpace cs = new CartesianSpace(250, 250, 0, 0, 500, 500);
        assertEquals(50, cs.getImageX(100));
        assertEquals(50, cs.getImageY(100));
    }

    @Test
    public void testOffestX() {
        CartesianSpace cs = new CartesianSpace(100, 100, 100, 0, 100, 100);
        assertEquals(50, cs.getImageX(150));
        assertEquals(50, cs.getImageY(50));
    }

    @Test
    public void testOffestY() {
        CartesianSpace cs = new CartesianSpace(100, 100, 0, 100, 100, 100);
        assertEquals(50, cs.getImageX(50));
        assertEquals(50, cs.getImageY(150));
    }

    @Test
    public void testScaleY() {
        CartesianSpace cs = new CartesianSpace(100, 200, 0, 0, 100, 100);
        assertEquals(100, cs.getImageX(100));
        assertEquals(150, cs.getImageY(100));
    }

    @Test
    public void testScaleX() {
        CartesianSpace cs = new CartesianSpace(200, 100, 0, 0, 100, 100);
        assertEquals(150, cs.getImageX(100));
        assertEquals(100, cs.getImageY(100));
    }

    @Test
    public void testMove() {
        CartesianSpace cs = new CartesianSpace(500, 500, 0, 0, 1000, 1000);
        assertEquals(50, cs.getImageX(100));
        assertEquals(50, cs.getImageY(100));
        cs.move(50, 0);
        assertEquals(0, cs.getImageX(100));
        assertEquals(50, cs.getImageY(100));
        cs.move(0, 50);
        assertEquals(0, cs.getImageX(100));
        assertEquals(0, cs.getImageY(100));
    }
}
