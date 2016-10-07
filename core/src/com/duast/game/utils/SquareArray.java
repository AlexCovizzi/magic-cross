package com.duast.game.utils;

import com.duast.game.entities.Square;

/**
 * Created by alex on 10/7/16.
 */

public class SquareArray {
    public static final int SIZE = C.SS*3; // SIZE X SIZE
    public static final int ROW = 0;
    public static final int COLUMN = 1;

    private Square[][] arr;

    public SquareArray() {
        arr = new Square[SIZE][SIZE];
    }

    public void set(int x, int y, Square square) {
        arr[x][y] = square;
        arr[x][y].setCoordinates(x, y);
    }

    public Square get(int x, int y) {
        return arr[x][y];
    }

    /* i = row/column number >2 , <6
     * m = how much i want to move the row/column
     * l = 0 if ROW, 1 if COLUMN
     */
    public boolean move(Coordinates coords, int m, int l) {
        int i = -1;
        if(l==ROW) i = coords.y;
        else if(l==COLUMN) i = coords.x;

        if(i>C.SS-1 && i<C.SS*2 && m!=0) {
            int x=0, y=0;
            int k=0;
            if(m==1) k=arr.length-1;

            if(l==ROW) {
                x = k;
                y = i;
            }
            else if(l==COLUMN) {
                x = i;
                y = k;
            }

            Square aux = get(x, y); //store in a variable the first square of the row;
            for(int j=0; j<arr.length; j++) {
                int h=k-j*m;
                int pos = h-m;
                if(pos<0) pos=SIZE-1; if(pos>SIZE-1) pos=0;

                if(l==ROW) x = h;
                else if(l==COLUMN) y = h;

                if(j==arr.length-1) {
                    set(x, y, aux);
                }else{
                    if(l==ROW) set(x, y, arr[pos][y]);
                    else if(l==COLUMN) set(x, y, arr[x][pos]);
                }
            }
            return true;
        }else{
            return false;
        }
    }
}
