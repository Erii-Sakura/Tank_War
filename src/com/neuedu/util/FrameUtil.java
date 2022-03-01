package com.neuedu.util;

import com.neuedu.pojo.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FrameUtil extends Frame {
    Explode explode;
    Image heroImg = GameUtil.getImage("images/hero/p1tankU.gif");
    Image bg = GameUtil.getImage("images/bg.png");
    Hero hero = new Hero(heroImg, 480, 700, 10);
    //    Enemy[] enemies = new Enemy[7];
    public static List<Enemy> enemies1 = new LinkedList<>();
    public static List<Bullet> bullets = new LinkedList<>();

    public void launchFrame() {
        GameUtil.getMusic("D:\\BaiZhan\\Tank_War\\src\\music\\start.wav");
        this.setBackground(Color.black);
        this.setTitle("坦克大战");//设置窗口标题
        this.setVisible(true);//设置窗口可见
        this.setSize(GameUtil.FRAME_WIDTH, GameUtil.FRAME_HEIGHT);//设置窗口大小
        this.setLocation(300, 100);//设置窗口位置
        //添加窗口事件  关闭窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//退出窗口
            }
        });
        new PaintThread().start();//启动窗口绘制线程
        this.addKeyListener(new KeyMonitor());//启动键盘监听
        for (int i = 0; i < 7; i++) {
            enemies1.add(new Enemy());
        }
    }

    @Override
    public void paint(Graphics g) {
        Iterator<Enemy> iterator = enemies1.iterator();
        g.drawImage(bg, 0, 0, null);
        if (hero.live){
            hero.drawSelf(g);
        }
        for (Enemy e : enemies1) {
            e.draw(g);
        }
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.draw(g);
        }
        for (Bullet b : bullets) {
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                if (enemy.getRectangle().intersects(b.getRectangle())) {
                    iterator.remove();
                    b.live = false;
                    if (explode == null) {
                        explode = new Explode(b.x, b.y);
                    }
                    explode.draw(g);
                }
            }
        }
        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            if (enemy.getRectangle().intersects(hero.getRectangle())) {
                iterator.remove();
                hero.live = false;
                if (explode == null) {
                    explode = new Explode(hero.x, hero.y);
                }
                explode.draw(g);
            }
        }
        if (enemies1.size() == 0) {
            printInfo(g, "游戏胜利！！！", 70, 500, 500, Color.WHITE);
        }
        if (hero.live==false){
            printInfo(g, "游戏失败！！！", 70, 300, 400, Color.WHITE);
        }
    }

    private Image offScreenImage = null;

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GameUtil.FRAME_WIDTH, GameUtil.FRAME_HEIGHT);//这里是游戏窗口的宽和高
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    class KeyMonitor extends KeyAdapter {
        //按下
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
//            System.out.println("按下" + e.getKeyCode());
            hero.addDriection(e);
        }

        //抬起
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
//            System.out.println("抬起" + e.getKeyCode());
            hero.minusDriection(e);
        }
    }

    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printInfo(Graphics g, String str, int size, int x, int y, Color color) {
        Font oldFont = g.getFont();
        Color oldColor = g.getColor();
        Font f = new Font("宋体", Font.BOLD, size);
        g.setFont(f);
        g.setColor(color);
        g.drawString(str, x, y);
        g.setFont(oldFont);
        g.setColor(oldColor);
    }
}
