package com.duast.game.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.duast.game.entities.Square;
import com.duast.game.entities.Squares;
import com.duast.game.screens.GameScreen;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;
import com.duast.game.utils.SquareArray;

import java.util.ArrayList;

/**
 * Created by alex on 10/4/16.
 */

public class GameInputProcessor implements InputProcessor {

    private GameScreen screen;
    private float x0, y0;
    private Coordinates t_coords; //touch coordinates
    private int l;

    public GameInputProcessor(GameScreen screen) {
        this.screen = screen;
        t_coords = new Coordinates();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //set starting position
        this.x0 = screenX;
        this.y0 = C.HEIGHT-screenY;

        //transform touch position in coordinates
        t_coords.setX((int) ((x0-C.PAD_LR-(C.SS*C.SS-1)*C.DIST)/Square.SIZE));
        t_coords.setY((int) ((y0-C.PAD_DOWN-(C.SS*C.SS-1)*C.DIST)/Square.SIZE));

        l = -1;

        if(y0 > C.HEIGHT-64) shuffle(10);

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //calculate drag distance
        float dx = screenX-x0;
        float dy = C.HEIGHT-screenY-y0;

        if(l==SquareArray.ROW) dy = 0;
        else if(l==SquareArray.COLUMN) dx = 0;

        int m = 0; //how much to move the line (+1, -1)

        if(Math.abs(dx) > Square.SIZE/1.5f) {
            m = (int) Math.signum(dx);
            this.x0=screenX; //set new starting x
            if(l==-1) l = SquareArray.ROW;
        }

        if(Math.abs(dy) > Square.SIZE/1.5f) {
            m = (int) Math.signum(dy);
            this.y0=C.HEIGHT-screenY; //set new starting y
            if(l==-1) l = SquareArray.COLUMN;
        }

        if(screen.getSquares().move(t_coords, m, l)) {
            Gdx.app.log("vinto", ""+screen.getSquares().checkWin());
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    private void shuffle(int iterations) {
        for(int i=0;i<iterations;i++) {
            int line = randomWithRange(0, 1); //row=0, column=1
            int line_n = randomWithRange(C.SS, C.SS*2-1);
            int move = randomWithRange(1, SquareArray.SIZE-1);

            for(int j=0; j<move; j++) {
                screen.getSquares().move(new Coordinates(line_n, line_n), 1, line);
            }
        }
    }

    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
