package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static calculator.ExpressionRegex.NUM_OR_VAR_WITH_BRACKETS;
import static calculator.ExpressionRegex.OPERATIONS;

public class PostfixNotationConverter {
    public Deque<String> convert(List<String> infix) {
        Deque<String> result = new ArrayDeque<>();
        Deque<String> stack = new ArrayDeque<>();
        for (String term : infix) {
            if (isOperand(term)) {
                result.offer(term);
            } else if (isOperation(term)) {
                if (stack.isEmpty() || isLeftBracket(stack.peek())) {
                    stack.push(term);
                } else if (hasHigherPrecedence(term, stack.peek())) {
                    stack.push(term);
                } else if (hasLowerOrEqualPrecedence(term, stack.peek())) {
                    do {
                        result.offer(stack.pop());
                    } while (!stack.isEmpty()
                            && !isLeftBracket(stack.peek())
                            && hasLowerOrEqualPrecedence(term, stack.peek()));
                    stack.push(term);
                }
            } else if (isLeftBracket(term)) {
                stack.push(term);
            } else if (isRightBracket(term)) {
                String top;
                while (!stack.isEmpty() && !isLeftBracket((top = stack.pop()))) {
                    if (!isRightBracket(top)) {
                        result.offer(top);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            result.offer(stack.pop());
        }
        return result;
    }

    private boolean isOperand(String term) {
        return term.matches(NUM_OR_VAR_WITH_BRACKETS);
    }

    private boolean isOperation(String term) {
        return term.matches(OPERATIONS);
    }

    private boolean isRightBracket(String term) {
        return ")".equals(term);
    }

    private boolean isLeftBracket(String term) {
        return "(".equals(term);
    }

    private boolean hasHigherPrecedence(String left, String right) {
        return getPrecedence(left) > getPrecedence(right);
    }

    private boolean hasLowerOrEqualPrecedence(String left, String right) {
        return getPrecedence(left) <= getPrecedence(right);
    }

    private int getPrecedence(String operation) {
        if (operation.matches("\\++") || operation.matches("-+")) {
            return 0;
        } else if ("*".equals(operation) || "/".equals(operation)) {
            return 1;
        } else if ("^".equals(operation)) {
            return 2;
        } else {
            throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }
}
