package util;

import convexhull.Terminal;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PlaneDrawer extends JPanel implements Runnable {

    public static final int originalTileSize = 20;
    public static int scale = 1;
    public static final int tileSize = originalTileSize * scale;
    public final static int maxScreenCol = 22;
    public final static int maxScreenRow = 28;
    private final static int screenWidth = maxScreenCol * tileSize;
    private final static int screenHeight = maxScreenRow * tileSize;

    private final int fps = 144;

    private Thread gameThread;
    private Terminal terminal;

    public PlaneDrawer() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.terminal = new Terminal();
        terminal.run();
    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.run();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                // Since sleep thinks in milliseconds, we must divide our remaining time by some number to get a smaller number in miliseconds
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void paintComponent(Graphics g) {
        // Encouraged behaviour to call method in superclass
        super.paintComponent(g);

        // Cast our given graphics to 2D for class specific functions
        Graphics2D g2 = (Graphics2D) g;

        Stroke stroke = new BasicStroke(1f);
        g2.setStroke(stroke);
        /*
        g2.drawRect(50, 0, 1, 1000);
        g2.drawRect(100, 0, 1, 1000);
        g2.drawRect(150, 0, 1, 1000);
        g2.drawRect(200, 0, 1, 1000);
        g2.drawRect(250, 0, 1, 1000);
        g2.drawRect(300, 0, 1, 1000);

        g2.drawRect(0, 50, 1000, 1);
        g2.drawRect(0, 100, 1000, 1);
        g2.drawRect(0, 150, 1000, 1);
        g2.drawRect(0, 200, 1000, 1);
        g2.drawRect(0, 250, 1000, 1);
        g2.drawRect(0, 300, 1000, 1);
        */


        terminal.paint(g2);

        g2.dispose();

    }

}
