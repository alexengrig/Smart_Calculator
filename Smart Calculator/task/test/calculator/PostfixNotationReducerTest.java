package calculator;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PostfixNotationReducerTest {
    private final PostfixNotationReducer reducer = new PostfixNotationReducer();

    @Test
    public void check_simple() {
        int result = reducer.reduce(new ArrayDeque<>(List.of("3", "2", "4", "*", "+")));
        assertEquals(11, result);
    }

    @Test
    public void check_simple_with_variables() {
        Map<String, Integer> variables = Map.of("a", 3, "b", 4);
        int result = reducer.reduce(new ArrayDeque<>(List.of("a", "2", "b", "*", "+")), variables);
        assertEquals(11, result);
    }

    @Test
    public void check_complex() {
        int result = reducer.reduce(new ArrayDeque<>(List.of("2", "3", "4", "+", "*", "1", "+")));
        assertEquals(15, result);
    }

    @Test
    public void check_complex_with_variables() {
        Map<String, Integer> variables = Map.of("a", 2, "b", 4);
        int result = reducer.reduce(new ArrayDeque<>(List.of("a", "3", "b", "+", "*", "1", "+")), variables);
        assertEquals(15, result);
    }
}