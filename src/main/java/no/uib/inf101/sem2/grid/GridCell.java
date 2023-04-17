package no.uib.inf101.sem2.grid;


/**
 * Represents a cell in a grid, consisting of a position and a value.
 * @param <E> the type of value stored in the cell
 */

public record GridCell<E>(CellPosition pos, E value) {

    /**
     * Returns the position of this cell.
     * @return the position of this cell
     */
    public CellPosition getPos() {
        return null;
    }
}

    