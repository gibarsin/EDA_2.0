package tree.binary;

import java.util.*;

public class BinaryTree<T> {
    private static final Random random = new Random();
    private T head;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree(T head, BinaryTree<T> left, BinaryTree<T> right) {
        this.head = head;
        this.left = left;
        this.right = right;
    }

    public static <T> BinaryTree<T> add(BinaryTree<T> tree, T value) {
        if (tree == null) {
            return new BinaryTree<T>(value, null, null);
        }
        BinaryTree<T> nextTree = random.nextBoolean() ? tree.left : tree.right;

        nextTree = add(nextTree, value);

        return tree;
    }

    public static <T> boolean checkPostOrder(BinaryTree<T> tree, List<T> values) {
        if (values == null) {
            throw new IllegalArgumentException();
        }

        Iterator it = values.iterator();

        return checkPostOrderRec(tree, it) && !it.hasNext();
    }

    private static <T> boolean checkPostOrderRec(BinaryTree<T> tree, Iterator it) {
        if (tree == null) {
            return true;
        }
        if (!checkPostOrderRec(tree.left, it) || !checkPostOrderRec(tree.right, it)) {
            return false;
        }
        if (!it.hasNext()) {
            return false;
        }

        return tree.head.equals(it.next());
    }

    public static <T> int height(BinaryTree<T> tree) {
        if (tree == null) {
            return -1;
        } else {
            return 1 + Math.max(height(tree.left), height(tree.right));
        }
    }

    public static <T> int nodeCount(BinaryTree<T> tree) {
        if (tree == null) {
            return 0;
        } else {
            return 1 + nodeCount(tree.left) + nodeCount(tree.right);
        }
    }

    public static <T> int ocurrencesOf(BinaryTree<T> tree, T value) {
        if (tree == null) {
            return 0;
        } else {
            int res = tree.head.equals(value) ? 1 : 0;

            return res + ocurrencesOf(tree.left, value) + ocurrencesOf(tree.right, value);
        }
    }

    public static <T> T max(BinaryTree<T> tree, Comparator<T> cmp) {
        if (tree == null) {
            return null;
        } else {
            T max = tree.head;
            T leftMax = max(tree.left, cmp);

            if (leftMax != null && cmp.compare(leftMax, max) > 0) {
                max = leftMax;
            }

            T rightMax = max(tree.right, cmp);
            if (rightMax != null && cmp.compare(rightMax, max) > 0) {
                max = rightMax;
            }

            return max;
        }
    }

    public static <T> int width(BinaryTree<T> tree, int level) {
        if (tree == null) {
            return 0;
        } else if (level == 0) {
            return 1;
        }

        return width(tree.left, level - 1) + width(tree.right, level - 1);
    }

    public static <T> BinaryTree<T> spanning(BinaryTree<T> tree, T value) {
        if (tree == null) {
            return null;
        }
        BinaryTree<T> leftSpanning = spanning(tree.left, value);
        BinaryTree<T> rightSpanning = spanning(tree.right, value);

        if (leftSpanning == null && rightSpanning == null && !tree.head.equals(value)) {
            return null;
        }
        BinaryTree<T> spanning = new BinaryTree<>(value, null, null);

        if (leftSpanning != null) {
            spanning.left = leftSpanning;
        }
        if (rightSpanning != null) {
            spanning.right = rightSpanning;
        }
        return spanning;
    }

    /* Linked List doesn't accept null elements, so I should create new BinaryTree's with null head to detect them */
    public static <T> boolean isHeap(BinaryTree<T> tree, Comparator<T> cmp) {
        Queue<BinaryTree<T>> q = new LinkedList<>();
        int currLevelSize = 1;
        boolean isComplete = true;

        q.offer(tree);
        while (isComplete) {
            for (int j = 0; j < currLevelSize && isComplete; j++) {
                BinaryTree<T> currTree = q.poll();

                if (currTree == null) {
                    isComplete = false;
                } else {
                    BinaryTree<T> nextTree = currTree.left;

                    if (nextTree == null || cmp.compare(currTree.head, nextTree.head) < 0) {
                        q.offer(nextTree);
                    } else {
                        return false;
                    }

                    nextTree = currTree.right;

                    if (nextTree == null || cmp.compare(currTree.head, nextTree.head) < 0) {
                        q.offer(nextTree);
                    } else {
                        return false;
                    }
                }
            }
            currLevelSize *= 2;
        }
        while (!q.isEmpty()) {
            if (q.poll() != null) {
                return false;
            }
        }
        return true;
    }

