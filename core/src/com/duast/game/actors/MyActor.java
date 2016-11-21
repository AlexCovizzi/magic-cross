package com.duast.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.duast.game.utils.Assets;

/**
 * Created by alex on 10/21/16.
 */

public class MyActor extends Actor {

    private NinePatchDrawable npd;

    public MyActor() {
        NinePatch np = new NinePatch(Assets.TEXTURE_SQUARE, 4, 4, 4, 4);
        npd = new NinePatchDrawable(np);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        npd.draw(batch, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        npd = npd.tint(getColor());
    }
}
