package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.CyborgChase;
import com.mygdx.game.Sprites.Girl;
import com.mygdx.game.Sprites.Monkey;


public class PlayState extends State {
    //distance between each monkey
    private static final float MONKEYSPACING = 250;
    private static final int MONKEYCOUNT = 4;
    private static final int GROUNDYOFFSET = -50;
    private int i = 0;
    private Girl girl;
    private Texture bg;
    private Texture ground;
    private Array<Monkey> monkeys;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        //girl creation & starting position (x,y)
        girl = new Girl(25, 0);


        //zooms in to display only a portion of the play state -- makes sprite appear larger
        cam.setToOrtho(false, CyborgChase.WIDTH / 2, CyborgChase.HEIGHT / 2);
        //creating background for play state
        bg = new Texture("smallpark2.png");
        ground = new Texture("ground.png");
        //array of monkeys to be rendered
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
            //counter for animation
            i++;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        //updateGround();
        //pass in i for animation counter
        girl.update(dt, i);
        //updating monkey animation within array
        for(Monkey monkey : monkeys) {
            monkey.update(dt);
        }
        cam.position.x = girl.getPosition().x + 275;
        //what to do with monkey image after it exits left side of screen
        for(int i=0; i < monkeys.size; i++) {
            Monkey monkey = monkeys.get(i);
            //if monkey sprite goes off of left side of screen:
                //reposition monkey to back of line and up the score counter by one
            if (cam.position.x - (cam.viewportWidth / 2) > monkey.getPosTopMonkey().x + 75) {
                monkey.reposition(monkey.getPosTopMonkey().x + ((Monkey.MONKEYWIDTH + MONKEYSPACING) * MONKEYCOUNT));
                score = score+1;
                //System.out.println(score);
            }
            //if game ends, display game over screen
            if(monkey.collides(girl.getBounds())){
                //If collision with monkey detected send to gameOverScreen
                gsm.set(new GameOverState(gsm));
            } if(girl.getPosition().y <= 75 + GROUNDYOFFSET){
                gsm.set(new GameOverState(gsm));
            } if((girl.getPosition().y+79) >= CyborgChase.HEIGHT/2){
                //If collision with ceiling detected send to gameOverScreen
                gsm.set(new GameOverState(gsm));
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
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        for(Monkey monkey : monkeys) {
            sb.draw(monkey.getMonkey(), monkey.getPosTopMonkey().x, monkey.getPosTopMonkey().y);
        }
        sb.draw(girl.getTexture(), girl.getPosition().x, girl.getPosition().y);
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


}
