package net.nspika.entities.statics;

import net.nspika.entities.Entity;
import net.nspika.handler.Handler;

public abstract class StaticEntity extends Entity{
	
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
	
}
