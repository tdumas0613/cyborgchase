package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.CyborgChase;


public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    private Texture tutorialBtn;
    private long startTime;
    private long endTime;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        //define game background and play button
        background = new Texture("menubackground.png");
        //playBtn = new Texture("playbtn.png");
        //link to the tutorial activity
        tutorialBtn = new Texture("playbtn.png");
        startTime = TimeUtils.millis();
    }


    @Override
    public void handleInput() {

        /*if user touches screen
        if(Gdx.input.justTouched()){
            gsm.set(new TutorialState(gsm));
            //dispose textures to prevent memory leaks

        }*/
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
        //draws play button in middle of screen
        //sb.draw(playBtn, (CyborgChase.WIDTH/2) - (playBtn.getWidth()/2), CyborgChase.HEIGHT/2);
        //closes box
        if (TimeUtils.millis()>(startTime+2000)){
            gsm.set(new TutorialState(gsm));
        }
        sb.end();
    }

    @Override
    //dispose of background and button object after menu state is left
    public void dispose() {
        background.dispose();
        //playBtn.dispose();
        tutorialBtn.dispose();
        endTime = TimeUtils.millis();
        System.out.println("Menu State Disposed");
    }
}
