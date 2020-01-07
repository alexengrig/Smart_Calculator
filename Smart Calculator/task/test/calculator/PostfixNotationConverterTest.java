package calculator;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.List;

public class PostfixNotationConverterTest {
    private final PostfixNotationConverter converter = new PostfixNotationConverter();

    @Test
    public void check_simple() {
        List<String> infix = List.of("3", "+", "2", "*", "4");
        Deque<String> postfix = converter.convert(infix);
        List<String> expected = List.of("3", "2", "4", "*", "+");
        Assert.assertArrayEquals(expected.toArray(), postfix.toArray());
    }

    @Test
    public void check_simple_with_variables() {
        List<String> infix = List.of("a", "+", "2", "*", "b");
        Deque<String> postfix = converter.convert(infix);
        List<String> expected = List.of("a", "2", "b", "*", "+");
        Assert.assertArrayEquals(expected.toArray(), postfix.toArray());
    }

    @Test
    public void check_complex() {
        List<String> infix = List.of("2", "*", "(", "3", "+", "4", ")", "+", "1");
        Deque<String> postfix = converter.convert(infix);
        List<String> expected = List.of("2", "3", "4", "+", "*", "1", "+");
        Assert.assertArrayEquals(expected.toArray(), postfix.toArray());
    }

    @Test
    public void check_complex_with_variables() {
        List<String> infix = List.of("a", "*", "(", "3", "+", "b", ")", "+", "1");
        Deque<String> postfix = converter.convert(infix);
        List<String> expected = List.of("a", "3", "b", "+", "*", "1", "+");
        Assert.assertArrayEquals(expected.toArray(), postfix.toArray());
    }
}