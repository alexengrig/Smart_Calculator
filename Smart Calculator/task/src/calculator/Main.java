package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (isCommand(line)) {
                if ("/exit".equals(line)) {
                    break;
                } else if ("/help".equals(line)) {
                    System.out.println("The program calculates the sum of numbers");
                } else {
                    System.out.println("Unknown command");
                }
            } else if (!line.isBlank()) {
                if (!isValidExpression(line)) {
                    System.out.println("Invalid expression");
                } else {
                    String[] numbers = line.trim().split("\\s+");
                    if (numbers.length > 0 && !numbers[0].isBlank()) {
                        String first = numbers[0];
                        int result = Integer.parseInt(first);
                        for (int i = 1; i < numbers.length; i++) {
                            String operation = numbers[i];
                            String reduceOperation = reduceOperation(operation);
                            String number = numbers[++i];
                            int value = Integer.parseInt(number);
                            if ("+".equals(reduceOperation)) {
                                result += value;
                            } else if ("-".equals(reduceOperation)) {
                                result -= value;
                            }
                        }
                        System.out.println(result);
                    }
                }
            }
        }
        System.out.println("Bye!");
    }

    private static boolean isCommand(String line) {
        return line.startsWith("/");
    }

    private static boolean isValidExpression(String line) {
        return line.matches("[-+]?\\d+(\\s+(-+|\\++)\\s+[-+]?\\d+)*");
    }

    private static String reduceOperation(String operation) {
        if (operation.length() < 2) {
            return operation;
        } else if ('+' == operation.charAt(0)) {
            return "+";
        } else if ('-' == operation.charAt(0) && operation.length() % 2 == 0) {
            return "+";
        } else if ('-' == operation.charAt(0) && operation.length() % 2 != 0) {
            return "-";
        } else {
            return operation;
        }
    }
}
