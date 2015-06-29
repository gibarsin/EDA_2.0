package pile.stack;

import java.util.NoSuchElementException;

public class StackImpl<T> implements Stack<T> {
    private Node<T> last;

    @Override
    public void push(T elem) {
        if (elem == null) {
            throw new IllegalArgumentException();
        }
        last = new Node<>(elem, last);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T value = last.head;

        last = last.prev;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return last == null;
    }

    private static class Node<T> {
        private T head;
        private Node<T> prev;

        private Node(T head, Node<T> prev) {
            this.head = head;
            this.prev = prev;
        }
    }
}
