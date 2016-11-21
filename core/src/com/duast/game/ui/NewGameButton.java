package com.duast.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.duast.game.screens.GameScreen;
import com.duast.game.stages.UIStage;
import com.duast.game.utils.C;

/**
 * Created by alex on 11/12/16.
 */

public class NewGameButton extends TextButton {
    public NewGameButton(final GameScreen screen, TextButtonStyle tbStyle, final DifficultySelectBox diff) {
        super("New Game", tbStyle);
        pad(UIStage.PAD/3);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGameStage().setDifficulty(diff.getSelected());
                screen.getGameStage().init(null);
            }
        });
    }

    public void setTheme(int theme) {
        switch(theme) {
            case C.LIGHT: getStyle().fontColor = C.WHITE; break;
            case C.DARK: getStyle().fontColor = C.BLACK; break;
            default: getStyle().fontColor = C.WHITE;
        }
    }
}
