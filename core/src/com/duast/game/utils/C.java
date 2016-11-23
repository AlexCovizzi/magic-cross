package com.duast.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by alex on 9/22/16.
 */

/* Constants */

public class C {
    public static final int DEVICE_HEIGHT = Gdx.graphics.getHeight();
    public static final int DEVICE_WIDTH = Gdx.graphics.getWidth();

    public static final int HEIGHT = DEVICE_HEIGHT;
    public static final int WIDTH =  HEIGHT * 9/16;

    public static final Color WHITE = new Color(0xe1e1e1ff);
    public static final Color GRAY = new Color(0.5f, 0.5f, 0.5f, 0.8f);
    public static final Color LIGHT_GRAY = new Color(0.5f, 0.5f, 0.5f, 0.7f);
    public static final Color BLACK = new Color(0x0C090Aff);
    public static final Color TRANSPARENT_BLACK = new Color(0x0C090Ad0);
    public static final Color RED = new Color(0xf44336ff);
    public static final Color GREEN = new Color(0x4caf50ff);
    public static final Color BLUE = new Color(0x2196f3ff);
    public static final Color YELLOW = new Color(0xffc107ff);
    public static final Color VIOLET = new Color(0x9c27b0ff);

    public static final int ROW = 0;
    public static final int COLUMN = 1;
    public static final int ROW_COLUMN = 2;

    public static final int PAD_LR = WIDTH/40; //distance left and right
    public static final int PAD_DOWN = HEIGHT/6; //distance down
    public static final int DIST = WIDTH/200; //distance between squares

    public static final int LIGHT = 0;
    public static final int DARK = 1;

    public static final int EASY = 0;
    public static final int MEDIUM = 1;
    public static final int HARD = 2;
}
