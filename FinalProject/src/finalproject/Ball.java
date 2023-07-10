package finalproject;

import javax.swing.ImageIcon;

public class Ball extends Piece {

    private int xdirection;
    private int ydirection;

    public Ball() {
        initBall();
    }

    private void loadImage() {
        var ball_image = new ImageIcon("src/resources/ball.png");
        image = ball_image.getImage();
    }
    
    private void initBall() {        
        xdirection = 1;
        ydirection = -1;

        loadImage();
        getImageDimensions();
        resetBall();
    }
    
    void setXDir(int x) {
        xdirection = x;
    }
    
    void setYDir(int y) {
        ydirection = y;
    }
    
    int getYDir() {
        return ydirection;
    }

    void move() {
        x += xdirection;
        y += ydirection;

        if (x == 0) {
            setXDir(1);
        }

        if (x == FinalVars.WIDTH - imageWidth) {
            setXDir(-1);
        }

        if (y == 0) {
            setYDir(1);
        }
    }

    private void resetBall() {
        x = FinalVars.BALL_X;
        y = FinalVars.BALL_Y;
    }
}
