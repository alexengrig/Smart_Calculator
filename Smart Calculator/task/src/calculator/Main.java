package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            String line = scanner.nextLine();
            if ("/exit".equals(line)) {
                break;
            } else if ("/help".equals(line)) {
                System.out.println("The program calculates the sum of numbers");
                continue;
            }
            String[] numbers = line.trim().split("\\s+");
            if (numbers.length > 0 && !numbers[0].isBlank()) {
                int sum = 0;
                for (String number : numbers) {
                    sum += Integer.parseInt(number);
                }
                System.out.println(sum);
            }
        }
        System.out.println("Bye!");
    }
}
