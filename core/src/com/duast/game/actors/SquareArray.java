package com.duast.game.actors;

import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 10/7/16.
 */

public class SquareArray {
    private Square[][] arr;

    public SquareArray(int squares_in_sector) {
        arr = new Square[squares_in_sector*3][squares_in_sector*3];
    }

    public void set(int x, int y, Square square) {
        arr[x][y] = square;
        arr[x][y].setCoordinates(x, y);
    }

    public Square get(int x, int y) {
        return arr[x][y];
    }
}
