package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static calculator.ExpressionMatcher.isNumber;
import static calculator.ExpressionMatcher.isOperand;

public class PostfixNotationReducer {
    public int reduce(Deque<String> postfix) {
        return reduce(postfix, null);
    }
    public int reduce(Deque<String> postfix, Map<String, Integer> variables) {
        Deque<Integer> stack = new ArrayDeque<>();
        while (!postfix.isEmpty()) {
            String term = postfix.pop();
            if (isOperand(term)) {
                if (isNumber(term)) {
                    stack.push(Integer.valueOf(term));
                } else {
                    stack.push(variables.get(term));
                }
            } else {
                Integer a = stack.pop();
                Integer b = stack.pop();
                int result = Operation.valueOfTerm(term).perform(a, b);
                stack.push(result);
            }
        }
        return stack.remove();
    }
}
