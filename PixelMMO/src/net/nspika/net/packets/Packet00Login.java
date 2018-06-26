package net.nspika.net.packets;

import net.nspika.net.GameClient;
import net.nspika.net.GameServer;

public class Packet00Login extends Packet {

	private String username;
	
	public Packet00Login(byte[] data) {
		super(00);
		this.username = readData(data);
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData() {
		return ("00" + this.username).getBytes();
	}
	
	public String getUsername() {
		return username;
	}
	
}
