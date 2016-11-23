package com.duast.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.duast.game.cameras.UICamera;
import com.duast.game.screens.GameScreen;
import com.duast.game.ui.DifficultySelectBox;
import com.duast.game.ui.Menu;
import com.duast.game.ui.NewGameButton;
import com.duast.game.ui.TimeLabel;
import com.duast.game.utils.Assets;
import com.duast.game.utils.C;

/**
 * Created by Alex on 16/02/2016.
 */
public class UIStage extends Stage {
    public static final float PAD = C.DEVICE_WIDTH / 24;

    private GameScreen screen;
    private UICamera camera;

    private TimeLabel timer;
    private NewGameButton new_game;
    private DifficultySelectBox diff;
    private Menu menu;

    public UIStage(GameScreen screen) {
        this.screen = screen;

        camera = new UICamera();
        setViewport(new FillViewport(camera.viewportWidth, camera.viewportHeight, camera));

        NinePatch np = new NinePatch(Assets.TEXTURE_PIXEL, 1, 1, 1, 1);
        NinePatchDrawable npd = new NinePatchDrawable(np);
        NinePatchDrawable buttonUp = npd.tint(C.GRAY);
        NinePatchDrawable buttonDown = npd.tint(C.LIGHT_GRAY);
        TextButton.TextButtonStyle tbStyle = new TextButton.TextButtonStyle(buttonUp, buttonDown, buttonUp, Assets.FONT_SMALL);


        Table table_left = new Table();
        table_left.setFillParent(true);
        table_left.top().left();

        timer = new TimeLabel(screen.getGameStage());
        timer.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                diff.hide();
                menu.hide();
                return false;
            }
        });
        diff = new DifficultySelectBox(tbStyle, screen.getGameStage().getDifficulty());
        diff.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menu.hide();
                return false;
            }
        });
        new_game = new NewGameButton(screen, tbStyle, diff);
        new_game.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                diff.hide();
                menu.hide();
                return false;
            }
        });

        table_left.add(timer).left().padLeft(PAD).padTop(PAD+PAD/2).colspan(2);
        table_left.row();
        table_left.add(new_game).left().top().padLeft(PAD).padRight(C.DIST).padTop(PAD/2);
        table_left.add(diff).left().top().padTop(PAD/2);

        Table table_right = new Table();
        table_right.setFillParent(true);
        table_right.top().right();

        menu = new Menu(screen, tbStyle);
        menu.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                diff.hide();
                return false;
            }
        });

        table_right.add(menu).padRight(PAD).padTop(PAD+PAD);

        addActor(table_left);
        addActor(table_right);

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(event.getTarget().toString().length() > 60) {
                    diff.hide();
                    menu.hide();
                }
                return false;
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        camera.update();

        if(!screen.isTouchable()) {
            menu.setTouchable(Touchable.disabled);
            diff.setTouchable(Touchable.disabled);
            new_game.setTouchable(Touchable.disabled);
        }else{
            menu.setTouchable(Touchable.enabled);
            diff.setTouchable(Touchable.enabled);
            new_game.setTouchable(Touchable.enabled);
        }
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public TimeLabel getTimer() {
        return timer;
    }

    public DifficultySelectBox getDiff() {
        return diff;
    }
}
