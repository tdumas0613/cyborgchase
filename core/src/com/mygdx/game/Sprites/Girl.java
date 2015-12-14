package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.CyborgChase;

/**
 * Created by taylordumas on 12/2/15.
 */
public class Girl {

    private static final int GRAVITY = -25;
    private static final int MOVEMENT = 275;
    private static final int STARTINGPOS = 200;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture girl;
    private Texture girlJump;
    private Texture girlReturn;

    //constructor
    public Girl(int x, int y){
        position = new Vector3(x, STARTINGPOS, 0);
        velocity = new Vector3(0,0,0);
        girl = new Texture("vivdown.png");
        girlReturn = new Texture("vivdown.png");
        girlJump = new Texture("vivup.png");
        bounds = new Rectangle(x, y, girl.getWidth(), 79);
    }



    //sends delta time to girl class -- allows position to be reset
    public void update(float dt, int i){
        if(position.y > 0) {
            //applies gravity to the y coordinate of sprite position upon update
            velocity.add(0, GRAVITY, 0);
        }
        //apply dt for effect over time
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 0){
            position.y = 0;
        }
        //attempt to create a ceiling
        /*if(position.y > CyborgChase.HEIGHT){
            position.y = CyborgChase.HEIGHT;
        }*/
        //reverses what we scaled previously so gravity can be applied again on next update
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
        //animation
        if(i % 2 == 0){
            girl = girlJump;
        }
        else{
            girl = girlReturn;
        }
    }

    public Vector3 getPosition() {

        return position;
    }

    public Texture getTexture() {

        return girl;
    }

    public void jump(){
        velocity.y = 300;

    }
    public Rectangle getBounds(){
        return bounds;
    }
    public void dispose(){
        girl.dispose();
    }
}
