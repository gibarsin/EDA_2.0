package tree.b;

import java.util.LinkedList;
import java.util.List;

public class BTree<T extends Comparable<? super T>> {
    private final int order;
    private Node root;

    public BTree(int order) {
        this.order = order;
    }

    public List<T> range(T inf, T sup) {
        if (inf == null || sup == null || inf.compareTo(sup) > 0) {
            throw new IllegalArgumentException();
        }
        List<T> list = new LinkedList<>();
        root.range(inf, sup, list);

        return list;
    }

    private class Node {
        private T[] values;
        private Node[] pointers;
        private int valuesSize;

        private Node() {
            values = (T[]) new Object[order - 1];
            pointers = (Node[]) new Object[order];
            valuesSize = 0;
        }

        public void range(T inf, T sup, List<T> list) {
            int cmpInf, cmpSup;

            for (int i = 0; i < valuesSize; i++) {
                cmpInf = inf.compareTo(values[i]);
                if (cmpInf <= 0) {
                    pointers[i].range(inf, sup, list);
                    cmpSup = sup.compareTo(values[i]);
                    if (cmpSup >= 0) {
                        list.add(values[i]);
                    } else {
                        return;
                    }
                }
            }
            pointers[valuesSize + 1].range(inf, sup, list);
        }
    }
}
