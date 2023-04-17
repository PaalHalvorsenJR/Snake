package no.uib.inf101.sem2.view;

import java.awt.Color;

/**
 * The DefaultColorTheme class implements the ColorTheme interface.
 */
public class DefaultColorTheme implements ColorTheme {
    
    @Override
    public Color getCellColor(char c) {
    Color color = switch(c) {
        case 'I' -> new Color(167,235,155);
        case 'J' -> new Color (191, 134, 255);
        case 'L' -> new Color(255, 134, 138);
        case 'O' -> new Color(134,198,255);
        case 'S' -> new Color(255, 255, 134);
        case 'T' -> new Color(255, 134, 255);
        case 'Z' -> new Color(134, 255, 255);
        case 'Q' -> new Color(226, 226, 226, 255);

        // .... fyll ut dine favorittfarger
        case ' ' -> new Color(226, 226, 226, 255);
        case '-' -> gameOverColor();
        default -> throw new IllegalArgumentException(
            "No available color for '" + c + "'");
    };
    return color;
}

    @Override
    public Color getBackgroundColor() {
        return new Color(0, 0, 0, 255);
    }

    @Override
    public Color getFrameColor() {
        return new Color(204,204,204, 255);
    }

    @Override 
    public Color gameOverColor(){
        return new Color(226, 226, 226, 255);

    } 




}


