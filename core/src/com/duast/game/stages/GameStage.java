package com.duast.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.duast.game.actors.Cross;
import com.duast.game.actors.Highlights;
import com.duast.game.cameras.GameCamera;
import com.duast.game.listeners.GameInputListener;
import com.duast.game.screens.GameScreen;
import com.duast.game.utils.Assets;
import com.duast.game.utils.C;

/**
 * Created by alex on 10/8/16.
 */

public class GameStage extends Stage {
    private GameScreen screen;
    private GameCamera camera;
    private Cross cross;
    private Highlights highlights;
    private Label starting_game_label;
    private int diff;

    public GameStage(GameScreen screen) {
        this.screen = screen;

        camera = new GameCamera();
        setViewport(new FitViewport(C.WIDTH, C.HEIGHT, camera));

        setDifficulty(Assets.SAVE_FILE.getDifficulty());

        highlights = new Highlights(this);
        cross = new Cross(this);

        init(Assets.SAVE_FILE.getCross());
    }

    public void init(int[][] arr) {
        if(screen.getUiStage() != null) screen.getUiStage().getTimer().reset();
        clear();
        highlights.init();
        highlights.setTheme(screen.getTheme());
        if(arr == null) {
            cross.init(diff);
            if(Assets.SAVE_FILE.exists()) cross.shuffle();
        }else{
            cross.init(arr);
        }

        String text;
        if (arr != null) text = "Resumed last game";
        else text = "Started new game";
        starting_game_label = new Label(text, new Label.LabelStyle(Assets.FONT_SMALL, C.GRAY));
        starting_game_label.setPosition(C.WIDTH / 2 - starting_game_label.getWidth() / 2, C.PAD_DOWN + (cross.getSquareSize() + C.DIST) * cross.size() + C.PAD_LR * 4);
        addActor(starting_game_label);
        starting_game_label.addAction(Actions.sequence(Actions.alpha(0), Actions.alpha(1f, 0.75f), Actions.delay(2f), Actions.alpha(0, 0.75f)));

        GameInputListener listener = new GameInputListener(screen); addListener(listener);
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

    public void setDifficulty(int diff) {
        this.diff = diff;
    }

    public Cross getCross() {
        return cross;
    }

    public Highlights getHighlights() {
        return highlights;
    }

    public int getDifficulty() {
        return diff;
    }
}
