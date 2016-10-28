package com.duast.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.duast.game.cameras.GameCamera;
import com.duast.game.stages.GameStage;
import com.duast.game.stages.HudStage;
import com.duast.game.utils.Theme;

/**
 * Created by alex on 9/22/16.
 */

public class GameScreen implements Screen {

    private Theme theme;
    private GameCamera camera;
    private GameStage game;
    private HudStage hud;

    public GameScreen() {
        theme = new Theme();
        camera = new GameCamera();
        game = new GameStage(this);
        hud = new HudStage(this);

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
        Color bc = theme.getBackgroundColor();
        Gdx.gl.glClearColor(bc.r, bc.g, bc.b, bc.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update
        camera.update();
        game.act();
        hud.act();

        //draw
        game.draw();
        hud.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height, true);
        hud.getViewport().update(width, height, true);
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

    /* setters */
    public void setDark(boolean dark) {
        theme.setDark(dark);
        game.getHighlights().setColor(theme.getHighlightColor());
    }

    /* getters */
    public Theme getTheme() {
        return theme;
    }

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
