package com.duast.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;
import com.duast.game.utils.SquareArray;

/**
 * Created by alex on 9/25/16.
 */

public class Squares{
    private static final int[][] MATRIX = { {0, 1, 0},
                                            {2, 3, 4},
                                            {0, 5, 0}  };

    private SquareArray squares;
    public Squares() {
        squares = new SquareArray();
        initSquares();
    }

    public void initSquares() {
        //every number >0 in MATRIX represent a section of 9 squares with the same color
        for(int i=0; i<MATRIX.length; i++) {
            for(int j=0; j<MATRIX[i].length; j++) {
                if(MATRIX[i][j] > 0) {
                    for(int k=0; k<C.SS; k++) {
                        for(int l=0; l<C.SS; l++) {
                            Square square = new Square();
                            int x = i*C.SS+k;
                            int y = j*C.SS+l;
                            squares.set(x, y, square);
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
        for(int i=0; i<SquareArray.SIZE; i++) {
            for(int j=0; j<SquareArray.SIZE; j++) {
                if(squares.get(i,j) != null) squares.get(i,j).update(delta);
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for(int i=0; i<SquareArray.SIZE; i++) {
            for(int j=0; j<SquareArray.SIZE; j++) {
                if(squares.get(i,j) != null) squares.get(i,j).draw(batch);
            }
        }
    }

    public void dispose() {
        for(int i=0; i<SquareArray.SIZE; i++) {
            for(int j=0; j<SquareArray.SIZE; j++) {
                if(squares.get(i,j) != null) squares.get(i,j).dispose();
            }
        }
    }

    public boolean move(Coordinates coords, int m, int l) {
        return squares.move(coords, m, l);
    }

    /* getters */
    public Square get(int x, int y) {
        return squares.get(x, y);
    }

    public boolean checkWin() {
        for(int i=0; i<MATRIX.length; i++) {
            for(int j=0; j<MATRIX[i].length; j++) {
                if(MATRIX[i][j] > 0) {
                    Color color = null;
                    for(int k=0; k<C.SS; k++) {
                        for(int l=0; l<C.SS; l++) {
                            int x = i*C.SS+k;
                            int y = j*C.SS+l;
                            if(color!=null) {
                                if(!color.equals(get(x,y).getColor())) return false;
                            } else {
                                color = get(x, y).getColor();
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
