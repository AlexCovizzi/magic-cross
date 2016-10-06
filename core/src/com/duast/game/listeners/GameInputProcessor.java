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

/**
 * Created by alex on 10/4/16.
 */

public class GameInputProcessor implements InputProcessor {

    private GameScreen screen;
    private float x0, y0;
    private Coordinates t_coords; //touch coordinates

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
        t_coords.setX((int) ((x0-C.PAD_LR-8*C.DIST)/Square.SIZE));
        t_coords.setY((int) ((y0-C.PAD_DOWN-8*C.DIST)/Square.SIZE));

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //calculate drag distance
        float dx = screenX-x0;
        float dy = C.HEIGHT-screenY-y0;

        //if the dx is > square size i'll move the squares in the row where i started the touch
        if(Math.abs(dx) > Square.SIZE) {
            dx=1*Math.signum(dx);
            this.x0=screenX; //set new starting x
        } else { dx=0; }

        //if the dy is > square size i'll move the squares in the column where i started the touch
        if(Math.abs(dy) > Square.SIZE) {
            dy=1*Math.signum(dy);
            this.y0=C.HEIGHT-screenY; //set new starting y
        } else { dy=0; }

        if(dx!=0) {
            screen.getSquares().move(t_coords.y, Squares.ROW, (int)dx);
        }
        if(dy!=0) {
            screen.getSquares().move(t_coords.x, Squares.COLUMN, (int)dy);
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

    /*private void shuffle(int iterations) {
        for(int j=0;j<iterations;j++) {
            int line = randomWithRange(0, 1); //row=0, column=1
            int line_n = randomWithRange(3, 5);
            //int move = randomWithRange(1, 7);
            Group group = (Group) stage.getActors().get(0);
            for (int i = 0; i < group.getChildren().size; i++) {
                MyActor square = (MyActor) group.getChildren().get(i);
                Coordinates v = square.getPosition();
                if (line == 0) {
                    if (v.getY() == line_n) {
                        int pos_y = v.getY();
                        int pos_x = v.getX() + 1;
                        if (pos_x > 8) pos_x = 0;
                        if (pos_x < 0) pos_x = 8;
                        if (pos_y > 8) pos_y = 0;
                        if (pos_y < 0) pos_y = 8;
                        square.setPosition(pos_x, pos_y);
                        square.setXY(C.GRID_PAD_LR+C.GRID_DIST+square.getPosition().getX()*(square.getWidth()+3*C.GRID_DIST),
                                C.GRID_PAD_DOWN+C.GRID_DIST+square.getPosition().getY()*(square.getWidth()+3*C.GRID_DIST));
                    }
                }
                if (line == 1) {
                    if (v.getX() == line_n) {
                        int pos_y = v.getY() + 1;
                        int pos_x = v.getX();
                        if (pos_x > 8) pos_x = 0;
                        if (pos_x < 0) pos_x = 8;
                        if (pos_y > 8) pos_y = 0;
                        if (pos_y < 0) pos_y = 8;
                        square.setPosition(pos_x, pos_y);
                        square.setXY(C.GRID_PAD_LR+C.GRID_DIST+square.getPosition().getX()*(square.getWidth()+3*C.GRID_DIST),
                                C.GRID_PAD_DOWN+C.GRID_DIST+square.getPosition().getY()*(square.getWidth()+3*C.GRID_DIST));
                    }
                }
            }
        }
    }

    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    private boolean checkWin() {
        boolean won = true;
        won = checkColorsAround(4,1);
        won = checkColorsAround(1,4);
        won = checkColorsAround(4,4);
        won = checkColorsAround(7,4);
        won = checkColorsAround(4,7);

        return won;
    }

    private boolean checkColorsAround(int i, int j) {
        return true;
    }*/
}
