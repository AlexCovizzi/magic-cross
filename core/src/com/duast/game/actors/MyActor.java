package com.duast.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.duast.game.utils.Coordinates;

/**
 * Created by alex on 9/22/16.
 */

public class MyActor extends Actor {

    private Coordinates position;
    private Sprite sprite;

    public MyActor() {
        this.position = new Coordinates();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite = new Sprite(texture);
        pixmap.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        sprite.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }

    /* setters */
    public void setPosition(Coordinates position) {
        setPosition(position.getX(), position.getY());
    }

    public void setPosition(int x, int y) {
        this.position.set(x, y);
        setUserObject(position);
    }

    public void setXY(float x, float y) {
        setX(x); setY(y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        sprite.setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        sprite.setColor(color);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /* getters */
    public Coordinates getPosition() {
        return position;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
