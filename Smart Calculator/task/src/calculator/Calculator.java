package calculator;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

import static calculator.ExpressionMatcher.*;

class Calculator implements Runnable {
    private final Scanner scanner;
    private final PrintStream printer;
    private final ExpressionSplitter splitter;
    private final PostfixNotationConverter converter;
    private final PostfixNotationReducer reducer;

    private final Map<String, Integer> variables;

    public Calculator(InputStream in, OutputStream out) {
        scanner = new Scanner(in);
        printer = new PrintStream(out);
        splitter = new ExpressionSplitter();
        converter = new PostfixNotationConverter();
        reducer = new PostfixNotationReducer();
        variables = new HashMap<>();
    }

    @Override
    public void run() {
        while (true) {
            String line = scanner.nextLine();
            if (isCommand(line)) {
                if ("/exit".equals(line)) {
                    break;
                } else if ("/help".equals(line)) {
                    String help = "The program calculates the sum/subtraction/multiplication/division/power of numbers";
                    System.out.println(help);
                } else {
                    System.out.println("Unknown command");
                }
            } else if (!line.isBlank()) {
                if (isCalculationExpression(line)) {
                    try {
                        int left = 0, right = 0;
                        for (char ch : line.toCharArray()) {
                            if (ch == '(') {
                                left++;
                            } else if (ch == ')') {
                                right++;
                            }
                        }
                        if (left != right) {
                            throw new IllegalArgumentException();
                        }
                        int result = calculate(line);
                        System.out.println(result);
                    } catch (Exception e) {
                        System.out.println("Invalid expression");
                    }
                } else if (isAssignmentExpression(line)) {
                    try {
                        String[] split = line.split(Regexs.EQUAL);
                        String variable = split[0];
                        String expression = split[1];
                        int value = calculate(expression);
                        variables.put(variable, value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Invalid expression");
                }
            }
        }
        printer.println("Bye!");
    }

    private int calculate(String line) {
        List<String> members = splitter.split(line);
        List<String> postfix = new ArrayList<>();
        for (String member : members) {
            if (isOperand(member)) {
                postfix.add(member);
            } else if (isOperation(member)) {
                postfix.add(reduceOperation(member));
            } else {
                throw new IllegalArgumentException("Unknown expression member: " + member);
            }
        }
        Deque<String> infix = converter.convert(postfix);
        return reducer.reduce(infix);
    }


    private boolean isCommand(String line) {
        return line.startsWith("/");
    }

    private String reduceOperation(String operation) {
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
