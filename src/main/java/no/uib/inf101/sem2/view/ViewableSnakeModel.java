package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.GameState;

/**
 * The ViewableTetrisModel interface defines the methods that a class must
 * implement to be a ViewableTetrisModel.
 */
public interface ViewableSnakeModel {

    int boombEaten = 0;

    /**
     * Returns the dimension of the board.
     * 
     * @return
     */
    public GridDimension getDimension();

    /**
     * returns the tiles on the board.
     * 
     * @return
     */
    public Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * Returns the game state.
     * 
     * @return
     */
    public GameState getGameState();

    void welcome();

    void startGame();

    void pauseGame();

    /**
     * Returns the score.
     * 
     * @return
     */
    public int getScore();

    public int getLevel();

    public boolean snakeLives();

    public int getBoombEaten();

    public Object getCellColor(char c);

    public Object gameOverColor();

}
