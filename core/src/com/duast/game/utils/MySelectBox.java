package com.duast.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by alex on 10/26/16.
 */

public class MySelectBox extends Table {
    private static final float FONT_SCALE = C.WIDTH/800f;
    private Skin skin;
    private TextButton title;
    private Table list;
    private float width;
    private int selected = 0;

    public MySelectBox(float width, Skin skin, String... texts) {
        this.width = width;
        this.skin = skin;
        title = new TextButton(texts[0], skin);
        title.getLabel().setFontScale(FONT_SCALE/2);
        title.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(list.isVisible()) hide();
                else show();
            }
        });
        list = new Table();
        //list.setDebug(true);
        add(title).width(width);
        row();
        add(list);
        for(int i=0; i<texts.length; i++) {
            add(texts[i], i);
        }
        hide();
    }

    public MySelectBox(Skin skin, float size, String up, String down) {
        this.skin = skin;
        ImageButton ib = new ImageButton(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(up)))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(down)))));
        ib.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(list.isVisible()) hide();
                else show();
            }
        });
        list = new Table();
        list.setDebug(true);
        add(ib).size(size).right();
        row();
        add(list);
        hide();
    }

    public <T extends Actor> Cell<T> addCell(T actor) {
        list.row();
        return list.add(actor);
    }

    public Cell<TextButton> add(String text, final int i) {
        list.row();
        final TextButton tb = new TextButton(text, skin);
        tb.getLabel().setFontScale(FONT_SCALE/2);
        tb.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(list.isVisible()) hide();
                if(title!=null) title.setText(tb.getText().toString());
                selected = i;
            }
        });
        return list.add(tb).width(width);
    }

    public void show() {
        list.setVisible(true);
    }

    public void hide() {
        list.setVisible(false);
    }

    public void setWidth(float width) {
        this.width = width;
    }

    /* getters */
    public TextButton getTitleLabel() {
        return title;
    }

    public int getSelected() {
        return selected;
    }
}
