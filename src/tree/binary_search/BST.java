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
        if (node == null) {
            return new Node<>(value);
        }

        int comparison = cmp.compare(node.head, value);

        if (comparison > 0) {
            node.left = addRec(node.left, value);
        } else if (comparison < 0) {
            node.right = addRec(node.right, value);
        }

        return node;
    }

    public boolean contains(T elem) {
        if (elem == null) {
            throw new IllegalArgumentException();
        }
        return containsRec(root, elem);
    }

    private boolean containsRec(Node<T> curr, T elem) {
        if (curr == null) {
            return false;
        }
        int res = cmp.compare(curr.head, elem);
        if (res == 0) {
            return true;
        } else {
            Node<T> next = res > 0 ? curr.left : curr.right;

            return containsRec(next, elem);
        }
    }

    public void remove(T elem) {
        if (elem == null) {
            throw new IllegalArgumentException();
        }
        root = removeRec(root, elem);
    }

    private Node<T> removeRec(Node<T> curr, T elem) {
        if (curr == null) {
            return null;
        }
        int res = cmp.compare(curr.head, elem);

        if (res != 0) {
            if (res > 0) {
                curr.left = removeRec(curr.left, elem);
            } else {
                curr.right = removeRec(curr.right, elem);
            }
            return curr;
        }
        if (curr.left == null) {
            return curr.right;
        } else if (curr.right == null) {
            return curr.left;
        } else {
            curr.left = replaceWithInOrderSuccessor(curr.left, curr);

            return curr;
        }
    }

    private Node<T> replaceWithInOrderSuccessor(Node<T> curr, Node<T> toReplace) {
        if (curr.right == null) {
            toReplace.head = curr.head;

            return curr.left;
        }
        curr.right = replaceWithInOrderSuccessor(curr.right, toReplace);

        return curr;
    }

    public boolean branchN(int n) {
        return branchNRec(root, n, 0);
    }

    /* Should only be called when T is Integer */
    private boolean branchNRec(Node<T> curr, int n, int sum) {
        if (curr == null) {
            return false;
        }
        int acum = sum + (Integer) curr.head;

        if (acum == n && curr.left == null && curr.right == null) {
            return true;
        } else if (acum > n) {
            return false;
        }
        return branchNRec(curr.left, n, acum) || branchNRec(curr.right, n, acum);
    }

    public List<T> getInOrder(int inf, int sup) {
        if (inf >= 1 && sup >= inf) {
            List<T> list = new LinkedList<T>();
            getInOrderRec(root, 1, inf, sup, list);

            return list;
        }
        return null;
    }

    private int getInOrderRec(Node<T> node, int index, int inf, int sup, List<T> l) {
        if (node == null) {
            return index;
        }
        index = getInOrderRec(node.left, index, inf, sup, l);

        if (index == -1) {
            return -1;
        } else if (index > sup) {
            return -1;
        } else if (index >= inf) {
            l.add(node.head);
        }
        return getInOrderRec(node.right, index + 1, inf, sup, l);
    }

    public boolean isPBalanced(double p) {
        if (p < 1) {
            return false;
        }
        return isPBalancedRec(root, p) != -1;
    }

    private int isPBalancedRec(Node<T> curr, double p) {
        if (curr == null) {
            return 0;
        }
        int left = isPBalancedRec(curr.left, p);

        if (left == -1) {
            return -1;
        }
        int right = isPBalancedRec(curr.right, p);

        if (right == -1) {
            return -1;
        }

        double res = p * (left + right);

        if (res > left || res > right) {
            return -1;
        }
        return left + right + 1;
    }

    public int longestPath() {
        int[] res = longestPathRec(root);

        return Math.max(res[0], res[1]);
    }

    private int[] longestPathRec(Node<T> curr) {
        if (curr == null) {
            return new int[]{0, 0};
        }
        int[] left = longestPathRec(curr.left);
        int[] right = longestPathRec(curr.right);
        int[] res = new int[2];

        res[0] = Math.max(left[0], right[0]) + 1;
        if (curr.left == null || curr.right == null) {
            res[0] = Math.max(left[0], right[0]) + 1;
        } else {
            res[1] = Math.max(left[1], Math.max(right[1], left[0] + right[0] + 1));
        }
        return res;
    }

    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(Node<T> curr) {
        if (curr == null) {
            return 0;
        }
        return 1 + sizeRec(curr.left) + sizeRec(curr.right);
    }

    private int levelOf(T elem) {
        return levelOfRec(root, elem);
    }

    private int levelOfRec(Node<T> curr, T elem) {
        if (curr == null) {
            return -1;
        }
        int res = cmp.compare(curr.head, elem);

        if (res == 0) {
            return 0;
        }
        Node<T> next = res > 0 ? curr.left : curr.right;
        int ret = levelOfRec(next, elem);

        return ret == -1 ? -1 : ret + 1;
    }

    public int leaves() {
        return leavesRec(root);
    }

    private int leavesRec(Node<T> curr) {
        if (curr == null) {
            return 0;
        }
        if (curr.left == null && curr.right == null) {
            return 1;
        }
        return leavesRec(curr.left) + leavesRec(curr.right);
    }

    public T max() {
        if (root == null) {
            return null;
        }
        Node<T> curr = root;

        while (curr != null) {
            curr = curr.right;
        }
        return curr.head;
    }

    private static class Node<T> {
        private T head;
        private Node<T> left, right;

        private Node(T head) {
            this.head = head;
        }
    }
}
