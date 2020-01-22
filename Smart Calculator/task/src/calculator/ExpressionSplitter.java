package calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

public class ExpressionSplitter {
    public List<String> split(String line) {
        List<String> strings = new ArrayList<>();
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
}
