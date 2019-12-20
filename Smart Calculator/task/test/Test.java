import calculator.Main;
import org.hyperskill.hstest.v6.stage.BaseStageTest;
import org.hyperskill.hstest.v6.testcase.CheckResult;
import org.hyperskill.hstest.v6.testcase.TestCase;

import java.util.List;
import java.util.Objects;

public class Test extends BaseStageTest<String> {

    public Test() throws Exception {
        super(Main.class);
    }

    private String solveStage(String input) {
        String[] nums = input.split("\\s+");
        int a = Integer.parseInt(nums[0]);
        int b = Integer.parseInt(nums[1]);
        return Objects.toString(a + b);
    }

    @Override
    public List<TestCase<String>> generate() {
        List<TestCase<String>> tests = List.of(
            new TestCase<String>().setInput("0 1"),
            new TestCase<String>().setInput("1 0"),
            new TestCase<String>().setInput("2 3"),
            new TestCase<String>().setInput("100 123"),
            new TestCase<String>().setInput("-1 5"),
            new TestCase<String>().setInput("5 -2"),
            new TestCase<String>().setInput("-300 -400")
        );

        for (TestCase<String> test : tests) {
            test.setAttach(solveStage(test.getInput()));
        }

        return tests;
    }

    @Override
    public CheckResult check(String reply, String clue) {
        try {
            int actual = Integer.parseInt(reply.trim());
            int expected = Integer.parseInt(clue.trim());
            return new CheckResult(actual == expected);
        }
        catch (Exception ex) {
            return new CheckResult(false, "Can't check the answer");
        }
    }
}
