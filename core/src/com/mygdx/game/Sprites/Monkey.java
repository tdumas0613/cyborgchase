package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

import javax.xml.soap.Text;

/**
 * Created by taylordumas on 12/2/15.
 */
public class Monkey {
    public static final int MONKEYWIDTH = 75;
    private static final int FLUCTUATION = 300;
    //private Texture topMonkey;
    private Texture texture;
    private Vector2 posTopMonkey;
    private Rectangle boundsTop;
    private Random rand;
    private Animation monkeyAnimation;

    //constructor
    public Monkey(float x){

        texture = new Texture("monkeyanimation.png");
        monkeyAnimation = new Animation(new TextureRegion(texture), 2, 0, 0.5f);
        rand = new Random();
        //topmonkey position varies on the y axis
        posTopMonkey = new Vector2(x, rand.nextInt(FLUCTUATION));
        boundsTop = new Rectangle(posTopMonkey.x, posTopMonkey.y, texture.getWidth()/2, texture.getHeight());

    }


    public TextureRegion getMonkey() {
        return monkeyAnimation.getFrame();
    }

    public Vector2 getPosTopMonkey() {

        return posTopMonkey;
    }
    //randomly updates position of each monkey in array --- monkeys actually generated in render function of playstate
    public void reposition(float x){
        posTopMonkey.set(x, rand.nextInt(FLUCTUATION));
        boundsTop.setPosition(posTopMonkey.x, posTopMonkey.y);

    }

    public boolean collides(Rectangle player){

        return player.overlaps(boundsTop);
    }

    public void dispose(){
        texture.dispose();
    }
    public void update(float dt){
        monkeyAnimation.update(dt);
    }
}
