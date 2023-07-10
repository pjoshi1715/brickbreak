package finalproject;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends Piece  {

    private int movement;

    public Paddle() {        
        initPaddle();        
    }
    
    private void initPaddle() {
        loadImage();
        getImageDimensions();

        resetPaddle();
    }
    
    private void loadImage() {        
        var paddle_image = new ImageIcon("src/resources/paddle.png");
        image = paddle_image.getImage();        
    }    

    void move() {
        x += movement;

        if (x <= 0) {
            x = 0;
        }

        if (x >= FinalVars.WIDTH - imageWidth) {
            x = FinalVars.WIDTH - imageWidth;
        }
    }

    void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            movement = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            movement = 1;
        }
    }

    void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            movement = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            movement = 0;
        }
    }

    private void resetPaddle() {
        x = FinalVars.PADDLE_X;
        y = FinalVars.PADDLE_Y;
    }
}
