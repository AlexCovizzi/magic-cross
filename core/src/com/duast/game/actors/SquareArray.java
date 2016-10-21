package com.duast.game.actors;

import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 10/7/16.
 */

public class SquareArray {
    private Square[][] arr;

    public SquareArray() {
        arr = new Square[C.SS*3][C.SS*3];
    }

    public void set(int x, int y, Square square) {
        arr[x][y] = square;
        arr[x][y].setCoordinates(x, y);
    }

    public Square get(int x, int y) {
        return arr[x][y];
    }
}
