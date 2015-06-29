package list.sorted;

import java.util.Comparator;

public class SortedListImpl<T> implements SortedList<T> {
    private Node<T> first, lastAdded;
    private Comparator<T> cmp;

    public SortedListImpl(Comparator<T> cmp) {
        this.cmp = cmp;
        this.first = null;
        this.lastAdded = null;
    }

    @Override
    public void add(T value) {
        first = addRec(first, null, value);
    }

    private Node<T> addRec(Node<T> node, Node<T> prev, T value) {
        if (node == null || cmp.compare(node.head, value) > 0) {
            Node<T> newNode = new Node<T>(value, prev, lastAdded, node);
            lastAdded = newNode;

            return newNode;
        }
        node.tail = addRec(node.tail, node, value);

        return node;
    }

    @Override
    public void undo() {
        if (lastAdded != null) {
            if (lastAdded.prev != null) {
                lastAdded.prev.tail = lastAdded.tail;
            } else { //It is the first element of the list
                first = first.tail;
            }
            if (lastAdded.tail != null) {
                lastAdded.tail.prev = lastAdded.prev;
            }
            lastAdded = lastAdded.last;
        }
    }

    @Override
    public void print() {
        Node<T> node = first;

        while (node != null) {
            System.out.print(node.head + " ");
            node = node.tail;
        }
        System.out.println();
    }

    private static class Node<T> {
        private T head;
        private Node<T> prev, last, tail;

        private Node(T head, Node<T> prev, Node<T> last, Node<T> tail) {
            this.head = head;
            this.prev = prev;
            this.last = last;
            this.tail = tail;
        }
    }
}
