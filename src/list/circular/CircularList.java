package list.circular;

public interface CircularList<T> {
    public void addLast(T elem);

    public T getNext();

    public void reset();

    public CircularList<T> split();
}

