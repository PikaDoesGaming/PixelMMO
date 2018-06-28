package net.nspika.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import net.nspika.handler.Handler;

public class Fonts {
	public static Font playerNameFont = new Font("Arial", Font.PLAIN, 25);
	
	public static Font lobbyNameFont = new Font("Arial", Font.PLAIN, 30);
	public static Font buttonFont = new Font("Arial", Font.PLAIN, 30);
	public static Font playButtonFont = new Font("Arial", Font.BOLD, 50);
	public static Font inputFieldFont = new Font("", Font.ITALIC, 40);
	public static Font titleFont = new Font("", Font.BOLD, 150);
	public static Font winScreenWinner = new Font("", Font.BOLD, 60);
	
	public static void LoadFonts() {
	}
	
	public static void drawCenteredText(Graphics g, String text, int x, int y, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		
		x = x - metrics.stringWidth(text) / 2;
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		
		g.setFont(font);
		g.drawString(text, x, y);
	}
	
	public static void drawCenteredText(Graphics g, String[] strings, int x, int y, Font font, Color[] colors) {
		String text = "";
		for(String s : strings) {
			text += s;
		}
		
		FontMetrics metrics = g.getFontMetrics(font);
		
		x = x - metrics.stringWidth(text) / 2;
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		
		g.setFont(font);
		
		int xOffset = 0;
		for(int i = 0; i<strings.length; i++) {
			g.setColor(colors[i]);
			g.drawString(strings[i], x + xOffset, y);
			xOffset += metrics.stringWidth(strings[i]);
		}
	}
	
	public static void drawRightAllinedText(Graphics g, String text, int x, int y, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		
		x = x - metrics.stringWidth(text);
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		
		g.setFont(font);
		g.drawString(text, x, y);
	}
	
	public static void drawRightAllinedText(Graphics g,Color color, String text, int x, int y, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		
		x = x - metrics.stringWidth(text);
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
	}
	
	public static void drawLeftAllinedText(Graphics g, String text, int x, int y, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		
		g.setFont(font);
		g.drawString(text, x, y);
	}
	
	public static void drawLeftAllinedText(Graphics g, String text,Color color, int x, int y, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
	}
	
	public static void drawTitle(Graphics g, String text, Color color, Font font, int titleYOffset, boolean shadow) {
		
		if(shadow) {
			g.setColor(Color.GRAY);
			Fonts.drawCenteredText(g, text, Handler.getWidth()/2 + 4, titleYOffset + 5, font);
		}
		g.setColor(color);
		Fonts.drawCenteredText(g, text, Handler.getWidth()/2, titleYOffset, font);
	}
}
