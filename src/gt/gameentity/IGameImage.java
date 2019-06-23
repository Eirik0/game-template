package gt.gameentity;

public interface IGameImage extends Sizable, Sized {
    IGraphics getGraphics();

    IGameImage getSubimage(int x0, int y0, int width, int height);
}
