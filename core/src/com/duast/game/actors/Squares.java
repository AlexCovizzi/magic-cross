package com.duast.game.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.duast.game.utils.C;

/**
 * Created by alex on 9/25/16.
 */

public class Squares extends Group {

    public Squares() {
        int[][] matrix = {  {0, 1, 0},
                            {1, 1, 1},
                            {0, 1, 0}  };
        float size = ((C.WIDTH - 2*C.GRID_PAD_LR - 2*C.GRID_DIST)/3 - 8*C.GRID_DIST)/3;
        int c = 0;
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j] == 1) {
                    for(int k=0; k<3; k++) {
                        for(int l=0; l<3; l++) {
                            MyActor square = new MyActor();
                            square.setSize(size, size);
                            square.setPosition(i*3+k, j*3+l);
                            square.setXY(C.GRID_PAD_LR+C.GRID_DIST+square.getPosition().getX()*(size+3*C.GRID_DIST),
                                    C.GRID_PAD_DOWN+C.GRID_DIST+square.getPosition().getY()*(size+3*C.GRID_DIST));
                            addActor(square);
                            switch(c) {
                                case 0: square.setColor(C.RED); break;
                                case 1: square.setColor(C.VIOLET); break;
                                case 2: square.setColor(C.BLUE); break;
                                case 3: square.setColor(C.GREEN); break;
                                case 4: square.setColor(C.ORANGE); break;
                            }
                        }
                    }
                    c++;
                }
            }
        }
    }
}
