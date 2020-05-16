package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class Game extends Canvas implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 240;
    public static final int HEIGHT = 200;
    public static final int SCALE = 3;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public BufferStrategy bs;
    Graphics g;

    public static Sprite PLAYER;
    protected final int PLAYER_WIDTH = 40;
    protected final int PLAYER_HEIGHT = 10;
    protected final int PLAYER_X_INITIAL_POS = 100;
    protected final int PLAYER_Y_INITIAL_POS = HEIGHT - PLAYER_HEIGHT;
    protected final int PLAYER_MOVE_RIGHT = 1;
    protected final int PLAYER_MOVE_LEFT = -1;

    public static Sprite ENEMY;
    protected final int ENEMY_WIDTH = 40;
    protected final int ENEMY_HEIGHT = 10;
    protected final int ENEMY_X_INITIAL_POS = 100;
    protected final int ENEMY_Y_INITIAL_POS = 0;
    protected final int ENEMY_MOVE_RIGHT = 1;
    protected final int ENEMY_MOVE_LEFT = -1;

    public static Sprite BALL;
    protected final int BALL_WIDTH = 4;
    protected final int BALL_HEIGHT = 4;
    protected final int BALL_X_INITIAL_POS = 100;
    protected final int BALL_Y_INITIAL_POS = HEIGHT / 2 - 1;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.addKeyListener(this);
        PLAYER = new Player(PLAYER_X_INITIAL_POS, PLAYER_Y_INITIAL_POS, PLAYER_WIDTH, PLAYER_HEIGHT);
        ENEMY = new Enemy(ENEMY_X_INITIAL_POS, ENEMY_Y_INITIAL_POS, ENEMY_WIDTH, ENEMY_HEIGHT);
        BALL = new Ball(BALL_X_INITIAL_POS, BALL_Y_INITIAL_POS, BALL_WIDTH, BALL_HEIGHT);
    }

    public static void main(String[] args) {
        final Game game = new Game();
        final JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(game).start();
    }

    public void update() {
        PLAYER.update();
        ENEMY.update();
        BALL.update();
    }

    public void draw() {
        bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            bs = this.getBufferStrategy();
        }

        g = layer.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        PLAYER.draw(g);
        ENEMY.draw(g);
        BALL.draw(g);
        drawLayer();
    }

    @Override
    public void run() {
        while (true) {
            update();
            draw();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            PLAYER.move(PLAYER_MOVE_RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            PLAYER.move(PLAYER_MOVE_LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private void drawLayer() {
        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        bs.show();
    }
}
