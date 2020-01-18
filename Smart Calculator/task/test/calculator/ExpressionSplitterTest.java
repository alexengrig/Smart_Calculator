package calculator;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExpressionSplitterTest {
    private final ExpressionSplitter splitter = new ExpressionSplitter();

    @Test
    public void check_1sum1() {
        List<String> actual = splitter.split("1+1");
        assertEquals(List.of("1", "+", "1"), actual);
    }

    @Test
    public void check_1sub1() {
        List<String> actual = splitter.split("1-1");
        assertEquals(List.of("1", "-", "1"), actual);
    }

    @Test
    public void check_1multi1() {
        List<String> actual = splitter.split("1*1");
        assertEquals(List.of("1", "*", "1"), actual);
    }

    @Test
    public void check_1div1() {
        List<String> actual = splitter.split("1/1");
        assertEquals(List.of("1", "/", "1"), actual);
    }

    @Test
    public void check_1exp1() {
        List<String> actual = splitter.split("1^1");
        assertEquals(List.of("1", "^", "1"), actual);
    }

    @Test
    public void check_1sum1sub1multi1div1exp1() {
        List<String> actual = splitter.split("1+1-1*1/1^1");
        assertEquals(List.of("1", "+", "1", "-", "1", "*", "1", "/", "1", "^", "1"), actual);
    }

    @Test
    public void check_AbcSumAbcSubAbcMultiAbcDivAbcExpAbc() {
        List<String> actual = splitter.split("Abc+Abc-Abc*Abc/Abc^Abc");
        assertEquals(List.of("Abc", "+", "Abc", "-", "Abc", "*", "Abc", "/", "Abc", "^", "Abc"), actual);
    }

    @Test
    public void check_AbcSumAbcSubAbcMultiAbcDivAbcExpAbc_with_brackets() {
        List<String> actual = splitter.split("(Abc)+(Abc)-(Abc)*(Abc)/(Abc)^(Abc)");
        assertEquals(List.of(
                "(", "Abc", ")", "+",
                "(", "Abc", ")", "-",
                "(", "Abc", ")", "*",
                "(", "Abc", ")", "/",
                "(", "Abc", ")", "^",
                "(", "Abc", ")"),
                actual);
    }

    @Test
    public void check_a_multi_2_sum_b_multi_3_sum_c_multi_left_2_sum_3_right() {
        List<String> actual = splitter.split("a*2+b*3+c*(2+3)");
        assertEquals(List.of("a", "*", "2", "+", "b", "*", "3", "+", "c", "*", "(", "2", "+", "3", ")"), actual);
    }

    @Test
    public void check_1_3sum_2_multi_3_2sub_4() {
        List<String> actual = splitter.split("1 +++ 2 * 3 -- 4");
        assertEquals(List.of("1", "+++", "2", "*", "3", "--", "4"), actual);
    }
}