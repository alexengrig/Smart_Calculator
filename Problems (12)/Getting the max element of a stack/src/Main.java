import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int commandCount = Integer.parseInt(scanner.nextLine());
        String[] commands = new String[commandCount];
        for (int i = 0; i < commandCount; i++) {
            commands[i] = scanner.nextLine();
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        for (String command : commands) {
            if ("max".equals(command)) {
                System.out.println(max);
            } else if ("pop".equals(command)) {
                Integer number = stack.poll();
                if (number != null && number > max) {
                    max = 2 * max - number;
                }
            } else if (command.startsWith("push ")) {
                String value = command.substring("push ".length());
                int number = Integer.parseInt(value);
                if (stack.isEmpty()) {
                    stack.push(number);
                    max = number;
                } else {
                    if (number > max) {
                        stack.push(2 * number - max);
                        max = number;
                    } else {
                        stack.push(number);
                    }
                }
            }
        }
    }
}