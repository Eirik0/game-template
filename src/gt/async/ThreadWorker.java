package gt.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class ThreadWorker {
    public static final Consumer<ThreadWorker> DO_NOTHING_ON_COMPLETE = worker -> {
    };

    private final String name;

    private final Consumer<ThreadWorker> completedWorkerConsumer;

    private Thread thread;
    private volatile boolean keepRunning = true;
    private volatile boolean workStarted = false;

    private final BlockingQueue<Runnable> runnableQueue = new ArrayBlockingQueue<>(1);

    public ThreadWorker() {
        this("Worker", DO_NOTHING_ON_COMPLETE);
    }

    public ThreadWorker(String name) {
        this(name, DO_NOTHING_ON_COMPLETE);
    }

    public ThreadWorker(Consumer<ThreadWorker> completedWorkerConsumer) {
        this("Worker", completedWorkerConsumer);
    }

    public ThreadWorker(String name, Consumer<ThreadWorker> completedWorkerConsumer) {
        this.name = name;
        this.completedWorkerConsumer = completedWorkerConsumer;
    }

    private void maybeInitThread() {
        if (thread == null) {
            keepRunning = true;
            thread = new Thread(() -> {
                while (keepRunning) {
                    try {
                        Runnable runnable = runnableQueue.take();
                        synchronized (this) {
                            workStarted = true;
                            notify();
                        }
                        runnable.run();
                        if (keepRunning) {
                            completedWorkerConsumer.accept(this);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, name + "_Thread_" + ThreadNumber.getThreadNum(name));

            thread.start();
        }
    }

    public void joinThread() {
        keepRunning = false;
        if (thread != null) {
            try {
                runnableQueue.put(() -> {
                    // We have to say do nothing if we are waiting to take from the queue
                });
                thread.join();
                thread = null;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void workOn(Runnable runnable) {
        workStarted = false;
        maybeInitThread();
        try {
            runnableQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void waitForStart() {
        while (!workStarted) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
