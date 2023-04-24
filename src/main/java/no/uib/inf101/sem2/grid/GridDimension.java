package no.uib.inf101.sem2.grid;

/**
 * Represents the dimensions of a grid, consisting of a number of rows and
 * columns.
 * I have implemented this class from the Tetris project.
 */
public interface GridDimension {

  /** Number of rows in the grid */
  int rows();

  /** Number of columns in the grid */
  int cols();
}
