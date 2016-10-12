package com.duast.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by alex on 9/22/16.
 */

public class C {
    public static final int WIDTH = Gdx.graphics.getWidth();
    public static final int HEIGHT = Gdx.graphics.getHeight();

    public static final Color BACKGROUND_WHITE = new Color(0xe1e1e1ff); //white
    public static final Color BACKGROUND_DARK = new Color(0x0C090Aff); //white
    public static final Color RED = new Color(0xf44336ff);
    public static final Color GREEN = new Color(0x4caf50ff);
    public static final Color BLUE = new Color(0x2196f3ff);
    public static final Color YELLOW = new Color(0xffc107ff);
    public static final Color VIOLET = new Color(0x9c27b0ff);

    public static int SS = 2; //squares in a section;


    public static final int PAD_LR = C.WIDTH/40; //distance left and right
    public static final int PAD_DOWN = C.HEIGHT/10; //distance down
    public static final int DIST = C.WIDTH/300; //distance between squares

}
