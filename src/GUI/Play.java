import java.awt.Color;
import javax.swing.*;
import java.awt.GridLayout;

public class Play {
    // Other methods and variables...

    private void createBoardPanel(int size) {
        int gap = size == 3 ? 14 : 10;
        setLayout(new GridLayout(size, size, gap, gap));

        int boardPx = (size == 3) ? 460 : 760;
        // Other panel setup code...

        int fontSize = (size == 3) ? 68 : 32;
        Color tileBase = (size == 3) ? tileBg : brighten(tileBg, 18);
        // Use tileBase when constructing new RoundedButton
        // Other code to create buttons...
    }

    private static Color brighten(Color base, int delta) {
        int r = Math.min(255, base.getRed() + delta);
        int g = Math.min(255, base.getGreen() + delta);
        int b = Math.min(255, base.getBlue() + delta);
        return new Color(r, g, b);
    }

    // Other methods and variables...
}