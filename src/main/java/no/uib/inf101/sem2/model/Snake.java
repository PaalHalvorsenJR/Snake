package no.uib.inf101.sem2.model;

import java.util.List;

import no.uib.inf101.sem2.model.SnakeModel.Direction;

/**
 * Represents a snake in the game
 */

public class Snake {
    private int length;
    private List<Integer[]> body;

    /**
     * Constructs a new snake object
     * length is the length of the snake
     * position is the position of the snake's head as an array of two integers
     * (row, col)
     * body is the snake's body as a list of arrays of two integers (row, col)
     * The first element in the list is the snake's head
     * The last element in the list is the snake's tail
     * 
     * @param length
     * @param position
     * @param body
     */
    public Snake(int length, Integer[] position, List<Integer[]> body) {
        this.length = length;
        this.body = body;
    }

    /**
     * Returns the snake's body as a list of arrays of two integers (row, col)
     * The first element in the list is the snake's head
     * The last element in the list is the snake's tail
     * 
     * @return
     */
    public List<Integer[]> getBody() {
        return body;
    }

    /**
     * Returns the length of the snake
     * 
     * @return
     */
    public int getLength() {
        return body.size();
    }

    /**
     * Returns the position of the snake's
     * head as an array of two integers (row, col)
     * 
     * @return
     * 
     */
    public Integer[] getHead() {
        return body.get(0);
    }

}
