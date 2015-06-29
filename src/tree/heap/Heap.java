package tree.heap;

import java.util.Comparator;

public class Heap<T> {
    private Node<T> root;
    private Comparator<? super T> cmp;

    public Heap(Comparator<? super T> cmp) {
        this.cmp = cmp;
    }

    public void add(T value) {
        root = add(root, value);
    }

    public boolean contains(T value) {
        return contains(root, value);
    }

    private Node<T> add(Node<T> node, T value) {
        return null;
    }

    private boolean contains(Node<T> node, T value) {
        return false;
    }

    public T remove(Node<T> node, T value) {
        Node<T> aux = root;

        root = deleteRec(root);

        return aux == null ? null : aux.head;
    }

    private Node<T> deleteRec(Node<T> curr) {
        if (curr == null) {
            return null;
        }
        if (curr.left != null && curr.right != null) {
            int res = cmp.compare(curr.left.head, curr.right.head);

            if (res > 0) {
                curr.head = curr.left.head;
                curr.left = deleteRec(curr.left);
            } else {
                curr.head = curr.right.head;
                curr.right = deleteRec(curr.right);
            }
        } else if (curr.left != null) {
            curr.head = curr.left.head;
            curr.left = deleteRec(curr.left);
        } else if (curr.right != null) {
            curr.head = curr.right.head;
            curr.right = deleteRec(curr.right);
        } else { //I'm a leaf and have been selected as the max from the prev. node
            return null;
        }

        return curr;
    }

    private static class Node<T> {
        private T head;
        private Node<T> left, right;

        private Node(T head) {
            this.head = head;
        }
    }
}
