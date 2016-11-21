package com.duast.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.duast.game.screens.GameScreen;
import com.duast.game.stages.GameStage;
import com.duast.game.stages.UIStage;
import com.duast.game.utils.Assets;
import com.duast.game.utils.C;

/**
 * Created by alex on 11/13/16.
 */

public class Menu extends Table{
    public static final int ICON_SIZE = Assets.FONT_SIZE*2;
    public static final int LIST_WIDTH = Assets.FONT_SIZE*10;
    public static final String[] ENTRIES = {"Set theme: Light", "Set theme: Dark", "Qualcosa 1", "Qualcosa 2", "Qualcosa 3"};
    private ImageButton icon;
    private Table list;
    private TextButton tb_theme, tb_ads, tb_info, tb_donate;

    public Menu(final GameScreen screen, TextButton.TextButtonStyle tbStyle) {
        //setDebug(true);
        Sprite image = new Sprite(new Texture("menu.png"));
        image.setColor(C.GRAY);
        image.setSize(ICON_SIZE, ICON_SIZE);
        icon = new ImageButton(new SpriteDrawable(image));
        icon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(list.isVisible()) hide();
                else show();
            }
        });
        add(icon).size(ICON_SIZE).right();
        row();

        list = new Table();
        list.defaults().expand().fill();
        //list.setDebug(true);
        add(list).width(LIST_WIDTH);

        tb_theme = new TextButton("", tbStyle);
        tb_theme.getLabel().setAlignment(Align.left);
        if(screen.getTheme() == C.DARK) tb_theme.setText(ENTRIES[0]);
        else tb_theme.setText(ENTRIES[1]);
        tb_theme.pad(UIStage.PAD/2);
        tb_theme.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (screen.getTheme() == C.DARK) {
                    screen.setTheme(C.LIGHT);
                    tb_theme.setText(ENTRIES[1]);
                } else {
                    screen.setTheme(C.DARK);
                    tb_theme.setText(ENTRIES[0]);
                }
            }
        });

        tb_ads = new TextButton(ENTRIES[2], tbStyle);
        tb_ads.getLabel().setAlignment(Align.left);
        tb_ads.pad(UIStage.PAD/2);

        tb_info = new TextButton(ENTRIES[3], tbStyle);
        tb_info.getLabel().setAlignment(Align.left);
        tb_info.pad(UIStage.PAD/2);

        tb_donate = new TextButton(ENTRIES[4], tbStyle);
        tb_donate.getLabel().setAlignment(Align.left);
        tb_donate.pad(UIStage.PAD/2);

        list.add(tb_theme); list.row();
        list.add(tb_ads); list.row();
        list.add(tb_info); list.row();
        list.add(tb_donate);

        hide();
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

        tb_theme.getStyle().fontColor = font_color;
        tb_ads.getStyle().fontColor = font_color;
        tb_info.getStyle().fontColor = font_color;
        tb_donate.getStyle().fontColor = font_color;
    }
}
