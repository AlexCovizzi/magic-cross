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

    private ArrayList<Square> squares;
    public Squares() {
        squares = new ArrayList<Square>(45);
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
                            square.setCoordinates(i*3+k, j*3+l);
                            squares.add(square);
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
        for(int i=0; i<squares.size(); i++) {
            squares.get(i).update(delta);
        }
    }

    public void draw(SpriteBatch batch) {
        for(int i=0; i<squares.size(); i++) {
            squares.get(i).draw(batch);
        }
    }

    public void dispose() {
        for(int i=0; i<squares.size(); i++) {
            squares.get(i).dispose();
        }
    }

    /* getters */
    public Square get(int i) {
        return squares.get(i);
    }

    public int size() {
        return squares.size();
    }

    public int[] getLine(int i, int l) {
        int[] line = new int[9];

        int c=0;
        for(int j=0; j<squares.size(); j++) {
            int coord = -1;
            if(l == ROW) coord = squares.get(j).getCoordinates().y;
            else if (l == COLUMN) coord = squares.get(j).getCoordinates().x;
            if(coord == i) {
                line[c] = j;
                c++;
            }
        }
        if(c<8) {
            for(int k=0; k<line.length; k++) line[k] = -1;
        }
        return line;
    }

}
