package gt.gameentity;

import gt.util.EMath;

public class GridSizer {
    public final double cellSize;
    public final double gridWidth;
    public final double gridHeight;
    public final double offsetX;
    public final double offsetY;

    public GridSizer(int imageWidth, int imageHeight, int numCellsX, int numCellsY) {
        double cellSizeX = (double) imageWidth / numCellsX;
        double cellSizeY = (double) imageHeight / numCellsY;
        cellSize = Math.min(cellSizeX, cellSizeY);
        gridWidth = cellSize * numCellsX;
        gridHeight = cellSize * numCellsY;
        offsetX = (imageWidth - gridWidth) / 2;
        offsetY = (imageHeight - gridHeight) / 2;
    }

    public double getCornerX(int x) { // Upper left
        return x * cellSize + offsetX;
    }

    public double getCornerY(int y) { // Upper left
        return y * cellSize + offsetY;
    }

    public double getCenterX(int x) {
        return x * cellSize + cellSize / 2 + offsetX;
    }

    public double getCenterY(int y) {
        return y * cellSize + cellSize / 2 + offsetY;
    }

    public int getCoordinateX(int screenX) {
        return EMath.round((screenX - offsetX - cellSize / 2) / cellSize);
    }

    public int getCoordinateY(int screenY) {
        return EMath.round((screenY - offsetY - cellSize / 2) / cellSize);
    }
}
