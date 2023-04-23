package no.uib.inf101.sem2;

import javax.swing.JFrame;
import no.uib.inf101.sem2.controller.SnakeController;
import no.uib.inf101.sem2.model.SnakeBoard;
import no.uib.inf101.sem2.model.SnakeModel;
import no.uib.inf101.sem2.view.SnakeView;

/**
 * The main class for the Snake game.
 */
public class SnakeMain {
  public static final String WINDOW_TITLE = "INF101 ";

  /**
   * The main method.
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {

    SnakeBoard board = new SnakeBoard(20, 20);

    SnakeModel model = new SnakeModel(board);

    SnakeView SnakeView = new SnakeView(model);

    new SnakeController(model, SnakeView);

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle(WINDOW_TITLE);
    frame.setContentPane(SnakeView);
    // frame.setContentPane(SnakeView);
    // frame.getContentPane().add(gifLabel);
    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
  }
}
