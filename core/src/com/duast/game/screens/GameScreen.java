package com.duast.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.duast.game.Main;
import com.duast.game.cameras.GameCamera;
import com.duast.game.stages.GameStage;
import com.duast.game.stages.HudStage;
import com.duast.game.utils.C;

/**
 * Created by alex on 9/22/16.
 */

public class GameScreen implements Screen {
    private Main main;
    private FPSLogger log;
    private GameCamera camera;
    private GameStage game;
    private HudStage hud;

    public GameScreen(Main main) {
        this.main = main;
        log = new FPSLogger();

        camera = new GameCamera();

        hud = new HudStage(this);
        game = new GameStage(this);

        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(hud);
        im.addProcessor(game);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClearColor(C.BACKGROUND_DARK.r, C.BACKGROUND_DARK.g, C.BACKGROUND_DARK.b, C.BACKGROUND_DARK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update
        camera.update();
        hud.act();
        game.act();

        //draw
        hud.draw();
        game.draw();

        //fps logger
        //log.log();
    }

    @Override
    public void resize(int width, int height) {
        hud.getViewport().update(width, height, true);
        game.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        hud.dispose();
        game.dispose();
    }

    /* getters */
    public GameCamera getCamera() {
        return camera;
    }

    public HudStage getHud() {
        return hud;
    }

    public GameStage getGame() {
        return game;
    }
}
