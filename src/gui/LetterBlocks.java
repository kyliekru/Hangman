import javax.swing.*;
import java.awt.*;

public class LetterBlocks extends JPanel {
    int width;
    int height;
    int length;
    int squareSize = 50;
    int gapSize = 10;
    int startWidth;
    int currentWidth;

    public LetterBlocks(int width, int height, int length, int iteration) {
        this.width = width;
        this.height = height;
        this.length = length;
        startWidth = (width - ((squareSize * length) + (gapSize * (length - 1)))) / 2;
        currentWidth = startWidth + ((gapSize + squareSize) * iteration);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(currentWidth, height * 3/4, squareSize, squareSize);
    }
}
