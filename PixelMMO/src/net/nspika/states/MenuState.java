package net.nspika.states;

import java.awt.Graphics;

import net.nspika.handler.Handler;
import net.nspika.handler.KeyHandler;
import net.nspika.ui.MenuScreen;

public class MenuState extends State {
	
	MenuScreen menuScreen;
	KeyHandler keyHandler;

    public MenuState(Handler handler){
        super(handler);
        menuScreen = new MenuScreen(handler, keyHandler);
    }

    @Override
    public void tick() {
    	menuScreen.tick();
    }

    @Override
    public void render(Graphics g) {
    	menuScreen.render(g);
    }
}
