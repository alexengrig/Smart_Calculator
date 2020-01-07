package calculator;

interface Regexs1 {
    String VARIABLE = "[-+]?[A-Za-z]+";
    String NUMBER = "[-+]?\\d+";
    String VARIABLE_OR_NUMBER = "\\(*(" + NUMBER + "|" + VARIABLE + ")\\)*";
    String OPERATIONS = "(-+|\\++|/|*|^)";
    String EQUAL = "\\s*=\\s*";
}

public class ExpressionMatcher {
    private final static String OPERATION = "(-+|\\++|/|\\*|^)";
    private static final String OPERAND = "\\(*\\s*[-+]?(\\d+|[A-Za-z]+)\\s*\\)*";

    public static boolean isNumber(String term) {
        return term.matches("[-+]?\\d+");
    }

    public static boolean isVariable(String term) {
        return term.matches("[-+]?[A-Za-z]+");
    }

    public static boolean isOperand(String term) {
        return isNumber(term) || isVariable(term);
    }

    public static boolean isSubtraction(String term) {
        return term.matches("-+");
    }

    public static boolean isAddition(String term) {
        return term.matches("\\++");
    }

    public static boolean isDivision(String term) {
        return term.matches("/");
    }

    public static boolean isMultiplication(String term) {
        return term.matches("\\*");
    }

    public static boolean isPower(String term) {
        return term.matches("^");
    }

    public static boolean isOperation(String term) {
        return isSubtraction(term)
                || isAddition(term)
                || isDivision(term)
                || isMultiplication(term)
                || isPower(term);
    }

    public static boolean isCalculationExpression(String line) {
        return line.trim().matches(OPERAND + "(" + "\\s+" + OPERATION + "\\s+" + OPERAND + ")*");
    }

    public static boolean isAssignmentExpression(String line) {
        boolean hasOneEqualSign = line.matches("[^=]+=[^=]+");
        if (!hasOneEqualSign) {
            throw new InvalidAssignmentException();
        }
        boolean isValidIdentifier = line.matches("[A-Za-z]+" + "\\s*=.+");
        if (!isValidIdentifier) {
            throw new InvalidIdentifierException();
        }
        String expression = line.substring(line.indexOf('=') + 1);
        boolean isValidAssignment = isCalculationExpression(expression);
        if (!isValidAssignment) {
            throw new InvalidAssignmentException();
        }
        return true;
    }
}
