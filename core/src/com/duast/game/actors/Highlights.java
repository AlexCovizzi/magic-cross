package com.duast.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.duast.game.screens.GameScreen;
import com.duast.game.stages.GameStage;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 10/21/16.
 */

public class Highlights {

    private GameStage stage;
    private Highlight highlight_row;
    private Highlight highlight_column;

    public Highlights(GameStage stage) {
        this.stage = stage;
    }

    public void init() {
        highlight_row = new Highlight(stage);
        highlight_row.setLine(C.ROW);
        highlight_column = new Highlight(stage);
        highlight_column.setLine(C.COLUMN);

        setSize();

        stage.addActor(highlight_row);
        stage.addActor(highlight_column);

        hide(C.ROW_COLUMN);
    }

    public void setSize() {
        int ss = stage.getDifficulty()+2;
        int square_size = ((C.WIDTH - 2*C.PAD_LR - 2*C.DIST)/3 - (ss-1)*C.DIST)/ss;
        int size_short = square_size + 2*C.DIST;
        int size_long = square_size*3*ss+C.DIST*(ss*3+1);
        highlight_row.setSize(size_long, size_short);
        highlight_column.setSize(size_short, size_long);
    }

    public void setTheme(int theme) {
        highlight_row.setTheme(theme);
        highlight_column.setTheme(theme);
    }

    public void setCoords(Coordinates coords) {
        int sector_size = stage.getCross().size()/3;
        if(coords.y>=sector_size && coords.y<=sector_size*2-1) highlight_row.setLineNumber(coords.y);
        else highlight_row.hide();
        if(coords.x>=sector_size && coords.x<=sector_size*2-1) highlight_column.setLineNumber(coords.x);
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
