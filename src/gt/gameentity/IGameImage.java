package gt.gameentity;

public interface IGameImage extends SizedSizable {
    IGraphics getGraphics();

    IGameImage getSubimage(int x0, int y0, int width, int height);
}
