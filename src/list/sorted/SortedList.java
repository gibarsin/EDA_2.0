package list.sorted;

public interface SortedList<T> {
    void add(T value);

    void undo();

    void print();
}
