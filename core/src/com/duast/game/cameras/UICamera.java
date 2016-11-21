package com.duast.game.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.duast.game.utils.C;

/**
 * Created by alex on 11/18/16.
 */

public class UICamera extends OrthographicCamera {

    public UICamera() {
        setToOrtho(false, C.DEVICE_WIDTH, C.DEVICE_HEIGHT);
        position.set(viewportWidth / 2, viewportHeight / 2, 0);
    }
}
