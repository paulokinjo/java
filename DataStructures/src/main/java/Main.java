import java.util.Arrays;
import linkedlists.LinkedList;

public class Main {
    public static void main(String[] args) {
        var list = new LinkedList();
        list.addFirst(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);

        System.out.println(list.getKthFromTheEnd(6));

        list.reverse();
        System.out.println(Arrays.toString(list.toArray()));

        System.out.println(list.getSize());
        System.out.println(list.indexOf(10));
        System.out.println(list.contains(12));
        System.out.println(list.contains(10));
        System.out.println(Arrays.toString(list.toArray()));

        list.removeFirst();

        System.out.println(list.getSize());
        System.out.println(list.indexOf(10));
        System.out.println(list.contains(12));
        System.out.println(list.contains(10));
        System.out.println(Arrays.toString(list.toArray()));

        list.removeLast();

        System.out.println(list.getSize());
        System.out.println(list.indexOf(10));
        System.out.println(list.contains(12));
        System.out.println(list.contains(10));
        System.out.println(Arrays.toString(list.toArray()));

    }
}
