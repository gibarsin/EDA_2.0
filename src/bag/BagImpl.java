package bag;

public class BagImpl<T> implements Bag<T> {
    private Node<T> first;

    @Override
    public void add(T elem) {
        first = addRec(first, elem);
    }

    private Node<T> addRec(Node<T> curr, T elem) {
        if (curr == null) {
            return new Node(elem);
        }
        if (curr.elem.equals(elem)) {
            curr.count++;
            return curr;
        }
        curr.tail = addRec(curr.tail, elem);
        if (curr.tail.elem.equals(elem) && curr.tail.count >= curr.count) {
            Node<T> ret = curr.tail;
            curr.tail = curr.tail.tail;
            ret.tail = curr;

            return ret;
        }
        return curr;
    }

    @Override
    public void remove(T elem) {
        first = removeRec(first, elem);
    }

    private Node<T> removeRec(Node<T> curr, T elem) {
        if (curr == null) {
            return null;
        } else if (curr.elem.equals(elem)) {
            curr.count--;
            if (curr.count == 0) {
                return curr.tail;
            }
            return relocateRec(curr, curr.tail);
        }
        curr.tail = removeRec(curr.tail, elem);

        return curr;
    }

    private Node<T> relocateRec(Node<T> relocated, Node<T> curr) {
        if (curr == null) {
            return relocated;
        } else if (curr.count <= relocated.count) {
            relocated.tail = curr;

            return relocated;
        }
        curr.tail = relocateRec(relocated, curr.tail);

        return curr;
    }

    @Override
    public void print() {
        Node<T> curr = first;

        while (curr != null) {
            System.out.print(curr.elem + "(" + curr.count + ") ");
            curr = curr.tail;
        }
        System.out.println();
    }

    private static class Node<T> {
        private T elem;
        private int count;
        private Node<T> tail;

        private Node(T elem) {
            this.elem = elem;
            this.count = 1;
        }
    }
}
