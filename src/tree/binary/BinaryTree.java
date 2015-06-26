package tree.binary;

import java.util.*;

public class BinaryTree<T> {
    private T head;
    private BinaryTree<T> left;
    private BinaryTree<T> right;
    private static final Random random = new Random();

    public BinaryTree(T head, BinaryTree<T> left, BinaryTree<T> right) {
        this.head = head;
        this.left = left;
        this.right = right;
    }

    public static <T> BinaryTree<T> add(BinaryTree<T> tree, T value) {
        if(tree == null) {
            return new BinaryTree<T>(value, null, null);
        }
        BinaryTree<T> nextTree = random.nextBoolean() ? tree.left : tree.right;

        nextTree = add(nextTree, value);

        return tree;
    }

    public static <T> boolean checkPostOrder(BinaryTree<T> tree, List<T> values) {
        if(tree == null || values == null) {
            throw new IllegalArgumentException();
        }

        Iterator it = values.iterator();

        return checkPostOrderRec(tree, it) && !it.hasNext();
    }

    private static <T> boolean checkPostOrderRec(BinaryTree<T> tree, Iterator it) {
        if(tree == null) {
            return true;
        }
        if(!checkPostOrderRec(tree.left, it) || !checkPostOrderRec(tree.right, it)) {
            return false;
        }
        if(!it.hasNext()) {
            return false;
        }

        return tree.head.equals(it.next());
    }

    public static <T> int height(BinaryTree<T> tree) {
        if(tree == null) {
            return -1;
        } else {
            return 1 + Math.max(height(tree.left), height(tree.right));
        }
    }

    public static <T> int nodeCount(BinaryTree<T> tree) {
        if(tree == null) {
            return 0;
        } else {
            return 1 + nodeCount(tree.left) + nodeCount(tree.right);
        }
    }

    public static <T> int ocurrencesOf(BinaryTree<T> tree, T value) {
        if(tree == null) {
            return 0;
        } else {
            int res = tree.head.equals(value) ? 1 : 0;

            return res + ocurrencesOf(tree.left, value) + ocurrencesOf(tree.right, value);
        }
    }

    public static <T> T max(BinaryTree<T> tree, Comparator<T> cmp) {
        if(tree == null) {
            return null;
        } else {
            T max = tree.head;
            T leftMax = max(tree.left, cmp);

            if(leftMax != null && cmp.compare(leftMax, max) > 0) {
                max = leftMax;
            }

            T rightMax = max(tree.right, cmp);
            if(rightMax != null && cmp.compare(rightMax, max) > 0) {
                max = rightMax;
            }

            return max;
        }
    }

    public static <T> int width(BinaryTree<T> tree, int level) {
        if(tree == null) {
            return 0;
        } else if(level == 0) {
            return 1;
        }

        return width(tree.left, level - 1) + width(tree.right, level -1);
    }

    public static <T> BinaryTree<T> spanning(BinaryTree<T> tree, T value) {
        if(tree == null) {
            return null;
        }
        BinaryTree<T> leftSpanning = spanning(tree.left, value);
        BinaryTree<T> rightSpanning = spanning(tree.right, value);

        if(leftSpanning == null && rightSpanning == null && !tree.head.equals(value)) {
            return null;
        }
        BinaryTree<T> spanning = new BinaryTree<>(value, null, null);

        if(leftSpanning != null) {
            spanning.left = leftSpanning;
        }
        if(rightSpanning != null) {
            spanning.right = rightSpanning;
        }
        return spanning;
    }

    public static <T> boolean isHeap(BinaryTree<T> tree, Comparator<T> cmp) {
        Queue<BinaryTree<T>> q = new LinkedList<>();
        int currLevelSize = 1;
        boolean isComplete = true;

        q.offer(tree);
        while(isComplete) {
            for(int j = 0; j < currLevelSize && isComplete; j++) {
                BinaryTree<T> currTree = q.poll();

                if(currTree == null) {
                    isComplete = false;
                } else {
                    BinaryTree<T> nextTree = currTree.left;

                    if(nextTree == null || cmp.compare(currTree.head, nextTree.head) < 0) {
                        q.offer(nextTree);
                    } else {
                        return false;
                    }

                    nextTree = currTree.right;

                    if(nextTree == null || cmp.compare(currTree.head, nextTree.head) < 0) {
                        q.offer(nextTree);
                    } else {
                        return false;
                    }
                }
            }
            currLevelSize *= 2;
        }
        while(!q.isEmpty()) {
            if(q.poll() != null) {
                return false;
            }
        }
        return true;
    }
}
