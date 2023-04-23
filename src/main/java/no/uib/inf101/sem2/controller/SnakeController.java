package no.uib.inf101.sem2.controller;

import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.SnakeModel;
import no.uib.inf101.sem2.view.SnakeView;

import java.awt.event.KeyEvent;

public class SnakeController extends JPanel implements KeyListener {
    private SnakeModel model;
    private SnakeView view;
    private Timer timer;
    private int speed = 200;

    /**
     * Constructs a new SnakeController object with the given Snake model and view.
     * 
     * @param model
     * @param view
     */
    public SnakeController(SnakeModel model, SnakeView view) {
        this.model = model;
        this.view = view;

        view.addKeyListener(this);
        view.setFocusable(true);

        timer = new Timer(speed, e -> onTimerTick());
        timer.start();
        System.out.println("timer started" + speed);
    }

    /**
     * This method is called every time the timer ticks, which is every 200 ms,
     * it make the snake to move forward.
     */
    private void onTimerTick() {
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            model.move(model.getCurrentDirection());
            GameSpeed();
            view.repaint();
        }
    }

    /**
     * This method is called every time the snake eats an apple, it makes the
     * snake to move faster, the speed is calculated by the number of apples
     * eaten. for every 10 apples eaten the speed is increased by 50 ms.
     */
    public void GameSpeed() {
        int eatenApples = model.getScore();
        int newSpeed = Math.max(50, 200 - (int) (eatenApples / 10) * 50);
        speed = newSpeed;
        timer.setDelay(speed);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        SnakeModel.Direction direction = null;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                direction = SnakeModel.Direction.UP;
                System.out.println("up" + direction);
                if (model.getCurrentDirection() != SnakeModel.Direction.DOWN) {
                    model.move(direction);
                }
                break;
            case KeyEvent.VK_DOWN:
                direction = SnakeModel.Direction.DOWN;
                System.out.println("down" + direction);
                if (model.getCurrentDirection() != SnakeModel.Direction.UP) {
                    model.move(direction);
                }

                break;
            case KeyEvent.VK_LEFT:
                direction = SnakeModel.Direction.LEFT;
                System.out.println("left" + direction);
                if (model.getCurrentDirection() != SnakeModel.Direction.RIGHT) {
                    model.move(direction);

                }

                break;
            case KeyEvent.VK_RIGHT:
                direction = SnakeModel.Direction.RIGHT;
                System.out.println("right" + direction);
                if (model.getCurrentDirection() != SnakeModel.Direction.LEFT) {
                    model.move(direction);
                }

                break;
            case KeyEvent.VK_SPACE:
                System.out.println("space");
                if (model.getGameState() == GameState.WELCOME) {
                    model.welcome();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.out.println("escape");
                if (model.getGameState() == GameState.ACTIVE_GAME) {
                    model.pauseGame();
                } else if (model.getGameState() == GameState.PAUSED) {
                    model.startGame();

                }
                break;
            case KeyEvent.VK_R:
                System.out.println("r");
                if (model.getGameState() == GameState.GAME_OVER) {
                    model.restartGame();
                }
                break;
        }

        view.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // You can implement this method if needed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // You can implement this method if needed
    }
}
