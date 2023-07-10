package finalproject;


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;


public class Board extends JPanel {

    private Timer timer;
    private Ball ball;
    private Paddle paddle;
    private Block[] blocks;
    private boolean inGame = true;
    private String msg = "Game Over";


    public Board() {
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(FinalVars.WIDTH, FinalVars.HEIGHT));
        gameInit();
    }

    private void gameInit() {
        blocks = new Block[FinalVars.NUM_OF_BLOCKS];

        ball = new Ball();
        paddle = new Paddle();

        int k = 0;

        for (int c1 = 0; c1 < 5; c1++) {
            for (int c2 = 0; c2 < 6; c2++) {
                blocks[k] = new Block(c2 * 40 + 30, c1 * 10 + 50);
                k++;
            }
        }

        timer = new Timer(FinalVars.PERIOD, new GameCycle());
        timer.start();
    }
    
    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private void doGameCycle() {
        ball.move();
        paddle.move();
        checkImpact();
        repaint();
    }

    private void stopGame() {
        inGame = false;
        timer.stop();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        if (inGame) {
            drawObjects(g2d);
        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }
    
    private void drawObjects(Graphics2D g2d) {
        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int c3 = 0; c3 < FinalVars.NUM_OF_BLOCKS; c3++) {
            if (!blocks[c3].isBroke()) {
                g2d.drawImage(blocks[c3].getImage(), blocks[c3].getX(),
                        blocks[c3].getY(), blocks[c3].getImageWidth(),
                        blocks[c3].getImageHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {
        var font = new Font("Times New Roman", Font.BOLD, 20);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(msg,
                (FinalVars.WIDTH - fontMetrics.stringWidth(msg)) / 2,
                FinalVars.WIDTH / 2);
    }

    private void checkImpact() {
        if (ball.getRect().getMaxY() > FinalVars.BOTTOM) {
            stopGame();
        }

        for (int c4 = 0, blocksbroken = 0; c4 < FinalVars.NUM_OF_BLOCKS; c4++) {
            if (blocks[c4].isBroke()) {
                blocksbroken++;
            }

            if (blocksbroken == FinalVars.NUM_OF_BLOCKS) {
                msg = "Victory";
                stopGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            if (((int) ball.getRect().getMinX()) < (((int) paddle.getRect().getMinX()) + 8)) {
                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (((int) ball.getRect().getMinX()) >= (((int) paddle.getRect().getMinX()) + 8) && ((int) ball.getRect().getMinX()) < (((int) paddle.getRect().getMinX()) + 16)) {
                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (((int) ball.getRect().getMinX()) >= (((int) paddle.getRect().getMinX()) + 16) && ((int) ball.getRect().getMinX()) < (((int) paddle.getRect().getMinX()) + 24)) {
                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (((int) ball.getRect().getMinX()) >= (((int) paddle.getRect().getMinX()) + 24) && ((int) ball.getRect().getMinX()) < (((int) paddle.getRect().getMinX()) + 32)) {
                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (((int) ball.getRect().getMinX()) > (((int) paddle.getRect().getMinX()) + 32)) {
                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }

        for (int c5 = 0; c5 < FinalVars.NUM_OF_BLOCKS; c5++) {

            if ((ball.getRect()).intersects(blocks[c5].getRect())) {

                if (!blocks[c5].isBroke()) {
                    if (blocks[c5].getRect().contains(new Point(((int) ball.getRect().getMinX()) + ((int) ball.getRect().getWidth()) + 1, ((int) ball.getRect().getMinY())))) {
                        ball.setXDir(-1);
                    } else if (blocks[c5].getRect().contains(new Point(((int) ball.getRect().getMinX()) - 1, ((int) ball.getRect().getMinY())))) {
                        ball.setXDir(1);
                    }
                    if (blocks[c5].getRect().contains(new Point(((int) ball.getRect().getMinX()), ((int) ball.getRect().getMinY()) - 1))) {
                        ball.setYDir(1);
                    } else if (blocks[c5].getRect().contains(new Point(((int) ball.getRect().getMinX()), ((int) ball.getRect().getMinY()) + ((int) ball.getRect().getHeight()) + 1))) {
                        ball.setYDir(-1);
                    }

                    blocks[c5].setBroke(true);
                }
            }
        }
    }
}
