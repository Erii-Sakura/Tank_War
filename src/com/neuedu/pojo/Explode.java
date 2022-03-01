package com.neuedu.pojo;

import com.neuedu.util.GameUtil;

import java.awt.*;

public class Explode {
    int x, y;
    static Image[] imgs = new Image[8];

    static {
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = GameUtil.getImage("images/explode/blast" + i + ".gif");
            imgs[i].getWidth(null);//懒加载
        }
    }

    int count;
    boolean live = true;

    public void draw(Graphics g) {
        if (!live) {
            return;
        }
        if (count < 8) {
            GameUtil.getMusic("D:\\BaiZhan\\Tank_War\\src\\music\\bomb.wav");
            g.drawImage(imgs[count], x, y, null);
            count++;
        } else {
            live = false;
        }

    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
