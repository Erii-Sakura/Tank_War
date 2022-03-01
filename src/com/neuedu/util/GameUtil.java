package com.neuedu.util;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GameUtil {
    public static final int FRAME_WIDTH=980;
    public static final int FRAME_HEIGHT=780;
    //构造器私有  防止外部创建对象
    private GameUtil(){}
    //定义获取图片的方法
    public static Image getImage(String path){
        Image img = null;
        URL url = GameUtil.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    public static void getMusic(String musicPath){
        try {
            InputStream is = new FileInputStream(musicPath);
            AudioStream as = new AudioStream(is);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
