package net.nspika.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import net.nspika.handler.KeyHandler;
import net.nspika.handler.MouseHandler;

public class InputField {
	private KeyHandler keyHandler;
	
	
	private int x, y;
	private int width, height;
	private boolean rounded = false;
	
	private String text = "";
	private boolean active = false;
	private boolean showHint;
	
	
	private static final int USERNAME_MAX_LENGHT = 15;
	
	private int arcWidth;
	private int arcHeight;
	
	private static final int TEXT_X_OFFSET = 10;
	private static final int TEXT_Y_OFFSET = 11;
	
	
	private Rectangle clickBox;
	
	
	public InputField(KeyHandler keyHandler, int x, int y, int width, int height) {
		this.keyHandler = keyHandler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		clickBox = new Rectangle(x, y, width, height);
		
		keyHandler.addActiveInputField(this);
	}
	
	public InputField(KeyHandler keyHandler, int x, int y, int width, int height, String hint) {
		this.keyHandler = keyHandler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = hint;
		
		clickBox = new Rectangle(x, y, width, height);
		showHint = true;
		
		keyHandler.addActiveInputField(this);
	}
	
	public InputField(KeyHandler keyHandler, int x, int y, int width, int height, int arcWidth, int arcHeight) {
		this.keyHandler = keyHandler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
		
		rounded = true;
		clickBox = new Rectangle(x, y, width, height);
		
		keyHandler.addActiveInputField(this);
	}
	
	public InputField(KeyHandler keyHandler, int x, int y, int width, int height, String hint, int arcWidth, int arcHeight) {
		this.keyHandler = keyHandler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = hint;
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
		
		clickBox = new Rectangle(x, y, width, height);
		showHint = true;
		rounded = true;
		
		keyHandler.addActiveInputField(this);
	}
	
	
	public void tick() {
		if(MouseHandler.hasClicked()) {
			if(clickBox.contains(MouseHandler.getX(), MouseHandler.getY())) {
				if(showHint) {
					text = "";
					showHint = false;
				}
			
				if(!active)
					active = true;
				keyHandler.addActiveInputField(this);
			}
			else {
				if(active) 
					active = false;
				keyHandler.removeActiveInputFields(this);
			}
		}
	}
	
	public void render(Graphics g) {
		if(rounded) {
			g.setColor(Color.DARK_GRAY);
			g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
		}
		else {
			g.setColor(Color.DARK_GRAY);
			g.drawRect(x, y, width, height);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(Fonts.inputFieldFont);
		g.drawString(text, x + TEXT_X_OFFSET, y + height - TEXT_Y_OFFSET);
	}
	
	
	public void onKeyTyped(KeyEvent e) {
		if(active) {
			Character c = e.getKeyChar();
			if((Character.isLetter(c) || Character.isDigit(c)) && text.length() < USERNAME_MAX_LENGHT) {
				text += e.getKeyChar();
			}
			if(c.equals('\b') && text.length() > 0) {
				text = text.substring(0, text.length()-1);
			}
		}
	}
	
	
	public String getText() {
		return text;
	}
}