    public static <T> BinaryTree<T> buildFromList(List<T> values) {
        if (values == null) {
            throw new IllegalArgumentException();
        }
        Iterator<T> it = values.iterator();

        if (!it.hasNext()) {
            return null;
        }
        Queue<BinaryTree<T>> q = new LinkedList<>();
        BinaryTree<T> root = new BinaryTree<>(it.next(), null, null);

        q.offer(root);
        while (!q.isEmpty()) {
            BinaryTree<T> tree = q.poll();

            if (it.hasNext()) {
                BinaryTree<T> left = new BinaryTree<>(it.next(), null, null);

                tree.left = left;
                q.offer(left);
                if (it.hasNext()) {
                    BinaryTree<T> right = new BinaryTree<>(it.next(), null, null);

                    tree.right = right;
                    q.offer(right);
                }
            }
        }

        return root;
    }

    public static <T> void BFSPrint(BinaryTree<T> tree) {
        Queue<BinaryTree<T>> q = new LinkedList<>();
        int maxNodesInCurrLevel = 1;
        int currNodes = 0;

        q.offer(tree);

        while (!q.isEmpty()) {
            BinaryTree<T> curr = q.poll();

            if (curr != null) {
                System.out.print(curr.head + " ");

                q.offer(curr.left);
                q.offer(curr.right);

                currNodes++;
                if (currNodes == maxNodesInCurrLevel) {
                    System.out.println();
                    currNodes = 0;
                    maxNodesInCurrLevel *= 2;
                }
            }
        }
    }

    public static <S, T> BinaryTree<S> buildFromFunction(BinaryTree<T> tree, Function<S, T> f) {
        if (tree == null) {
            return null;
        }
        BinaryTree<S> left = buildFromFunction(tree.left, f);
        BinaryTree<S> right = buildFromFunction(tree.right, f);

        return new BinaryTree<S>(f.apply(tree.head), left, right);
    }

    public static <T> boolean areMirrored(BinaryTree<T> tree1, BinaryTree<T> tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        } else if (tree1 != null && tree2 != null) {
            return areMirrored(tree1.left, tree2.right) && areMirrored(tree1.right, tree2.left);
        }
        return false;
    }

    public static BinaryTree<Integer> buildFromList(int[] values) {
        return buildFromListRec(values, 0, values.length - 1);
    }

    private static BinaryTree<Integer> buildFromListRec(int[] values, int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            return null;
        } else if (lowerBound == upperBound) {
            return new BinaryTree<>(values[lowerBound], null, null);
        } else {
            int index = (lowerBound + upperBound) / 2;
            return new BinaryTree<>(
                    values[index],
                    buildFromListRec(values, lowerBound, index - 1),
                    buildFromListRec(values, index + 1, upperBound));
        }
    }

    public static <T> boolean isBST(BinaryTree<T> tree, Comparator<T> cmp) {
        if (cmp == null) {
            throw new IllegalArgumentException();
        }
        return isBSTRec(tree, cmp, null, null);
    }

    private static <T> boolean isBSTRec(BinaryTree<T> tree, Comparator<T> cmp, T min, T max) {
        if (tree == null) {
            return true;
        } else if (min != null && cmp.compare(min, tree.head) > 0) {
            return false;
        } else if (max != null && cmp.compare(max, tree.head) < 0) {
            return false;
        } else {
            return isBSTRec(tree.left, cmp, min, tree.head) && isBSTRec(tree.right, cmp, tree.head, max);
        }
    }

    public static BinaryTree<Integer> buildFibonacciTree(int level) {
        if (level <= 0) {
            return null;
        }
        return buildFibonacciTreeRec(level);
    }

    private static BinaryTree<Integer> buildFibonacciTreeRec(int level) {
        if (level == 0 || level == 1) {
            return new BinaryTree<>(level, null, null);
        }
        BinaryTree<Integer> left = buildFibonacciTreeRec(level - 1);
        BinaryTree<Integer> right = buildFibonacciTreeRec(level - 2);

        return new BinaryTree<>(left.head + right.head, left, right);
    }
}
