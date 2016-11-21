package com.duast.game;

import com.badlogic.gdx.Game;
import com.duast.game.screens.GameScreen;
import com.duast.game.utils.Assets;

public class Main extends Game
{
	public static final String VERSION = "0.0.1";
	public static final String LOG = "Main";

	@Override
	public void create () {
		Assets.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
}