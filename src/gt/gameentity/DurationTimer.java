package gt.gameentity;

public class DurationTimer implements Updatable {
    private final double duration;

    private double internalTimer = 0;

    private boolean countUp = true;

    public DurationTimer(double duration) {
        this.duration = duration;
    }

    public DurationTimer(double duration, boolean countUp) {
        this.duration = duration;
        this.countUp = countUp;
    }

    @Override
    public void update(double dt) {
        internalTimer += countUp ? dt : -dt;
    }

    public double getPercentComplete() {
        return Math.min(Math.max(0, internalTimer / duration), 1);
    }

    public void reset() {
        internalTimer = 0;
        countUp = true;
    }

    public void setCountUp(boolean countUp) {
        if (countUp) {
            countUp();
        } else {
            countDown();
        }
    }

    public void countUp() {
        internalTimer = Math.max(internalTimer, 0);
        countUp = true;
    }

    public void countDown() {
        internalTimer = Math.min(duration, internalTimer);
        countUp = false;
    }
}
