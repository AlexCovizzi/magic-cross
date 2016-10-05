package com.duast.game.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.duast.game.utils.C;

/**
 * Created by Alex on 24/08/16.
 */
public class GameCamera extends OrthographicCamera {

    public GameCamera() {
        setToOrtho(false, C.WIDTH, C.HEIGHT);
        position.set(viewportWidth / 2, viewportHeight / 2, 0);
    }

}
