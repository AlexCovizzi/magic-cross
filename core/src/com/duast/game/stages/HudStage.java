package com.duast.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
    private static final int WIDTH = C.WIDTH / 8;
    private static final int HEIGHT = C.HEIGHT / 24;
    private static final int PAD_TOP = PAD + (PAD + LABEL_SIZE + N_SIZE - HEIGHT) / 2;
    private static final Color COL = Color.GRAY;

    private GameScreen screen;
    private Label time;
    private TextButton menu, shuffle, n_sq;
    private float cs=0;
    private int s=0, m=0;

    public HudStage(final GameScreen screen) {
        this.screen = screen;

        setViewport(new FillViewport(C.WIDTH, C.HEIGHT, screen.getCamera()));

        Skin skin = new Skin(Gdx.files.internal("skin.json"));

        Table table = new Table();
        table.top().right();
        table.setFillParent(true);

        /*Texture tMenu = new Texture("menu.png");
        tMenu.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        skin.add("menu", tMenu);

        Texture tShuffle = new Texture("shuffle.png");
        tShuffle.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        skin.add("shuffle", tShuffle);

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
        });*/

        menu = new TextButton("Menu", skin, "default");
        shuffle = new TextButton("Shuffle", skin, "default");
        shuffle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGame().getSquares().shuffle();
                cs=0; s=0; m=0;
            }
        });
        n_sq = new TextButton(""+C.SS, skin, "default");
        n_sq.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                C.SS+=1;
                if(C.SS == 7) C.SS = 1;
                screen.getGame().reset();
                cs=0; s=0; m=0;
                time.setText(m+":"+s+":"+(int)cs);
            }
        });
        time = new Label(m+":"+s+":"+cs, skin, "default", Color.DARK_GRAY);

        table.add(time).size(WIDTH, HEIGHT).pad(PAD_TOP, PAD, PAD, PAD);
        table.add(n_sq).size(WIDTH, HEIGHT).pad(PAD_TOP, PAD, PAD, PAD);
        table.add(shuffle).size(WIDTH, HEIGHT).pad(PAD_TOP, PAD, PAD, PAD);
        table.add(menu).size(WIDTH, HEIGHT).pad(PAD_TOP, PAD, PAD, PAD);

        addActor(table);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(!screen.getGame().getSquares().checkWin()) {
            cs += delta*100;
            if(cs>99) {
                s+=1;
                cs=0;
            }
            if(s>59) {
                m+=1;
                s=0;
            }

            time.setText(m+":"+s+":"+(int)cs);
        }
        n_sq.setText(""+C.SS);
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
