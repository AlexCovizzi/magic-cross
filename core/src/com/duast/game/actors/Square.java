package com.duast.game.actors;

import com.duast.game.utils.C;

/**
 * Created by alex on 10/8/16.
 */

public class Square extends MyActor {

    public Square() {

    }

    public void setCoordinates(int x, int y) {
        setPosition(C.PAD_LR+x*(getWidth()+C.DIST), C.PAD_DOWN+y*(getHeight()+C.DIST));
    }
}
