package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class PostfixNotationConverter {
    public Deque<String> convert(List<String> infix) {
        Deque<String> result = new ArrayDeque<>();
        Deque<String> stack = new ArrayDeque<>();
        for (String term : infix) {
            if (isOperand(term)) {
                result.offer(term);
            } else if (isOperation(term)) {
                if (stack.isEmpty() || "(".equals(stack.peek())) {
                    stack.push(term);
                } else if (hasHigherPrecedence(term, stack.peek())) {
                    stack.push(term);
                } else if (hasLowerOrEqualPrecedence(term, stack.peek())) {
                    do {
                        result.offer(stack.pop());
                    } while (!stack.isEmpty()
                            && !"(".equals(stack.peek())
                            && hasLowerOrEqualPrecedence(term, stack.peek()));
                    stack.push(term);
                }
            } else if ("(".equals(term)) {
                stack.push(term);
            } else if (")".equals(term)) {
                String pop;
                while (!stack.isEmpty() && !"(".equals((pop = stack.pop()))) {
                    if (!")".equals(pop)) {
                        result.offer(pop);
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
        return term.matches("[-+]?(\\d+|[A-Za-z]+)");
    }

    private boolean isOperation(String term) {
        return term.matches("[-+/*]");
    }

    private boolean hasHigherPrecedence(String term, String other) {
        return getPrecedence(term) > getPrecedence(other);
    }

    private boolean hasLowerOrEqualPrecedence(String term, String other) {
        return getPrecedence(term) <= getPrecedence(other);
    }

    private int getPrecedence(String term) {
        if ("^".equals(term)) {
            return 2;
        } else if ("*".equals(term)) {
            return 1;
        } else if ("/".equals(term)) {
            return 1;
        } else if ("+".equals(term)) {
            return 0;
        } else if ("-".equals(term)) {
            return 0;
        } else {
            throw new IllegalArgumentException("Unknown operation: " + term);
        }
    }
}
