package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.CyborgChase;
import com.mygdx.game.Sprites.Girl;
import com.mygdx.game.Sprites.Monkey;


public class PlayState extends State {
    private static final int MONKEYSPACING = 125;
    private static final int MONKEYCOUNT = 4;

    private Girl girl;
    private Texture bg;


    private Array<Monkey> monkeys;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        //girl creation & starting position (x,y)
        girl = new Girl(25, 0);
        //zooms in to display only a portion of the play state -- makes sprite appear larger
        cam.setToOrtho(false, CyborgChase.WIDTH/2, CyborgChase.HEIGHT/2);
        //creating background for play state
        bg = new Texture("background.png");

        monkeys = new Array<Monkey>();

        for(int i = 1; i <= MONKEYCOUNT; i++){
            monkeys.add(new Monkey(i * (MONKEYSPACING + Monkey.MONKEYWIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            girl.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        girl.update(dt);
        cam.position.x = girl.getPosition().x + 80;

        for(Monkey monkey : monkeys) {
            //This line is different than the line T created below
            if (cam.position.x - (cam.viewportWidth / 2) > monkey.getPosTopMonkey().x + monkey.getTopMonkey().getWidth()) {
                monkey.reposition(monkey.getPosTopMonkey().x + ((Monkey.MONKEYWIDTH + MONKEYSPACING) * MONKEYCOUNT));
            }

            if(monkey.collides(girl.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        //adjusts sprite batch to render within scope of the camera set up in PlayState method
        //objects will move into screen from out of player's scope
        //only draws things camera should be able to see
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //drawing background
        //done differently in tutorial, however bg did not fit tablet screen
        sb.draw(bg, 0, 0, cam.viewportWidth, cam.viewportHeight);
        sb.draw(girl.getTexture(), girl.getPosition().x, girl.getPosition().y);
        for(Monkey monkey : monkeys) {
            sb.draw(monkey.getTopMonkey(), monkey.getPosTopMonkey().x, monkey.getPosTopMonkey().y);
            sb.draw(monkey.getBottomMonkey(), monkey.getPosBottomMonkey().x, monkey.getPosBottomMonkey().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
