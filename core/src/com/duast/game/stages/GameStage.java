package com.duast.game.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.duast.game.actors.Squares;
import com.duast.game.cameras.GameCamera;
import com.duast.game.listeners.GameInputListener;
import com.duast.game.screens.GameScreen;

/**
 * Created by alex on 9/22/16.
 */

public class GameStage extends Stage {
    private GameScreen game;
    private GameCamera camera;

    public GameStage(GameScreen game) {
        this.game = game;

        camera = new GameCamera();
        setViewport(new FitViewport(camera.viewportWidth, camera.viewportHeight, camera));
        addListener(new GameInputListener(this));
        //Grid grid = new Grid();
        //addActor(grid);
        Squares squares = new Squares();
        addActor(squares);
    }

    @Override
    public void act() {
        super.act();
        camera.update();
    }

    @Override
    public void draw() {
        super.draw();

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /* getters */
    @Override
    public GameCamera getCamera() {
        return camera;
    }

}
