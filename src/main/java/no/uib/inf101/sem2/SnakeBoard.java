package no.uib.inf101.sem2;

import no.uib.inf101.sem2.grid.Grid;

import no.uib.inf101.sem2.grid.GridDimension;

public class SnakeBoard extends Grid<Character> {

    /**
     * Constructs a TetrisBoard object with the specified number of rows and
     * columns.
     * All cells are initialized to the default character ('-').
     * 
     * @param rows the number of rows on the board.
     * @param cols the number of columns on the board.
     */
    public SnakeBoard(int rows, int cols) {
        super(rows, cols, '-');
    }
}
