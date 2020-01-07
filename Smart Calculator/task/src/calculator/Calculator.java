package calculator;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

import static calculator.ExpressionMatcher.*;

class Calculator implements Runnable {
    private final Scanner scanner;
    private final PrintStream printer;
    private final PostfixNotationConverter converter;
    private final PostfixNotationReducer reducer;

    public Calculator(InputStream in, OutputStream out) {
        scanner = new Scanner(in);
        printer = new PrintStream(out);
        converter = new PostfixNotationConverter();
        reducer = new PostfixNotationReducer();
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
                        String[] members = line.split("\\s+");
                        List<String> postfix = new ArrayList<>();
                        for (String member : members) {
                            if (isOperand(member)) {
                                postfix.add(member);
                            } else if (isOperation(member)) {
                                postfix.add(reduceOperation(member));
                            } else if (member.startsWith("(")) {
                                int index = member.lastIndexOf('(');
                                for (int i = 0; i < index + 1; i++) {
                                    postfix.add("(");
                                }
                                postfix.add(member.substring(index + 1));
                            } else if (member.endsWith(")")) {
                                int index = member.indexOf(')');
                                postfix.add(member.substring(0, index));
                                for (int i = 0; i < member.length() - index; i++) {
                                    postfix.add(")");
                                }
                            } else {
                                throw new IllegalArgumentException("Unknown expression member: " + member);
                            }
                        }
                        Deque<String> infix = converter.convert(postfix);
                        int result = reducer.reduce(infix);
                        System.out.println(result);
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
