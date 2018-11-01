package gt.drawable;

public interface GameState extends Drawable {
    void componentResized(int width, int height);

    void handleUserInput(UserInput input);
}
