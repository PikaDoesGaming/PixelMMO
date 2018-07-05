package net.nspika.levels;

import java.awt.Graphics;

import net.nspika.entities.creatures.Player;
import net.nspika.entities.statics.Tree;
import net.nspika.handler.EntityHandler;
import net.nspika.handler.Handler;
import net.nspika.handler.KeyHandler;
import net.nspika.tiles.Tile;
import net.nspika.ui.MenuScreen;
import net.nspika.utils.Utils;

public class Level {

	private Handler handler;
	private int width;
	private int height;
	private int[][] tiles;
	private int spawnX, spawnY;
	private EntityHandler entityHandler;
	private KeyHandler keyHandler;

	public Level(Handler handler, String path) {
		this.handler = handler;
		handler.getMenu();
		entityHandler = new EntityHandler(handler, new Player(handler, keyHandler, 100, 100, MenuScreen.name));

		entityHandler.addEntity(new Tree(handler, 10, 250));

		loadLevel(path);

		entityHandler.getPlayer().setX(spawnX);
		entityHandler.getPlayer().setY(spawnY);
	}

	public void tick() {
		entityHandler.tick();
	}

	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + Handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,
				(handler.getCamera().getyOffset() + Handler.getHeight()) / Tile.TILEHEIGHT + 1);
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getCamera().getyOffset()));
			}
		}
		entityHandler.render(g);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.voidTile;
		}
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
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width + 4)]);
			}
		}

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
