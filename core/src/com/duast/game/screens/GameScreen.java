package com.duast.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.duast.game.cameras.GameCamera;
import com.duast.game.stages.GameStage;
import com.duast.game.stages.HudStage;
import com.duast.game.utils.C;

/**
 * Created by alex on 9/22/16.
 */

public class GameScreen implements Screen {

    private GameCamera camera;
    private GameStage game;
    private HudStage hud;
    private Color background_color = C.BACKGROUND_DARK;

    public GameScreen() {

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
        Gdx.gl.glClearColor(background_color.r, background_color.g, background_color.b, background_color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update
        camera.update();
        hud.act();
        game.act();

        //draw
        hud.draw();
        game.draw();
    }

    @Override
    public void resize(int width, int height) {
        hud.getViewport().update(width, height, true);
        game.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        //save game state on Android
    }

    @Override
    public void resume() {
        //resume saved game state on Android
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

    public Color getBackgroundColor() {
        return background_color;
    }

    public HudStage getHud() {
        return hud;
    }

    public GameStage getGame() {
        return game;
    }
}
