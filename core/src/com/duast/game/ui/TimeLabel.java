package com.duast.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.duast.game.stages.GameStage;
import com.duast.game.utils.Assets;
import com.duast.game.utils.C;

/**
 * Created by alex on 11/8/16.
 */

public class TimeLabel extends Label {
    private static final LabelStyle LABEL_STYLE = new LabelStyle(Assets.FONT_BIG, C.GRAY);

    private GameStage stage;
    private boolean count;
    private float cs;
    private int s, m;

    public TimeLabel(GameStage stage) {
        super("", LABEL_STYLE);
        this.stage = stage;
        init();
    }

    public void init() {
        String[] times = Assets.SAVE_FILE.getTime().split(":");
        m = Integer.parseInt(times[0]);
        s = Integer.parseInt(times[1]);
        count = false;
        setText(toStr(m) + ":" + toStr(s));
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(count) {
            if(stage.getCross().checkWin()) stop();

            cs += delta * 100;
            if (cs > 99) {
                s += 1;
                cs = 0;
            }
            if (s > 59) {
                m += 1;
                s = 0;
            }

            setText(toStr(m) + ":" + toStr(s));
        }
    }

    public void start() {
        count = true;
    }

    public void stop() {
        count = false;
    }

    public void reset() {
        m = 0;
        s = 0;
        cs = 0;
        count = false;
        setText("00:00");
    }

    private String toStr(int i) {
        String s = ""+i;
        if(i<10) s = "0"+i;
        return s;
    }

    public String get() {
        return getText().toString();
    }

    public void setTheme(int theme) {
        /*switch(theme) {
            case C.LIGHT: getStyle().fontColor = BLACK; break;
            case C.DARK: getStyle().fontColor = WHITE; break;
            default: getStyle().fontColor = BLACK;
        }*/
    }
}
