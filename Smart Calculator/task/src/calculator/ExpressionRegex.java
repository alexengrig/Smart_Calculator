package calculator;

public class ExpressionRegex {
    public static final String UNSIGNED_NUMBER = "[0-9]+";
    public static final String UNSIGNED_VARIABLE = "[A-Za-z]+";
    public static final String NUM_OR_VAR_WITH_BRACKETS = "\\(*[-+]?(" + UNSIGNED_NUMBER + "|" + UNSIGNED_VARIABLE + ")\\)*";
    public static final String OPERATIONS = "(\\++|-+|\\*|/|\\^)";
}
