package net.nspika.handler;

import net.nspika.game.Game;
import net.nspika.gfx.Camera;
import net.nspika.levels.Level;
import net.nspika.net.GameClient;
import net.nspika.net.GameServer;

public class Handler {

	private static Game game;
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
	
	public static int getWidth() {
		return game.getWidth();
	}
	
	public static int getHeight() {
		return game.getHeight();
	}
	
	public static Game getGame() {
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

	/*
	public GameClient getGameClient() {
		return GameClient;
	}
	
	public GameServer getGameServer() {
		
	}
	*/

}
