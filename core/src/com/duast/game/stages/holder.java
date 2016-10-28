package com.duast.game.stages;

/**
 * Created by alex on 10/23/16.
 */

public class holder {
    /*
    public class HudStage extends Stage {
    private static final int PAD = C.WIDTH / 40;
    private static final int WIDTH = C.WIDTH / 8;
    private static final int HEIGHT = C.HEIGHT / 20;
    private static final float FONT_SCALE = C.WIDTH/1344f;

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
        n_sq = new TextButton(""+screen.getGame().getNumSquaresInSector(), skin, "default");
        n_sq.getLabel().setFontScale(FONT_SCALE);
        n_sq.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int n = screen.getGame().getNumSquaresInSector();
                n++;
                screen.getGame().setNumSquaresInSector(n);
                if(n == 5) screen.getGame().setNumSquaresInSector(2);
                screen.getGame().init();
                cs=0; s=0; m=0;
                time.setText(toStr(m)+":"+toStr(s)+":"+toStr((int)cs));
            }
        });
        time = new TextButton(toStr(m)+":"+toStr(s)+":"+toStr((int)cs), skin, "label");
        time.getLabel().setFontScale(FONT_SCALE);

        table.add(time).size(WIDTH*1.2f, HEIGHT).pad(PAD, PAD/2, 0, PAD/2);
        table.add(n_sq).size(WIDTH, HEIGHT).pad(PAD, PAD/2, 0, PAD/2);
        table.add(shuffle).size(WIDTH, HEIGHT).pad(PAD, PAD/2, 0, PAD/2);
        table.add(menu).size(WIDTH, HEIGHT).pad(PAD, PAD/2, 0, PAD);

        addActor(table);

        final Table mainTable = new Table();

        ScrollPane scrollPane = new ScrollPane(mainTable);
        scrollPane.setFillParent(false);

        TextButton b1 = new TextButton("button 1", skin);
        b1.getLabel().setFontScale(FONT_SCALE);
        TextButton b2 = new TextButton("button 2", skin);
        b2.getLabel().setFontScale(FONT_SCALE);
        final MyCheckBox cb1 = new MyCheckBox("dark", skin);
        cb1.getLabel().setFontScale(FONT_SCALE);
        cb1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setDark(cb1.isChecked());
            }
        });

        mainTable.defaults().width(120);
        mainTable.add(b1).row();
        mainTable.add(b2).row();
        mainTable.add(cb1).row();
        mainTable.setVisible(false);

        table.row();
        table.add(mainTable).colspan(4).right().padRight(PAD);

        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!mainTable.isVisible()) mainTable.setVisible(true);
            }
        });

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(mainTable.isVisible()) mainTable.setVisible(false);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
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
        n_sq.setText(""+screen.getGame().getNumSquaresInSector());
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
     */
}
