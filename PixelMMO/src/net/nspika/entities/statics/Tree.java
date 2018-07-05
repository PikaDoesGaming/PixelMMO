package net.nspika.entities.statics;

import java.awt.Graphics;

import net.nspika.gfx.Assets;
import net.nspika.handler.Handler;
import net.nspika.tiles.Tile;

public class Tree extends StaticEntity {

	
	
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2);
	}

	@Override
	public void tick() {
		if(x < 500) {
			x++;
		}else {
			x = 100;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bushes[26], (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null);
	}

}
