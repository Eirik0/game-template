package gt.component;

import java.awt.Graphics;

import gt.drawable.GameState;
import gt.drawable.UserInput;
import gt.gameloop.FixedDurationGameLoop;

public class GamePanelController {
    private final GameImage gameImage = new GameImage();

    private GameState currentState;

    private volatile boolean ignoreWait = false;
    private volatile boolean paintComplete = false;

    public GamePanelController(GamePanel panel, GameState initialState) {
        currentState = initialState;
        MouseTracker mouseTracker = new MouseTracker(this::handleUserInput);
        GameMouseAdapter mouseAdapter = new GameMouseAdapter(mouseTracker);
        panel.addMouseListener(mouseAdapter);
        panel.addMouseMotionListener(mouseAdapter);
    }

    public void addToGameLoop(String name, Runnable repaintRunnable) {
        FixedDurationGameLoop.addRunnable(name, () -> {
            currentState.drawOn(gameImage.getGraphics());
            repaintAndWait(repaintRunnable);
        });
    }

    public void removeFromGameLoop(String name) {
        FixedDurationGameLoop.removeRunnable(name);
        synchronized (this) {
            ignoreWait = true;
            notify();
        }
    }

    private void repaintAndWait(Runnable repaintRunnable) {
        paintComplete = false;
        repaintRunnable.run();
        synchronized (this) {
            int tries = 0;
            while (!ignoreWait && !paintComplete && ++tries < 5) {
                try {
                    wait(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void drawOn(Graphics g) {
        g.drawImage(gameImage.getImage(), 0, 0, null);
        synchronized (this) {
            paintComplete = true;
            notify();
        }
    }

    public void checkResized(int width, int height) {
        gameImage.checkResized(width, height);
        currentState.componentResized(width, height);
    }

    private void handleUserInput(UserInput input) {
        currentState.handleUserInput(input);
    }
}
