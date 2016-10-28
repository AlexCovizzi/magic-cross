package com.duast.game.utils;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by alex on 10/23/16.
 */

public class Theme {

    private boolean dark;

    public Theme() {
        dark = false;
    }

    public Theme(boolean dark) {
        this.dark = dark;
    }

    /* setters */
    public void setDark(boolean dark) {
        this.dark = dark;
    }

    /* getters */
    public boolean isDark() {
        return dark;
    }

    public Color getBackgroundColor() {
        Color color;
        if(dark) color = C.DARK;
        else color = C.WHITE;
        return color;
    }

    public Color getHighlightColor() {
        Color color;
        if(dark) color = C.WHITE;
        else color = C.DARK;
        return color;
    }
}
