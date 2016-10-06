package com.duast.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.duast.game.utils.C;

import java.util.ArrayList;

/**
 * Created by alex on 9/25/16.
 */

public class Squares{
    public static final int ROW = 0;
    public static final int COLUMN = 1;
    private static final int[][] MATRIX = { {0, 1, 0},
                                            {2, 3, 4},
                                            {0, 5, 0}  };

    private Square[][] squares;
    public Squares() {
        squares = new Square[9][9];
        initSquares();
    }

    public void initSquares() {
        //every number >0 in MATRIX represent a section of 9 squares with the same color
        for(int i=0; i<MATRIX.length; i++) {
            for(int j=0; j<MATRIX[i].length; j++) {
                if(MATRIX[i][j] > 0) {
                    for(int k=0; k<3; k++) {
                        for(int l=0; l<3; l++) {
                            Square square = new Square();
                            int x = i*3+k;
                            int y = j*3+l;
                            square.setCoordinates(x, y);
                            squares[x][y] = square;
                            switch(MATRIX[i][j]) {
                                case 1: square.setColor(C.RED); break;
                                case 2: square.setColor(C.VIOLET); break;
                                case 3: square.setColor(C.BLUE); break;
                                case 4: square.setColor(C.GREEN); break;
                                case 5: square.setColor(C.ORANGE); break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void update(float delta) {
        for(int i=0; i<squares.length; i++) {
            for(int j=0; j<squares[i].length; j++) {
                if(squares[i][j] != null) squares[i][j].update(delta);
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for(int i=0; i<squares.length; i++) {
            for(int j=0; j<squares[i].length; j++) {
                if(squares[i][j] != null) squares[i][j].draw(batch);
            }
        }
    }

    public void dispose() {
        for(int i=0; i<squares.length; i++) {
            for(int j=0; j<squares[i].length; j++) {
                if(squares[i][j] != null) squares[i][j].dispose();
            }
        }
    }

    /* i = row/column number >2 , <6
     * l = 0 if ROW, 1 if COLUMN
     * m = how much i want to move the row/column
     */
    public void move(int i, int l, int m) {
        if(i>2 && i<6) {
            if(l == ROW) {
                int k=0;
                if(m==1) k=squares.length-1;
                Square aux = squares[k][i]; //store in a variable the first square of the row;
                for(int j=0; j<squares.length; j++) {
                    int h=k-j*m;
                    int pos = h-m;
                    if(pos<0) pos=8; if(pos>8) pos=0;

                    if(j==squares.length-1) {
                        squares[h][i] = aux;
                        squares[h][i].setCoordinates(h, i);
                    }else{
                        squares[h][i] = squares[pos][i];
                        squares[h][i].setCoordinates(h, i);
                    }
                }
            }else if(l == COLUMN) {
                int k=0;
                if(m==1) k=squares.length-1;
                Square aux = squares[i][k]; //store in a variable the first square of the row;
                for(int j=0; j<squares[i].length; j++) {
                    int h=k-j*m;
                    int pos = h-m;
                    if(pos<0) pos=8; if(pos>8) pos=0;

                    if(j==squares.length-1) {
                        squares[i][h] = aux;
                        squares[i][h].setCoordinates(i, h);
                    }else{
                        squares[i][h] = squares[i][pos];
                        squares[i][h].setCoordinates(i, h);
                    }
                }
            }
        }
    }

    /* getters */
    public Square get(int x, int y) {
        return squares[x][y];
    }

}
