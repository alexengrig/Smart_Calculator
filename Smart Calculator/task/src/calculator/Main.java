package calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface Regexs {
    String VARIABLE = "[-+]?" + "[A-Za-z]+";
    String NUMBER = "[-+]?\\d+";
    String VARIABLE_OR_NUMBER = "\\(*(" + NUMBER + "|" + VARIABLE + ")\\)*";
    String OPERATIONS = "(-+|\\++|/|*|^)";
    String EQUAL = "\\s*=\\s*";
}

public class Main {
    private static final Map<String, Integer> VARIABLES = new HashMap<>();

    public static void main(String[] args) {
        Calculator calculator = new Calculator(System.in, System.out);
        calculator.run();
    }

    public static void main1(String[] args) {
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
                try {
                    if (isCalculationExpression(line)) {
                        try {
                            int result = getResult(line);
                            System.out.println(result);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (isAssignmentExpression(line)) {
                        try {
                            String[] split = line.split(Regexs.EQUAL);
                            String variable = split[0];
                            String expression = split[1];
                            int value = getResult(expression);
                            VARIABLES.put(variable, value);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Bye!");
    }

    private static boolean isCommand(String line) {
        return line.startsWith("/");
    }

    private static boolean isCalculationExpression(String line) {
        String calculationExpression = Regexs.VARIABLE_OR_NUMBER + "(\\s+" + Regexs.OPERATIONS + "\\s+"
                + Regexs.VARIABLE_OR_NUMBER + ")*";
        return line.matches(calculationExpression);
    }

    private static boolean isAssignmentExpression(String line) {
        boolean hasOneEqualSign = line.matches("[^=]+=[^=]+");
        if (!hasOneEqualSign) {
            throw new IllegalArgumentException("Invalid assignment");
        }
        boolean ok = line.matches("[A-Za-z]+" + "\\s*=.*");
        if (!ok) {
            throw new IllegalArgumentException("Invalid identifier");
        }
        boolean isValidExpression = line.matches(".*=\\s*" + Regexs.VARIABLE_OR_NUMBER
                + "(\\s+" + Regexs.OPERATIONS + "\\s+" + Regexs.VARIABLE_OR_NUMBER + ")*");
        if (!isValidExpression) {
            throw new IllegalArgumentException("Invalid assignment");
        }
        return true;
    }

    private static Integer getValue(String line) {
        if (line.matches(Regexs.NUMBER)) {
            return Integer.parseInt(line);
        } else if (line.matches(Regexs.VARIABLE)) {
            Integer value = VARIABLES.get(line);
            if (value != null) {
                return value;
            } else {
                throw new IllegalArgumentException("Unknown variable");
            }
        } else {
            throw new IllegalArgumentException("Unknown pattern: " + line);
        }
    }

    private static int getResult(String line) {
        String[] values = line.trim().split("\\s+");
        String first = values[0];
        int result = getValue(first);
        for (int i = 1; i < values.length; i++) {
            String operation = values[i];
            String reduceOperation = reduceOperation(operation);
            String next = values[++i];
            int value = getValue(next);
            if ("+".equals(reduceOperation)) {
                result += value;
            } else if ("-".equals(reduceOperation)) {
                result -= value;
            }
        }
        return result;
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