package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.GameStateManager;
import com.mygdx.game.States.MenuState;

public class CyborgChase extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "CyborgChase";
	private GameStateManager gsm;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		//one spritebatch per project -- contains all sprites to be used
		batch = new SpriteBatch();
		//for transitioning between menu state, game state, and end state
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		//pushes a new menu state onto the stack
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
