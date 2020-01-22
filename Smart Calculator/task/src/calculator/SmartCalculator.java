package calculator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;

import static calculator.ExpressionRegex.*;

public class SmartCalculator implements Runnable {
    private final Scanner scanner;
    private final PrintStream printer;

    private final ExpressionSplitter splitter;
    private final PostfixNotationConverter converter;
    private final PostfixNotationReducer reducer;

    private final Map<String, BigInteger> variables;

    public SmartCalculator() {
        this(System.in, System.out);
    }

    public SmartCalculator(InputStream input, OutputStream output) {
        this.scanner = new Scanner(input);
        this.printer = new PrintStream(output);
        this.splitter = new ExpressionSplitter();
        this.converter = new PostfixNotationConverter();
        this.reducer = new PostfixNotationReducer();
        this.variables = new HashMap<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.isBlank()) {
                    continue;
                }
                if (isCommand(input)) {
                    if ("/exit".equals(input)) {
                        System.out.println("Bye!");
                        break;
                    }
                    executeCommand(input);
                } else if (isAssignment(input)) {
                    String name = getVariableName(input);
                    requireValidName(name);
                    String expression = getExpression(input);
                    requireValidAssignment(expression);
                    BigInteger result = getResult(expression);
                    variables.put(name, result);
                } else {
                    requireValidExpression(input);
                    BigInteger result = getResult(input);
                    printer.println(result);
                }
            } catch (SmartCalculatorException e) {
                printer.println(e.getMessage());
            }
        }
    }

    private boolean isCommand(String line) {
        return line.startsWith("/");
    }

    private void executeCommand(String input) {
        if ("/help".equals(input)) {
            String help = "The program calculates the sum/subtraction/multiplication/division/power of numbers";
            printer.println(help);
        } else {
            printer.println("Unknown command");
        }
    }


    private boolean isAssignment(String input) {
        return input.contains("=");
    }

    private String getVariableName(String input) {
        int index = input.indexOf("=");
        return input.substring(0, index).trim();
    }

    private String getExpression(String input) {
        int index = input.indexOf("=");
        return input.substring(index + 1).trim();
    }

    private void requireValidName(String name) throws InvalidIdentifierException {
        if (!name.matches(UNSIGNED_VARIABLE)) {
            throw new InvalidIdentifierException();
        }
    }

    private void requireValidAssignment(String assignment) throws InvalidAssignmentException {
        try {
            requireValidExpression(assignment);
        } catch (InvalidExpressionException e) {
            throw new InvalidAssignmentException();
        }
    }


    private void requireValidExpression(String expression) throws InvalidExpressionException {
        String regex = NUM_OR_VAR_WITH_BRACKETS + "(\\s*" + OPERATIONS + "\\s*" + NUM_OR_VAR_WITH_BRACKETS + ")*";
        if (!expression.matches(regex)) {
            throw new InvalidExpressionException();
        }
        int left = 0, right = 0;
        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                left++;
            } else if (ch == ')') {
                right++;
            }
        }
        if (left != right) {
            throw new InvalidExpressionException();
        }
    }

    private BigInteger getResult(String expression) {
        List<String> infix = splitter.split(expression);
        Deque<String> postfix = converter.convert(infix);
        return reducer.reduce(postfix, variables);
    }


    private static class SmartCalculatorException extends Exception {
        public SmartCalculatorException(String message) {
            super(message);
        }
    }

    private static class InvalidIdentifierException extends SmartCalculatorException {
        public InvalidIdentifierException() {
            super("Invalid identifier");
        }
    }

    private static class InvalidExpressionException extends SmartCalculatorException {
        public InvalidExpressionException() {
            super("Invalid expression");
        }

        public InvalidExpressionException(String message) {
            super(message);
        }
    }

    private static class InvalidAssignmentException extends InvalidExpressionException {
        public InvalidAssignmentException() {
            super("Invalid assignment");
        }
    }
}
