package list.circular;

public interface CircularList<T> {
    void addLast(T elem);

    T getNext();

    void reset();

    CircularList<T> split();
}

