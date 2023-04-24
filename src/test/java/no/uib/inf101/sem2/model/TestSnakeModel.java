package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.model.SnakeModel.Direction;

class TestSnakeModel {

    private SnakeModel model;
    private SnakeBoard board;

    @Test
    public void sanityTest() {
        SnakeModel model = new SnakeModel(new SnakeBoard(20, 20));
        assertNotNull(model);
    }

    @Test
    public void testMoveRight() {
        // Move snake up
        board = new SnakeBoard(20, 20);
        model = new SnakeModel(board);
        model.welcome();
        model.move(Direction.RIGHT);
        assertEquals(GameState.ACTIVE_GAME, model.getGameState());
    }

    @Test
    public void testMoveDown() {
        board = new SnakeBoard(20, 20);
        model = new SnakeModel(board);
        model.welcome();
        // Move snake to the left
        model.move(Direction.DOWN);
        assertEquals(GameState.ACTIVE_GAME, model.getGameState());
    }

    @Test
    public void testMoveLeft() {
        board = new SnakeBoard(20, 20);
        model = new SnakeModel(board);
        model.welcome();
        // Move snake down
        model.move(Direction.LEFT);
        assertEquals(GameState.GAME_OVER, model.getGameState());
    }

    @Test
    public void testMoveUp() {
        board = new SnakeBoard(20, 20);
        model = new SnakeModel(board);
        model.welcome();
        // Move snake to the right
        model.move(Direction.UP);
        assertEquals(GameState.ACTIVE_GAME, model.getGameState());
    }

    @BeforeEach
    public void setUp() {
        board = new SnakeBoard(20, 20);
        model = new SnakeModel(board);
        model.welcome();
    }

    @Test
    public void testMoveOutOfBounds() {
        // Move snake out of the board
        model.move(Direction.RIGHT);
        model.move(Direction.RIGHT);
        model.move(Direction.RIGHT);
        model.move(Direction.RIGHT);
        model.move(Direction.RIGHT);
        model.move(Direction.RIGHT);
        model.move(Direction.RIGHT);
        model.move(Direction.RIGHT);
        model.move(Direction.RIGHT);
        model.move(Direction.RIGHT);
        assertEquals(GameState.GAME_OVER, model.getGameState());
    }

    @Test
    public void testMoveIntoSelf() {
        // Move snake into itself
        model.move(Direction.RIGHT);
        model.move(Direction.DOWN);
        model.move(Direction.LEFT);
        model.move(Direction.UP);

        assertEquals(GameState.GAME_OVER, model.getGameState());
    }

    @Test
    public void testGetScore() {
        assertEquals(0, model.getScore());

    }

    @Test
    public void TestRestartGame() {
        model.move(Direction.RIGHT);

        model.restartGame();

        assertEquals(GameState.ACTIVE_GAME, model.getGameState());

        assertEquals(0, model.getScore());

        assertEquals(1, model.getLevel());

        assertEquals(0, model.getBoombEaten());

        assertEquals(SnakeModel.Direction.RIGHT, model.getCurrentDirection());

    }

}