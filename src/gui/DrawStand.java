import javax.swing.*;
import java.awt.*;

class DrawStand extends JPanel {
//    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//    double screenWidth = screenSize.getWidth();
//    double screenHeight = screenSize.getHeight();
//    int centerX = (int) (screenWidth / 2);
//    int centerY = (int) (screenHeight / 2);
    int width;
    int height;

    public DrawStand(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine((width / 2) - (width / 8), height * 3/5, (width / 2) + (width / 8), height * 3/5);
        g.drawLine(width * 5/12, height * 3/5, width * 5/12, height / 6);
        g.drawLine(width * 5/12, height / 6, width * 6/12, height / 6);
        g.drawLine(width * 6/12, height / 6, width * 6/12, height * 5/20);
    }
}
