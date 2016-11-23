package com.duast.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.duast.game.screens.GameScreen;
import com.duast.game.stages.UIStage;
import com.duast.game.utils.Assets;
import com.duast.game.utils.C;

/**
 * Created by alex on 11/23/16.
 */

public class WinDialog extends Table {

    public WinDialog(final GameScreen screen) {
        center();
        setFillParent(true);
        Sprite sprite = new Sprite(new Texture("square.jpg"));
        sprite.setColor(C.TRANSPARENT_BLACK);
        setBackground(new SpriteDrawable(sprite));

        Label label = new Label("You Won!", new Label.LabelStyle(Assets.FONT_BIG, C.WHITE));
        add(label).pad(UIStage.PAD/2);
        row();
        Label diff = new Label("Difficulty: "+screen.getUiStage().getDiff().getTitleLabel().getLabel().getText().toString(), new Label.LabelStyle(Assets.FONT_SMALL, C.WHITE));
        add(diff).pad(UIStage.PAD/2);
        row();
        Label time = new Label("Time: "+screen.getUiStage().getTimer().get(), new Label.LabelStyle(Assets.FONT_SMALL, C.WHITE));
        add(time).pad(UIStage.PAD/2);
        row();

        NinePatch np = new NinePatch(Assets.TEXTURE_PIXEL, 1, 1, 1, 1);
        NinePatchDrawable npd = new NinePatchDrawable(np);
        NinePatchDrawable buttonUp = npd.tint(C.GRAY);
        NinePatchDrawable buttonDown = npd.tint(C.LIGHT_GRAY);
        TextButton.TextButtonStyle tbStyle = new TextButton.TextButtonStyle(buttonUp, buttonDown, buttonUp, Assets.FONT_SMALL);
        TextButton tb = new TextButton("Start new game", tbStyle);
        tb.pad(UIStage.PAD / 2);
        tb.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addAction(Actions.sequence(Actions.alpha(0, 0.35f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        screen.setTouchable(true);
                        setVisible(false);
                        setTouchable(Touchable.disabled);
                        screen.getGameStage().init(null);
                    }
                })));
            }
        });
        add(tb).pad(UIStage.PAD);
    }
}
