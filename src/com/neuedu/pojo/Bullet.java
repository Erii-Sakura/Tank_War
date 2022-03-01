package com.neuedu.pojo;

import com.neuedu.util.FrameUtil;
import com.neuedu.util.GameUtil;
import com.neuedu.util.ImgUtil;

import java.awt.*;

public class Bullet {
    private Image image;
    public int x;
    public int y;
    private int speed;
    private Direction dir;
    private int width;
    private int height;
    public boolean live = true;

    public Bullet(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = 17;
        if (this.dir == Direction.U) {
            this.image = ImgUtil.getBulletImage(this.dir);
        } else if (this.dir == Direction.D) {
            this.image = ImgUtil.getBulletImage(this.dir);
        } else if (this.dir == Direction.L) {
            this.image = ImgUtil.getBulletImage(this.dir);
        } else if (this.dir == Direction.R) {
            this.image = ImgUtil.getBulletImage(this.dir);
        }
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void draw(Graphics g) {
        if (!live) {
            FrameUtil.bullets.remove(this);
        }
        g.drawImage(this.image, x, y, null);
        move();
    }

    public void move() {
        if (this.dir == Direction.R) {
            x += speed;
        } else if (this.dir == Direction.L) {
            x -= speed;
        } else if (this.dir == Direction.U) {
            y -= speed;
        } else if (this.dir == Direction.D) {
            y += speed;
        }
        outOfBound();
    }
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
    public void outOfBound() {
        if (x < 0) {
            live = false;
        }
        if (x > GameUtil.FRAME_WIDTH - width) {
            live = false;
        }
        if (y < 0) {
            live = false;
        }
        if (y > GameUtil.FRAME_HEIGHT - height) {
            live = false;
        }
    }
}
