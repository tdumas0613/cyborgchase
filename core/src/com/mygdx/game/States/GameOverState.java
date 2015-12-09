package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CyborgChase;


public class GameOverState extends State{
    private Texture background;
    private Texture tryAgainBtn;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        //readjusting camera
        cam.setToOrtho(false, 1280, 800);
        cam.update();
        //creates tutorial background and a back button
        background = new Texture("gameoverbackground.png");
        //tryAgainBtn = new Texture("playbtn.png");
    }


    @Override
    public void handleInput() {
        //if user touches screen
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            //dispose textures to prevent memory leaks
        }
    }
    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //opens box and draws image starting in bottom left hand corner
        sb.begin();
        //readjusting camera
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background, 0, 0, 1280, 800);
        //closes box
        sb.end();
    }

    @Override
    //dispose of background and button object after menu state is left
    public void dispose() {
        background.dispose();
        System.out.println("Gameover State Disposed");
    }
}