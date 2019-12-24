import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> buffer = new ArrayDeque<>();
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            String command = scanner.nextLine();
            if ("max".equals(command)) {
                int max = stack.pop();
                buffer.push(max);
                Integer current;
                while ((current = stack.poll()) != null) {
                    if (current > max) {
                        max = current;
                    }
                    buffer.push(current);
                }
                if (!builder.toString().isBlank()) {
                    builder.append("\n");
                }
                builder.append(max);
                while ((current = buffer.poll()) != null) {
                    stack.push(current);
                }
            } else if ("pop".equals(command)) {
                stack.poll();
            } else if (command.startsWith("push")) {
                String number = command.substring("push ".length());
                stack.push(Integer.parseInt(number));
            }
        }
        System.out.println(builder);
    }
}