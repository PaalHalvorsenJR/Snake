package no.uib.inf101.sem2.view;

import java.awt.Color;

/**
 * The ColorTheme interface defines the methods that a class must implement to be a ColorTheme.
 */
public interface ColorTheme {
    
    /**
     * Returns the color of the specified cell.
     * @param c
     * @return
     */
    public Color getCellColor(char c);

    /**
     * Returns the background color.
     * @return
     */
    public Color getBackgroundColor();

    /**
     * Returns the frame color.
     * @return
     */
    public Color getFrameColor();

    /**
     * Returns the color of the game over text.
     * @return
     */
    public Color gameOverColor();
   






}
    


