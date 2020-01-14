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
}