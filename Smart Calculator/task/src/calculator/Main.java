package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            String line = scanner.nextLine();
            if ("/exit".equals(line)) {
                break;
            }
            String[] numbers = line.trim().split("\\s+");
            if (numbers.length == 1 && !numbers[0].isBlank()) {
                int a = Integer.parseInt(numbers[0]);
                System.out.println(a);
            } else if (numbers.length == 2) {
                int a = Integer.parseInt(numbers[0]);
                int b = Integer.parseInt(numbers[1]);
                System.out.println(a + b);
            }
        }
        System.out.println("Bye!");
    }
}
