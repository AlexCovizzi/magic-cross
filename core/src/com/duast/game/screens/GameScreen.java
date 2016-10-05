package com.duast.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.duast.game.MyGame;
import com.duast.game.stages.GameStage;
import com.duast.game.utils.C;

/**
 * Created by alex on 9/22/16.
 */

public class GameScreen implements Screen {
    private MyGame game;
    private GameStage gameStage;
    private FPSLogger log;

    public GameScreen(MyGame game) {
        this.game = game;
        log = new FPSLogger();

        gameStage = new GameStage(this);
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClearColor(C.BACKGROUND.r, C.BACKGROUND.g, C.BACKGROUND.b, C.BACKGROUND.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //act
        gameStage.act();

        //draw
        gameStage.draw();

        //fps logger
        //log.log();
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height);
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
        gameStage.dispose();
    }

    public GameStage getGameStage() {
        return gameStage;
    }
}
