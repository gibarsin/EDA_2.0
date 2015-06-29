package pile.stack;

public interface Stack<T> {
    void push(T elem);

    T pop();

    boolean isEmpty();

}
