package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.GameStateManager;
import com.mygdx.game.States.MenuState;

public class CyborgChase extends ApplicationAdapter {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 800;
	public static final String TITLE = "CyborgChase";
	private GameStateManager gsm;
	//one per game
	private SpriteBatch batch;
	private int score;


	public CyborgChase(){
		this.score = 0;
	}

	public int getScore(){
		return this.score;
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		score = 0;
		//for transitioning between menu state, game state, and end state
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		//pushes a new menu state onto the stack
		//can now be manipulated in menustate.java
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}
}
