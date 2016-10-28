package com.duast.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

/**
 * Created by alex on 10/22/16.
 */

public class MyCheckBox extends TextButton {
    private Image image;
    private Cell imageCell;
    private MyCheckBoxStyle style;

    public MyCheckBox (String text, Skin skin) {
        this(text, skin.get(MyCheckBoxStyle.class));
    }

    public MyCheckBox (String text, Skin skin, String styleName) {
        this(text, skin.get(styleName, MyCheckBoxStyle.class));
    }

    public MyCheckBox (String text, MyCheckBoxStyle style) {
        super(text, style);
        clearChildren();
        Label label = getLabel();
        add(label);
        label.setAlignment(Align.left);
        imageCell = add(image = new Image(style.checkboxOff, Scaling.none));
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void setStyle (ButtonStyle style) {
        if (!(style instanceof MyCheckBoxStyle)) throw new IllegalArgumentException("style must be a CheckBoxStyle.");
        super.setStyle(style);
        this.style = (MyCheckBoxStyle)style;
    }

    /** Returns the checkbox's style. Modifying the returned style may not have an effect until {@link #setStyle(ButtonStyle)} is
     * called. */
    public MyCheckBoxStyle getStyle () {
        return style;
    }

    public void draw (Batch batch, float parentAlpha) {
        Drawable checkbox = null;
        if (isDisabled()) {
            if (isChecked() && style.checkboxOnDisabled != null)
                checkbox = style.checkboxOnDisabled;
            else
                checkbox = style.checkboxOffDisabled;
        }
        if (checkbox == null) {
            if (isChecked() && style.checkboxOn != null)
                checkbox = style.checkboxOn;
            else if (isOver() && style.checkboxOver != null && !isDisabled())
                checkbox = style.checkboxOver;
            else
                checkbox = style.checkboxOff;
        }
        image.setDrawable(checkbox);
        super.draw(batch, parentAlpha);
    }

    public Image getImage () {
        return image;
    }

    public Cell getImageCell () {
        return imageCell;
    }

    /** The style for a select box, see {@link MyCheckBox}.
     * @author Nathan Sweet */
    static public class MyCheckBoxStyle extends TextButtonStyle {
        public Drawable checkboxOn, checkboxOff;
        /** Optional. */
        public Drawable checkboxOver, checkboxOnDisabled, checkboxOffDisabled;

        public MyCheckBoxStyle () {
        }

        public MyCheckBoxStyle (Drawable checkboxOff, Drawable checkboxOn, BitmapFont font, Color fontColor) {
            this.checkboxOff = checkboxOff;
            this.checkboxOn = checkboxOn;
            this.font = font;
            this.fontColor = fontColor;
        }

        public MyCheckBoxStyle (MyCheckBoxStyle style) {
            super(style);
            this.checkboxOff = style.checkboxOff;
            this.checkboxOn = style.checkboxOn;
            this.checkboxOver = style.checkboxOver;
            this.checkboxOffDisabled = style.checkboxOffDisabled;
            this.checkboxOnDisabled = style.checkboxOnDisabled;
        }
    }
}
