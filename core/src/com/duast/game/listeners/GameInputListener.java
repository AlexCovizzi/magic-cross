package com.duast.game.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.duast.game.screens.GameScreen;
import com.duast.game.stages.GameStage;
import com.duast.game.ui.WinDialog;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 10/8/16.
 */

public class GameInputListener extends InputListener {
    private GameScreen screen;
    private float x0, y0;
    private Coordinates t_coords; //touch coordinates
    private int line, coord;
    private boolean isTouchValid = false;

    public GameInputListener(GameScreen screen) {
        this.screen = screen;
        t_coords = new Coordinates();
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        if(!screen.isTouchable()) return false;
        //set starting position
        this.x0 = x;
        this.y0 = y;

        //transform touch position in coordinates
        int square_size = screen.getGameStage().getCross().getSquareSize()+C.DIST;
        t_coords.setX((int) ((x0- C.PAD_LR)/square_size));
        t_coords.setY((int) ((y0-C.PAD_DOWN)/square_size));

        int ss = screen.getGameStage().getCross().size()/3;
        if(t_coords.x>=ss && t_coords.x<=ss*2-1 && t_coords.y>=0 && t_coords.y<=ss*3-1) isTouchValid = true;
        if(t_coords.y>=ss && t_coords.y<=ss*2-1 && t_coords.x>=0 && t_coords.x<=ss*3-1) isTouchValid = true;

        line = -1;

        if(isTouchValid) {
            screen.getGameStage().getHighlights().show(C.ROW_COLUMN);
            screen.getGameStage().getHighlights().setCoords(t_coords);

            screen.getUiStage().getTimer().start();
        }
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        if(isTouchValid) {
            //calculate drag distance
            float dx = x - x0;
            float dy = y - y0;

            if (line == C.ROW) dy = 0;
            else if (line == C.COLUMN) dx = 0;

            int dir = 0; //direction (+1 is UP or RIGHT; -1 is DOWN or LEFT; 0 is don't move)

            int square_size = screen.getGameStage().getCross().getSquareSize();
            int sector_size = screen.getGameStage().getCross().size() / 3;

            if (Math.abs(dx) > square_size / 1.8f) {
                dir = (int) Math.signum(dx);
                this.x0 = x; //set new starting x
                if (line == -1) {
                    line = C.ROW;
                    coord = t_coords.y;
                    screen.getGameStage().getHighlights().hide(C.COLUMN);
                }
            }

            if (Math.abs(dy) > square_size / 1.8f) {
                dir = (int) Math.signum(dy);
                this.y0 = y; //set new starting y
                if (line == -1) {
                    line = C.COLUMN;
                    coord = t_coords.x;
                    screen.getGameStage().getHighlights().hide(C.ROW);
                }
            }

            if (coord > sector_size - 1 && coord < sector_size * 2 && dir != 0) {
                screen.getGameStage().getCross().move(coord, dir, line);
            }
        }
    }

    @Override
    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        if(isTouchValid) {
            screen.getGameStage().getHighlights().hide(C.ROW_COLUMN);
            isTouchValid = false;
        }
        if(screen.getGameStage().getCross().checkWin()) {
            screen.setTouchable(false);
            screen.getUiStage().addActor(new WinDialog(screen));
        }
    }
}
