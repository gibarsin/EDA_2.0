package tree.binary_search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class BST<T> {
    private Node<T> root;
    private Comparator<T> cmp;

    public BST(Comparator<T> cmp) {
        this.cmp = cmp;
    }

    public void add(T value) {
        root = addRec(root, value);
    }

    private Node<T> addRec(Node<T> node, T value) {
        if(node == null) {
            return new Node<>(value);
        }

        int comparison = cmp.compare(node.head, value);

        if(comparison > 0) {
            node.left = addRec(node.left, value);
        } else if(comparison < 0) {
            node.right = addRec(node.right, value);
        }

        return node;
    }

    public boolean branchN(int n) {
        return branchNRec(root, n, 0);
    }

    /* Should only be called when T is Integer */
    private boolean branchNRec(Node<T> curr, int n, int sum) {
        if(curr == null) {
            return false;
        }
        int acum = sum + (Integer)curr.head;

        if(acum == n && curr.left == null && curr.right == null) {
            return true;
        } else if(acum > n) {
            return false;
        }
        return branchNRec(curr.left, n, acum) || branchNRec(curr.right, n, acum);
    }

    public List<T> getInOrder(int inf, int sup) {
        if(inf >= 1 && sup >= inf) {
            List<T> list = new LinkedList<T>();
            getInOrderRec(root, 1, inf, sup, list);

            return list;
        }
        return null;
    }

    private int getInOrderRec(Node<T> node, int index, int inf, int sup, List<T> l) {
        if(node == null) {
            return index;
        }
        index = getInOrderRec(node.left, index, inf, sup, l);

        if(index == -1) {
            return -1;
        } else if(index > sup) {
            return -1;
        } else if(index >= inf) {
            l.add(node.head);
        }
        return getInOrderRec(node.right, index + 1, inf, sup, l);
    }

    private static class Node<T> {
        private T head;
        private Node<T> left, right;

        private Node(T head) {
            this.head = head;
        }
    }
}
