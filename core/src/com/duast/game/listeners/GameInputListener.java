package com.duast.game.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.duast.game.stages.GameStage;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 10/8/16.
 */

public class GameInputListener extends InputListener {

    private GameStage game;
    private float x0, y0;
    private Coordinates t_coords; //touch coordinates
    private int line, coord;

    public GameInputListener(GameStage game) {
        this.game = game;
        t_coords = new Coordinates();

    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        //set starting position
        this.x0 = x;
        this.y0 = y;

        //transform touch position in coordinates
        t_coords.setX((int) ((x0-C.PAD_LR-(game.getNumSquaresInSector()*3-1)*C.DIST)/(int) game.getSquares().getSquareSize()));
        t_coords.setY((int) ((y0-C.PAD_DOWN-(game.getNumSquaresInSector()*3-1)*C.DIST)/(int) game.getSquares().getSquareSize()));

        line = -1;

        game.getHighlights().show(C.ROW_COLUMN);
        game.getHighlights().setCoords(t_coords);

        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        //calculate drag distance
        float dx = x-x0;
        float dy = y-y0;

        if(line== C.ROW) dy = 0;
        else if(line==C.COLUMN) dx = 0;

        int dir = 0; //direction (+1 is UP or RIGHT; -1 is DOWN or LEFT; 0 is don't move)

        if(Math.abs(dx) > game.getSquares().getSquareSize()/1.5f) {
            dir = (int) Math.signum(dx);
            this.x0=x; //set new starting x
            if(line==-1) {
                line = C.ROW;
                coord = t_coords.y;
                game.getHighlights().hide(C.COLUMN);
            }
        }

        if(Math.abs(dy) > game.getSquares().getSquareSize()/1.5f) {
            dir = (int) Math.signum(dy);
            this.y0=y; //set new starting y
            if(line==-1) {
                line = C.COLUMN;
                coord = t_coords.x;
                game.getHighlights().hide(C.ROW);
            }
        }

        if(coord>game.getNumSquaresInSector()-1 && coord<game.getNumSquaresInSector()*2 && dir!=0) game.getSquares().move(coord, dir, line);

    }

    @Override
    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        game.getHighlights().hide(C.ROW_COLUMN);
    }
}
