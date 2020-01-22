package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.BiFunction;

import static calculator.ExpressionRegex.UNSIGNED_NUMBER;
import static calculator.ExpressionRegex.UNSIGNED_VARIABLE;

public class PostfixNotationReducer {
    public int reduce(Deque<String> postfix) {
        return reduce(postfix, null);
    }

    public int reduce(Deque<String> postfix, Map<String, Integer> variables) {
        Deque<Integer> stack = new ArrayDeque<>();
        while (!postfix.isEmpty()) {
            String term = postfix.pop();
            if (term.matches(UNSIGNED_NUMBER)) {
                stack.push(Integer.valueOf(term));
            } else if (term.matches(UNSIGNED_VARIABLE)) {
                if (!variables.containsKey(term)) {
                    throw new IllegalArgumentException("Unknown variable: " + term);
                }
                stack.push(variables.get(term));
            } else {
                BiFunction<Integer, Integer, Integer> operation = getOperation(term);
                Integer b = stack.pop();
                Integer a = stack.pop();
                int result = operation.apply(a, b);
                stack.push(result);
            }
        }
        return stack.remove();
    }

    private BiFunction<Integer, Integer, Integer> getOperation(String operation) {
        if (operation.matches("\\++")) {
            return (a, b) -> a + b;
        } else if (operation.matches("-+")) {
            if (operation.length() % 2 == 0) {
                return (a, b) -> a + b;
            } else {
                return (a, b) -> a - b;
            }
        } else if ("*".equals(operation)) {
            return (a, b) -> a * b;
        } else if ("/".equals(operation)) {
            return (a, b) -> a / b;
        } else if ("^".equals(operation)) {
            return (a, b) -> (int) Math.pow(a, b);
        } else {
            throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }
}
