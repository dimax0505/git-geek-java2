package ru.geekbrains.maksimov.geek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindowM extends JFrame {
    private static final int POS_X = 600;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindowM();
            }
        });
    }

    MainWindowM() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X,POS_Y,WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Пузырики");

        GameCanvas gameCanvas = new GameCanvas(this);
        add(gameCanvas, BorderLayout.CENTER);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON3) {
                    sprites[currentBalls]=new OneBall();
                    currentBalls++;
                    if (currentBalls==sprites.length-5){
                        Sprites[] spritestemp = new Sprites[currentBalls*2];
                        for (int i=0; i<sprites.length; i++) {
                            spritestemp[i]=sprites[i];
                        }
                        sprites = spritestemp;
                    }
                }
                if (e.getButton()==MouseEvent.BUTTON1) {
                    if (currentBalls>0) {
                        sprites[currentBalls] = null;
                        currentBalls--;
                    }
                }

            }
        });
        repaint();
        initGame();
        setVisible(true);
    }
    int currentBalls = 5;
    int maxBalls = 20;
    public Sprites[] sprites = new Sprites[maxBalls];
    Backgrounds backgrounds;

    private void initGame() {
        for (int i = 0; i < currentBalls; i++) {
            sprites[i] = new OneBall();
        }
        backgrounds = new Backgrounds();
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        backgrounds.update();
        for (int i = 0; i < currentBalls; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        backgrounds.render(canvas,g);
        for (int i = 0; i < currentBalls; i++) {
            sprites[i].render(canvas, g);

        }
    }
}
