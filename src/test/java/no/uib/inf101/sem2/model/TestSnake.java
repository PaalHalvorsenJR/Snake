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
        assertEquals(expectedBody, snake.getBody());
    }

    @Test
    public void testConstructorAndGetLength() {
        Integer[] head = { 5, 5 };
        Integer[] bodySegment = { 6, 5 };
        List<Integer[]> body = new ArrayList<>(Arrays.asList(head, bodySegment));
        Snake snake = new Snake(5, head, body);

        int expectedLength = 2;
        assertEquals(expectedLength, snake.getLength());
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

}