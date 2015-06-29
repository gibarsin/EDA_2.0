package pile.queue.priority;

import java.util.NoSuchElementException;

public class PriorityQueueImpl<T> implements PriorityQueue<T> {
    private Node<T> first;

    @Override
    public void enqueue(T elem, int priority) {
        if (elem == null || priority <= 0) {
            throw new IllegalArgumentException();
        }
        first = enqueueRec(first, elem, priority);

        Node<T> curr = first;

        while (curr != null) {
            System.out.print(curr.head + "(" + curr.priority + ") ");
            curr = curr.tail;
        }
        System.out.println();
    }

    private Node<T> enqueueRec(Node<T> curr, T elem, int priority) {
        if (curr == null || priority <= curr.priority) {
            Node<T> node = new Node<>(elem, priority);
            node.tail = curr;

            return node;
        }
        curr.tail = enqueueRec(curr.tail, elem, priority);

        return curr;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T value = first.head;
        first = first.tail;

        return value;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    private static class Node<T> {
        private T head;
        private int priority;
        private Node<T> tail;

        public Node(T elem, int priority) {
            this.head = elem;
            this.priority = priority;
        }
    }
}
