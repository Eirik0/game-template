package gt.gameentity;

import gt.util.EMath;

public class GridSizer implements Sized {
    private final double imageWidth;
    private final double imageHeight;
    public final double cellSize;
    public final double gridWidth;
    public final double gridHeight;
    public final double offsetX;
    public final double offsetY;

    public GridSizer(double imageWidth, double imageHeight, int numCellsX, int numCellsY) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        double cellSizeX = imageWidth / numCellsX;
        double cellSizeY = imageHeight / numCellsY;
        cellSize = Math.min(cellSizeX, cellSizeY);
        gridWidth = cellSize * numCellsX;
        gridHeight = cellSize * numCellsY;
        offsetX = (imageWidth - gridWidth) / 2;
        offsetY = (imageHeight - gridHeight) / 2;
    }

    @Override
    public double getWidth() {
        return imageWidth;
    }

    @Override
    public double getHeight() {
        return imageHeight;
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
