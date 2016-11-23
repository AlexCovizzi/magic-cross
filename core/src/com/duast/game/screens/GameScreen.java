package com.duast.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Json;
import com.duast.game.cameras.GameCamera;
import com.duast.game.stages.GameStage;
import com.duast.game.stages.UIStage;
import com.duast.game.ui.StartingDialog;
import com.duast.game.utils.Assets;
import com.duast.game.utils.C;

/**
 * Created by alex on 9/22/16.
 */

public class GameScreen implements Screen {

    private int theme;
    private Color background;
    private boolean touchable = true;
    private GameStage gameStage;
    private UIStage uiStage;

    public GameScreen() {
        theme = Assets.SAVE_FILE.getTheme();
        gameStage = new GameStage(this);
        uiStage = new UIStage(this);
        setTheme(Assets.SAVE_FILE.getTheme());

        if(!Assets.SAVE_FILE.exists()) {
            setTouchable(false);
            uiStage.addActor(new StartingDialog(this));
        }

        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(uiStage);
        im.addProcessor(gameStage);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClearColor(background.r, background.g, background.b, background.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update
        gameStage.act();
        uiStage.act();

        //draw
        gameStage.getViewport().apply();
        gameStage.draw();
        uiStage.getViewport().apply();
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        //save game state
        if(gameStage.getCross().checkWin())
            Assets.SAVE_FILE.save(theme, gameStage.getDifficulty(), "00:00", null);
        else
            Assets.SAVE_FILE.save(theme, gameStage.getDifficulty(), uiStage.getTimer().get(), gameStage.getCross().getArr());
    }

    @Override
    public void resume() {
        //resume saved game state
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameStage.dispose();
        uiStage.dispose();
    }

    /* setters */
    public void setTheme(int theme) {
        this.theme = theme;
        switch(theme) {
            case C.LIGHT: background = C.WHITE; break;
            case C.DARK: background = C.BLACK; break;
            default: background = C.WHITE;
        }
        gameStage.getHighlights().setTheme(theme);
    }

    public void setTouchable(boolean touchable) {
        this.touchable = touchable;
    }

    public boolean isTouchable() {
        return touchable;
    }

    /* getters */
    public UIStage getUiStage() {
        return uiStage;
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public int getTheme() {
        return theme;
    }
}
