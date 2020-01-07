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
                /* Check simple additions and subtractions */
                new PredefinedIOTestCase(
                        "4 + 6 - 8\n\n\n2 - 3 - 4\n\n8 + 7 - 4\n1 +++ 2 * 3 -- 4\n/exit",
                        "2\n-5\n11\n11\nBye!"
                ),
                /* Check handling unknown commands */
                new PredefinedIOTestCase(
                        "/command\n/exit",
                        "Unknown command\nBye!"
                ),
                /* Check all operations */
                new PredefinedIOTestCase(
                        "3 + 8 * ((4 + 3) * 2 + 1) - 6 / (2 + 1)\n/exit",
                        "121\nBye!"
                ),
                /* Check with an invalid expressions */
                new PredefinedIOTestCase(
                        "8 * 3 + 12 * (4 - 2)\n4 * (2 + 3\n4 + 3)\n/exit",
                        "48\nInvalid expression\nInvalid expression\nBye!"
                ),
                /* Check expressions with variables */
                new PredefinedIOTestCase(
                        "a = 4\nb = 5\nc = 6\na*2+b*3+c*(2+3)\n/exit",
                        "53\nBye!"
                ),
                /* Check reassignment */
                new PredefinedIOTestCase(
                        "a = 1\na = 2\na = 3\na\n/exit",
                        "3\nBye!"
                )
        );
    }
}
