package com.duast.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.duast.game.utils.C;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 10/4/16.
 */

public class Square {
    public static final int SIZE = ((C.WIDTH - 2*C.PAD_LR - 2*C.DIST)/3 - 8*C.DIST)/3;

    private Coordinates coords;
    private Vector2 position;
    private Sprite sprite;

    public Square() {
        coords = new Coordinates();
        position = new Vector2();
        initSprite();
    }

    public void update() {
        setPosition(C.PAD_LR+C.DIST+coords.x*(SIZE+3*C.DIST),
                    C.PAD_DOWN+C.DIST+coords.y*(SIZE+3*C.DIST));
        sprite.setPosition(position.x, position.y);
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
        pixmap.dispose();
    }

    /* setters */
    public void setCoordinates(int x, int y) {
        coords.set(x, y);
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void setColor(Color color) {
        sprite.setColor(color);
    }

    /* getters */
    public Coordinates getCoordinates() {
        return coords;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Color getColor() {
        return sprite.getColor();
    }
}
