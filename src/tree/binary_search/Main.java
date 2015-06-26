package tree.binary_search;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer integer2) {
                return integer.compareTo(integer2);
            }
        });

        tree.add(5);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(6);
        tree.add(10);

        System.out.println("branchN: " + tree.branchN(21));
    }
}
