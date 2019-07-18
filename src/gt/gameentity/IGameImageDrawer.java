package gt.gameentity;

public interface IGameImageDrawer {
    IGameImage newGameImage();

    IGameImage newGameImage(int width, int height);

    void drawImage(IGraphics g, IGameImage image, double x, double y);

    void drawImage(IGraphics g, IGameImage image, double x, double y, double width, double height);
}
