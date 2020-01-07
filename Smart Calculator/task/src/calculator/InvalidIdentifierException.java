package calculator;

public class InvalidIdentifierException extends RuntimeException {
    @Override
    public String toString() {
        return "Invalid identifier";
    }
}
