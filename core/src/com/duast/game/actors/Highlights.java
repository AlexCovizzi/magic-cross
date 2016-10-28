package com.duast.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.duast.game.stages.GameStage;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 10/21/16.
 */

public class Highlights {

    private GameStage game;
    private Highlight highlight_row;
    private Highlight highlight_column;

    public Highlights(GameStage game, Color background) {
        this.game = game;

        highlight_row = new Highlight(game);
        highlight_row.setLine(C.ROW);
        highlight_column = new Highlight(game);
        highlight_column.setLine(C.COLUMN);

        setSize();
        setColor(background);

        game.addActor(highlight_row);
        game.addActor(highlight_column);

        hide(C.ROW_COLUMN);
    }

    public void setSize() {
        int square_size = ((C.WIDTH - 2*C.PAD_LR - 2*C.DIST)/3 - (game.getNumSquaresInSector()-1)*C.DIST)/game.getNumSquaresInSector();
        int size_short = square_size+C.DIST*2;
        int size_long = square_size*3*game.getNumSquaresInSector()+C.DIST*(game.getNumSquaresInSector()*3+1);
        highlight_row.setSize(size_long, size_short);
        highlight_column.setSize(size_short, size_long);
    }

    public void setColor(Color color) {
        highlight_row.setColor(color);
        highlight_column.setColor(color);
    }

    public void setCoords(Coordinates coords) {
        int ss = game.getNumSquaresInSector();
        if(coords.y>=ss && coords.y<=ss*2-1) highlight_row.setLineNumber(coords.y);
        else highlight_row.hide();
        if(coords.x>=ss && coords.x<=ss*2-1) highlight_column.setLineNumber(coords.x);
        else highlight_column.hide();
    }

    public void show(int line) {
        if(line==C.ROW|line==C.ROW_COLUMN) highlight_row.show();
        if(line==C.COLUMN|line==C.ROW_COLUMN) highlight_column.show();
    }

    public void hide(int line) {
        if(line==C.ROW|line==C.ROW_COLUMN) highlight_row.hide();
        if(line==C.COLUMN|line==C.ROW_COLUMN) highlight_column.hide();
    }
}
