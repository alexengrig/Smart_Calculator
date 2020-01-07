package calculator;

public class InvalidAssignmentException extends RuntimeException {
    @Override
    public String toString() {
        return "Invalid assignment";
    }
}
