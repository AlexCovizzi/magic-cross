package com.duast.game.actors;

import com.duast.game.utils.C;

/**
 * Created by alex on 10/8/16.
 */

public class Square extends MyActor {

    public Square() {

    }

    /* setters */
    public void setCoordinates(int x, int y) {
        int crt_x = correctCoord(x);
        int crt_y = correctCoord(y);
        setPosition(C.PAD_LR+crt_x*(getWidth()+C.DIST), C.PAD_DOWN+crt_y*(getHeight()+C.DIST));
    }

    /* checks if a coordinate is out of bounds and if it is it corrects the coordinate */
    private int correctCoord(int coord) {
        if(coord < 0) coord = C.SS*3-1;
        if(coord > C.SS*3-1) coord = 0;
        return coord;
    }
}
