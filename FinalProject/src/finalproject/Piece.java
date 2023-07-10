package finalproject;

import java.awt.Image;
import java.awt.Rectangle;

public class Piece {
    int imageWidth;
    int imageHeight;
    int x;
    int y;
    Image image;

    int getImageWidth() {
        return imageWidth;
    }

    int getImageHeight() {
        return imageHeight;
    }
    
    void getImageDimensions() {
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }
    
    protected void setX(int x) {
        this.x = x;
    }

    int getX() {
        return x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    int getY() {
        return y;
    }

    Image getImage() {
        return image;
    }

    Rectangle getRect() {
        return new Rectangle(x, y,
                image.getWidth(null), image.getHeight(null));
    }

}