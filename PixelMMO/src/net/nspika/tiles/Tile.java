package net.nspika.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//TILES
	public static Tile[] tiles = new Tile[256];
	public static Tile voidTile = new VoidTile(0);
	public static Tile grassTile = new GrassTile(1);
	public static Tile dirtTile = new DirtTile(2);
	public static Tile rockTile = new RockTile(3);

	
	protected BufferedImage texture;
	protected final int id;
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64; 
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getid() {
		return id;
	}

}
