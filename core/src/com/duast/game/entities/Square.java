package com.duast.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

import java.util.ArrayList;

/**
 * Created by alex on 10/4/16.
 */

public class Square {
    public static final int SIZE = ((C.WIDTH - 2*C.PAD_LR - 2*C.DIST)/3 - (C.SS-1)*C.DIST)/C.SS;

    private Sprite sprite;

    public Square() {
        initSprite();
    }

    public void update(float delta) {

    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
        sprite.getTexture().dispose();
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
        sprite.setPosition(C.PAD_LR+x*(SIZE+C.DIST),
                C.PAD_DOWN+y*(SIZE+C.DIST));
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void setColor(Color color) {
        sprite.setColor(color);
    }

    /* getters */

    public Vector2 getPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }

    public Color getColor() {
        return sprite.getColor();
    }
}
