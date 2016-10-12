package com.duast.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 10/8/16.
 */

public class Square extends Actor {
    public static int SIZE;

    private NinePatchDrawable npd;

    public Square() {
        SIZE = ((C.WIDTH - 2*C.PAD_LR - 2*C.DIST)/3 - (C.SS-1)*C.DIST)/C.SS;
        initSprite();
    }

    private void initSprite() {
        Texture texture = new Texture("rect.png");
        NinePatch np = new NinePatch(texture, 24, 24, 24, 24);
        npd = new NinePatchDrawable(np);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        npd = npd.tint(getColor());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        npd.draw(batch, getX()-SIZE*(getScaleX()-1)/2, getY()-SIZE*(getScaleY()-1)/2, SIZE*getScaleX(), SIZE*getScaleY());
    }

    /* setters */
    public void setCoordinates(Coordinates coords) {
        setCoordinates(coords.x, coords.y);
    }

    public void setCoordinates(int x, int y) {
        setPosition(C.PAD_LR+x*(SIZE+C.DIST),
                C.PAD_DOWN+y*(SIZE+C.DIST));
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        npd = npd.tint(getColor());
    }

    /* getters */
    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }
}
