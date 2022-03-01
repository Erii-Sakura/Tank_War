package com.neuedu.pojo;

import com.neuedu.util.ImgUtil;

import java.awt.*;

public class Tank {
    public static int width = 60;
    public static int height = 60;
    public int speed;
    public int x, y;
    Image img;
    Direction direction = Direction.U;
    boolean live = true;

    public Tank() {
    }

    public Tank(Image img, int x, int y, int speed) {
        this(img, x, y);
        this.speed = speed;
    }

    public Tank(Image img, int x, int y) {
        this(img);
        this.x = x;
        this.y = y;
    }

    public Tank(Image img) {
        this.img = ImgUtil.getPlayerImage(this.direction);
        if (this.img != null) {
            this.width = 60;
            this.height = 60;
        }
    }

    public void drawSelf(Graphics g) {
        g.drawImage(this.img, this.x, this.y, this.width, this.height, null);
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
    public void outOfBound(){}
}
