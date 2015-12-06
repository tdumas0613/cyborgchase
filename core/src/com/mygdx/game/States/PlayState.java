package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.CyborgChase;
import com.mygdx.game.Sprites.Girl;
import com.mygdx.game.Sprites.Monkey;


public class PlayState extends State {
    //distance between each monkey
    private static final float MONKEYSPACING = 400;
    private static final int MONKEYCOUNT = 4;
    private static final int GROUNDYOFFSET = -50;

    private Girl girl;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Array<Monkey> monkeys;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        //girl creation & starting position (x,y)
        girl = new Girl(25, 0);
        //zooms in to display only a portion of the play state -- makes sprite appear larger
        cam.setToOrtho(false, CyborgChase.WIDTH/2, CyborgChase.HEIGHT/2);
        //creating background for play state
        bg = new Texture("background.png");
        ground = new Texture("land.png");

        //cam.position.x line is probably wrong --- tutorial 12
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUNDYOFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2)+ ground.getWidth(), GROUNDYOFFSET);

        monkeys = new Array<Monkey>();
        for(int i = 1; i <= MONKEYCOUNT; i++){
            monkeys.add(new Monkey(i * (MONKEYSPACING + Monkey.MONKEYWIDTH)));
        }
    }

    //call jump function on screen touch
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            girl.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        girl.update(dt);
        cam.position.x = girl.getPosition().x + 275;
        //what to do with monkey image after it exits left side of screen
        for(int i=0; i < monkeys.size; i++) {
            Monkey monkey = monkeys.get(i);
            //general forumula for random boundaries: (int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);
            if (cam.position.x - (cam.viewportWidth / 2) > monkey.getPosTopMonkey().x + monkey.getTopMonkey().getWidth()) {
                monkey.reposition(monkey.getPosTopMonkey().x + ((Monkey.MONKEYWIDTH + MONKEYSPACING) * MONKEYCOUNT));
            }
            //if game ends, start new PlayState
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
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.draw(girl.getTexture(), girl.getPosition().x, girl.getPosition().y);
        for(Monkey monkey : monkeys) {
            sb.draw(monkey.getTopMonkey(), monkey.getPosTopMonkey().x, monkey.getPosTopMonkey().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        girl.dispose();
        ground.dispose();
        for(Monkey monkey : monkeys){
            monkey.dispose();
        }
        System.out.println("Play State Disposed");
    }

    private void updateGround(){
        //cam.position.x line is probably wrong
        if(cam.position.x > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
