package list.priority;

public class Main {
    public static void main(String[] args) {
        PriorityList<Integer, String> list = new PriorityList<>();

        list.add(1, "Hi");
        list.print();
        list.add(2, "Bye");
        list.print();
        list.add(3, "Sayonara");
        list.print();
        list.add(2, "Hi");
        list.print();

        System.out.println(list.get(1));
        list.print();
        System.out.println(list.get(2));
        list.print();
    }
}
