package com.duast.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.duast.game.MyGame;
import com.duast.game.cameras.GameCamera;
import com.duast.game.entities.Squares;
import com.duast.game.listeners.GameInputProcessor;
import com.duast.game.utils.C;

/**
 * Created by alex on 9/22/16.
 */

public class GameScreen implements Screen {
    private MyGame game;
    private FPSLogger log;
    private GameCamera camera;
    private SpriteBatch batch;
    private Squares squares;
    private GameInputProcessor inputProcessor;

    public GameScreen(MyGame game) {
        this.game = game;
        log = new FPSLogger();

        camera = new GameCamera();
        batch = new SpriteBatch();
        squares = new Squares();

        inputProcessor = new GameInputProcessor(this);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClearColor(C.BACKGROUND.r, C.BACKGROUND.g, C.BACKGROUND.b, C.BACKGROUND.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update
        camera.update();
        squares.update(delta);

        //draw
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        squares.draw(batch);
        batch.end();

        //fps logger
        //log.log();
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        squares.dispose();
    }

    /* getters */

    public Squares getSquares() {
        return squares;
    }

    public GameCamera getCamera() {
        return camera;
    }
}
