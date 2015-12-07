package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
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
    private Texture topMonkey;
    private Vector2 posTopMonkey;
    private Rectangle boundsTop;
    private Random rand;

    //constructor
    public Monkey(float x){
        topMonkey = new Texture("monkey.png");
        rand = new Random();
        //topmonkey position varies on the x axis
        posTopMonkey = new Vector2(x, rand.nextInt(FLUCTUATION));
        //posTopMonkey = new Vector2(x + 75 + rand.nextInt(FLUCTUATION), 0);
        boundsTop = new Rectangle(posTopMonkey.x, posTopMonkey.y, topMonkey.getWidth(), topMonkey.getHeight());
    }


    public Texture getTopMonkey() {
        return topMonkey;
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
        topMonkey.dispose();
    }
}
