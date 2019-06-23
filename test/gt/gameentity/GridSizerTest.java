package gt.gameentity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GridSizerTest {
    private static void checkSizer(GridSizer sizer, double cellWidth, double gridWidth, double gridHeight, double offsetX, double offsetY) {
        assertEquals(cellWidth, sizer.cellSize);
        assertEquals(gridWidth, sizer.gridWidth);
        assertEquals(gridHeight, sizer.gridHeight);
        assertEquals(offsetX, sizer.offsetX);
        assertEquals(offsetY, sizer.offsetY);
    }

    private static void checkCoordinates(GridSizer sizer, double cornerX0, double cornerY0, double cornerX1, double cornerY1,
            double centerX1, double centerY1) {
        assertEquals(cornerX0, sizer.getCornerX(0));
        assertEquals(cornerY0, sizer.getCornerY(0));
        assertEquals(cornerX1, sizer.getCornerX(1));
        assertEquals(cornerY1, sizer.getCornerY(1));
        assertEquals(centerX1, sizer.getCenterX(1));
        assertEquals(centerY1, sizer.getCenterY(1));
    }

    @Test
    public void testFourByFour() {
        GridSizer sizer = new GridSizer(4, 4, 4, 4);
        checkSizer(sizer, 1, 4, 4, 0, 0);
        checkCoordinates(sizer, 0, 0, 1, 1, 1.5, 1.5);
    }

    @Test
    public void testFourByFourCenteredX() {
        GridSizer sizer = new GridSizer(200, 100, 4, 4);
        checkSizer(sizer, 25, 100, 100, 50, 0);
        checkCoordinates(sizer, 50, 0, 75, 25, 87.5, 37.5);
    }

    @Test
    public void testFourByFourCenteredY() {
        GridSizer sizer = new GridSizer(100, 200, 4, 4);
        checkSizer(sizer, 25, 100, 100, 0, 50);
    }

    @Test
    public void testTwoByFour() {
        GridSizer sizer = new GridSizer(100, 100, 2, 4);
        checkSizer(sizer, 25, 50, 100, 25, 0);
    }

    @Test
    public void testFourByTwo() {
        GridSizer sizer = new GridSizer(100, 100, 4, 2);
        checkSizer(sizer, 25, 100, 50, 0, 25);
    }

    @Test
    public void testGetCellCoordinates() {
        GridSizer sizer = new GridSizer(60, 30, 3, 3);
        checkSizer(sizer, 10, 30, 30, 15, 0);
        assertEquals(15, sizer.getCornerX(0));
        assertEquals(0, sizer.getCornerY(0));
        assertEquals(20, sizer.getCenterX(0));
        assertEquals(5, sizer.getCenterY(0));
        assertEquals(25, sizer.getCornerX(1));
        assertEquals(10, sizer.getCornerY(1));
        assertEquals(30, sizer.getCenterX(1));
        assertEquals(15, sizer.getCenterY(1));
    }
}
