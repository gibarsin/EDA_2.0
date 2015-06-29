package set;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetImpl<E> implements Set<E> {
    private Node<E> first;

    @Override
    public int size() {
        int size = 0;
        Node<E> curr = first;

        while (curr != null) {
            size++;
            curr = curr.tail;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        return containsRec(first, o);
    }

    private boolean containsRec(Node<E> curr, Object o) {
        if (curr == null) {
            return false;
        }
        if (curr.head.equals(o)) {
            return true;
        }
        return containsRec(curr.tail, o);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> curr = first;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public E next() {
                E value = curr.head;
                curr = curr.tail;

                return value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        Node<E> prev = null;
        Node<E> curr = first;
        Node<E> newNode = new Node<>(e);

        while (curr != null) {
            if (curr.head.equals(e)) {
                return false;
            }
            prev = curr;
            curr = curr.tail;
        }
        if (prev == null) {
            first = newNode;
        } else {
            prev.tail = newNode;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        Node<E> prev = null;
        Node<E> curr = first;

        while (curr != null) {
            if (curr.head.equals(o)) {
                if (prev == null) {
                    first = null;
                } else {
                    prev.tail = curr.tail;
                }
                return true;
            }
            prev = curr;
            curr = curr.tail;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        first = null;
    }

    private static class Node<E> {
        private E head;
        private Node<E> tail;

        private Node(E head) {
            this.head = head;
        }
    }
}
