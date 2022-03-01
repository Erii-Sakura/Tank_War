package com.neuedu.pojo;

import com.neuedu.util.FrameUtil;
import com.neuedu.util.GameUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Hero extends Tank {
    private boolean shoot;
    private boolean stop, walk;
    private boolean u, d, l, r;
    public boolean live;
    Direction direction = Direction.U;

    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        if (!live) {
            return;
        }
        move();
    }

    public void move() {
        if (u) {
            y -= speed;
        } else if (d) {
            y += speed;
        } else if (l) {
            x -= speed;
        } else if (r) {
            x += speed;
        }
        outOfBound();
    }

    public void addDriection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                img = GameUtil.getImage("images/hero/p1tankU.gif");
                walk = true;
                stop = false;
                u = true;
                d = false;
                l = false;
                r = false;
                shoot = true;
                break;
            case KeyEvent.VK_DOWN:
                img = GameUtil.getImage("images/hero/p1tankD.gif");
                walk = true;
                stop = false;
                d = true;
                l = false;
                r = false;
                u = false;
                shoot = true;
                break;
            case KeyEvent.VK_LEFT:
                img = GameUtil.getImage("images/hero/p1tankL.gif");
                walk = true;
                stop = false;
                l = true;
                r = false;
                u = false;
                d = false;
                shoot = true;
                break;
            case KeyEvent.VK_RIGHT:
                img = GameUtil.getImage("images/hero/p1tankR.gif");
                walk = true;
                stop = false;
                r = true;
                u = false;
                d = false;
                l = false;
                shoot = true;
                break;
        }
    }

    public void minusDriection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction = Direction.U;
                stop = true;
                walk = false;
                u = false;
                break;
            case KeyEvent.VK_DOWN:
                direction = Direction.D;
                stop = true;
                walk = false;
                d = false;
                break;
            case KeyEvent.VK_LEFT:
                direction = Direction.L;
                stop = true;
                walk = false;
                l = false;
                break;
            case KeyEvent.VK_RIGHT:
                direction = Direction.R;
                stop = true;
                walk = false;
                r = false;
                break;
            case KeyEvent.VK_SPACE:
                shoot = false;
                shoot();
        }
    }

    @Override
    public void outOfBound() {
        if (x < 0) {
            x = 0;
        }
        if (x > GameUtil.FRAME_WIDTH - width) {
            x = GameUtil.FRAME_WIDTH - width;
        }
        if (y < 0) {
            y = 30;
        }
        if (y > GameUtil.FRAME_HEIGHT - height) {
            y = GameUtil.FRAME_HEIGHT - height;
        }
    }

    public Hero(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
        this.live = true;
    }

    public void shoot() {
        GameUtil.getMusic("D:\\BaiZhan\\Tank_War\\src\\music\\fire.wav");
        if (this.direction == Direction.U) {
            Bullet bullet = new Bullet(this.x + this.width / 2 - 7, this.y - 10, Direction.U);
            FrameUtil.bullets.add(bullet);
        } else if (this.direction == Direction.D) {
            Bullet bullet = new Bullet(this.x + this.width / 2 - 7, this.y + this.height, Direction.D);
            FrameUtil.bullets.add(bullet);
        } else if (this.direction == Direction.L) {
            Bullet bullet = new Bullet(this.x - 15, this.y + this.height / 2 - 7, Direction.L);
            FrameUtil.bullets.add(bullet);
        } else if (this.direction == Direction.R) {
            Bullet bullet = new Bullet(this.x + this.width + 5, this.y + this.height / 2 - 7, Direction.R);
            FrameUtil.bullets.add(bullet);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return super.getRectangle();
    }
}
