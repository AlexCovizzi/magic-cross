package com.duast.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 10/8/16.
 */

public class Square extends Actor {
    public static int SIZE;

    private Sprite sprite;

    public Square() {
        SIZE = ((C.WIDTH - 2*C.PAD_LR - 2*C.DIST)/3 - (C.SS-1)*C.DIST)/C.SS;
        initSprite();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        sprite.setPosition(getX(), getY());
        sprite.setColor(getColor());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        sprite.draw(batch);
    }

    private void initSprite() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite = new Sprite(texture);
        sprite.setBounds(0, 0, SIZE, SIZE);
        pixmap.dispose();
    }

    /* setters */
    public void setCoordinates(Coordinates coords) {
        setCoordinates(coords.x, coords.y);
    }

    public void setCoordinates(int x, int y) {
        setPosition(C.PAD_LR+x*(SIZE+C.DIST),
                C.PAD_DOWN+y*(SIZE+C.DIST));
    }

    /* getters */
    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }
}
