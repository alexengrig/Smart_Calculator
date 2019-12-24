import java.util.*;

public class Main {

    public static void main(String[] args) {
        Deque<Integer> queue = new ArrayDeque<>(Arrays.asList(1,2,3,4));
        queue.poll();
        queue.poll();
        queue.offerLast(5);
        System.out.println(queue);
    }
}