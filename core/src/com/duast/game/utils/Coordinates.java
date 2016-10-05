package com.duast.game.utils;

/**
 * Created by alex on 9/22/16.
 */

public class Coordinates {
    public int x;
    public int y;

    public Coordinates() {
        x = 0;
        y = 0;
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* setters */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /* getters */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x+", "+y;
    }
}
