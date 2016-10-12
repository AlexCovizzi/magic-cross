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
    private static final int PAD = C.WIDTH / 40;
    private static final int WIDTH = C.WIDTH / 8;
    private static final int HEIGHT = C.HEIGHT / 24;
    private static final float FONT_SCALE = C.WIDTH/1536f;

    private GameScreen screen;
    private TextButton menu, shuffle, n_sq, time;
    private float cs=0;
    private int s=0, m=0;

    public HudStage(final GameScreen screen) {
        this.screen = screen;

        setViewport(new FillViewport(C.WIDTH, C.HEIGHT, screen.getCamera()));

        Skin skin = new Skin(Gdx.files.internal("skin.json"));
        for(int i=0; i<skin.getFont("default").getRegions().size; i++) {
            skin.getFont("default").getRegions().get(i).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        Table table = new Table();
        table.top().right();
        table.setFillParent(true);

        menu = new TextButton("Menu", skin, "default");
        menu.getLabel().setFontScale(FONT_SCALE);
        shuffle = new TextButton("Shuffle", skin, "default");
        shuffle.getLabel().setFontScale(FONT_SCALE);
        shuffle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGame().getSquares().shuffle();
                cs=0; s=0; m=0;
            }
        });
        n_sq = new TextButton(""+C.SS, skin, "default");
        n_sq.getLabel().setFontScale(FONT_SCALE);
        n_sq.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                C.SS+=1;
                if(C.SS == 5) C.SS = 2;
                screen.getGame().reset();
                cs=0; s=0; m=0;
                time.setText(toStr(m)+":"+toStr(s)+":"+toStr((int)cs));
            }
        });
        time = new TextButton(toStr(m)+":"+toStr(s)+":"+toStr((int)cs), skin, "label");
        time.getLabel().setFontScale(FONT_SCALE);

        table.add(time).size(WIDTH*1.2f, HEIGHT).pad(PAD, PAD/2, PAD, PAD/2);
        table.add(n_sq).size(WIDTH, HEIGHT).pad(PAD, PAD/2, PAD, PAD/2);
        table.add(shuffle).size(WIDTH, HEIGHT).pad(PAD, PAD/2, PAD, PAD/2);
        table.add(menu).size(WIDTH, HEIGHT).pad(PAD, PAD/2, PAD, PAD);

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

            time.setText(toStr(m)+":"+toStr(s)+":"+toStr((int)cs));
        }
        n_sq.setText(""+C.SS);
    }

    private String toStr(int i) {
        String s = ""+i;
        if(i<10) s = "0"+i;
        return s;
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
