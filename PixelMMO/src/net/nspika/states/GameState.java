package net.nspika.states;

import java.awt.Graphics;

import net.nspika.entities.creatures.Player;
import net.nspika.game.Game;
import net.nspika.handler.Handler;
import net.nspika.levels.Level;

public class GameState extends State {

	private Player player;
	private Level level;

	public GameState(Handler handler) {
		super(handler);
		level = new Level(handler, "res/levels/level00.map");
		handler.setLevel(level);
		player = new Player(handler, 100, 100);

	}

	@Override
	public void tick() {
		level.tick();
		player.tick();

	}

	@Override
	public void render(Graphics g) {
		level.render(g);
		player.render(g);

	}

}
