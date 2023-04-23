package no.uib.inf101.sem2.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.SnakeModel;

/**
 * The SnakeView class is responsible for drawing the game.
 * It is a JPanel that draws the game, and it is also a listener to the model.
 */
public class SnakeView extends JPanel {
    private ViewableSnakeModel model;
    private GameState GameState;
    final int screenWidth = 1000;
    final int screenHeight = 1000;

    /**
     * Creates a new SnakeView.
     * 
     * @param model The model to view.
     */
    public SnakeView(SnakeModel model) {
        this.model = model;
        new DefaultColorTheme();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    /**
     * draws the cells on the game board.
     * 
     * @param g2d     the graphics object to draw on.
     * @param cells   cells the iterable of cells to draw.
     * @param cellPos the cell position to pixel converter.
     * @param colors  the color theme.
     */
    public static void drawcells(Graphics2D g2d, Iterable<GridCell<Character>> cells,
            CellPositionToPixelConverter cellPos, ColorTheme colors) {
        for (GridCell<Character> cell : cells) {
            Rectangle2D rect = cellPos.getBoundsForCell(cell.pos());
            g2d.setColor(colors.getCellColor(cell.value()));
            g2d.fill(rect);
        }
    }

    /**
     * Draws the game.
     * 
     * @param g2d the graphics object to draw on.
     */
    public void drawGame(Graphics2D g2d) {
        double margin = 2;
        double x = margin;
        double y = margin;
        double width = this.getWidth() - 2 * margin;
        double height = this.getHeight() - 2 * margin;
        ColorTheme colorsT = new DefaultColorTheme();
        g2d.setColor(colorsT.getFrameColor());
        g2d.fill(new Rectangle2D.Double(x, y, width, height));
        CellPositionToPixelConverter cellPos = new CellPositionToPixelConverter(
                new Rectangle2D.Double(x, y, width, height), model.getDimension(), margin);
        drawcells(g2d, model.getTilesOnBoard(), cellPos, colorsT);
        g2d.setColor(Color.RED);
        g2d.setFont(new FontUIResource("Arial", FontUIResource.BOLD, 20));
        g2d.drawString("Apple eaten: " + model.getScore(), 10, 20);
        g2d.drawString("Level: " + model.getLevel(), 10, 40);
        if (model.getBoombEaten() == 0) {
            g2d.drawString("You have 3 lives left", 10, 60);
        } else if (model.getBoombEaten() == 1) {
            g2d.drawString("You have 2 lives left", 10, 60);
        } else if (model.getBoombEaten() == 2) {
            g2d.drawString("You have 1 lives left", 10, 60);
        } else if (model.getBoombEaten() == 3) {
            g2d.drawString("You have 0 lives left", 10, 60);
        }

    }

    /**
     * Draws the game over screen.
     * 
     * @param g2d the graphics object to draw on.
     */
    public void drawGameOver(Graphics2D g2d) {
        ColorTheme colorsT = new DefaultColorTheme();
        g2d.setColor(colorsT.getFrameColor());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.WHITE);
        g2d.setFont(new FontUIResource("Arial", FontUIResource.BOLD, 50));
        g2d.drawString("Game Over", this.getWidth() / 2 - 100, this.getHeight() / 2);
        g2d.setFont(new FontUIResource("Arial", FontUIResource.BOLD, 40));
        g2d.drawString("Press R to restart", this.getWidth() / 2 - 150, this.getHeight() / 2 + 100);
        g2d.drawString("Your score: " + model.getScore(), this.getWidth() / 2 - 150, this.getHeight() / 2 + 200);

    }

    /**
     * Draws the start screen.
     * 
     * @param g2d the graphics object to draw on.
     */
    public void drawStartScreen(Graphics2D g2d) {
        ColorTheme colorsT = new DefaultColorTheme();
        g2d.setColor(colorsT.getFrameColor());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.WHITE);
        g2d.setFont(new FontUIResource("Arial", FontUIResource.BOLD, 50));
        // seet border around the text with a stroke

        g2d.setStroke(new BasicStroke(5));

        g2d.drawString("Snake", this.getWidth() / 2 - 100, this.getHeight() / 2);
        g2d.drawString("Press space to start", this.getWidth() / 2 - 250, this.getHeight() / 2 + 100);
        g2d.drawString("Press ESC to pause", this.getWidth() / 2 - 250, this.getHeight() / 2 + 200);
    }

    /**
     * Draws the pause screen.
     * 
     * @param g2d the graphics object to draw on.
     */
    public void drawPaused(Graphics2D g2d) {
        ColorTheme colorsT = new DefaultColorTheme();
        g2d.setColor(colorsT.getFrameColor());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.WHITE);
        g2d.setFont(new FontUIResource("Arial", FontUIResource.BOLD, 50));
        g2d.drawString("Paused", this.getWidth() / 2 - 100, this.getHeight() / 2);
        g2d.drawString("Press ESC to continue", this.getWidth() / 2 - 250, this.getHeight() / 2 + 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            drawGame(g2);
        } else if (model.getGameState() == GameState.GAME_OVER) {
            System.out.println("game over");
            drawGameOver(g2);
        } else if (model.getGameState() == GameState.WELCOME) {
            drawStartScreen(g2);
        } else if (model.getGameState() == GameState.PAUSED) {
            drawPaused(g2);
        }
    }

}
