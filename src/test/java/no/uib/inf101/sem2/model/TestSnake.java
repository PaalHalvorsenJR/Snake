package no.uib.inf101.sem2.model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.model.SnakeModel.Direction;

public class TestSnake {

    //
    @Test
    public void testConstructorAndGetBody() {
        Integer[] head = { 5, 5 };
        Integer[] bodySegment = { 6, 5 };
        List<Integer[]> body = new ArrayList<>(Arrays.asList(head, bodySegment));
        Snake snake = new Snake(5, head, body);

        List<Integer[]> expectedBody = new ArrayList<>(Arrays.asList(head, bodySegment));
        assertEquals(expectedBody, snake.getBody(), "Snake body should match the given body");
    }

    @Test
    public void testConstructorAndGetLength() {
        Integer[] head = { 5, 5 };
        Integer[] bodySegment = { 6, 5 };
        List<Integer[]> body = new ArrayList<>(Arrays.asList(head, bodySegment));
        Snake snake = new Snake(5, head, body);

        int expectedLength = 2;
        assertEquals(expectedLength, snake.getLength(), "Snake length should match the given length");
    }

    @Test
    public void testConstructorAndGetHead() {
        Integer[] head = { 5, 5 };
        Integer[] bodySegment = { 6, 5 };
        List<Integer[]> body = new ArrayList<>(Arrays.asList(head, bodySegment));
        Snake snake = new Snake(2, head, body);

        Integer[] expectedHead = { 5, 5 };
        assertTrue(Arrays.equals(expectedHead, snake.getHead()));
    }

    private boolean compereListArray(List<Integer[]> list1, List<Integer[]> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!Arrays.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testMove() {
        Integer[] head = { 5, 5 };
        Integer[] bodySegment = { 6, 5 };
        List<Integer[]> body = new ArrayList<>(Arrays.asList(head, bodySegment));
        Snake snake = new Snake(2, head, body);

        snake.move(Direction.UP);
        List<Integer[]> expectedBody = new ArrayList<>(Arrays.asList(new Integer[] { 4, 5 }, head));
        assertTrue(compereListArray(expectedBody, snake.getBody()));
        assertTrue(Arrays.equals(new Integer[] { 4, 5 }, snake.getHead()));

        snake.move(Direction.DOWN);
        expectedBody = new ArrayList<>(Arrays.asList(new Integer[] { 5, 5 }, new Integer[] { 4, 5 }));
        assertTrue(compereListArray(expectedBody, snake.getBody()));
        assertTrue(Arrays.equals(new Integer[] { 5, 5 }, snake.getHead()));

        snake.move(Direction.LEFT);
        expectedBody = new ArrayList<>(Arrays.asList(new Integer[] { 5, 4 }, new Integer[] { 5, 5 }));
        assertTrue(compereListArray(expectedBody, snake.getBody()));
        assertTrue(Arrays.equals(new Integer[] { 5, 4 }, snake.getHead()));

        snake.move(Direction.RIGHT);
        expectedBody = new ArrayList<>(Arrays.asList(new Integer[] { 5, 5 }, new Integer[] { 5, 4 }));
        assertTrue(compereListArray(expectedBody, snake.getBody()));
        assertTrue(Arrays.equals(new Integer[] { 5, 5 }, snake.getHead()));
    }

}