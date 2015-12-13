package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CyborgChase;


public class GameOverState extends State{
    private Texture background;
    private Texture tryAgainBtn;
    BitmapFont font;




    public GameOverState(GameStateManager gsm) {
        super(gsm);
        //readjusting camera
        cam.setToOrtho(false, 1280, 800);
        cam.update();
        background = new Texture("loserbackground.png");

        //System.out.println("Final Score: " + this.score);

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
        BitmapFont font = new BitmapFont();
        //opens box and draws image starting in bottom left hand corner
        sb.begin();
        //readjusting camera
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background, 0, 0, 1280, 800);

        //scoreDisplay.draw(sb, scoreString, (cam.viewportWidth/2), cam.viewportHeight/2);
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