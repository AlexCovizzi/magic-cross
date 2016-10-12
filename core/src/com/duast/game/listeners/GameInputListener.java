package com.duast.game.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.duast.game.actors.Square;
import com.duast.game.stages.GameStage;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;
import com.duast.game.actors.SquareArray;

/**
 * Created by alex on 10/8/16.
 */

public class GameInputListener extends InputListener {

    private GameStage game;
    private float x0, y0;
    private Coordinates t_coords; //touch coordinates
    private int l;

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
        t_coords.setX((int) ((x0-C.PAD_LR-(C.SS*3-1)*C.DIST)/ Square.SIZE));
        t_coords.setY((int) ((y0-C.PAD_DOWN-(C.SS*3-1)*C.DIST)/Square.SIZE));

        l = -1;

        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        //calculate drag distance
        float dx = x-x0;
        float dy = y-y0;

        if(l== SquareArray.ROW) dy = 0;
        else if(l==SquareArray.COLUMN) dx = 0;

        int m = 0; //how much to move the line (+1, -1)

        if(Math.abs(dx) > Square.SIZE/1.5f) {
            m = (int) Math.signum(dx);
            this.x0=x; //set new starting x
            if(l==-1) l = SquareArray.ROW;
        }

        if(Math.abs(dy) > Square.SIZE/1.5f) {
            m = (int) Math.signum(dy);
            this.y0=y; //set new starting y
            if(l==-1) l = SquareArray.COLUMN;
        }

        if(game.getSquares().move(t_coords, m, l)) {
            Gdx.app.log("vinto", ""+game.getSquares().checkWin());
        }

    }

    @Override
    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        //
    }
}
