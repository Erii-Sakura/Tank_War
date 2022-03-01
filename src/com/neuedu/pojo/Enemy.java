package com.neuedu.pojo;

import com.neuedu.util.GameUtil;
import com.neuedu.util.ImgUtil;

import java.awt.*;
import java.util.Random;

public class Enemy extends Tank {
    public boolean live = true;
    public int x;
    public int y;
    private int height;
    private int width;
    private Direction direction;
    private int step = 0;
    private Random random = new Random();
    private Image image;

    public Enemy() {
        this.direction = RandomDirection();
        this.speed = random.nextInt(4) + 3;
        this.step = 0;
        this.x = random.nextInt(9) * 100;
        this.y = random.nextInt(2) * 100 + 100;
        this.width = Tank.width;
        this.height = Tank.height;
    }

    public void draw(Graphics g) {
        g.drawImage(this.image, x, y, null);
        move();
    }

    public Direction RandomDirection() {
        int res = random.nextInt(4);
        if (res == 0) {
            this.direction = Direction.U;
            return this.direction;
        } else if (res == 1) {
            this.direction = Direction.D;
            return this.direction;
        } else if (res == 2) {
            this.direction = Direction.L;
            return this.direction;
        } else if (res == 3) {
            this.direction = Direction.R;
            return this.direction;
        }
        return this.direction;
    }

    public void move() {
        switch (this.direction) {
            case U:
                this.image = ImgUtil.getEnemyImage(this.direction);
                y -= speed;
                step++;
                if (step > 50) {
                    RandomDirection();
                    step = 0;
                }
                break;
            case D:
                this.image = ImgUtil.getEnemyImage(this.direction);
                y += speed;
                step++;
                if (step > 50) {
                    RandomDirection();
                    step = 0;
                }
                break;
            case L:
                this.image = ImgUtil.getEnemyImage(this.direction);
                x -= speed;
                step++;
                if (step > 50) {
                    RandomDirection();
                    step = 0;
                }
                break;
            case R:
                this.image = ImgUtil.getEnemyImage(this.direction);
                x += speed;
                step++;
                if (step > 50) {
                    RandomDirection();
                    step = 0;
                }
                break;
        }
        outOfBounds();
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public void outOfBounds() {
        if (x < 10) {
            this.direction = Direction.R;
        }
        if (x > GameUtil.FRAME_WIDTH - 60) {
            this.direction = Direction.L;
        }
        if (y < 30) {
            this.direction = Direction.D;
        }
        if (y > GameUtil.FRAME_HEIGHT - 60) {
            this.direction = Direction.U;
        }
    }
}
