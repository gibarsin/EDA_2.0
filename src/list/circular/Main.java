package list.circular;


public class Main {
    public static void main(String[] args) {
        CircularList<String> list1 = new CircularListImpl<>();

        list1.addLast("A"); list1.addLast("B"); list1.addLast("C");

        list1.reset();

        print(list1);

        list1.addLast("D"); list1.addLast("E"); list1.addLast("F");

        print(list1);

        list1.reset(); list1.getNext(); list1.getNext(); list1.getNext();

        CircularList<String> list2 = list1.split();

        print(list1);

        list2.reset();

        print(list2);

        list2.reset(); list2.getNext(); list2.getNext(); list2.getNext();

        CircularList<String> list3 = list2.split();

        print(list2);

        list3.reset();

        print(list3);
    }

    private static void print(CircularList<String> list) {
        for(int i = 0; i < 10; i++) {
            System.out.print(list.getNext() + " ");
        }
        System.out.println();
    }
}
