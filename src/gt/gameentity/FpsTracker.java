package gt.gameentity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import gt.component.ComponentCreator;
import gt.gameloop.TimeConstants;

public class FpsTracker implements GameEntity {
    private int frames = 0;
    private double updateTime = 0;
    private double sleepingTime = 0;

    private long actualTimeStart = System.nanoTime();
    private int currentFps = 0;
    private double currentSleepingTime = 0;

    private Font font = ComponentCreator.DEFAULT_FONT_SMALL;

    private static final FpsTracker instance = new FpsTracker();

    private FpsTracker() {
    }

    public static FpsTracker getInstance() {
        return instance;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void update(double dt) {
        ++frames;
        updateTime += dt;
        if (System.nanoTime() - actualTimeStart > TimeConstants.NANOS_PER_SECOND) {
            actualTimeStart = System.nanoTime();
            currentFps = frames;
            currentSleepingTime = sleepingTime;
            frames = 0;
            updateTime = 0;
            sleepingTime = 0;
        }
    }

    public void addTimeSleeping(double dt) {
        sleepingTime += dt;
    }

    @Override
    public void drawOn(Graphics2D graphics) {
        graphics.setColor(Color.RED);
        graphics.setFont(font);

        int percentSleeping = round(currentSleepingTime / TimeConstants.NANOS_PER_SECOND * 100);
        long actualElapsed = System.nanoTime() - actualTimeStart;
        int drift = round((updateTime - actualElapsed) / TimeConstants.NANOS_PER_MILLISECOND);

        String fpsText = new StringBuilder("Fps: ").append(currentFps).toString();
        String sleepText = new StringBuilder("Sleeping: ").append(Integer.toString(percentSleeping)).append("%").toString();
        String driftText = new StringBuilder("Drift: ").append(Integer.toString(drift)).append("ms").toString();

        drawCenteredYString(graphics, fpsText, 10, 15);
        drawCenteredYString(graphics, sleepText, 10, 35);
        drawCenteredYString(graphics, driftText, 10, 55);
    }
}
