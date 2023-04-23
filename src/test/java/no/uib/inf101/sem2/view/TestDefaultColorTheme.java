package no.uib.inf101.sem2.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Color;
import org.junit.jupiter.api.Test;

public class TestDefaultColorTheme {

    @Test
    public void sanityTestDefaultColorTheme() {
        ColorTheme colors = new DefaultColorTheme();
        assertEquals(new Color(0, 0, 0, 255), colors.getBackgroundColor());
        assertEquals(new Color(204, 204, 204, 255), colors.getFrameColor());
    }

}
