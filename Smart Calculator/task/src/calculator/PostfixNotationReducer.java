package calculator;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.BiFunction;

import static calculator.ExpressionRegex.UNSIGNED_NUMBER;
import static calculator.ExpressionRegex.UNSIGNED_VARIABLE;

public class PostfixNotationReducer {
    public BigInteger reduce(Deque<String> postfix) {
        return reduce(postfix, null);
    }

    public BigInteger reduce(Deque<String> postfix, Map<String, BigInteger> variables) {
        Deque<BigInteger> stack = new ArrayDeque<>();
        while (!postfix.isEmpty()) {
            String term = postfix.pop();
            if (term.matches(UNSIGNED_NUMBER)) {
                stack.push(new BigInteger(term));
            } else if (term.matches(UNSIGNED_VARIABLE)) {
                if (!variables.containsKey(term)) {
                    throw new IllegalArgumentException("Unknown variable: " + term);
                }
                stack.push(variables.get(term));
            } else {
                BiFunction<BigInteger, BigInteger, BigInteger> operation = getOperation(term);
                BigInteger b = stack.pop();
                BigInteger a = stack.pop();
                BigInteger result = operation.apply(a, b);
                stack.push(result);
            }
        }
        return stack.remove();
    }

    private BiFunction<BigInteger, BigInteger, BigInteger> getOperation(String operation) {
        if (operation.matches("\\++")) {
            return BigInteger::add;
        } else if (operation.matches("-+")) {
            if (operation.length() % 2 == 0) {
                return BigInteger::add;
            } else {
                return BigInteger::subtract;
            }
        } else if ("*".equals(operation)) {
            return BigInteger::multiply;
        } else if ("/".equals(operation)) {
            return BigInteger::divide;
        } else if ("^".equals(operation)) {
            return (a, b) -> a.modPow(b, BigInteger.valueOf(Integer.MAX_VALUE));
        } else {
            throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }
}
