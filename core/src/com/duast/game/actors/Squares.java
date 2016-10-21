package com.duast.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.duast.game.stages.GameStage;
import com.duast.game.utils.C;

import static com.duast.game.utils.C.COLUMN;
import static com.duast.game.utils.C.ROW;

/**
 * Created by alex on 9/25/16.
 */

public class Squares{
    private static final int ITERS = 20;
    /* every number >0 in MATRIX represent a section of 9 squares with the same color */
    private static final int MATRIX_SIZE = 3;
    private static final int[][] MATRIX = { {0, 1, 0},
                                            {2, 3, 4},
                                            {0, 5, 0} };
    private GameStage game;
    private SquareArray squares;

    public Squares(GameStage game) {
        this.game = game;
        int z = 2;

        squares = new SquareArray();
        for(int i=0; i<MATRIX_SIZE; i++) {
            for(int j=0; j<MATRIX_SIZE; j++) {
                if(MATRIX[i][j] > 0) {
                    for(int k=0; k<C.SS; k++) {
                        for(int l=0; l<C.SS; l++) {
                            Square square = new Square();
                            int x = i*C.SS+k;
                            int y = j*C.SS+l;
                            switch(MATRIX[i][j]) {
                                case 1: square.setColor(C.RED); break;
                                case 2: square.setColor(C.VIOLET); break;
                                case 3: square.setColor(C.BLUE); break;
                                case 4: square.setColor(C.GREEN); break;
                                case 5: square.setColor(C.YELLOW); break;
                            }
                            int size = ((C.WIDTH - 2*C.PAD_LR - 2*C.DIST)/3 - (game.getNumSquaresInSector()-1)*C.DIST)/game.getNumSquaresInSector();
                            square.setSize(size,size);
                            squares.set(x, y, square);
                            game.addActor(square);
                        }
                    }
                }
            }
        }
    }

    /**
     * line_n -> number of the line to move (the line has to be of the central sector)
     * dir -> direction (+1 is UP or RIGHT; -1 is DOWN or LEFT)
     * line -> ROW(0) or COLUMN(1)
     */
    public void move(int line_n, int dir, int line) {
        int x=0, y=0;
        int k=0;
        if(dir==1) k=C.SS*3-1;

        if(line== ROW) {
            x = k;
            y = line_n;
        }
        if(line==COLUMN) {
            x = line_n;
            y = k;
        }

        Square aux = get(x, y); //store in a variable the first square of the row;
        for(int j=0; j<C.SS*3; j++) {
            int h = k - j*dir;
            int pos = h - dir;
            if(pos<0) pos=C.SS*3-1; if(pos>C.SS*3-1) pos=0;

            if(line== ROW) x = h;
            if(line==COLUMN) y = h;

            if(j==C.SS*3-1) {
                squares.set(x, y, aux);
            }else{
                if(line==ROW) squares.set(x, y, squares.get(pos, y));
                else if(line==COLUMN) squares.set(x, y, squares.get(x, pos));
            }
        }
    }

    public void shuffle() {
        for(int i=0;i<ITERS;i++) {
            int line = randomWithRange(C.ROW, C.COLUMN); //random row or column
            int line_n = randomWithRange(C.SS, C.SS*2-1); //random line in the central sector
            int move = randomWithRange(1, C.SS*3-1); //random number of shifts

            for(int j=0; j<move; j++) {
                move(line_n, 1, line);
            }
        }
    }

    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public boolean checkWin() {
        for(int i=0; i<MATRIX_SIZE; i++) {
            for(int j=0; j<MATRIX_SIZE; j++) {
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


    /* getters */
    public Square get(int x, int y) {
        return squares.get(x, y);
    }

    public float getSquareSize() {
        return get(C.SS, C.SS).getWidth();
    }
}
