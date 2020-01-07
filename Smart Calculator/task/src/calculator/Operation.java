package calculator;

public enum Operation {
    SUBTRACTION(0) {
        @Override
        public String getTerm() {
            return "-";
        }

        @Override
        public String getTermRegex() {
            return "\\-";
        }

        @Override
        public int perform(int a, int b) {
            return a - b;
        }
    },
    ADDITION(0) {
        @Override
        public String getTerm() {
            return "+";
        }

        @Override
        public String getTermRegex() {
            return "\\+";
        }

        @Override
        public int perform(int a, int b) {
            return a + b;
        }
    },
    DIVISION(1) {
        @Override
        public String getTerm() {
            return "/";
        }

        @Override
        public String getTermRegex() {
            return "/";
        }

        @Override
        public int perform(int a, int b) {
            return a / b;
        }
    },
    MULTIPLICATION(1) {
        @Override
        public String getTerm() {
            return "*";
        }

        @Override
        public String getTermRegex() {
            return "\\*";
        }

        @Override
        public int perform(int a, int b) {
            return a * b;
        }
    },
    POWER(2) {
        @Override
        public String getTerm() {
            return "^";
        }

        @Override
        public String getTermRegex() {
            return "^";
        }

        @Override
        public int perform(int a, int b) {
            return (int) Math.pow(a, b);
        }
    };

    private final int precedence;

    Operation(int precedence) {
        this.precedence = precedence;
    }

    public static Operation valueOfTerm(String term) {
        for (Operation operation : values()) {
            if (operation.getTerm().equals(term)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Unknown term: " + term);
    }

    public int getPrecedence() {
        return precedence;
    }

    public abstract String getTerm();

    public abstract String getTermRegex();

    public abstract int perform(int a, int b);
}
