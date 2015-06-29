package bag;

public class Main {
    public static void main(String[] args) {
        Bag<String> bag = new BagImpl<>();
        bag.print();

        bag.add("A");
        bag.add("B");
        bag.add("C");
        bag.add("B");
        bag.print();

        bag.add("A");
        bag.add("C");
        bag.add("C");
        bag.print();

        bag.remove("C");
        bag.add("D");
        bag.print();

        bag.remove("C");
        bag.print();

        bag.remove("C");
        bag.print();
    }

}
