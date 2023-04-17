package no.uib.inf101.sem2;
import java.util.List;



import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.view.ViewableSnakeModel;

import no.uib.inf101.sem2.Snake;

/**
 * The TetrisModel class implements the ViewableTetrisModel and ControllableTetrisModel interfaces.
 * It is the model of the tetris game.
 */

public class SnakeModel implements ViewableSnakeModel{
    //Filds for the class
    private final SnakeBoard board;
    private GameState gameState;
    private Snake snake;
    private int[] food;
    private int startingLength = 5;
    private Timer timer;



    public SnakeModel(SnakeBoard board) {
        this.board = board;
        gameState = GameState.WELCOME;
        initializeSnake();
        spawnFood();
       
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    
    
        
    private void initializeSnake() {
        Integer[] startingPosition = {board.rows() / 2, board.cols() / 2};
        List<Integer[]> body = new ArrayList<>();
        
        for (int i = 0; i < startingLength; i++) {
            body.add(new Integer[]{startingPosition[0], startingPosition[1] - i});
            board.set(new CellPosition(startingPosition[0], startingPosition[1] - i), 'S');
        }
        board.set(new CellPosition(startingPosition[0], startingPosition[1]), 'O');
        snake = new Snake(startingLength, startingPosition, body);
    }
        

    private void spawnFood() {
        int newRow, newCol;
        boolean validPosition;
    
        do {
            newRow = (int) (Math.random() * board.rows());
            newCol = (int) (Math.random() * board.cols());
            validPosition = true;
    
            // Check if the new position is occupied by the snake
            for (Integer[] segment : snake.getBody()) {
                if (segment[0] == newRow && segment[1] == newCol) {
                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);
    
        // Update the apple's position and set it on the board
        food = new int[]{newRow, newCol};
        board.set(new CellPosition(food[0], food[1]), 'L');
    }

    public void move(Direction direction) {
        if (!isValidMove(direction)) {
            gameState = GameState.GAME_OVER;
            return;
        }
        Integer[] oldHead = snake.getHead();
        // Get the index of the last segment in the snake body
        int lastSegmentIndex = snake.getBody().size() - 1; // Subtract 1 because the index starts at 0
        Integer[] lastSegment = snake.getBody().get(lastSegmentIndex); // Get the last segment
        snake.move(direction);
        Integer[] newHead = snake.getHead();
    
        // Set the board cells
        board.set(new CellPosition(oldHead[0], oldHead[1]), 'S'); // Clear the cell where the head was
        board.set(new CellPosition(newHead[0], newHead[1]), 'O'); // Draw the new head position

        if (collidesWithItself()) {
            System.out.println("You died!");
            gameState = GameState.GAME_OVER;
            return;
        }
        if(!checkCollisionAndMoveApple(lastSegment)){
            board.set(new CellPosition(lastSegment[0], lastSegment[1]), ' '); // Draw 'Q' behind the last segment
        }
        System.out.println("Snake head: (" + newHead[0] + ", " + newHead[1] + ")");
        System.out.println("Board dimensions: (" + board.rows() + ", " + board.cols() + ")");

    }

    private boolean isValidMove(Direction direction) {
        Integer[] snakeHead = snake.getHead();
        Integer[] newPosition = new Integer[2];
    
        switch (direction) {
            case UP:
                newPosition[0] = snakeHead[0] - 1;
                newPosition[1] = snakeHead[1];
                break;
            case DOWN:
                newPosition[0] = snakeHead[0] + 1;
                newPosition[1] = snakeHead[1];
                break;
            case LEFT:
                newPosition[0] = snakeHead[0];
                newPosition[1] = snakeHead[1] - 1;
                break;
            case RIGHT:
                newPosition[0] = snakeHead[0];
                newPosition[1] = snakeHead[1] + 1;
                break;
        }
    
        if (newPosition[0] < 0 || newPosition[0] >= board.rows() || newPosition[1] < 0 || newPosition[1] >= board.cols()) {
            return false;
        }
    
        return true;
    }

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

    private boolean checkCollisionAndMoveApple(Integer[] lastSegment) {
        Integer[] snakeHead = snake.getHead();
    
        // Check if the snake's head is at the same position as the apple
        if (snakeHead[0] == food[0] && snakeHead[1] == food[1]) {
            // Spawn a new apple at a valid position
            spawnFood();
    
            // Grow the snake by adding a new segment // Get the last segment // Duplicate the last segment
            snake.getBody().add(lastSegment); // Add the new segment to the snake's body
            return true;
            // Optionally, you can also increase the snake's speed here
        }
        return false;
    }


    // Hint: når du skal implementere metoden som returnerer noe med typen GridDimension som inneholder antall rader og kolonner -- har du tilfeldigvis et objekt med denne typen allerede som du enkelt kan returnere? :think:
    @Override
    public GridDimension getDimension(){ 
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
        if (gameState == GameState.WELCOME){
        gameState = GameState.ACTIVE_GAME;
        }
    }

    @Override
    public void pauseGame() {
        if (gameState == GameState.ACTIVE_GAME){
            gameState = GameState.PAUSED;
        }
    }

    @Override
    public void startGame() {
        if (gameState == GameState.PAUSED){
            gameState = GameState.ACTIVE_GAME;
        }
    }




}
