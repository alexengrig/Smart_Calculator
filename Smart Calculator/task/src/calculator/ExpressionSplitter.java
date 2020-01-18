package calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static calculator.ExpressionMatcher.isOperand;
import static calculator.ExpressionMatcher.isOperation;
import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

public class ExpressionSplitter {
    public List<String> split(String line) {
        ArrayList<String> strings = new ArrayList<>();
        String[] members = line.split("\\s+");
        Deque<Character> queue = new ArrayDeque<>();
        for (String member : members) {
            for (char ch : member.toCharArray()) {
                if (needsPool(queue, ch)) {
                    String value = poolAll(queue);
                    strings.add(value);
                }
                queue.add(ch);
            }
            if (!queue.isEmpty()) {
                strings.add(poolAll(queue));
            }
        }
        return strings;
    }

    private boolean needsPool(Deque<Character> queue, char curr) {
        if (queue.isEmpty()) {
            return false;
        }
        Character prev = queue.getLast();
        if (isDigit(curr) && isDigit(prev)) {
            return false;
        } else if (isAlphabetic(curr) && isAlphabetic(prev)) {
            return false;
        } else if (isPlus(curr) && isPlus(prev)) {
            return false;
        } else if (isMinus(curr) && isMinus(prev)) {
            return false;
        } else if (isMulti(prev)) {
            return true;
        } else if (isDiv(prev)) {
            return true;
        } else if (isExp(prev)) {
            return true;
        } else if (isLeftBracket(prev)) {
            return true;
        } else if (isRightBracket(prev)) {
            return true;
        } else if (isEqual(prev)) {
            return true;
        }
        return true;
    }

    private boolean isPlus(char ch) {
        return ch == '+';
    }

    private boolean isMinus(char ch) {
        return ch == '-';
    }

    private boolean isMulti(char ch) {
        return ch == '*';
    }

    private boolean isDiv(char ch) {
        return ch == '/';
    }

    private boolean isExp(char ch) {
        return ch == '^';
    }

    private boolean isLeftBracket(char ch) {
        return ch == '(';
    }

    private boolean isRightBracket(char ch) {
        return ch == ')';
    }

    private boolean isEqual(char ch) {
        return ch == '=';
    }

    private String poolAll(Deque<Character> deque) {
        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) {
            builder.append(deque.poll());
        }
        return builder.toString();
    }


    public List<String> split1(String line) {
        String[] members = line.split("\\s+");
        List<String> result = new ArrayList<>();
        for (String member : members) {
            if (isOperand(member)) {
                result.add(member);
            } else if (isOperation(member)) {
                result.add(reduceOperation(member));
            } else if (member.startsWith("(")) {
                int index = member.lastIndexOf('(');
                for (int i = 0; i < index + 1; i++) {
                    result.add("(");
                }
                result.add(member.substring(index + 1));
            } else if (member.endsWith(")")) {
                int index = member.indexOf(')');
                result.add(member.substring(0, index));
                for (int i = 0; i < member.length() - index; i++) {
                    result.add(")");
                }
            } else {
                throw new IllegalArgumentException("Unknown expression member: " + member);
            }
        }
        return result;
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
