package net.nspika.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import net.nspika.entities.creatures.PlayerMP;
import net.nspika.game.Game;
import net.nspika.handler.EntityHandler;
import net.nspika.handler.Handler;
import net.nspika.net.packets.Packet;
import net.nspika.net.packets.Packet.PacketTypes;
import net.nspika.net.packets.Packet00Login;

public class GameServer extends Thread {
	private DatagramSocket socket;
	private Game game;
	private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
	private Handler handler;

	public GameServer(Game game) {
		this.game = game;
		try {
			this.socket = new DatagramSocket(12345);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());

			
			  String message = new String(packet.getData()); System.out.println("CLIENT ["
			  + packet.getAddress().getHostAddress() + ":" + packet.getPort() + "] > "+
			  message); if (message.trim().equalsIgnoreCase("ping")) {
			  sendData("pong".getBytes(), packet.getAddress(), packet.getPort()); }
			 
		}
	}

	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			Packet00Login packet = new Packet00Login(data);
			System.out.println(
					"[" + address.getHostAddress() + ":" + port + "]" + packet.getUsername() + " has connected...");
			PlayerMP player = null;
			if (address.getHostAddress().equalsIgnoreCase("127.0.0.1")) {
				player = new PlayerMP(handler, 100, 100, packet.getUsername(), address, port);
			}
			if (player != null) {
				this.connectedPlayers.add(player);
				EntityHandler entityManager = new EntityHandler(handler, player);
				
			}
			break;
		case DISCONNECT:
			break;
		}
	}

	public void sendData(byte[] data, InetAddress ipAddress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendDataToAllClients(byte[] data) {
		for (PlayerMP p : connectedPlayers) {
			sendData(data, p.ipAddress, p.port);
		}
	}
}
