package com.duast.game.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.duast.game.screens.GameScreen;
import com.duast.game.utils.C;

/**
 * Created by Alex on 16/02/2016.
 */
public class HudStage extends Stage {
    private static final int TABLE_SIZE = C.WIDTH / 10;
    private static final int FONT_SIZE = C.WIDTH / 20;
    private static final int LABEL_SIZE = C.WIDTH / 50;
    private static final int N_SIZE = C.WIDTH / 28;
    private static final int PAD = C.WIDTH / 40;
    private static final int SIZE = C.WIDTH / 16;
    private static final int PAD_TOP = PAD + (PAD + LABEL_SIZE + N_SIZE - SIZE) / 2;
    private static final Color COL = Color.GRAY;

    private GameScreen screen;
    private ImageButton menu, shuffle, diff, time;

    public HudStage(final GameScreen screen) {
        this.screen = screen;

        setViewport(new FillViewport(C.WIDTH, C.HEIGHT, screen.getCamera()));

        Skin skin = new Skin();

        Texture tMenu = new Texture("menu.png");
        tMenu.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        skin.add("menu", tMenu);

        Texture tShuffle = new Texture("shuffle.png");
        tShuffle.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        skin.add("shuffle", tShuffle);

        Table table = new Table();
        table.top().right();
        table.setFillParent(true);

        menu = new ImageButton(new ImageButton.ImageButtonStyle(skin.newDrawable("menu", COL), skin.newDrawable("menu", COL),skin.newDrawable("menu", COL),skin.newDrawable("menu", COL),skin.newDrawable("menu", COL),skin.newDrawable("menu", COL)));
        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //
            }
        });

        shuffle = new ImageButton(new ImageButton.ImageButtonStyle(skin.newDrawable("shuffle", COL), skin.newDrawable("shuffle", COL),skin.newDrawable("shuffle", COL),skin.newDrawable("shuffle", COL),skin.newDrawable("shuffle", COL),skin.newDrawable("shuffle", COL)));
        shuffle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGame().getSquares().shuffle();
            }
        });

        table.add(shuffle).size(SIZE).pad(PAD_TOP, PAD, PAD, PAD);
        table.add(menu).size(SIZE).pad(PAD_TOP, PAD, PAD, PAD);

        addActor(table);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
