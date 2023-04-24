package no.uib.inf101.sem2.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A 2-dimensional grid data structure that stores elements of type E.
 *
 * @param <E> The type of elements to store in the grid.
 *            I have implemented this class from the Tetris project.
 */

public class Grid<E> implements IGrid<E> {
    private final List<List<E>> grid;
    private final int rows;
    private final int cols;

    /**
     * Constructs a new grid with the specified number of rows and columns,
     * and initializes all cells with the specified default value.
     *
     * @param rows         The number of rows in the grid.
     * @param cols         The number of columns in the grid.
     * @param defaultValue The default value to initialize all cells with.
     */

    public Grid(int rows, int cols, E defaultValue) {
        this.rows = rows;
        this.cols = cols;
        grid = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                grid.get(i).add(defaultValue);
            }
        }
    }

    /**
     * Constructs a new grid with the specified number of rows and columns,
     * and initializes all cells to null.
     *
     * @param rows The number of rows in the grid.
     * @param cols The number of columns in the grid.
     */

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                grid.get(i).add(null);
            }
        }
    }

    // method that returns the number of rows
    @Override
    public int rows() {
        return rows;

    }

    // method that returns the number of columns
    @Override
    public int cols() {
        return cols;

    }

    // method that returns the number of cells in the grid
    @Override
    public Iterator<GridCell<E>> iterator() {
        ArrayList<GridCell<E>> list = new ArrayList<GridCell<E>>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                CellPosition pos = new CellPosition(i, j);
                list.add(new GridCell<>(pos, grid.get(i).get(j)));
            }
        }
        return list.iterator();
    }

    // method that returns the number of cells in the grid
    @Override
    public void set(CellPosition pos, E value) {
        grid.get(pos.row()).set(pos.col(), value);
    }

    // method that returns the number of cells in the grid
    @Override
    public E get(CellPosition pos) {
        return grid.get(pos.row()).get(pos.col());

    }

    // method that returns the number of cells in the grid
    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        if (pos.row() < 0 || pos.row() >= rows || pos.col() < 0 || pos.col() >= cols) {
            return false;
        }
        return true;
    }

}
