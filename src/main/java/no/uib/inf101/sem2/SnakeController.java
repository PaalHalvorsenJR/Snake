package no.uib.inf101.sem2;

import java.awt.event.KeyListener;

import javax.swing.JPanel;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.view.SnakeView;

import java.awt.event.KeyEvent;

public class SnakeController extends JPanel implements KeyListener{
    private SnakeModel model;
    private SnakeView view;


    public SnakeController(SnakeModel model, SnakeView view) {
        this.model = model;
        this.view = view;
        

        view.addKeyListener(this);
        view.setFocusable(true);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        SnakeModel.Direction direction = null;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                direction = SnakeModel.Direction.UP;
                System.out.println("up" + direction);
                model.move(direction);
                break;
            case KeyEvent.VK_DOWN:
                direction = SnakeModel.Direction.DOWN;
                System.out.println("down" + direction);
                model.move(direction);
                break;
            case KeyEvent.VK_LEFT:
                direction = SnakeModel.Direction.LEFT;
                System.out.println("left" + direction);
                model.move(direction);
                break;
            case KeyEvent.VK_RIGHT:
                direction = SnakeModel.Direction.RIGHT;
                System.out.println("right" + direction);
                model.move(direction);
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






