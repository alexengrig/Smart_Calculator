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
                        "4 + 6 - 8\n\n\n2 - 3 - 4\n\n8 + 7 - 4\n/exit",
                        "2\n-5\n11\nBye!"
                ),
                /* Check handling unknown commands */
                new PredefinedIOTestCase(
                        "/command\n/exit",
                        "Unknown command\nBye!"
                ),
                /* Check different assignments */
                new PredefinedIOTestCase(
                        "n = 3\nm=4\na =   5\nb = a\nn\nm\na\nb\ncount = 10\ncount\n/exit",
                        "3\n4\n5\n5\n10\nBye!"
                ),
                /* Check expressions with variables */
                new PredefinedIOTestCase(
                        "a = 3\nb = 4\nc = 5\na + b - c\nb - c + 4 - a\na = 800\na + b + c\n/exit",
                        "2\n0\n809\nBye!"
                ),
                /* Check reassignment */
                new PredefinedIOTestCase(
                        "a = 1\na = 2\na = 3\na\n/exit",
                        "3\nBye!"
                ),
                /* Check handling unknown variables */
                new PredefinedIOTestCase(
                        "q\nr\nq = 10\nr = 20\nq\nr\nR\n/exit",
                        "Unknown variable\n" +
                                "Unknown variable\n" +
                                "10\n20\n" +
                                "Unknown variable\n" +
                                "Bye!"
                ),
                /* Check handling incorrect assignments */
                new PredefinedIOTestCase(
                        "a1 = 8\nn = a2a\na = 7 = 8\nnum = 10\n/exit",
                        "Invalid identifier\n" +
                                "Invalid assignment\n" +
                                "Invalid assignment\n" +
                                "Bye!"
                )
        );
    }
}
