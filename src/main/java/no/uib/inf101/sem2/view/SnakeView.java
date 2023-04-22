package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.SnakeModel;

import javax.swing.Icon;
import javax.swing.JLabel;

public class SnakeView extends JPanel {
    private ViewableSnakeModel model;
    private GameState GameState;
    private Icon gifIcon;
    final int screenWidth = 1000;
    final int screenHeight = 1000;

    private ColorTheme colorBackrond;

    public SnakeView(SnakeModel model) {
        this.model = model;
        colorBackrond = new DefaultColorTheme();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    public static void drawcells(Graphics2D g2d, Iterable<GridCell<Character>> cells,
            CellPositionToPixelConverter cellPos, ColorTheme colors) {
        for (GridCell<Character> cell : cells) {
            Rectangle2D rect = cellPos.getBoundsForCell(cell.pos());
            g2d.setColor(colors.getCellColor(cell.value()));
            g2d.fill(rect);
        }
    }

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
        g2d.drawString("Appel eaten: " + model.getScore(), 10, 20);
        g2d.drawString("Level: " + model.getLevel(), 10, 40);

    }

    public void drawGameOver(Graphics2D g2d) {
        ColorTheme colorsT = new DefaultColorTheme();
        g2d.setColor(colorsT.getFrameColor());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.WHITE);
        g2d.setFont(new FontUIResource("Arial", FontUIResource.BOLD, 50));
        g2d.drawString("Game Over", this.getWidth() / 2 - 100, this.getHeight() / 2);
        g2d.drawString("Press R to restart", this.getWidth() / 2 - 250, this.getHeight() / 2 + 100);
    }

    public void drawStartScreen(Graphics2D g2d) {
        ColorTheme colorsT = new DefaultColorTheme();
        g2d.setColor(colorsT.getFrameColor());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.WHITE);
        g2d.setFont(new FontUIResource("Arial", FontUIResource.BOLD, 50));
        g2d.drawString("Snake", this.getWidth() / 2 - 100, this.getHeight() / 2);
        g2d.drawString("Press space to start", this.getWidth() / 2 - 250, this.getHeight() / 2 + 100);
        g2d.drawString("Press ESC to pause", this.getWidth() / 2 - 250, this.getHeight() / 2 + 200);
    }

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
            // gifLabel.setVisible(false);
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
