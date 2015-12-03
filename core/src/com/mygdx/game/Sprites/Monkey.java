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
    public static final int MONKEYWIDTH = 300;

    private static final int FLUCTUATION = 130;
    private static final int MONKEYGAP = 100;
    private static final int LOWESTOPENING = 120;

    private Texture topMonkey;
    private Texture bottomMonkey;
    private Vector2 posTopMonkey;
    private Vector2 posBottomMonkey;
    private Rectangle boundsTop, boundsBot;
    private Random rand;


    public Monkey(float x){
        bottomMonkey = new Texture("monkey_big.png");
        topMonkey = new Texture("monkey_big.png");
        rand = new Random();


        posTopMonkey = new Vector2(x, rand.nextInt(FLUCTUATION) + MONKEYGAP + LOWESTOPENING);
        posBottomMonkey = new Vector2(x, posTopMonkey.y - MONKEYGAP - bottomMonkey.getHeight());
        //not typecasted in tutorial
        boundsTop = new Rectangle(posTopMonkey.x, posTopMonkey.y, topMonkey.getWidth(), topMonkey.getHeight());
        boundsBot = new Rectangle(posBottomMonkey.x, posBottomMonkey.y, bottomMonkey.getWidth(), bottomMonkey.getHeight());
    }


    public Texture getTopMonkey() {
        return topMonkey;
    }

    public Texture getBottomMonkey() {
        return bottomMonkey;
    }

    public Vector2 getPosTopMonkey() {
        return posTopMonkey;
    }

    public Vector2 getPosBottomMonkey() {
        return posBottomMonkey;
    }

    public void reposition(float x){
        posTopMonkey.set(x, rand.nextInt(FLUCTUATION) + MONKEYGAP + LOWESTOPENING);
        posBottomMonkey.set(x, posTopMonkey.y - MONKEYGAP - bottomMonkey.getHeight());
        //Brent uses .setPosition --- not available to us
        //Monkey size and monkey bounds must be significantly reduced --- tutorial #10
        boundsTop.setPosition(posTopMonkey.x, posTopMonkey.y);
        boundsBot.setPosition(posBottomMonkey.x, posBottomMonkey.y);
    }
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
}
