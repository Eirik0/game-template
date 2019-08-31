package gt.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class EventQueue<E> {
    private final Queue<E> sourceQueue = new LinkedList<>();
    private final Queue<E> sinkQueue = new LinkedList<>();

    public synchronized void push(E e) {
        sourceQueue.add(e);
    }

    public void popAll(Consumer<E> eventConsumer) {
        drainSource();
        E event;
        while ((event = sinkQueue.poll()) != null) {
            eventConsumer.accept(event);
        }
    }

    private synchronized void drainSource() {
        E event;
        while ((event = sourceQueue.poll()) != null) {
            sinkQueue.add(event);
        }
    }
}
