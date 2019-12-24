import calculator.Main;
import org.hyperskill.hstest.v6.stage.BaseStageTest;
import org.hyperskill.hstest.v6.testcase.PredefinedIOTestCase;

import java.util.List;

public class Test extends BaseStageTest {

    public Test() throws Exception {
        super(Main.class);
    }

    @Override
    public List<PredefinedIOTestCase> generate() {
        return List.of(
                new PredefinedIOTestCase(
                        "100 + 200 - 300\n/exit",
                        "0\nBye!"
                ),
                new PredefinedIOTestCase(
                        "4 + 6 - 8\n\n\n2 - 3 - 4\n\n8 + 7 - 4\n/exit",
                        "2\n-5\n11\nBye!"
                ),
                new PredefinedIOTestCase(
                        "abc\n123+\n+15\n18 22\n\n-22\n22-\n/exit",
                        "Invalid expression\n" +
                                "Invalid expression\n" +
                                "15\n" +
                                "Invalid expression\n" +
                                "-22\n" +
                                "Invalid expression\n" +
                                "Bye!"
                ),
                new PredefinedIOTestCase(
                        "/go\n/exit",
                        "Unknown command\nBye!"
                )
        );
    }
}
