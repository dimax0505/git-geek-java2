package ru.geekbrains.maksimov.geek;

import ru.geekbrains.maksimov.lesson1.Background;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    private MainWindowM mainWindowM;
    private long lastFrameTime;

    GameCanvas(MainWindowM mainWindowM) {
        this.mainWindowM = mainWindowM;
        lastFrameTime = System.nanoTime();
        setBackground(Color.BLUE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        System.out.println(Math.sin(deltaTime*1000f));

        // friday magic
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainWindowM.onDrawFrame(this, g, deltaTime);

        repaint();
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }
}
