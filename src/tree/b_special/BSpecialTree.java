package tree.b_special;

import java.util.Comparator;

public class BSpecialTree<T> {
    private final int keysNumber;
    private final Comparator<T> cmp;
    private Node<T> root;

    public BSpecialTree(Comparator<T> cmp, int keysNumber) {
        this.cmp = cmp;
        this.keysNumber = keysNumber;
    }

    private class LeafNode implements Node<T> {
        private T[] values;
        private int size;

        private LeafNode() {
            values = (T[]) new Object[keysNumber];
            size = 0;
        }

        @Override
        public boolean belongs(T elem) {
            for (int i = 0; i < size; i++) {
                if (cmp.compare(elem, values[i]) == 0) {
                    return true;
                }
            }
            return false;
        }
    }

    private class internalNode implements Node<T> {
        private T head;
        private Node<T> left, right;

        public internalNode(T head, Node<T> left, Node<T> right) {
            this.head = head;
            this.left = left;
            this.right = right;

        }

        @Override
        public boolean belongs(T elem) {
            int res = cmp.compare(head, elem);
            Node next = res > 0 ? left : right;

            if (next == null) {
                return false;
            }

            return next.belongs(elem);
        }
    }
}
