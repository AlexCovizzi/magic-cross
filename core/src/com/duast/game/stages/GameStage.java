package com.duast.game.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.duast.game.actors.Highlights;
import com.duast.game.actors.Squares;
import com.duast.game.listeners.GameInputListener;
import com.duast.game.screens.GameScreen;
import com.duast.game.utils.C;

/**
 * Created by alex on 10/8/16.
 */

public class GameStage extends Stage {

    private GameScreen screen;
    private int squares_in_sector = 2; //number of squares in a sector
    private Squares squares;
    private Highlights highlights;

    public GameStage(GameScreen screen) {
        this.screen = screen;

        setViewport(new FillViewport(C.WIDTH, C.HEIGHT, screen.getCamera()));

        addListener(new GameInputListener(this));

        highlights = new Highlights(this, screen.getBackgroundColor());

        squares = new Squares(this);

    }

    public void reset() {
        if(squares != null) {
            clear();
            addListener(new GameInputListener(this));
        }
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public Squares getSquares() {
        return squares;
    }

    public Highlights getHighlights() {
        return highlights;
    }

    public int getNumSquaresInSector() {
        return squares_in_sector;
    }
}
