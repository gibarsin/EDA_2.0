package list.priority;

public class PriorityList<S, T> {
    private Node<S, T> first;

    public void add(S key, T value) {
        first = addRec(first, key, value);
    }

    private Node<S, T> addRec(Node<S, T> curr, S key, T value) {
        if (curr == null) {
            return new Node<>(key, value);
        } else if (curr.key.equals(key)) {
            return addRec(curr.tail, key, value);
        } else {
            curr.tail = addRec(curr.tail, key, value);

            return curr;
        }
    }

    public T get(S key) {
        Node<S, T> aux = getRec(first, null, key);

        if (aux != null) {
            if (aux != first) {
                aux.tail = first;
                first = aux;
            }
            return aux.value;
        }

        return null;
    }

    private Node<S, T> getRec(Node<S, T> curr, Node<S, T> prev, S key) {
        if (curr == null) {
            return null;
        } else if (curr.key.equals(key)) {
            if (prev != null) {
                prev.tail = curr.tail;
            }
            return curr;
        }

        return getRec(curr.tail, curr, key);
    }

    public void print() {
        Node<S, T> curr = first;

        while (curr != null) {
            System.out.println("KEY: " + curr.key + "\t VALUE: " + curr.value);

            curr = curr.tail;
        }
    }

    private static class Node<S, T> {
        private S key;
        private T value;
        private Node<S, T> tail;

        private Node(S key, T value) {
            this.key = key;
            this.value = value;
        }
    }
}