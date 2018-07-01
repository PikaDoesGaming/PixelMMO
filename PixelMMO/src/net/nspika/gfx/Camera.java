package net.nspika.gfx;

import net.nspika.entities.Entity;
import net.nspika.handler.Handler;
import net.nspika.tiles.Tile;

public class Camera {

	private float xOffset, yOffset;
	private Handler handler;

	public Camera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void checkBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > handler.getLevel().getWidth() * Tile.TILEWIDTH - Handler.getWidth()) {
			xOffset = handler.getLevel().getWidth() * Tile.TILEWIDTH - Handler.getWidth();
		}

		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > handler.getLevel().getHeight() * Tile.TILEHEIGHT - Handler.getHeight()) {
			yOffset = handler.getLevel().getHeight() * Tile.TILEHEIGHT - Handler.getHeight();
		}
	}

	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - Handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - Handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}

	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
