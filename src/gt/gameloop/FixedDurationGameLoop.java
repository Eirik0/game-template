package gt.gameloop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import gt.gameentity.FpsTracker;
import gt.gamestate.UserInput;
import gt.util.EventQueue;
import gt.util.Pair;

public class FixedDurationGameLoop {
    private static final double TARGET_FPS = 60;

    private static final double TARGET_NANOS_PER_FRAME = TimeConstants.NANOS_PER_SECOND / TARGET_FPS;

    private static final FixedDurationGameLoop instance = new FixedDurationGameLoop();

    private Map<String, GameLoopItem> gameLoopItemMap = new HashMap<>();
    private final EventQueue<Pair<String, UserInput>> eQueue = new EventQueue<>();

    private FixedDurationGameLoop() {
    }

    public static void startLoop() {
        new Thread(() -> instance.runLoop(), "Game_Loop_Thread").start();
    }

    private void runLoop() {
        long loopStart;
        for (;;) {
            loopStart = System.nanoTime();

            FpsTracker.getInstance().update(TARGET_NANOS_PER_FRAME);

            eQueue.popAll(userInputPair -> {
                GameLoopItem gameLoopItem = gameLoopItemMap.get(userInputPair.getFirst());
                if (gameLoopItem != null) {
                    gameLoopItem.handleUserInput(userInputPair.getSecond());
                }
            });

            Collection<GameLoopItem> items = new ArrayList<>(gameLoopItemMap.values());
            for (GameLoopItem item : items) {
                item.update(TARGET_NANOS_PER_FRAME);
            }
            for (GameLoopItem item : items) {
                item.draw();
            }

            double timeToSleep = TARGET_NANOS_PER_FRAME - (System.nanoTime() - loopStart);
            if (timeToSleep > 0) {
                FpsTracker.getInstance().addTimeSleeping(timeToSleep);
                try {
                    Thread.sleep(Math.round(timeToSleep / TimeConstants.NANOS_PER_MILLISECOND));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void addItem(GameLoopItem item) {
        instance.addItemToMap(item.getName(), item);
    }

    private synchronized void addItemToMap(String name, GameLoopItem item) {
        HashMap<String, GameLoopItem> newItemMap = new HashMap<>(gameLoopItemMap);
        newItemMap.put(name, item);
        gameLoopItemMap = newItemMap;
    }

    public static void removeItem(GameLoopItem item) {
        instance.removeItemFromMap(item.getName());
    }

    private synchronized void removeItemFromMap(String name) {
        HashMap<String, GameLoopItem> newItemMap = new HashMap<>(gameLoopItemMap);
        newItemMap.remove(name);
        gameLoopItemMap = newItemMap;
    }

    public static void enqueueUserInput(GameLoopItem item, UserInput input) {
        instance.pushUserInput(item.getName(), input);
    }

    private synchronized void pushUserInput(String name, UserInput input) {
        eQueue.push(Pair.valueOf(name, input));
    }
}
