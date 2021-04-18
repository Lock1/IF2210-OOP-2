package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.EngimonScreen;
import com.mygdx.game.screens.InventoryScreen;
import com.mygdx.game.screens.MainMenuScreen;

public class MyGdxGame extends Game {

	public void create () {
		this.setScreen(new InventoryScreen(this));
	}

	public void render () {
		super.render();
	}

	public void dispose () {

	}
}
