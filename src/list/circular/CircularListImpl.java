package list.circular;


import java.util.NoSuchElementException;

public class CircularListImpl<T> implements CircularList<T> {
    private Node last, curr;

    public CircularListImpl() {

    }

    private CircularListImpl(Node last) {
        this.last = last;
        this.curr = null;
    }

    @Override
    public void addLast(T elem) {
        if(elem == null) {
            throw new IllegalArgumentException();
        }
        Node node = new Node(elem);

        if(last == null) {
            last = node;
            last.tail = last;
        } else {
            node.tail = last.tail;
            last.tail = node;
            last = node;
        }
    }

    @Override
    public T getNext() {
        if(last == null) {
            throw new NoSuchElementException();
        }
        curr = curr.tail;

        return curr.head;
    }

    @Override
    public void reset() {
        curr = last;
    }

    @Override
    public CircularList<T> split() {
        if(curr == null) {
            throw new IllegalStateException();
        }
        if(curr == last) {
            return new CircularListImpl<>();
        } else {
            Node first = curr.tail;
            curr.tail = last.tail;
            last.tail = first;
            Node newLast = last;
            last = curr;

            return new CircularListImpl<>(newLast);
        }
    }

    private class Node {
        private T head;
        private Node tail;

        private Node(T head) {
            this.head = head;
        }
    }
}
