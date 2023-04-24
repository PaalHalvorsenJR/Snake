package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.Grid;

public class SnakeBoard extends Grid<Character> {

    /**
     * Constructs a TetrisBoard object with the specified number of rows and
     * columns.
     * All cells are initialized to the default character ('-').
     * 
     * @param rows the number of rows on the board.
     * @param cols the number of columns on the board.
     * 
     *             I have implemented this class from the Tetris project.
     */
    public SnakeBoard(int rows, int cols) {
        super(rows, cols, '-');
    }
}
