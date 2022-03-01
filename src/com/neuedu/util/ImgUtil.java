package com.neuedu.util;

import com.neuedu.pojo.Direction;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImgUtil {
    public static Map<Direction, Image> player = new HashMap<>();

    public static Map<Direction, Image> enemy = new HashMap<>();
    public static Map<Direction, Image> bullet = new HashMap<>();
    static {
        player.put(Direction.U, GameUtil.getImage("images/hero/p1tankU.gif"));
        player.put(Direction.D, GameUtil.getImage("images/hero/p1tankD.gif"));
        player.put(Direction.L, GameUtil.getImage("images/hero/p1tankL.gif"));
        player.put(Direction.R, GameUtil.getImage("images/hero/p1tankR.gif"));
        enemy.put(Direction.U, GameUtil.getImage("images/enemy/enemy1U.gif"));
        enemy.put(Direction.D, GameUtil.getImage("images/enemy/enemy1D.gif"));
        enemy.put(Direction.L, GameUtil.getImage("images/enemy/enemy1L.gif"));
        enemy.put(Direction.R, GameUtil.getImage("images/enemy/enemy1R.gif"));
        bullet.put(Direction.U, GameUtil.getImage("images/tankmissile.gif"));
        bullet.put(Direction.D, GameUtil.getImage("images/tankmissile.gif"));
        bullet.put(Direction.L, GameUtil.getImage("images/tankmissile.gif"));
        bullet.put(Direction.R, GameUtil.getImage("images/tankmissile.gif"));

    }
    public  static Image getPlayerImage(Direction key){
        return player.get(key);
    }
    public  static Image getBulletImage(Direction key){
        return bullet.get(key);
    }
    public  static Image getEnemyImage(Direction key){
        return enemy.get(key);
    }
}
