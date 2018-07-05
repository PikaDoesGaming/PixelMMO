package net.nspika.states;

import java.awt.Graphics;

import net.nspika.entities.creatures.Player;
import net.nspika.entities.statics.Tree;
import net.nspika.handler.Handler;
import net.nspika.levels.Level;

public class GameState extends State {

	private Player player;
	private Level level;
	private Tree tree;

	public GameState(Handler handler) {
		super(handler);
		level = new Level(handler, "res/levels/LargeMap.map");
		handler.setLevel(level);

	}

	@Override
	public void tick() {
		level.tick();
		}

	@Override
	public void render(Graphics g) {
		level.render(g);
	}
	

}
