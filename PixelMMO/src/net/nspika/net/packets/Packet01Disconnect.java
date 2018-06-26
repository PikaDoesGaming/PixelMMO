package net.nspika.net.packets;

import net.nspika.net.GameClient;
import net.nspika.net.GameServer;

public class Packet01Disconnect extends Packet {

	public Packet01Disconnect(int packetID) {
		super(packetID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeData(GameClient client) {
		
	}

	@Override
	public void writeData(GameServer server) {
		
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
