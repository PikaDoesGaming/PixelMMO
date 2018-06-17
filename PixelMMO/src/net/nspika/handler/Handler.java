package net.nspika.handler;

import net.nspika.game.Game;
import net.nspika.gfx.Camera;
import net.nspika.levels.Level;

public class Handler {

	private Game game;
	private Level level;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public Camera getCamera() {
		return game.getCamera();
	}
	
	public KeyHandler getKeyHandler() {
		return game.getKeyHandler();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}


}
