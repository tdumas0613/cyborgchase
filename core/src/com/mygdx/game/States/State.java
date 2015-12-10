package com.mygdx.game.States;

//import statements
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    //camera dictates how much of game window is viewable on device
    protected OrthographicCamera cam;
    protected OrthographicCamera gameOverCam;
    protected Vector3 mouse;
    protected GameStateManager gsm;
    int score;
    String scoreString;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        gameOverCam = new OrthographicCamera();
        mouse = new Vector3();


    }
    protected abstract void handleInput();
    //dt = delta time -- time between frames being rendered
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    //disposes of sprites after menu state is left
    public abstract void dispose();
}
