package com.duast.game.listeners;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.duast.game.actors.MyActor;
import com.duast.game.stages.GameStage;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by Alex on 24/08/16.
 */
public class GameInputListener extends InputListener {

    private GameStage stage;
    private float x0, y0;
    private Coordinates touch;

    public GameInputListener(GameStage stage) {
        this.stage = stage;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.x0 = x;
        this.y0 = y;
        shuffle(20);
        int pos_x = (int) ((x-C.GRID_PAD_LR-8*C.GRID_DIST)/(((C.WIDTH - 2*C.GRID_PAD_LR - 2*C.GRID_DIST)/3 - 8*C.GRID_DIST)/3));
        int pos_y = (int) ((y-C.GRID_PAD_DOWN-8*C.GRID_DIST)/(((C.WIDTH - 2*C.GRID_PAD_LR - 2*C.GRID_DIST)/3 - 8*C.GRID_DIST)/3));
        touch = new Coordinates(pos_x, pos_y);
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        /*float dx = x-x0;
        float dy = y-y0;
        if(Math.abs(dx) > (((C.WIDTH - 2*C.GRID_PAD_LR - 2*C.GRID_DIST)/3 - 8*C.GRID_DIST)/3)) {
            dx=1*Math.signum(dx);
            this.x0=x;
        }
        else dx=0;
        if(Math.abs(dy) > (((C.WIDTH - 2*C.GRID_PAD_LR - 2*C.GRID_DIST)/3 - 8*C.GRID_DIST)/3)) {
            dy=1*Math.signum(dy);
            this.y0=y;
        }
        else dy=0;
        Group group = (Group)stage.getActors().get(0);
        for(int i=0; i<group.getChildren().size; i++) {
            MyActor square = (MyActor)group.getChildren().get(i);
            Coordinates v = square.getPosition();
            if(v.getY() == touch.getY() && touch.getY()>2 && touch.getY()<6) {
                int pos_y = v.getY();
                int pos_x = v.getX();
                pos_x += dx;
                //pos_y += dy;
                if(pos_x>8) pos_x=0; if(pos_x<0) pos_x=8;
                if(pos_y>8) pos_y=0; if(pos_y<0) pos_y=8;
                square.setPosition(pos_x, pos_y);
                square.setXY(C.GRID_PAD_LR+C.GRID_DIST+square.getPosition().getX()*(square.getWidth()+3*C.GRID_DIST),
                        C.GRID_PAD_DOWN+C.GRID_DIST+square.getPosition().getY()*(square.getWidth()+3*C.GRID_DIST));

            }
            if(v.getX() == touch.getX() && touch.getX()>2 && touch.getX()<6) {
                int pos_y = v.getY();
                int pos_x = v.getX();
                //pos_x += dx;
                pos_y += dy;
                if(pos_x>8) pos_x=0; if(pos_x<0) pos_x=8;
                if(pos_y>8) pos_y=0; if(pos_y<0) pos_y=8;
                square.setPosition(pos_x, pos_y);
                square.setXY(C.GRID_PAD_LR+C.GRID_DIST+square.getPosition().getX()*(square.getWidth()+3*C.GRID_DIST),
                        C.GRID_PAD_DOWN+C.GRID_DIST+square.getPosition().getY()*(square.getWidth()+3*C.GRID_DIST));

            }
        }*/
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        //checkWin();
    }

    private void shuffle(int iterations) {
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
    }
}
