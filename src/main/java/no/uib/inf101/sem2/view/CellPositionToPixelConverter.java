package no.uib.inf101.sem2.view;

import java.awt.geom.Rectangle2D;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;

/**
 * A CellPositionToPixelConverter converts cell positions to pixel positions.
 * 
 * @param margin The margin around the grid.
 * @param gd The grid dimension.
 * @param box The bounding box of the grid.
 *  
 */
public class CellPositionToPixelConverter {
  private double margin; 
  private GridDimension gd; 
  private Rectangle2D box; 

  /**
   * Creates a new CellPositionToPixelConverter.
   * 
   * @param margin The margin around the grid.
   * @param gd The grid dimension.
   * @param box The bounding box of the grid.
   */

  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.gd = gd;
    this.box = box;
    this.margin = margin;
  }

  /**
   * Returns the bounding box of the cell at the specified position.
   * 
   * @param pos The position of the cell.
   * @return The bounding box of the cell at the specified position.
   */

  public Rectangle2D getBoundsForCell(CellPosition pos) {
    double w = (box.getWidth() - margin - gd.cols() * margin) / gd.cols();
    double h = (box.getHeight() - margin - gd.rows() * margin) / gd.rows();
    double x = box.getX() + margin + pos.col() * (w + margin);
    double y = box.getY() + margin + pos.row() * (h + margin);
    return new Rectangle2D.Double(x, y, w, h);
  }
}

