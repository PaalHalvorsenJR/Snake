package no.uib.inf101.sem2.model;

import java.util.List;

import no.uib.inf101.sem2.model.SnakeModel.Direction;

public class Snake {
    private Integer[] position;
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
        this.position = position;
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

    /**
     * Moves the snake in the given direction and updates the body
     * 
     * @param direction
     */
    public void move(Direction direction) {
        Integer[] newPosition = new Integer[2]; // I set the integer array to 2 because it is a 2D array
        Integer[] snakeHead = getHead();
        switch (direction) {
            case UP:
                newPosition[0] = snakeHead[0] - 1;
                newPosition[1] = snakeHead[1];
                break;
            case DOWN:
                newPosition[0] = snakeHead[0] + 1;
                newPosition[1] = snakeHead[1];
                break;
            case LEFT:
                newPosition[0] = snakeHead[0];
                newPosition[1] = snakeHead[1] - 1;
                break;
            case RIGHT:
                newPosition[0] = snakeHead[0];
                newPosition[1] = snakeHead[1] + 1;
                break;
        }
        body.add(0, newPosition); // Adds the new position to the head of the list
        body.remove(body.size() - 1); // Removes the last element in the list
    }

}
