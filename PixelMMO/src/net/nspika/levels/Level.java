package net.nspika.levels;

import java.awt.Graphics;

import net.nspika.game.Game;
import net.nspika.handler.Handler;
import net.nspika.tiles.Tile;
import net.nspika.utils.Utils;

public class Level {

	private Handler handler;
	private int width, height;
	private int[][] tiles;
	private int spawnX, spawnY;

	public Level(Handler handler, String path) {
		this.handler = handler;
		loadLevel(path);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y).render(g, (int)(x * Tile.TILEWIDTH - handler.getCamera().getxOffset()), (int)(y * Tile.TILEHEIGHT - handler.getCamera().getyOffset()));
			}
		}
	}

	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null) {
			return Tile.grassTile;
		}
		return t;
	}

	private void loadLevel(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width + 4)]);
			}
		}
		
	}

}
