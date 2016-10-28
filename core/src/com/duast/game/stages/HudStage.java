package com.duast.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.duast.game.screens.GameScreen;
import com.duast.game.utils.C;
import com.duast.game.utils.MySelectBox;

/**
 * Created by Alex on 16/02/2016.
 */
public class HudStage extends Stage {
    private static final float PAD = C.WIDTH / 20;
    private static final float FONT_SCALE = C.WIDTH/800f;

    private GameScreen screen;
    private Label time, moves;
    private MySelectBox diff;
    private float cs=0;
    private int s=0, m=0;

    public HudStage(final GameScreen screen) {
        this.screen = screen;

        setViewport(new FillViewport(C.WIDTH, C.HEIGHT, screen.getCamera()));
        Skin skin = new Skin(Gdx.files.internal("skin.json"));
        for(int i=0; i<skin.getFont("default").getRegions().size; i++)
            skin.getFont("default").getRegions().get(i).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Table table = new Table();
        table.top().left();
        table.setFillParent(true);
        //table.setDebug(true);
        addActor(table);

        Table table_left = new Table();
        //table_left.setDebug(true);
        addActor(table_left);

        Label l_time = new Label("Time", skin);
        l_time.setFontScale(FONT_SCALE/2);
        l_time.setColor(C.DARK);

        Label l_moves = new Label("Moves", skin);
        l_moves.setFontScale(FONT_SCALE/2);
        l_moves.setColor(C.DARK);

        time = new Label("", skin, "default");
        time.setFontScale(FONT_SCALE);
        time.setColor(C.DARK);

        moves = new Label("0", skin, "default");
        moves.setFontScale(FONT_SCALE);
        moves.setColor(C.DARK);

        table_left.add(l_time).padRight(PAD);
        table_left.add(l_moves);
        table_left.row();
        table_left.add(time).padRight(PAD);
        table_left.add(moves);


        Table table_central = new Table();
        //table_left.setDebug(true);
        addActor(table_central);

        TextButton new_game = new TextButton("New game", skin);
        new_game.pad(PAD/2);
        new_game.getLabel().setFontScale(FONT_SCALE/2);
        new_game.getLabel().setColor(C.WHITE);
        new_game.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGame().init();
                cs=0; s=0; m=0;
            }
        });

        diff = new MySelectBox(C.WIDTH/6, skin, "Easy", "Medium", "Hard");

        table_central.add(new_game);
        table_central.row();
        table_central.add(diff);

        final MySelectBox menu = new MySelectBox(skin, C.WIDTH/14, "menu.png", "menu.png");
        menu.setWidth(C.WIDTH/6);
        menu.add("ciao", 0);
        menu.add("ciao2", 1);

        table.add(table_left).top().pad(PAD/2);
        table.add(table_central).top().pad(PAD/2);
        table.add(menu).expandX().top().right().pad(PAD/2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(true) {
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

        screen.getGame().setNumSquaresInSector(diff.getSelected()+2);
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
