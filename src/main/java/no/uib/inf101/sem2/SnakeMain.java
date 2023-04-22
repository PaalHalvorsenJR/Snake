package no.uib.inf101.sem2;

import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import no.uib.inf101.sem2.controller.SnakeController;
import no.uib.inf101.sem2.model.SnakeModel;
import no.uib.inf101.sem2.view.SnakeView;

/**
 * The main class for the Tetris game.
 */
public class SnakeMain {
  public static final String WINDOW_TITLE = "INF101 ";

  /**
   * The main method.
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {

    // Create the model, view and controller
    SnakeBoard board = new SnakeBoard(20, 20);

    // create a new tetris model with the board and the factory
    SnakeModel model = new SnakeModel(board);

    // create a new tetris view with the model
    SnakeView SnakeView = new SnakeView(model);
    // The JFrame is the "root" application window.
    // We here set som properties of the main window,
    // and tell it to display our tetrisView

    new SnakeController(model, SnakeView);

    // URL url =
    // SnakeMain.class.getClass().getResource("C:/Users/Bruker/OneDrive/Dokumenter/IMØ/2_Semester/INF101/sem2-1/src/main/java/no/uib/inf101/sem2/giphy.gif");
    // Icon gameOverGif = new
    // ImageIcon("src/main/java/no/uib/inf101/sem2/giphy.gif");
    // JLabel gifLabel = new JLabel(gameOverGif);
    // gifLabel.setBounds(0, 0, 400, 400 );

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
