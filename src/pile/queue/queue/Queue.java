package pile.queue.queue;

public interface Queue<T> {
    void enqueue(T elem);

    T dequeue();

    boolean isEmpty();
}

