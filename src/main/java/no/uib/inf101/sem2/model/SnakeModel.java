package no.uib.inf101.sem2.model;

import java.util.List;
import java.util.ArrayList;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.view.ViewableSnakeModel;

/**
 * The snake model class represent the model of the snake game.
 * In the class we find much of the logic of the game.
 * It defines the snake, the board, the food, the boomb, the score, the level
 * and the game state.
 * We find methods for moving the snake, spawning food,
 * spawning boomb, checking if the snake is dead,
 * checking if the snake has eaten food, checking if the snake has eaten boomb,
 * checking if it has collided with itself and checking if it has collided with
 * the wall.
 * The class implements the ViewableSnakeModel interface, which defines the
 * methods that the view
 * can use to get information about the model.
 */

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
    public int boombEaten = 0;

    /**
     * Creates a new SnakeModel object.
     * 
     * @param board
     */
    public SnakeModel(SnakeBoard board) {
        this.board = board;
        gameState = GameState.WELCOME;
        initializeSnake(); // Initialize the snake object and draw it on the board
        spawnFood(); // Spawn a new apple at a random position on the board
        spawnBoomb(); // Spawn a new bomb at a random position on the board

    }

    /**
     * return the current Direction of the snake
     * 
     * @return
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * The directions the snake can move in
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Places the snake on the board and creates a new snake object
     * The snake is placed in the middle of the board and has a length of 5
     * The snake's body is a list of arrays of two integers (row, col)
     * The first element in the list is the snake's head
     * The last elements in the list is the snake's tail
     * 
     */
    private void initializeSnake() {
        Integer[] startingPosition = { board.rows() / 2, board.cols() / 2 };
        List<Integer[]> body = new ArrayList<>();

        for (int i = 0; i < startingLength; i++) {
            body.add(new Integer[] { startingPosition[0], startingPosition[1] - i });

            board.set(new CellPosition(startingPosition[0], startingPosition[1] - i), 'S');

        }
        board.set(new CellPosition(startingPosition[0], startingPosition[1]), 'O');

        snake = new Snake(startingLength, startingPosition, body);
    }

    /**
     * Spawns a new apple at a random position on the board
     * The apple is represented by the character 'L'
     * The apple's position is stored in the food field
     * The method loops until a valid position is found
     * A position is valid if it is not occupied by the snake
     */
    private void spawnFood() {
        int newRow, newCol;
        boolean validPosition;
        // do while loop to find a valid position, use do while loop because we want to
        // run the loop at least once
        do {
            newRow = (int) (Math.random() * board.rows());
            newCol = (int) (Math.random() * board.cols());
            validPosition = true;

            for (Integer[] segment : snake.getBody()) {
                if (segment[0] == newRow && segment[1] == newCol) {

                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);

        // Update the apple's position and set it on the board
        food = new int[] { newRow, newCol };
        board.set(new CellPosition(food[0], food[1]), 'L');
    }

    /**
     * Spawns a new bomb at a random position on the board
     * The bomb is represented by the character 'B'
     * The bomb's position is stored in the boomb field
     * The method loops until a valid position is found
     * A position is valid if it is not occupied by the snake
     */
    private void spawnBoomb() {
        int newRow, newCol;
        boolean validPosition;

        do {
            newRow = (int) (Math.random() * board.rows()); // Generate a random row
            newCol = (int) (Math.random() * board.cols()); // Generate a random column
            validPosition = true;

            for (Integer[] segment : snake.getBody()) {
                if (segment[0] == newRow && segment[1] == newCol) {

                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);

        boomb = new int[] { newRow, newCol };
        board.set(new CellPosition(boomb[0], boomb[1]), 'B');
    }

    /**
     * Moves the snake in the specified direction. the method updates the snake's
     * position
     * and checks if the snake has collided with itself, the wall or the apple.
     * Updates the game state accordingly.
     * 
     * @param direction the direction to move the snake in
     */
    public void move(Direction direction) {
        currentDirection = direction;

        if (!isValidMove(direction)) {
            gameState = GameState.GAME_OVER;
            return;
        }

        Integer[] oldHead = snake.getHead();
        // Get the index of the last segment in the snake body
        int lastSegmentIndex = snake.getBody().size() - 1;
        Integer[] lastSegment = snake.getBody().get(lastSegmentIndex); // Get the last segment
        moveTheSnake(direction);
        Integer[] newHead = snake.getHead();
        checkCollisionAndMoveBomb(lastSegment);
        // Set the board cells
        board.set(new CellPosition(oldHead[0], oldHead[1]), 'S');
        board.set(new CellPosition(newHead[0], newHead[1]), 'O');

        if (snakeLives()) {
            gameState = GameState.GAME_OVER;
            return;
        }
        if (collidesWithItself()) {
            System.out.println("You died!");
            gameState = GameState.GAME_OVER;
            return;
        }
        if (!checkCollisionAndMoveApple(lastSegment)) {
            board.set(new CellPosition(lastSegment[0], lastSegment[1]), ' ');
        }
        checkCollisionAndMoveBomb(lastSegment);
        // Print some debug info to the console (for testing)
        System.out.println("Snake head: (" + newHead[0] + ", " + newHead[1] + ")");
        System.out.println("Board dimensions: (" + board.rows() + ", " + board.cols() + ")");
        System.out.println("Snake length: " + snake.getLength());
        System.out.println("Snake level: " + level);
        System.out.println("AppleEaten: " + epleEaten);
        System.out.println("Score: " + score);
        System.out.println("BoombEaten: " + boombEaten);
    }

    /**
     * Calculates the new position of the snake's head based on the direction
     * 
     * @param direction the direction to move the snake in
     * @return an integer array containing the new position of the snake's head
     */
    private Integer[] getNewPosition(Direction direction) {
        Integer[] snakeHead = snake.getHead();
        Integer[] newPosition = new Integer[2];

        switch (direction) {
            case UP: // check if the snake is moving Up
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
        return newPosition;
    }

    /**
     * Determines if the spesified direction is a valid move for the head of the
     * snake
     * A move is valid if the snake is not moving out of bounds
     * 
     * @param direction the direction to move the snake in
     * @return true if the move is valid, false otherwise
     */
    private boolean isValidMove(Direction direction) {
        Integer[] newPosition = getNewPosition(direction);
        if (newPosition[0] < 0 || newPosition[0] >= board.rows() || newPosition[1] < 0
                || newPosition[1] >= board.cols()) {
            return false;
        }
        return true;
    }

    /**
     * Moves the snake in the specified direction. The method updates the snake's
     * position and removes the last segment
     * 
     * @param direction the direction to move the snake in
     */
    public void moveTheSnake(Direction direction) {
        Integer[] newPosition = getNewPosition(direction);

        if (isValidMove(direction)) {
            snake.getBody().add(0, newPosition);
            snake.getBody().remove(snake.getBody().size() - 1);
        }
    }

    /**
     * Checks if the snake has collided with itself
     * The snake has collided with itself if the snake's head
     * is at the same position as one of its body segments
     * 
     * @return true if the snake has collided with itself, false otherwise
     */
    private boolean collidesWithItself() {
        Integer[] snakeHead = snake.getHead();

        // Iterate through the snake's body segments, excluding the head
        for (int i = 1; i < snake.getBody().size(); i++) {
            Integer[] segment = snake.getBody().get(i);

            // Check if the segment has the same position as the head
            if (segment[0] == snakeHead[0] && segment[1] == snakeHead[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the snake has collided with the apple
     * If the snake has collided with the apple, the apple is moved to a new
     * position
     * and the snake's length is increased by one segment
     * 
     * @param lastSegment the last segment of the snake's body
     * @return true if the snake has collided with the apple, false otherwise
     */
    private boolean checkCollisionAndMoveApple(Integer[] lastSegment) {
        Integer[] snakeHead = snake.getHead();

        // Check if the snake's head is at the same position as the apple
        if (snakeHead[0] == food[0] && snakeHead[1] == food[1]) {

            score++;
            epleEaten++;

            spawnFood();
            findLevel();

            snake.getBody().add(lastSegment); // Add the new segment to the snake's body
            return true;
        }
        return false;
    }

    /**
     * Checks if the snake has collided with the boomb
     * If the snake has collided with the boomb, the boomb is moved to a new
     * position
     * and the snake's length is degreased by one segment
     * 
     * @param lastSegment the last segment of the snake's body
     * @return true if the snake has collided with the boomb, false otherwise
     */
    private boolean checkCollisionAndMoveBomb(Integer[] lastSegment) {
        Integer[] snakeHead = snake.getHead();

        // Check if the snake's head is at the same position as the apple
        if (snakeHead[0] == boomb[0] && snakeHead[1] == boomb[1]) {
            spawnBoomb();
            if (score > 0) {
                score--;
            }
            boombEaten++;

            if (snake.getLength() > 1) {
                Integer[] segment = snake.getBody().get(snake.getLength() - 1);
                snake.getBody().remove(snake.getLength() - 1);
                board.set(new CellPosition(segment[0], segment[1]), ' ');

            }

            return true;
        }
        return false;
    }

    /**
     * finds the level of the game
     */
    public void findLevel() {
        if (epleEaten % 10 == 0) {
            level++;
            epleEaten = 0;
        }
    }

    /**
     * If the game is over, the snake is reset to its initial position if
     * the player presses the restart button "r"
     * all the variables are reset to their initial values
     */
    public void restartGame() {
        gameState = GameState.ACTIVE_GAME;
        score = 0;
        level = 1;
        epleEaten = 0;
        boombEaten = 0;
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

    /**
     * The snake has 3 lives.
     * If the snake eats 3 bombs,
     * the game is over and the
     * 
     * @return
     */
    public boolean snakeLives() {
        if (boombEaten == 3) {
            return true;
        }
        return false;
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
    public int getBoombEaten() {
        return boombEaten;
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

    @Override
    public Object getCellColor(char c) {
        return getCellColor(c);
    }

    @Override
    public Object gameOverColor() {
        return gameOverColor();

    }

}