package net.nspika.states;

import java.awt.Graphics;

import net.nspika.game.Game;
import net.nspika.handler.Handler;


public abstract class State {

    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }

    public static State getSate(){
        return currentState;
    }

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
