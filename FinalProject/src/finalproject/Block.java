package finalproject;

import javax.swing.ImageIcon;

public class Block extends Piece {

    private boolean broken;

    public Block(int x, int y) {        
        initBrick(x, y);
    }
    
    private void initBrick(int x, int y) { 
        this.x = x;
        this.y = y;
        
        broken = false;

        loadImage();
        getImageDimensions();
    }
    
    private void loadImage() {
        var brick_image = new ImageIcon("src/resources/brick.png");
        image = brick_image.getImage();        
    }

    boolean isBroke() {        
        return broken;
    }

    void setBroke(boolean val) {        
        broken = val;
    }
}