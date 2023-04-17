package no.uib.inf101.sem2;

import java.util.List;

import no.uib.inf101.sem2.SnakeModel.Direction;

import java.util.List;

public class Snake {
    private Integer[] position;
    private int length;
    private List<Integer[]> body;

    public Snake(int length, Integer[] position, List<Integer[]> body) {
        this.length = length;
        this.position = position;
        this.body = body;
    }

    public int getLength() {
        return body.size();
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Integer[] getPosition() {
        return position;
    }

    public void setPosition(Integer[] position) {
        this.position = position;
    }

    public List<Integer[]> getBody() {
        return body;
    }

    public void setBody(List<Integer[]> body) {
        this.body = body;
    }
    public Integer[] getHead() {
        return body.get(0);
    }

    /**
     * Moves the snake in the given direction and updates the body
     * @param direction
     * 
     */
    public void move(Direction direction) {
        Integer[] newPosition = new Integer[2];
        switch (direction) {
            case UP:
                newPosition[0] = getHead()[0] - 1;
                newPosition[1] = getHead()[1];
                break;
            case DOWN:
                newPosition[0] = getHead()[0] + 1;
                newPosition[1] = getHead()[1];
                break;
            case LEFT:
                newPosition[0] = getHead()[0];
                newPosition[1] = getHead()[1] - 1;
                break;
            case RIGHT:
                newPosition[0] = getHead()[0];
                newPosition[1] = getHead()[1] + 1;
                break;
        }


        body.add(0, newPosition);
        body.remove(body.size() - 1);
    }



}
