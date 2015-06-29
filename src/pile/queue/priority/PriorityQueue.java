package pile.queue.priority;

public interface PriorityQueue<T> {
    void enqueue(T elem, int priority);

    T dequeue();

    boolean isEmpty();
}
