package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSnakeBoard {

    private SnakeBoard board;
    private int rows = 20;
    private int cols = 20;

    @BeforeEach
    public void setUp() {
        board = new SnakeBoard(rows, cols);
    }

    @Test
    public void TestNewboardHasCorrectSize() {
        assertEquals(rows, board.rows());
        assertEquals(cols, board.cols());
    }

    @Test
    public void TestNewboardHasCorrectDefaultChar() {
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                CellPosition pos = new CellPosition(row, col);
                assertEquals('-', (char) board.get(pos));
            }
        }
    }

}
