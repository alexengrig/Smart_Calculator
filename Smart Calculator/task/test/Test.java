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
                        "/exit",
                        "Bye!"
                ),
                new PredefinedIOTestCase(
                        "17 9\n-2 5\n\n7\n/exit",
                        "26\n3\n7\nBye!"
                ),
                new PredefinedIOTestCase(
                        "100 200\n500\n300 400\n200\n\n\n-500\n/exit",
                        "300\n500\n700\n200\n-500\nBye!"
                ),
                new PredefinedIOTestCase(
                        "801 199 -300 500\n10 20 30 40 50 -10 -20 -30 -40\n/exit",
                        "1200\n50\nBye!"
                )
        );
    }

}
