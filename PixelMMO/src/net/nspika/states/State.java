package net.nspika.states;

import java.awt.Graphics;

import net.nspika.game.Game;


public abstract class State {

    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }

    public static State getSate(){
        return currentState;
    }

    protected Game game;

    public State(Game game){
        this.game = game;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
