package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CyborgChase;


public class TutorialState extends State{
    private Texture background;
    private Texture backBtn;
    
    public TutorialState(GameStateManager gsm) {
        super(gsm);
        //creates tutorial background and a back button
        background = new Texture("tutorialbackground.png");

        //backBtn = new Texture("playbtn.png");
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
        //rendering background and button for menu state
        //opens box and draws image starting in bottom left hand corner
        sb.begin();
        sb.draw(background, 0, 0, CyborgChase.WIDTH, CyborgChase.HEIGHT);
        //draws back button in middle of screen
        //sb.draw(backBtn, (CyborgChase.WIDTH/2) - (backBtn.getWidth()/2), CyborgChase.HEIGHT/2);
        //closes box
        sb.end();
    }

    @Override
    //dispose of background and button object after menu state is left
    public void dispose() {
        background.dispose();
        //backBtn.dispose();
        System.out.println("Tutorial State Disposed");
    }
}