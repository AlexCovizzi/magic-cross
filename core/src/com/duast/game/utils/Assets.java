package com.duast.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by alex on 11/18/16.
 */

public class Assets {
    public static final int FONT_SIZE = C.DEVICE_HEIGHT/44; //C.DEVICE_WIDTH/24;
    public static SaveFile SAVE_FILE;
    public static Texture TEXTURE_SQUARE;
    public static Texture TEXTURE_PIXEL;
    public static BitmapFont FONT_SMALL;
    public static BitmapFont FONT_MED;
    public static BitmapFont FONT_BIG;

    public static void load() {
        TEXTURE_SQUARE = new Texture(Gdx.files.internal("rect.png"));
        TEXTURE_PIXEL = new Texture(Gdx.files.internal("square.jpg"));
        SAVE_FILE = new SaveFile();
        loadFont();
    }

    private static void loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.size = FONT_SIZE;
        FONT_SMALL = generator.generateFont(param);

        param.size = (int) (FONT_SIZE*1.25f);
        FONT_MED = generator.generateFont(param);

        param.size = FONT_SIZE*3;
        FONT_BIG = generator.generateFont(param);

        generator.dispose();
    }
}
