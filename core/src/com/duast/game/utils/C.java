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

    public static final Color BACKGROUND = new Color(0xe1e1e1ff); //white
    public static final Color RED = Color.RED;
    public static final Color GREEN = Color.GREEN;
    public static final Color BLUE = Color.BLUE;
    public static final Color YELLOW = Color.YELLOW;
    public static final Color ORANGE = Color.ORANGE;
    public static final Color VIOLET = Color.VIOLET;

    public static final int PAD_LR = C.WIDTH/40; //distance left and right
    public static final int PAD_DOWN = C.WIDTH/10; //distance down
    public static final int DIST = C.WIDTH/400; //distance between squares

}
