package no.uib.inf101.sem2.model;

import java.util.List;
import java.util.ArrayList;
import no.uib.inf101.sem2.SnakeBoard;
import no.uib.inf101.sem2.controller.SnakeController;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.view.ViewableSnakeModel;

public class SnakeModel implements ViewableSnakeModel {
    // Filds for the class
    private final SnakeBoard board;
    private GameState gameState;
    private Snake snake;
    private int[] food;
    private int[] boomb;
    private int startingLength = 5;
    private Direction currentDirection = Direction.RIGHT;
    private int score = 0;
    private int epleEaten = 0;
    private int level = 1;

    // Here is
    public SnakeModel(SnakeBoard board) {
        this.board = board;
        gameState = GameState.WELCOME;
        initializeSnake(); // Initialize the snake object and draw it on the board
        spawnFood(); // Spawn a new apple at a random position on the board
        spawnBoomb(); // Spawn a new bomb at a random position on the board

    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public enum Direction { // The directions the snake can move in
        UP, DOWN, LEFT, RIGHT
    }

    private void initializeSnake() { // Initialize the snake object and draw it on the board
        Integer[] startingPosition = { board.rows() / 2, board.cols() / 2 }; // Start in the middle of the board
        List<Integer[]> body = new ArrayList<>(); // The snake's body segments as a list of arrays of two integers (row,
                                                  // col)

        for (int i = 0; i < startingLength; i++) { // Add the snake's body segments
            body.add(new Integer[] { startingPosition[0], startingPosition[1] - i }); // Add a new segment to the body
                                                                                      // list at the given position
                                                                                      // (row, col)
            board.set(new CellPosition(startingPosition[0], startingPosition[1] - i), 'S'); // Draw the segment on the
                                                                                            // board at the given
                                                                                            // position (row, col)
        }
        board.set(new CellPosition(startingPosition[0], startingPosition[1]), 'O'); // Draw the head on the board at the
                                                                                    // given position (row, col)
        snake = new Snake(startingLength, startingPosition, body); // Create a new snake object
    }

    private void spawnFood() { // Spawn a new apple at a random position
        int newRow, newCol; // The new position of the apple
        boolean validPosition; // Whether the new position is valid or not

        do {
            newRow = (int) (Math.random() * board.rows()); // Generate a random row
            newCol = (int) (Math.random() * board.cols()); // Generate a random column
            validPosition = true;

            // Check if the new position is occupied by the snake
            for (Integer[] segment : snake.getBody()) { // Loop through all the snake's body segments
                if (segment[0] == newRow && segment[1] == newCol) { // If the segment's position is the same as the new
                                                                    // position
                    validPosition = false; // The position is not valid
                    break;
                }
            }
        } while (!validPosition); // Loop until a valid position is found

        // Update the apple's position and set it on the board
        food = new int[] { newRow, newCol }; // Update the apple's position
        board.set(new CellPosition(food[0], food[1]), 'L'); // Draw the apple on the board
    }

    private void spawnBoomb() {
        int newRow, newCol; // The new position of the apple
        boolean validPosition; // Whether the new position is valid or not

        do {
            newRow = (int) (Math.random() * board.rows()); // Generate a random row
            newCol = (int) (Math.random() * board.cols()); // Generate a random column
            validPosition = true;

            // Check if the new position is occupied by the snake
            for (Integer[] segment : snake.getBody()) { // Loop through all the snake's body segments
                if (segment[0] == newRow && segment[1] == newCol) { // If the segment's position is the same as the new
                                                                    // position
                    validPosition = false; // The position is not valid
                    break;
                }
            }
        } while (!validPosition); // Loop until a valid position is found

        // Update the apple's position and set it on the board
        boomb = new int[] { newRow, newCol }; // Update the apple's position
        board.set(new CellPosition(boomb[0], boomb[1]), 'B'); // Draw the apple on the board
    }

    public void move(Direction direction) {
        currentDirection = direction;

        if (!isValidMove(direction)) {
            gameState = GameState.GAME_OVER;
            return;
        }
        Integer[] oldHead = snake.getHead();
        // Get the index of the last segment in the snake body
        int lastSegmentIndex = snake.getBody().size() - 1; // Subtract 1 because the index starts at 0
        Integer[] lastSegment = snake.getBody().get(lastSegmentIndex); // Get the last segment
        snake.move(direction); // Move the snake
        Integer[] newHead = snake.getHead();
        checkCollisionAndMoveBomb(lastSegment);
        // Set the board cells
        board.set(new CellPosition(oldHead[0], oldHead[1]), 'S'); // Clear the cell where the head was
        board.set(new CellPosition(newHead[0], newHead[1]), 'O'); // Draw the new head position

        if (collidesWithItself()) {
            System.out.println("You died!");
            gameState = GameState.GAME_OVER;
            return;
        }
        if (!checkCollisionAndMoveApple(lastSegment)) { // If the snake didn't eat an apple
            board.set(new CellPosition(lastSegment[0], lastSegment[1]), ' '); // Clear the cell where the tail was
        }
        checkCollisionAndMoveBomb(lastSegment);
        // Print some debug info to the console (for testing)
        System.out.println("Snake head: (" + newHead[0] + ", " + newHead[1] + ")");
        System.out.println("Board dimensions: (" + board.rows() + ", " + board.cols() + ")");
        System.out.println("Snake length: " + snake.getLength());
        System.out.println("Snake level: " + level);
        System.out.println("AppleEaten: " + epleEaten);
        System.out.println("Score: " + score);
    }

    // Check if the snake is moving in a valid direction
    private boolean isValidMove(Direction direction) {
        Integer[] snakeHead = snake.getHead();
        Integer[] newPosition = new Integer[2];

        switch (direction) {
            // Check if the snake is moving in the opposite direction
            case UP:
                newPosition[0] = snakeHead[0] - 1;
                newPosition[1] = snakeHead[1];
                break;
            case DOWN: // check if the snake is moving Down
                newPosition[0] = snakeHead[0] + 1;
                newPosition[1] = snakeHead[1];
                break;
            case LEFT: // check if the snake is moving Left
                newPosition[0] = snakeHead[0];
                newPosition[1] = snakeHead[1] - 1;
                break;
            case RIGHT: // check if the snake is moving Right
                newPosition[0] = snakeHead[0];
                newPosition[1] = snakeHead[1] + 1;
                break;
        }
        // Check if the new position is outside the board
        if (newPosition[0] < 0 || newPosition[0] >= board.rows() || newPosition[1] < 0
                || newPosition[1] >= board.cols()) {
            return false;
        }

        return true;
    }

    private boolean collidesWithItself() {
        Integer[] snakeHead = snake.getHead(); // Get the snake's head position

        // Iterate through the snake's body segments, excluding the head
        for (int i = 1; i < snake.getBody().size(); i++) { // Start at 1 because the head is at index 0
            Integer[] segment = snake.getBody().get(i); // Get the current segment

            // Check if the segment has the same position as the head
            if (segment[0] == snakeHead[0] && segment[1] == snakeHead[1]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCollisionAndMoveApple(Integer[] lastSegment) {
        Integer[] snakeHead = snake.getHead(); // Get the snake's head position

        // Check if the snake's head is at the same position as the apple
        if (snakeHead[0] == food[0] && snakeHead[1] == food[1]) {

            score++;
            epleEaten++;
            // Spawn a new apple at a valid position
            spawnFood();
            findLevel();

            // Grow the snake by adding a new segment // Get the last segment // Duplicate
            // the last segment
            snake.getBody().add(lastSegment); // Add the new segment to the snake's body
            return true;
        }
        return false;
    }

    private boolean checkCollisionAndMoveBomb(Integer[] lastSegment) {
        Integer[] snakeHead = snake.getHead(); // Get the snake's head position

        // Check if the snake's head is at the same position as the apple
        if (snakeHead[0] == boomb[0] && snakeHead[1] == boomb[1]) {
            // spawn 3 new bombs at a valid position
            spawnBoomb();
            score--;

            // delete the last segment and set the color to black
            if (snake.getLength() > 1) {
                Integer[] segment = snake.getBody().get(snake.getLength() - 1);
                snake.getBody().remove(snake.getLength() - 1);
                board.set(new CellPosition(segment[0], segment[1]), ' ');

            }

            return true;
        }
        return false;
    }

    public void findLevel() {
        if (epleEaten % 10 == 0) {
            level++;
            epleEaten = 0;
        }
    }

    public void restartGame() {
        gameState = GameState.ACTIVE_GAME;
        score = 0;
        level = 1;
        epleEaten = 0;
        currentDirection = Direction.RIGHT;

        // Clear the board
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                board.set(new CellPosition(i, j), ' ');
            }
        }

        initializeSnake();
        spawnFood();
        spawnBoomb();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void welcome() {
        if (gameState == GameState.WELCOME) {
            gameState = GameState.ACTIVE_GAME;
        }
    }

    @Override
    public void pauseGame() {
        if (gameState == GameState.ACTIVE_GAME) {
            gameState = GameState.PAUSED;
        }
    }

    @Override
    public void startGame() {
        if (gameState == GameState.PAUSED) {
            gameState = GameState.ACTIVE_GAME;
        }
    }
}
