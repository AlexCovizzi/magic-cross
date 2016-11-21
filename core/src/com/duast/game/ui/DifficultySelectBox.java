package com.duast.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.duast.game.stages.UIStage;
import com.duast.game.utils.Assets;
import com.duast.game.utils.C;

/**
 * Created by alex on 10/26/16.
 */

public class DifficultySelectBox extends Table {
    public static final int LIST_WIDTH = Assets.FONT_SIZE*5;
    private static final String[] DIFFICULTIES = {"Easy", "Medium", "Hard"};
    private TextButton title, easy, medium, hard;
    private Table list;
    private int selected;

    public DifficultySelectBox(TextButton.TextButtonStyle tbStyle, int diff) {
        title = new TextButton(DIFFICULTIES[diff], tbStyle);
        title.getLabel().setAlignment(Align.left);
        selected = diff;
        title.pad(UIStage.PAD/3);
        title.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(list.isVisible()) hide();
                else show();
            }
        });

        list = new Table();
        //list.setDebug(true);
        add(title).width(LIST_WIDTH);
        row();
        add(list);
        easy = new TextButton("Easy", tbStyle);
        medium = new TextButton("Medium", tbStyle);
        hard = new TextButton("Hard", tbStyle);
        add(easy, DIFFICULTIES[0], 0);
        add(medium, DIFFICULTIES[1], 1);
        add(hard, DIFFICULTIES[2], 2);
        hide();
    }

    public Cell<TextButton> add(TextButton tb, final String text, final int i) {
        list.row();
        tb.getLabel().setAlignment(Align.left);
        tb.pad(UIStage.PAD/3);
        tb.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(list.isVisible()) hide();
                if(title!=null) title.setText(text);
                selected = i;
            }
        });
        return list.add(tb).width(LIST_WIDTH);
    }

    public void show() {
        list.setVisible(true);
    }

    public void hide() {
        list.setVisible(false);
    }

    public boolean isListVisible() {
        return list.isVisible();
    }

    public void setTheme(int theme) {
        Color font_color;
        switch(theme) {
            case C.LIGHT: font_color = C.WHITE; break;
            case C.DARK: font_color = C.BLACK; break;
            default: font_color = C.WHITE;
        }
        title.getStyle().fontColor = font_color;
        easy.getStyle().fontColor = font_color;
        medium.getStyle().fontColor = font_color;
        hard.getStyle().fontColor = font_color;
    }

    /* getters */
    public TextButton getTitleLabel() {
        return title;
    }

    public int getSelected() {
        return selected;
    }

    public Table getList() {
        return list;
    }
}
