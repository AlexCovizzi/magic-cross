package com.duast.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.duast.game.stages.GameStage;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 9/25/16.
 */

public class Squares{
    private static final int SHUFFLE_ITERS = 20;
    private static final int[][] MATRIX = { {0, 1, 0},
                                            {2, 3, 4},
                                            {0, 5, 0}  };

    private SquareArray squares;
    private GameStage game;

    public Squares(GameStage game) {
        this.game = game;
        initSquares();
    }

    public void initSquares() {
        squares = new SquareArray();
        //every number >0 in MATRIX represent a section of 9 squares with the same color
        for(int i=0; i<MATRIX.length; i++) {
            for(int j=0; j<MATRIX[i].length; j++) {
                if(MATRIX[i][j] > 0) {
                    for(int k=0; k<C.SS; k++) {
                        for(int l=0; l<C.SS; l++) {
                            Square square = new Square();
                            square.setOrigin(Align.center);
                            int x = i*C.SS+k;
                            int y = j*C.SS+l;
                            squares.set(x, y, square);
                            switch(MATRIX[i][j]) {
                                case 1: square.setColor(C.RED); break;
                                case 2: square.setColor(C.VIOLET); break;
                                case 3: square.setColor(C.BLUE); break;
                                case 4: square.setColor(C.GREEN); break;
                                case 5: square.setColor(C.YELLOW); break;
                            }
                            game.addActor(square);
                            /*square.addAction(Actions.parallel(
                                    Actions.sequence(Actions.scaleTo(2,2), Actions.scaleTo(1, 1, 0.5f)),
                                    Actions.sequence(Actions.alpha(0), Actions.alpha(1, 0.5f))));*/
                        }
                    }
                }
            }
        }
    }

    public boolean move(Coordinates coords, int m, int l) {
        return squares.move(coords, m, l);
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

    public void shuffle() {
        for(int i=0;i<SHUFFLE_ITERS;i++) {
            int line = randomWithRange(0, 1); //row=0, column=1
            int line_n = randomWithRange(C.SS, C.SS*2-1);
            int move = randomWithRange(1, C.SS*3-1);

            for(int j=0; j<move; j++) {
                move(new Coordinates(line_n, line_n), 1, line);
            }
        }
    }

    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    /* getters */
    public Square get(int x, int y) {
        return squares.get(x, y);
    }

}
