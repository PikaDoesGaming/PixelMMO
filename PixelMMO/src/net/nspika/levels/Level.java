package net.nspika.levels;

import java.awt.Graphics;

import net.nspika.tiles.Tile;

public class Level {

	private int width, height;
	private int[][] tiles;

	public Level(String path) {
		loadLevel(path);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < 0; x++) {
				getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
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
		width = 5;
		height = 5;
		tiles = new int[width][height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = 1;
			}
		}
	}

}
