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

        int ss = game.getNumSquaresInSector();
        squares = new SquareArray(ss);
        for(int i=0; i<MATRIX_SIZE; i++) {
            for(int j=0; j<MATRIX_SIZE; j++) {
                if(MATRIX[i][j] > 0) {
                    for(int k=0; k<ss; k++) {
                        for(int l=0; l<ss; l++) {
                            Square square = new Square();
                            int x = i*ss+k;
                            int y = j*ss+l;
                            switch(MATRIX[i][j]) {
                                case 1: square.setColor(C.RED); break;
                                case 2: square.setColor(C.VIOLET); break;
                                case 3: square.setColor(C.BLUE); break;
                                case 4: square.setColor(C.GREEN); break;
                                case 5: square.setColor(C.YELLOW); break;
                            }
                            int size = ((C.WIDTH - 2*C.PAD_LR - 2*C.DIST)/3 - (ss-1)*C.DIST)/ss;
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
        int ss = game.getNumSquaresInSector();
        int x=0, y=0;
        int k=0;
        if(dir==1) k=ss*3-1;

        if(line== ROW) {
            x = k;
            y = line_n;
        }
        if(line==COLUMN) {
            x = line_n;
            y = k;
        }

        Square aux = get(x, y); //store in a variable the first square of the row;
        for(int j=0; j<ss*3; j++) {
            int h = k - j*dir;
            int pos = h - dir;
            if(pos<0) pos=ss*3-1; if(pos>ss*3-1) pos=0;

            if(line== ROW) x = h;
            if(line==COLUMN) y = h;

            if(j==ss*3-1) {
                squares.set(x, y, aux);
            }else{
                if(line==ROW) squares.set(x, y, squares.get(pos, y));
                else if(line==COLUMN) squares.set(x, y, squares.get(x, pos));
            }
        }
    }

    public void shuffle() {
        int ss = game.getNumSquaresInSector();
        for(int i=0;i<ITERS;i++) {
            int line = randomWithRange(C.ROW, C.COLUMN); //random row or column
            int line_n = randomWithRange(ss, ss*2-1); //random line in the central sector
            int move = randomWithRange(1, ss*3-1); //random number of shifts

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
        int ss = game.getNumSquaresInSector();
        for(int i=0; i<MATRIX_SIZE; i++) {
            for(int j=0; j<MATRIX_SIZE; j++) {
                if(MATRIX[i][j] > 0) {
                    Color color = null;
                    for(int k=0; k<ss; k++) {
                        for(int l=0; l<ss; l++) {
                            int x = i*ss+k;
                            int y = j*ss+l;
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
        int ss = game.getNumSquaresInSector();
        return ((C.WIDTH - 2*C.PAD_LR - 2*C.DIST)/3 - (ss-1)*C.DIST)/ss;
    }
}
