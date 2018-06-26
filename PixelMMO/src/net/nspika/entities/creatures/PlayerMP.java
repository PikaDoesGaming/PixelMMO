package net.nspika.entities.creatures;

import java.net.InetAddress;

import net.nspika.handler.Handler;

public class PlayerMP extends Player{
	
	public InetAddress ipAddress;
	public int port;

	public PlayerMP(Handler handler, float x, float y, String username, InetAddress ipAddress, int port) {
		super(handler, x, y, username);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	@Override
	public void tick() {
		super.tick();
	}

}
